/**

This class is a top-down, recursive-descent parser for the following
syntactic categories:

<E> --> <term> { (+|-) <term> }
<term> --> <primary> { (*|/) <primary> }
<primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")" | - <primary>

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

**/


import java.io.*;

public abstract class parseE extends lexArith
{
	private static String stateToOperator[] = new String[44];

	private static void setStateToOperator()
	{
		stateToOperator[State.Plus.ordinal()] = "add";
		stateToOperator[State.Minus.ordinal()] = "sub";
		stateToOperator[State.Times.ordinal()] = "mul";
		stateToOperator[State.Div.ordinal()] = "div";
		stateToOperator[State.Inverse.ordinal()] = "inv";
		stateToOperator[State.And.ordinal()] = "and";
		stateToOperator[State.Or.ordinal()] = "or";
		stateToOperator[State.LessThan.ordinal()] = "lt";
		stateToOperator[State.GreaterThan.ordinal()] = "gt";
		stateToOperator[State.Equal.ordinal()] = "eq";
		stateToOperator[State.NotEqual.ordinal()] = "neq";
		stateToOperator[State.LessThanEqual.ordinal()] = "le";
		stateToOperator[State.GreaterThanEqual.ordinal()] = "ge";
	}

	private static void emitOperator(State op)
	{
		displayln( stateToOperator[op.ordinal()] );
	}

	public static void statement()
	{
		//<statement> --> <assignment> | "{" <S list> "}"

		if (state == State.LBrace )
		{
			getToken();
			slist();
			if ( state == State.RBrace )
				getToken();
			else
				displayln(" } expected");
		}
		else if ( state == State.Id )
			assignment();
		else
			displayln(" { expected");
	} // end statement

	public static void assignment()
	{
		//<assignment> --> <id> "=" <expr> ";"
		String temp;

		temp = t;

		getToken();
		if ( state == State.Assign )
		{
			getToken();
			expr();
			if ( state == State.SemiColon )
			{
				getToken();
				displayln("pop  " + temp);
			}
			else
				displayln(" ; expected");
		}
		else
			displayln(" id or = expected");

	} // end assignment

	public static void slist()
	{
		// <S list> --> { <statement> }+
		statement();
		while ( state == State.Id || state == State.LBrace )
			statement();

	} // end slist

	public static void expr()
	{
		// <expr> --> <boolTerm> { "||" <boolTerm> }
		State op;

		boolterm();
		while ( state == State.Or )
		{
			op = state;
			getToken();
			boolterm();
			emitOperator(op);
		}

	} // end expr

	public static void boolterm()
	{
		// <boolTerm> --> <boolPrimary> { "&&" <boolPrimary> }
		State op;

		boolprimary();

		while( state == State.And )
		{
			op = state;
			getToken();
			boolprimary();
			emitOperator(op);
		}

	} // end boolterm

	public static void boolprimary()
	{
		// <boolPrimary> --> <E> [ <rel op> <E> ]
		State op;

		E();
		if (rel_op())
			E();

	} // end boolprimary

	public static boolean rel_op()
	{
		// <rel op> --> "<" | "<=" | ">" | ">=" | "==" | "!="
		State op;

		if ( state == State.LessThan )
		{
			op = state;
			getToken();
			emitOperator(op);
			return true;
		}
		else if ( state == State.LessThanEqual )
		{
			op = state;
			getToken();
			emitOperator(op);
			return true;
		}
		else if ( state == State.GreaterThan )
		{
			op = state;
			getToken();
			emitOperator(op);
			return true;
		}
		else if ( state == State.GreaterThanEqual )
		{
			op = state;
			getToken();
			emitOperator(op);
			return true;
		}
		else if ( state == State.Equal )
		{
			op = state;
			getToken();
			emitOperator(op);
			return true;
		}
		else if ( state == State.NotEqual)
		{
			op = state;
			getToken();
			emitOperator(op);
			return true;
		}
		else
			return false;




	} // end rel_op
	public static void E()

	// <E> --> <term> { (+|-) <term> }

	{
		State op;

		term();
		while ( state == State.Plus || state == State.Minus )
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
		while ( state == State.Times || state == State.Div )
		{
			op = state;
			getToken();
			primary();
			emitOperator(op);
		}
	} // end term

	public static void primary()

	// <primary> --> <id> | <int> | <float> | <floatE> | "(" <expr> ")" | - <primary> | ! <primary>

	{
		State op;

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
			expr();
			if ( state == State.RParen )
				getToken();
			else
				errorMsg(1);
		}
		else if ( state == State.Minus )
		{
			getToken();
			primary();
			displayln("neg");
		}
		else if ( state == State.Inverse )
		{
			op = state;
			getToken();
			primary();
			emitOperator(op);
		}
		else
			errorMsg(2);
	} // end primary

	public static void errorMsg(int i)
	{
		display(t + " : unexpected symbol where");

		if ( i == 1 )             // called by primary()
			displayln(" arith op or ) expected");
		else if( i == 2 )        // called by primary()
			displayln(" id, int, float, (, or - expected");

	} // end errorMsg

	public static void main(String argv[])

	// The input/output file names must be passed as argv[0] and argv[1].

	{
		setLex( argv[0], argv[1] );
		setStateToOperator();

		getToken();
		statement();
		if ( ! t.isEmpty() )
			displayln(t + "  -- unexpected symbol");

		closeIO();
	} // end main
}
