/**

This program was written and tested with JDK 1.6.0.

This class is a top-down, recursive-descent parser for the following
syntactic categories:

<E> --> <term> { (+|-) <term> }
<term> --> <primary> { (*|/) <primary> }
<primary> --> <id> | <int> | <float> | "(" <E> ")"

The definitions of <id>, <int>, <float> are given in the lexical analyzer 
class file "lexArith.java". The following variables and functions 
of the "lexArith" class are used:

static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token
static void display(String s)
static void displayln(String s)
static void setLex(String inFile, String outFile)
static void closeIO()

The program will display the parse tree in linearly indented form.
Each syntactic category name labeling a node is
displayed on a separate line, prefixed with the integer i representing
the node's depth and indented by i blanks. The arithmetic operators are
displayed in the same way. The string variable "indent"
will keep track of the correct number of blanks for indentation and
will be passed to parsing functions corresponding to syntactic categories.

**/


import java.io.*;

public abstract class parseArith extends lexArith
{
	public static void E(String indent)

	// <E> --> <term> { (+|-) <term> }

	{
		displayln(indent+indent.length()+" E");
		indent = indent+" ";

		term(indent);
		while ( state == State.Plus || state == State.Minus )
		{
			displayln(indent+indent.length()+" "+t);
			getToken();
			term(indent);
		}
	} // end E

	public static void term(String indent)

	// <term> --> <primary> { (*|/) <primary> }

	{
		displayln(indent+indent.length()+" term");
		indent = indent+" ";

		primary(indent);
		while ( state == State.Times || state == State.Div )
		{
			displayln(indent+indent.length()+" "+t);
			getToken();
			primary(indent);
		}
	} // end term

	public static void primary(String indent)

	// <primary> --> <id> | <int> | <float> | "(" <E> ")"

	{
		display(indent+indent.length()+" primary");
		indent = indent+" ";

		if ( state.compareTo(State.Id) >= 0 &&
		     state.compareTo(State.FloatE) <= 0 )

		// state == Id, Int, Float, or FloatE

		{
			displayln(" "+t); 

		     	getToken();
		}
		else if ( state == State.LParen )
		{
			displayln("");

			getToken();
			E(indent);
			if ( state == State.RParen )
				getToken();
			else
				errorMsg(1);
		}
		else
			errorMsg(2);
	} // end primary

	public static void errorMsg(int i)
	{
		display(t + ": unexpected symbol where");

		if ( i == 1 ) // called by primary()
			displayln(" arith op or ) expected");

		else // i == 2   called by primary()
			displayln(" id, int, float, or ( expected");

	} // end errorMsg

	public static void main(String argv[])

	// The input/output file names must be passed as argv[0] and argv[1].

	{
		setLex( argv[0], argv[1] );

		String indent = ""; // used to properly indent displayed
                                    // syntactic category names

		getToken();
		E(indent);
		if ( ! t.isEmpty() )
		{
			displayln(t + "  -- unexpected symbol");
		}

		closeIO();
	} // end main
}

