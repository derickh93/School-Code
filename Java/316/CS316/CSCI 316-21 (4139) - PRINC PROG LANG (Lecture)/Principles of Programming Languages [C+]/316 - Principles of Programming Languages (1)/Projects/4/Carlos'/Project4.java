/**

Carlos Uy
Project 4

This program was written and tested with JDK 1.6.0.

This class is a top-down, recursive-descent parser for the following
syntactic categories:

<statement> --> <assignment> | "{" <S list> "}"
<assignment> --> <id> "=" <expr> ";"
<S list> --> { <statement> }+
<expr> --> <boolTerm> { "||" <boolTerm> }
<boolTerm> --> <boolPrimary> { "&&" <boolPrimary> }
<boolPrimary> --> <E> [ <rel op> <E> ]
<rel op> --> "<" | "<=" | ">" | ">=" | "==" | "!="
<E> --> <term> { (+|-) <term> }
<term> --> <primary> { (*|/) <primary> }
<primary> --> <id> | <int> | <float> | <floatE> |
                   "(" <expr> ")" | - <primary> | ! <primary>

The definitions of the tokens are given in the lexical analyzer
class file "LexAnalyzer.java".

The program will do top-down parsing and emit the following
stack-based virtual machine instructions:

  push
  pop
  add          +
  sub          binary -
  mul          *
  div          /
  neg          unary -
  or           ||
  and          &&
  inv          !
  lt           <
  le           <=
  gt           >
  ge           >=
  eq           ==
  neq          !=

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

public abstract class Project4 extends lexAnalyzer
{

	private static String stateToOperator[] = new String[13];

	private static int count;

	private static void setStateToOperator()
	{
		stateToOperator[State.Add.ordinal()] = "	add";
		stateToOperator[State.Sub.ordinal()] = "	sub";
		stateToOperator[State.Mul.ordinal()] = "	mul";
		stateToOperator[State.Div.ordinal()] = "	div";
		stateToOperator[State.Or .ordinal()] = "	or" ;
		stateToOperator[State.And.ordinal()] = "	and";
		stateToOperator[State.Inv.ordinal()] = "	inv";
		stateToOperator[State.Lt.ordinal()] =  "	lt" ;
		stateToOperator[State.Le.ordinal()] =  "	le" ;
		stateToOperator[State.Gt.ordinal()] =  "	gt" ;
		stateToOperator[State.Ge.ordinal()] =  "	ge" ;
		stateToOperator[State.Eq.ordinal()] =  "	eq" ;
		stateToOperator[State.Neq.ordinal()] = "	neq";
	}

	private static void emitOperator(State op)
	{
		displayln( stateToOperator[op.ordinal()] );
	}

	public static void statement()

	// <statement> --> "{" <SList> "}" | <assignment>

	{
		if ( state == State.LBrace )
		{
			getToken();
			SList();
			if ( state == State.RBrace )
				getToken();
			else
				errorMsg(3);
		}
		else if ( state == State.If )
		{
			int countNum = 0;
			getToken();
			if( state == State.LParen )
			{
				getToken();
				expr();
				if( state == State.RParen )
				{
					displayln("	if_f goto " + ++count);
					countNum = count;
					getToken();
					statement();

					if( state == State.Else)
					{
						displayln("	goto " + ++count);
						displayln(countNum + ":");
						getToken();
						statement();
						displayln((countNum+1) +  ":");
					}
					else
						displayln(countNum+":");
				}
			}
		}
		else if ( state == State.While )
		{
			int countNum = 0;
			getToken();
			if( state == State.LParen )
			{
				displayln(++count + ":");
				getToken();
				expr();
				if( state == State.RParen )
				{
					displayln("	if_f goto " + (++count));
					countNum = count;
					getToken();
					statement();
					displayln("	goto " + (countNum-1));
					displayln(countNum + ":");
				}
				else
					errorMsg(1);
			}
		}
		else if ( state == State.For )
		{
			int countNum = 0;
			getToken();
			if( state == State.LParen )
			{
				getToken();
				assignment();
				displayln(++count + ":");
				expr();
				if( state == State.RParen )
				{
					displayln("	if_f goto " + (++count));
					countNum = count;
					getToken();
					statement();
					displayln("	goto " + (countNum-1));
					displayln(countNum + ":");
				}
				else
					errorMsg(1);
			}

		}

		else
			assignment();
	}

	public static void assignment()

	// <assignment> --> <id> = <expr>;

	{
		if ( state == State.Id )
		{
			String id = t;
			getToken();
			if ( state == State.Assign )
			{
				getToken();
				expr();
				if ( state == State.Semicolon )
				{
					displayln("	pop  "+id);
					getToken();
				}
				else
					errorMsg(4);
			}
			else
				errorMsg(5);
		}
		else
			errorMsg(6);
	}

	public static void SList()

	// <SList> --> { <statement> }+

	{
		statement();
		while ( state == State.LBrace || state == State.Id || state == State.While || state == State.If || state == State.Else || state == State.For)
			statement();
	}

	public static void expr()

	// <expr> --> <boolTerm> { "||" <boolTerm> }

	{
		boolTerm();
		while ( state == State.Or )
		{
			getToken();
			boolTerm();
			emitOperator(State.Or);
		}
	} // end expr

	public static void boolTerm()

	// <boolTerm> --> <boolPrimary> { "&&" <boolPrimary> }

	{
		boolPrimary();
		while ( state == State.And )
		{
			getToken();
			boolPrimary();
			emitOperator(State.And);
		}
	} // end boolTerm


	public static void boolPrimary()

	// <boolPrimary> --> <E> [ <relop> <E> ]

	{
		E();
		if ( state.compareTo(State.Lt) >= 0 &&
                     state.compareTo(State.Neq) <= 0 )

		// state = Lt, Le, Gt, Ge, Eq, or Neq

		{
			State op = state;

			getToken();
			E();
			emitOperator(op);
		}
	} // end boolPrimary

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

	// <primary> --> <id> | <int> | <float> | <floatE> | "(" <expr> ")" | - <primary> | ! <primary>

	{
		if ( state.compareTo(State.Id) >= 0 &&
		     state.compareTo(State.FloatE) <= 0 )

		// state == Id, Int, Float, or FloatE

		{
			displayln("	push  "+t);
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
		else if ( state == State.Sub )
		{
			getToken();
			primary();
			displayln("	neg");
		}
		else if ( state == State.Inv )
		{
			getToken();
			primary();
			emitOperator(State.Inv);
		}
		else
			errorMsg(2);
	} // end primary

	public static void errorMsg(int i)
	{
		display(t + ": unexpected symbol where");

		if ( i == 1 )             // called by primary()
			displayln(" arith op or ) expected");
		else if ( i == 2 )        // called by primary()
			displayln(" id, int, float, (, -, or ! expected");
		else if ( i == 3 )        // called by statement()
			displayln(" } expected");
		else if ( i == 4 )        // called by assignment()
			displayln(" ; expected");
		else if ( i == 5 )        // called by assignment()
			displayln(" = expected");
		else if ( i == 6 )        // called by assignment()
			displayln(" id expected");
	} // end errorMsg

	public static void main(String argv[])
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
