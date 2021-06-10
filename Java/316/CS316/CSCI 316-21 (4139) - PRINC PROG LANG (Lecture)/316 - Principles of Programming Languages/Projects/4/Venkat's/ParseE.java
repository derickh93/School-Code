/**

This class is a top-down, recursive-descent parser for the following
syntactic categories:

<E> --> <term> { (+|-) <term> }
<term> --> <primary> { (*|/) <primary> }
<primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")" | - <primary> | ! <primary>

<statement> --> <assignment> | "{" <S list> "}"
<assignment> --> <id> "=" <expr> ";"
<S list> --> { <statement> }+
<expr> --> <boolTerm> { "||" <boolTerm> }
<boolTerm> --> <boolPrimary> { "&&" <boolPrimary> }
<boolPrimary> --> <E> [ <rel op> <E> ]
<rel op> --> "<" | "<=" | ">" | ">=" | "==" | "!="

The definitions of the tokens are given in the lexical analyzer
class file "LexAnalyzer.java".

The program will do top-down parsing and emit the following
stack-based virtual machine instructions:

  push
  add          +
  sub          binary -
  mul          *
  div          /
  neg          unary -

The following variables and functions of the "LexAnalyzer" class are used:

static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token
static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

*@ author Venkata Suggula

**/


import java.io.*;

public abstract class ParseE extends LexAnalyzer
{
	private static int loopCount = 1;
	private static String stateToOperator[] = new String[46];

	private static void setStateToOperator()
	{
		stateToOperator[State.Add.ordinal()]    		 = "add";
		stateToOperator[State.Sub.ordinal()]   			 = "sub";
		stateToOperator[State.Mul.ordinal()]  	    	 = "mul";
		stateToOperator[State.Div.ordinal()]    	     = "div";
		stateToOperator[State.Or.ordinal()]     	     = "or";
		stateToOperator[State.And.ordinal()]    	     = "and";
		stateToOperator[State.Inv.ordinal()]             = "inv";
		stateToOperator[State.Lt.ordinal()]        		 = "lessThan";
		stateToOperator[State.Gt.ordinal()]     		 = "greaterThan";
		stateToOperator[State.Ge.ordinal()]      		 = "greaterThanEqual";
		stateToOperator[State.Le.ordinal()]    			 = "lessThanEqual";
		stateToOperator[State.Eq.ordinal()]              = "Equal";
		stateToOperator[State.Neq.ordinal()]             = "notEqual";
	}

	private static void emitOperator(State op)
	{
		displayln( stateToOperator[op.ordinal()] );
	}

	public static void E()

	// <E> --> <term> { (+|-) <term> }

	{
		State op;

		term();
		while ( state == State.Add || state == State.Sub )
		{
			op = state;
			getToken();
			term();
			emitOperator(op);
		}
	} // end E

	public static void term()

	// <term> --> <primary> { (*|/) <primary> }

	{
		State op;

		primary();
		while ( state == State.Mul || state == State.Div )
		{
			op = state;
			getToken();
			primary();
			emitOperator(op);
		}
	} // end term

	public static void primary()

	// <primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")" | - <primary>| ! <primary>

	{
		if ( state.compareTo(State.Id) >= 0 &&
		     state.compareTo(State.FloatE) <= 0 )

		// state == Id, Int, Float, or FloatE

		{
			displayln("push  "+t);
		     	getToken();
		}
		else if ( state == State.LParen )
		{
			getToken();
			Expr();
			if ( state == State.RParen )
				getToken();
			else
				errorMsg(1);
		}
		else if ( state == State.Sub )
		{
			getToken();
			primary();
			displayln("neg");
		}
		else if ( state == State.Neq)
		{
			getToken();
			primary();
			displayln("inv");
		}
		else
			errorMsg(2);
	} // end primary

	//<statement> --> <assignment> | "{" <S list> "}"| "if" "(" <expr> ")" <statement> [ "else" <statement> ] |
        // "while" "(" <expr> ")" <statement> | "for" "(" <assignment> <expr> ")" <statement>
	public static void statement()
	{
		if (state== State.LBrace)
		{
			getToken();
			Slist();
			if (state == State.RBrace)
				getToken();
			else
				errorMsg(3);
		}
		else if (state == State.Id)
			assignment();
		else if (state == State.If)
		{
			int IfLoop;
			getToken();
			if(state == State.LParen)
			{	getToken();
				Expr();
				if(state == State.RParen)
				{
					displayln("		if_f goto " + loopCount++);
					IfLoop = loopCount-1;
					getToken();
					statement();
					if (state==State.Else)
					{
						displayln("		goto " + loopCount++);
						displayln(IfLoop + ":");
						getToken();
						statement();
						displayln((IfLoop + 1) + ":");
					}
					else	displayln(IfLoop + ":");
				}
			}
			else
				displayln("Error: '(' expected");
		}//end of State.if

		else if (state == State.While)
		{
			int WhileLoop;
			getToken();
			if (state == State.LParen)
			{
				displayln((loopCount++) + ":");
				getToken();
				Expr();
				if (state == State.RParen)
				{
					displayln("		if_f goto " + loopCount++);
					WhileLoop = loopCount-1;
					getToken();
					statement();
					displayln("		goto " + (WhileLoop - 1));
					displayln(WhileLoop + ":");
				}
				else displayln("Error: ')' expected");
			}
			else displayln("Error: '(' expected");
		}// end of State.While

		else if (state == State.For)
		{
			getToken();
			if(state == State.LParen)
			{
				getToken();
				assignment();
				Expr();
				if (state == State.RParen)
				{
					getToken();
					statement();
				}
				else displayln("Error: ')' expected");
			}
			else displayln("Error: '(' expected");
		}
		else
			errorMsg(4);
	}// end of State.For

	public static void assignment ()
	{
		String Saved_id = t;
		getToken();
		if (state == State.Assign)
		{
			getToken();
			Expr();
			if (state == State.Semicolon)
			{
				getToken();
				displayln ("pop " + Saved_id);
			}
			else
				errorMsg(5);
		}
		else
			errorMsg(6);
	}
	//<S list> --> { <statement> }+
	public static void Slist()
	{
		statement();
		displayln("token: " + t);
		while (state == State.Id || state == State.LBrace||
				state== State.Else||state== State.While||
				state == State.If||state == State.For)
		{
			statement();
		}
	}
	//<expr> --> <boolTerm> { "||" <boolTerm> }
    public static void Expr()
    {
		State op;
		boolTerm();
		while ( state == State.Or)
		{
			op = state;
			getToken();
			boolTerm();
			emitOperator(op);
		}
	}
	//<boolTerm> --> <boolPrimary> { "&&" <boolPrimary> }
	public static void boolTerm()
	{
		State op;
		boolPrimary();
		while (state == State.And)
		{
			op = state;
			getToken();
			boolPrimary();
			emitOperator(op);
		}
	}
	//<boolPrimary> --> <E> [ <rel op> <E> ]
    //<rel op> --> "<" | "<=" | ">" | ">=" | "==" | "!="
    public static void boolPrimary()
    {
		State op;
		E();
		if (state == State.Gt||state == State.Ge||
			state == State.Le||state == State.Lt||
			state == State.Eq||state == State.Neq)
		{
			op = state;
			getToken();
			E();
			emitOperator(op);
		}
	}

	public static void errorMsg(int i)
	{
		if ( i == 1 )             // called by primary()
			displayln(" arith op or ) expected");
		else if ( i == 2 )        // called by primary()
			displayln(" id, int, float, (, -, or ! expected");
		else if (i == 3)		  // called by statement()
			displayln("Error: '}' expected");
		else if (i ==4)			  // called by statement()
			displayln ("Error: '{' or '<id>' expected");
		else if (i == 5)		  // called by assignment()
			displayln("Error: ';' expected");
		else if (i == 6)		  // called by assignment()
			displayln("Error: '=' expected");

	} // end errorMsg

	public static void main(String argv[])
	{
		setLex( "inFile1.txt", "outfile.txt");
		setStateToOperator();

		getToken();
		statement();

		closeIO();
	} // end main
}