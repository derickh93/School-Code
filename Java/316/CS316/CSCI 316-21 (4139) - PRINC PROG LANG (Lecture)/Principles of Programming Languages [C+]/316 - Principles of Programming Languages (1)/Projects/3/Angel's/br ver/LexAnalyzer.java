/**
 
This program uses the Enum type introduced in JDK 1.5.0.

This class is a lexical analyzer for the 22 token categories <int>
through <comma> defined by:

<letter> --> a | b | ... | z | A | B | ... | Z
<digit> --> 0 | 1 | ... | 9
<int> --> {<digit>}+
<id> --> <letter> { <letter> | <digit> }
<float> --> {<digit>}+ "." {<digit>} | "." {<digit>}+
<floatE> --> <float> (E|e) [+|-] {<digit>}+
<add> --> +
<sub> --> -
<mul> --> *
<div> --> /
<or> --> "||"
<and> --> "&&"
<inv> --> !
<lt> --> "<"
<le> --> "<="
<gt> --> ">"
<ge> --> ">="
<eq> --> "=="
<neq> --> "!="
<LParen> --> "("
<RParen> --> ")"
<LBrace> --> "{"
<RBrace> --> "}"
<comma> --> , 

This class implements a DFA that will accept the above tokens.
The DFA has 22 final and 7 non-final states represented by enum-type literals.

There are also special states for the keywords "if" and "else".
The keywords are initially extracted as identifiers.
The keywordCheck() function checks to see if the extracted token is a keyword,
and if so, moves the DFA to the corresponding special state.

The states are represented by an Enum type called "State".
The function "driver" is the driver to operate the DFA. 
The array "nextState" returns the next state given
the current state and the input character.

To modify this lexical analyzer to recognize a different token set,
the array "nextState", the functions "isFinal", "setNextState", "keywordCheck", and 
the enum type "State" need to be modified.
The function "driver" and the other utility functions remain the same.

**/

import java.io.*;

public abstract class LexAnalyzer
{
	public enum State 
	{ 
		//final states	ordinal number  	token accepted 
		Add,            // 0          		+
		Sub,            // 1          		-
		Mul,            // 2          		*
		Div,            // 3          		/
		Or,             // 4          		||
		And,            // 5          		&&
		Inv,            // 6          		!
		Lt,             // 7          		<
		Le,             // 8          		<=
		Gt,             // 9          		>
		Ge,             // 10         		>=
		Eq,             // 11         		==
		Neq,            // 12         		!=
		Id,             // 13         		identifiers
		Int,            // 14         		integers
		Float,          // 15         		floats without exponentiation part
		FloatE,         // 16         		floats with exponentiation part
		LParen,         // 17         		(
		RParen,         // 18         		)
		LBrace,         // 19         		{
		RBrace,         // 20        		}
		Comma,          // 21         		,
		//non-final states					string recognized    
		Start,           // 22      		the empty string
		Bar,             // 23      		|
		Ampersand,       // 24      		&
		Eqsign,          // 25      		=
		Period,          // 26      		"."
		E,               // 27      		float parts ending with E or e
		EPlusMinus,      // 28      		float parts ending with + or - in exponentiation part
		UNDEF,
		//keyword states
		If,
		Else, 
		//Added:
		Id_i, 
		Id_e, 
		Id_el, 
		Id_els,
		funDefs,
		funDef,
		header,
		funName,
		parameterList,
		body,
		Exp,
		expr,
		boolTerm,
		boolPrimary,
		relOp,
		term,
		primary,
		funE,
		eList,
		empty
	}//State

	/** 
	 * By enumerating the final states first and then the non-final states,
	 * test for a final state can be done by testing if the state's ordinal number
	 * is less than or equal to that of Semicolon.
	 */

	/**Holds an extracted token*/
	public static String t; 
	/**The current state of the FA*/
	public static State state;
	/**The current input character*/
	protected static int a; 
	/**used to convert the variable "a" to the char type whenever necessary*/
	private static char c;

	/**
	 * This array implements the state transition function.
	 * State x (ASCII char set) --> State.
	 * The state argument is converted to its ordinal number used as
	 * the first array index from 0 through 28.
	 */
	private static State nextState[][] = new State[29][128];

	private static String if_     = "if";
	private static String else_   = "else";

	private static BufferedReader inStream;
	private static PrintWriter outStream;

	/**
	 * Returns the next character on the input stream.
	 * 
	 * @return -1 if an error occurs.  Otherwise, returns the 
	 * next character on the input stream.
	 */
	private static int getNextChar()
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

	/**
	 * Returns the next non-whitespace character on the input stream.
	 * @return -1 if the end of the input stream is reached.  Otherwise,
	 * the next non-whitespace character is returned.
	 */
	private static int getChar()
	{
		int i = getNextChar();
		while ( Character.isWhitespace((char) i) )
			i = getNextChar();
		return i;
	} // end getChar

	/**
	 * This is the driver of the FA.
	 * If a valid token is found, assigns it to "t" and returns 1.
	 * If an invalid token is found, assigns it to "t" and returns 0.
	 * If end-of-stream is reached without finding any non-whitespace character, returns -1.
	 * @return 1 if a valid token is found.
	 * 		   0 if an invalid token is found
	 * 		  -1 if end of stream is reached
	 */
	private static int driver()
	{
		/**The next state of the FA*/
		State nextSt;

		t = "";
		state = State.Start;

		if ( Character.isWhitespace((char) a) )
		{
			a = getChar(); // get the next non-whitespace character
		}//if
		if ( a == -1 ) // end-of-stream is reached
		{
			return -1;
		}//if

		while ( a != -1 ) 								// do the body if "a" is not end-of-stream
		{
			c = (char) a;
			nextSt = nextState[state.ordinal()][a];
			if ( nextSt == State.UNDEF ) 				// The FA will halt.
			{
				if ( isFinal(state) )
				{
					return 1; 							// valid token extracted
				}//if
				else 									// "c" is an unexpected character
				{
					t = t+c;
					a = getNextChar();
					return 0; 							// invalid token found
				}//else
			}//if
			else 										// The FA will go on.
			{
				state = nextSt;
				t = t+c;
				a = getNextChar();
			}//else
		}//while

		// end-of-stream is reached while a token is being extracted
		if ( isFinal(state) )
		{
			return 1; // valid token extracted
		}//if
		else
		{
			return 0; // invalid token found
		}//else
	} // end driver

	/**
	 * 
	 */
	private static void setNextState()
	{
		for (int s = 0; s <= 28; s++ )
		{
			for (int c = 0; c <= 127; c++ )
			{
				nextState[s][c] = State.UNDEF;
			}//for
		}//for

		for (char c = 'A'; c <= 'Z'; c++)
		{
			nextState[State.Start.ordinal()][c] = State.Id;
			nextState[State.Id.ordinal()][c] = State.Id;
		}//for

		for (char c = 'a'; c <= 'z'; c++)
		{
			nextState[State.Start.ordinal()][c] = State.Id;
			nextState[State.Id   .ordinal()][c] = State.Id;
		}//for

		for (char d = '0'; d <= '9'; d++)
		{
			nextState[State.Start     .ordinal()][d] = State.Int;
			nextState[State.Id        .ordinal()][d] = State.Id;
			nextState[State.Int       .ordinal()][d] = State.Int;
			nextState[State.Period    .ordinal()][d] = State.Float;
			nextState[State.Float     .ordinal()][d] = State.Float;
			nextState[State.E         .ordinal()][d] = State.FloatE;
			nextState[State.EPlusMinus.ordinal()][d] = State.FloatE;
			nextState[State.FloatE    .ordinal()][d] = State.FloatE;
		}//for

		nextState[State.Start.ordinal()]['+'] = State.Add;
		nextState[State.Start.ordinal()]['-'] = State.Sub;
		nextState[State.Start.ordinal()]['*'] = State.Mul;
		nextState[State.Start.ordinal()]['/'] = State.Div;
		nextState[State.Start.ordinal()]['!'] = State.Inv;
		nextState[State.Start.ordinal()]['<'] = State.Lt;
		nextState[State.Start.ordinal()]['>'] = State.Gt;
		nextState[State.Start.ordinal()]['='] = State.Eqsign;
		nextState[State.Start.ordinal()]['('] = State.LParen;
		nextState[State.Start.ordinal()][')'] = State.RParen;
		nextState[State.Start.ordinal()]['{'] = State.LBrace;
		nextState[State.Start.ordinal()]['}'] = State.RBrace;
		nextState[State.Start.ordinal()][','] = State.Comma;
		nextState[State.Start.ordinal()]['|'] = State.Bar;
		nextState[State.Start.ordinal()]['&'] = State.Ampersand;
		nextState[State.Start.ordinal()]['.'] = State.Period;

		nextState[State.Bar      .ordinal()]['|'] = State.Or;
		nextState[State.Ampersand.ordinal()]['&'] = State.And;
		nextState[State.Eqsign   .ordinal()]['='] = State.Eq;
		nextState[State.Inv      .ordinal()]['='] = State.Neq;
		nextState[State.Lt       .ordinal()]['='] = State.Le;
		nextState[State.Gt       .ordinal()]['='] = State.Ge;
			
		nextState[State.Int.ordinal()]['.'] = State.Float;
			
		nextState[State.Float.ordinal()]['E'] = State.E;
		nextState[State.Float.ordinal()]['e'] = State.E;
			
		nextState[State.E.ordinal()]['+'] = State.EPlusMinus;
		nextState[State.E.ordinal()]['-'] = State.EPlusMinus;

	} // end setNextState

	/**
	 * 
	 * @param state
	 * @return
	 */
	private static boolean isFinal(State state)
	{
		return ( state.compareTo(State.Comma) <= 0 );  
	}
	
	/**
	 * 
	 */
	private static void keywordCheck()
	{
		if ( t.equals(if_) )
		{
			state = State.If;
		}//if
		else if ( t.equals(else_) )
		{
			state = State.Else;
		}//else if
	} // end keywordCheck

	/**
	 * Extract the next token using the driver of the FA.
	 * If an invalid token is found, issue an error message.
	 */
	public static void getToken()
	{
		int i = driver();
		if ( state == State.Id )
		{
			keywordCheck();
		}//if
		else if ( i == 0 )
		{
			displayln(t + "  -- Invalid Token");
		}//else if
	} // end getToken

	/**
	 * Prints a given string to the console and text file
	 * 
	 * @param s - The string to be printed
	 */
	public static void display(String s)
	{
		System.out.print(s);
		outStream.print(s);
	}//display

	/**
	 * Prints a given line to the console and text file
	 * 
	 * @param s - The string to be printed
	 */
	public static void displayln(String s)
	{
		System.out.println(s);
		outStream.println(s);
	}//displayln

	/**
	 * Sets the nextState array.
	 * Sets the input and output streams to "inFile" and "outFile", respectively.
	 * Sets the curernt input character "a" to the first character on the input stream.
	 * 
	 * @param inFile - The input file to be read.
	 * @param outFile - The output file to be created.
	 */
	public static void setLex(String inFile, String outFile)
	{
		setNextState();

		try
		{
			inStream = new BufferedReader( new FileReader(inFile) );
			outStream = new PrintWriter( new FileOutputStream(outFile) );
			a = inStream.read();
		}//try
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}//catch
		catch(IOException e)
		{
			e.printStackTrace();
		}//catch
	} // end setIO

	/**
	 * 
	 */
	public static void closeIO()
	{
		try
		{
			inStream.close();
			outStream.close();
		}//try
		catch(IOException e)
		{
			e.printStackTrace();
		}//catch
	} // end closeIO

	/**
	 * Runs the program.
	 * 
	 * @param argv [0] - The input file name
	 * @param argv [1] - The output file name
	 */
	public static void main(String argv[])
	{
		int i;

		setLex( argv[0], argv[1] );

		while ( a != -1 ) 						// while "a" is not end-of-stream
		{
			i = driver(); 						// extract the next token
			if ( i == 1 )
			{
				if ( state == State.Id )
					keywordCheck();
				displayln( t+"   : "+state.toString() );
			}//if
			else if ( i == 0 )
			{
				displayln( t+"  -- Invalid Token");
			}//else if
		}//while
		closeIO();
	} // end main
}//class LexAnalyzer
