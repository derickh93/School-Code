/*
 * Conditional Based Lexical Analyzer
 * Based on: http://picasso.cs.qc.cuny.edu/cs316/lexArith.java
 * Modifications by: Brian Ruslim (bruslim100@qc.cuny.edu)
 */

import java.io.*;

public class LexicalAnalyzer
{
	public enum State
	{
		// non-final states ordinal number

		Start, // 0
		Period, // 1
		E, // 2
		EPlusMinus, // 3

		EqualSign, Bar, Ampersand,

		// final states

		Id, // 4
		Int, // 5
		Float, // 6
		FloatE, // 7
		Plus, // 8
		Minus, // 9
		Times, // 10
		Div, // 11
		LParen, // 12
		RParen, // 13

		LBrace, RBrace, Comma, Or, And, Not, Lesser, Greater,

		Equal, GreaterEqual, LesserEqual, NotEqual,

		Id_i, If, Id_e, Id_el, Id_els, Else,

		UNDEF
	}

	// By enumerating the non-final states first and then the final states,
	// test for a final state can be done by testing if the state's ordinal
	// number is greater than or equal to that of Id.

	public static String t; // holds an extracted token
	public static State state; // the current state of the FA
	protected static int a; // the current input character
	private static char c; // used to convert the variable "a" to the char type
	// whenever necessary
	private static BufferedReader inStream;
	private static PrintWriter outStream;

    protected static int currentLine = 1;
	/**
	 * @return the next character on the input stream.
	 */
	private static int getNextChar()
	{
		try
		{
			return inStream.read();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return -1;
		}
	} // end getNextChar

	/**
	 * Returns the next non-whitespace character on the input stream.
	 * 
	 * @return -1, end-of-stream, if the end of the input stream is reached.
	 */
	private static int getChar()
	{
		int i = getNextChar();
		while (Character.isWhitespace((char) i))
		{
		    if (i == (int) '\n') ++currentLine;
			i = getNextChar();			
		}
		return i;
	} // end getChar

	/**
	 * This is the driver of the FA. If a valid token is found, assigns it to
	 * "t" and returns 1. If an invalid token is found, assigns it to "t" and
	 * returns 0. If end-of-stream is reached without finding any non-whitespace
	 * character, returns -1.
	 * 
	 * @return 1 - valid token, 0 - invalid token -1 EOS
	 */
	private static int driver()
	{
		State nextState; // the next state of the FA

		t = "";
		state = State.Start;

		if (Character.isWhitespace((char) a))
		{
   		    if (a == (int) '\n') ++currentLine;
			a = getChar(); // get the next non-whitespace character
		}
		if (a == -1) // end-of-stream is reached
			return -1;

		while (a != -1) // while "a" is not end-of-stream
		{
			c = (char) a;
			nextState = nextState(state, c);
			if (nextState == State.UNDEF) // The FA will halt.
			{
				if (isFinal(state))
					return 1; // valid token extracted
				else
				// "c" is an unexpected character
				{
					t = t + c;
					a = getNextChar();
					return 0; // invalid token found
				}
			}
			else
			// The FA will go on.
			{
				state = nextState;
				t = t + c;
				a = getNextChar();
			}
		}

		// end-of-stream is reached while a token is being extracted

		if (isFinal(state))
			return 1; // valid token extracted
		else
			return 0; // invalid token found
	} // end driver

	/**
	 * Returns the next state of the FA given the current state and input char;
	 * if the next state is undefined, UNDEF is returned.
	 * 
	 * @param s
	 * @param c
	 * @return
	 */
	private static State nextState(State s, char c)
	{
		if (s == State.Start)
		{
			if (c == 'i')
				return State.Id_i;
			else if (c == 'e')
				return State.Id_e;
			else if (Character.isLetter(c))
				return State.Id;
			else if (Character.isDigit(c))
				return State.Int;
			else if (c == '+')
				return State.Plus;
			else if (c == '-')
				return State.Minus;
			else if (c == '*')
				return State.Times;
			else if (c == '/')
				return State.Div;
			else if (c == '(')
				return State.LParen;
			else if (c == ')')
				return State.RParen;
			else if (c == '{')
				return State.LBrace;
			else if (c == '}')
				return State.RBrace;
			else if (c == '=')
				return State.EqualSign;
			else if (c == '<')
				return State.Lesser;
			else if (c == '>')
				return State.Greater;
			else if (c == '|')
				return State.Bar;
			else if (c == '&')
				return State.Ampersand;
			else if (c == ',')
				return State.Comma;
			else if (c == '!')
				return State.Not;
			else if (c == '.')
				return State.Period;
			else
				return State.UNDEF;
		}
		else if (s == State.Id_i)
		{
			if (c == 'f')
				return State.If;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		}
		else if (s == State.If)
		{
			if (c == '(')
				return State.LParen;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		}
		else if (s == State.Id_e)
		{
			if (c == 'l')
				return State.Id_el;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		}
		else if (s == State.Id_el)
		{
			if (c == 's')
				return State.Id_els;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		}
		else if (s == State.Id_els)
		{
			if (c == 'e')
				return State.Else;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		}
		else if (s == State.Else)
		{
			if (c == '{')
				return State.LBrace;
			else if (c == 'i')
				return State.Id_i;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		}
		else if (s == State.EqualSign)
		{
			if (c == '=')
				return State.Equal;
			else
				return State.UNDEF;
		}
		else if (s == State.Ampersand)
		{
			if (c == '&')
				return State.And;
			else
				return State.UNDEF;
		}
		else if (s == State.Bar)
		{
			if (c == '|')
				return State.Or;
			else
				return State.UNDEF;
		}
		else if (s == State.Greater)
		{
			if (c == '=')
				return State.GreaterEqual;
			else
				return State.UNDEF;
		}
		else if (s == State.Lesser)
		{
			if (c == '=')
				return State.LesserEqual;
			else
				return State.UNDEF;
		}
		else if (s == State.Not)
		{
			if (c == '=')
				return State.NotEqual;
			else
				return State.UNDEF;
		}
		else if (s == State.Id)
		{
			if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		}
		else if (s == State.Int)
		{
			if (Character.isDigit(c))
				return State.Int;
			else if (c == '.')
				return State.Float;
			else
				return State.UNDEF;
		}
		else if (s == State.Period)
		{
			if (Character.isDigit(c))
				return State.Float;
			else
				return State.UNDEF;
		}
		else if (s == State.Float)
		{
			if (Character.isDigit(c))
				return State.Float;
			else if (c == 'e' || c == 'E')
				return State.E;
			else
				return State.UNDEF;
		}
		else if (s == State.E)
		{
			if (Character.isDigit(c))
				return State.FloatE;
			else if (c == '+' || c == '-')
				return State.EPlusMinus;
			else
				return State.UNDEF;
		}
		else if (s == State.EPlusMinus)
		{
			if (Character.isDigit(c))
				return State.FloatE;
			else
				return State.UNDEF;
		}
		else if (s == State.FloatE)
		{
			if (Character.isDigit(c))
				return State.FloatE;
			else
				return State.UNDEF;
		}
		else
			return State.UNDEF;
	} // end nextState

	private static boolean isFinal(State state)
	{
		return (state.compareTo(State.Id) >= 0);
	}

	/**
	 * Extract the next token using the driver of the FA. If an invalid token is
	 * found, issue an error message.
	 */
	public static void getToken()
	{
		int i = driver();
		if (i == 0)
			displayln(t + "  -- Invalid Token");
	} // end getToken

	public static void display(String s)
	{
		outStream.print(s);
	}

	public static void displayln(String s)
	{
		outStream.println(s);
	}

	/**
	 * Sets the input and output streams to "inFile" and "outFile",
	 * respectively. Also sets the current input character "a" to the first
	 * character on the input stream.
	 * 
	 * @param inFile
	 * @param outFile
	 */
	public static void setLex(String inFile, String outFile)
	{
		try
		{
			inStream = new BufferedReader(new FileReader(inFile));
			outStream = new PrintWriter(new FileOutputStream(outFile));
			a = inStream.read();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	} // end setIO

	public static void closeIO()
	{
		try
		{
			inStream.close();
			outStream.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	} // end closeIO

	/**
	 * The input/output file names must be passed as argv[0] and argv[1].
	 * 
	 * @param argv
	 */
	public static void main(String argv[])
	{
		int i;
		setLex(argv[0], argv[1]);
		while (a != -1) // while "a" is not end-of-stream
		{
			i = driver(); // extract the next token
			if (i == 1)
				displayln(t + "   : " + state.toString());
			else if (i == 0)
				displayln(t + "  -- Invalid Token");
		}

		closeIO();
	} // end main

}
