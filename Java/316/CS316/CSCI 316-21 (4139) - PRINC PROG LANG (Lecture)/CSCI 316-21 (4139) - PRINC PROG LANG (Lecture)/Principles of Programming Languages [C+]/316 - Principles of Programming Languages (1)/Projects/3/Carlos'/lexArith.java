/**
@Author Carlos Uy

Project 3
April 21 2009

This program uses the Enum type introduced in JDK 1.5.0.

This class is a lexical analyzer for the tokens defined by the grammar:

<plus> --> +
<minus> --> -
<times> --> *
<div> --> /
<LParen> --> "("
<RParen> --> ")"
<int> --> { <digit> }+
<id> --> <letter> { <letter> | <digit> }
<float> --> { <digit> }+ "." { <digit> }+
<floatE> --> <float> (E|e) [+|-] { <digit> }+

This class implements a DFA that will accept the above tokens.
The DFA has 10 final states represented by enum-type literals:

state     token accepted

Id        		identifiers
Int       		integers
Float     		floats without exponentiation part
FloatE    		floats with exponentiation part
Plus      		+
Minus     		-
Times     		*
Div       		/
LParen    		(
RParen    		)
Or		  		||
And       		&&
Inverse   		!
Assign    		=
Equal     		==
NotEqual  		!=
SemiColon 		;
LessThan  		<
GreaterThan 	>
LessThanEqual	<=
GreaterThanEqual>=
LBrace			{
RBrace			}
Decimal			.
Modulus			%
If				if
While			while
Else			else
For				for

The DFA also uses 4 non-final states:

state      string recognized

Start      the empty string
Period     float parts ending with "."
E          float parts ending with E or e
EPlusMinus float parts ending with + or - in exponentiation part

The states are represented by an Enum type called "State".
The function "driver" is the driver to operate the DFA.
The function "nextState" returns the next state given
the current state and the input character.

To modify this lexical analyzer to recognize a different token set,
the functions "nextState", "isFinal" and the enum type "State" need to be modified;
the function "driver" and the other utility functions remain the same.

**/

import java.io.*;

public abstract class lexArith
{
	public enum State
       	{
	  // non-final states     ordinal number

		Start,             // 0
		Period,            // 1
		E,                 // 2
		EPlusMinus,        // 3
		Ampersand,         // 4
		Bar,			   // 5
	  // final states


		Id,                // 6
		Int,               // 7
		Float,             // 8
		FloatE,            // 9
		Plus,              // 10
		Minus,             // 11
		Times,             // 12
		Div,               // 13
		LParen,            // 14
		RParen,            // 15
		Or,				   // 16
		And,               // 17
		Inverse,           // 18
		Assign,            // 19
		Equal,             // 20
		NotEqual,          // 21
		SemiColon,         // 22
		LessThan,          // 23
		GreaterThan,       // 24
		LessThanEqual,     // 25
		GreaterThanEqual,  // 26
		LBrace,            // 27
		RBrace,            // 28
		Modulus,           // 29
		Id_w,              // 30
		Id_wh,             // 31
		Id_whi,            // 32
		Id_whil,           // 33
		While,             // 34
		Id_i,			   // 35
		If,                // 36
		Id_e,              // 37
		Id_el,             // 38
		Id_els,            // 39
		Else,              // 40
		Id_f,              // 41
		Id_fo,             // 42
		For,               // 43

		UNDEF
	}

	// By enumerating the non-final states first and then the final states,
	// test for a final state can be done by testing if the state's ordinal number
	// is greater than or equal to that of Id.

	public static String t; // holds an extracted token
	public static State state; // the current state of the FA
	private static int a; // the current input character
	private static char c; // used to convert the variable "a" to
	                       // the char type whenever necessary
	private static BufferedReader inStream;
	private static PrintWriter outStream;

	private static int getNextChar()

	// Returns the next character on the input stream.

	{
		try
		{
			return inStream.read();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return -1;
		}
	} //end getNextChar

	private static int getChar()

	// Returns the next non-whitespace character on the input stream.
	// Returns -1, end-of-stream, if the end of the input stream is reached.

	{
		int i = getNextChar();
		while ( Character.isWhitespace((char) i) )
			i = getNextChar();
		return i;
	} // end getChar

	private static int driver()

	// This is the driver of the FA.
	// If a valid token is found, assigns it to "t" and returns 1.
	// If an invalid token is found, assigns it to "t" and returns 0.
	// If end-of-stream is reached without finding any non-whitespace character, returns -1.

	{
		State nextState; // the next state of the FA

		t = "";
		state = State.Start;

		if ( Character.isWhitespace((char) a) )
			a = getChar(); // get the next non-whitespace character
		if ( a == -1 ) // end-of-stream is reached
			return -1;

		while ( a != -1 ) // while "a" is not end-of-stream
		{
			c = (char) a;
			nextState = nextState( state, c );
			if ( nextState == State.UNDEF ) // The FA will halt.
			{
				if ( isFinal(state) )
					return 1; // valid token extracted
				else // "c" is an unexpected character
				{
					t = t+c;
					a = getNextChar();
					return 0; // invalid token found
				}
			}
			else // The FA will go on.
			{
				state = nextState;
				t = t+c;
				a = getNextChar();
			}
		}

		// end-of-stream is reached while a token is being extracted

		if ( isFinal(state) )
			return 1; // valid token extracted
		else
			return 0; // invalid token found
	} // end driver

	private static State nextState(State s, char c)

	// Returns the next state of the FA given the current state and input char;
	// if the next state is undefined, UNDEF is returned.

	{
		if ( s == State.Start )
		{
			if ( Character.isLetter(c) )
			{
				if ( c == 'w' )
					return State.Id_w;
				else if ( c == 'i' )
					return  State.Id_i;
				else if ( c == 'f' )
					return State.Id_f;
				else if ( c == 'e' )
					return State.Id_e;
				else
					return State.Id;
			}

			else if ( Character.isDigit(c) )
				return State.Int;
			else if ( c == '.' )
				return State.Period;
			else if ( c == '+' )
				return State.Plus;
			else if ( c == '-' )
				return State.Minus;
			else if ( c == '*' )
				return State.Times;
			else if ( c == '/' )
				return State.Div;
			else if ( c == '(' )
				return State.LParen;
			else if ( c == ')' )
				return State.RParen;
			else if ( c == '=')
				return State.Assign;
			else if ( c == '|')
				return State.Bar;
			else if ( c == '&')
				return State.Ampersand;
			else if ( c == '!')
				return State.Inverse;
			else if ( c == ';')
				return State.SemiColon;
			else if ( c == '<')
				return State.LessThan;
			else if ( c == '>')
				return State.GreaterThan;
			else if ( c == '{')
				return State.LBrace;
			else if (c == '}')
				return State.RBrace;
			else if ( c == '%')
				return State.Modulus;
			else
				return State.UNDEF;
		}
		else if ( s == State.Id )
		{
			if ( Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		}
		else if ( s == State.Int )
		{
			if ( Character.isDigit(c) )
				return State.Int;
			else if ( c == '.' )
				return State.Period;
			else
				return State.UNDEF;
		}
		else if ( s == State.Period )
		{
			if ( Character.isDigit(c) )
				return State.Float;
			else
				return State.UNDEF;
		}
		else if ( s == State.Float )
		{
			if ( Character.isDigit(c) )
				return State.Float;
			else if ( c == 'e' || c == 'E' )
				return State.E;
			else
				return State.UNDEF;
		}
		else if ( s == State.E )
		{
			if ( Character.isDigit(c) )
				return State.FloatE;
			else if ( c == '+' || c == '-' )
				return State.EPlusMinus;
			else
				return State.UNDEF;
		}
		else if ( s == State.EPlusMinus )
		{
			if ( Character.isDigit(c) )
				return State.FloatE;
			else
				return State.UNDEF;
		}
		else if ( s == State.FloatE )
		{
			if ( Character.isDigit(c) )
				return State.FloatE;
			else
				return State.UNDEF;
		}
		else if ( s == State.Inverse )
		{
			if ( c == '=' )
				return State.NotEqual;
			else
				return State.UNDEF;
		}
		else if ( s == State.Bar)
		{
			if ( c == '|')
				return State.Or;
			else if ( Character.isLetterOrDigit(c) )
				return State.UNDEF;
			else
				return State.UNDEF;
		}
		else if ( s == State.Ampersand)
		{
			if ( c == '&')
				return State.And;
			else if ( Character.isLetterOrDigit(c) )
				return State.UNDEF;
			else
				return State.UNDEF;
		}
		else if ( s == State.LessThan)
		{
			if ( c == '=')
				return State.LessThanEqual;
			else
				return State.UNDEF;
		}
		else if ( s == State.GreaterThan)
		{
			if ( c == '=')
				return State.GreaterThanEqual;
			else
				return State.UNDEF;
		}
		else if ( s == State.Assign)
		{
			if ( c == '=')
				return State.Equal;
			else
				return State.UNDEF;
		}
		else if ( s == State.Id_w )
		{
			if( c == 'h' )
				return State.Id_wh;
			else if (Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		}
		else if ( s == State.Id_wh)
		{
			if( c == 'i')
				return State.Id_whi;
			else if ( Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		}
		else if ( s == State.Id_whi )
		{
			if( c == 'l' )
				return State.Id_whil;
			else if (Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		}
		else if ( s == State.Id_whil )
		{
			if( c == 'e' )
				return State.While;
			else if ( Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		}
		else if( s == State.While )
		{
			if( Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		}
		else if ( s == State.Id_i )
		{
			if( c=='f' )
				return State.If;
			else if ( Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		}
		else if( s== State.If )
		{
			if( Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		}
		else if ( s == State.Id_e )
		{
			if( c == 'l' )
				return State.Id_el;
			else if ( Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		}
		else if ( s == State.Id_el )
		{
			if( c=='s' )
				return State.Id_els;
			else if ( Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		}
		else if ( s == State.Id_els )
		{
			if( c=='e' )
				return State.Else;
			else if ( Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		}
		else if( s== State.Else )
		{
			if( Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		}
		else if ( s == State.Id_f )
		{
			if( c == 'o'  )
				return State.Id_fo;
			else if ( Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		}
		else if ( s == State.Id_fo )
		{
			if( c == 'r' )
				return State.For;
			else if ( Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		}
		else if( s== State.For )
		{
			if( Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		}
		else
			return State.UNDEF;
	} // end nextState

	private static boolean isFinal(State state)
	{
		return ( state.compareTo(State.Id) >= 0 );
	}

	public static void getToken()

	// Extract the next token using the driver of the FA.
	// If an invalid token is found, issue an error message.

	{
		int i = driver();
		if ( i == 0 )
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

	public static void setLex(String inFile, String outFile)

	// Sets the input and output streams to "inFile" and "outFile", respectively.
	// Also sets the current input character "a" to the first character on
	// the input stream.

	{
		try
		{
			inStream = new BufferedReader( new FileReader(inFile) );
			outStream = new PrintWriter( new FileOutputStream(outFile) );
			a = inStream.read();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
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
		catch(IOException e)
		{
			e.printStackTrace();
		}
	} // end closeIO

	public static void main(String argv[])

	// The input/output file names must be passed as argv[0] and argv[1].

	{
		int i;

		setLex( argv[0], argv[1] );

		while ( a != -1 ) // while "a" is not end-of-stream
		{
			i = driver(); // extract the next token
			if ( i == 1 )
				displayln( t+"   : "+state.toString() );
			else if ( i == 0 )
				displayln( t+"  -- Invalid Token" );
		}

		closeIO();
	} // end main
}

