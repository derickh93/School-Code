import java.io.*;

public abstract class LexInst
{
	public static String t; // holds an extracted token
	public static State state; // the current state of the FA
	private static int a; // the current input character
	private static char c; // used to convert the variable "a" to 
	                       // the char type whenever necessary

	private static State nextState[][] = new State[14][128];
 
          // This array implements the state transition function
          // State x (ASCII char set) --> State.
          // The state argument is converted to its ordinal number used as
          // the first array index from 0 through 13. 

	private static String add_    = "add";
	private static String sub_    = "sub";
	private static String mul_    = "mul";
	private static String div_    = "div";
	private static String or_     = "or";
	private static String and_    = "and";
	private static String inv_    = "inv";
	private static String neg_    = "neg";
	private static String lt_     = "lt";
	private static String le_     = "le";
	private static String gt_     = "gt";
	private static String ge_     = "ge";
	private static String eq_     = "eq";
	private static String neq_    = "neq";
	private static String push_   = "push";
	private static String iff_    = "if_f";
	private static String goto_   = "goto";
	private static String call_   = "call";
	private static String return_ = "return";

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
		State nextSt; // the next state of the FA

		t = "";
		state = State.Start;

		if ( Character.isWhitespace((char) a) )
			a = getChar(); // get the next non-whitespace character
		if ( a == -1 ) // end-of-stream is reached
			return -1;

		while ( a != -1 ) // do the body if "a" is not end-of-stream
		{
			c = (char) a;
			nextSt = nextState[state.ordinal()][a];
			if ( nextSt == State.UNDEF ) // The FA will halt.
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
				state = nextSt;
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

	private static void setNextState()
	{
		for (int s = 0; s <= 13; s++ )
			for (int c = 0; c <= 127; c++ )
				nextState[s][c] = State.UNDEF;

		for (char c = 'A'; c <= 'Z'; c++)
		{
			nextState[State.Start.ordinal()][c] = State.Id;
			nextState[State.Id   .ordinal()][c] = State.Id;
		}

		for (char c = 'a'; c <= 'z'; c++)
		{
			nextState[State.Start.ordinal()][c] = State.Id;
			nextState[State.Id   .ordinal()][c] = State.Id;
		}

		for (char d = '0'; d <= '9'; d++)
		{
			nextState[State.Start     .ordinal()][d] = State.Int;
			nextState[State.Id        .ordinal()][d] = State.Id;
			nextState[State.Int       .ordinal()][d] = State.Int;
			nextState[State.Plus      .ordinal()][d] = State.Int;
			nextState[State.Minus     .ordinal()][d] = State.Int;
			nextState[State.Period    .ordinal()][d] = State.Float;
			nextState[State.Float     .ordinal()][d] = State.Float;
			nextState[State.E         .ordinal()][d] = State.FloatE;
			nextState[State.EPlusMinus.ordinal()][d] = State.FloatE;
			nextState[State.FloatE    .ordinal()][d] = State.FloatE;
		}

		nextState[State.Start.ordinal()]['+'] = State.Plus;
		nextState[State.Start.ordinal()]['-'] = State.Minus;
		nextState[State.Start.ordinal()]['.'] = State.Period;

		nextState[State.Plus.ordinal()]['.']  = State.Period;
		nextState[State.Minus.ordinal()]['.'] = State.Period;
		nextState[State.Int.ordinal()]['.'] = State.Float;
			
		nextState[State.Float.ordinal()]['E'] = state.E;
		nextState[State.Float.ordinal()]['e'] = state.E;
			
		nextState[State.E.ordinal()]['+'] = State.EPlusMinus;
		nextState[State.E.ordinal()]['-'] = State.EPlusMinus;

		nextState[State.Start.ordinal()][':'] = State.Colon;
		nextState[State.Start.ordinal()]['#'] = State.Sharp;
		nextState[State.Start.ordinal()][','] = State.Comma;
		nextState[State.Id.ordinal()]['_'] = State.Underscore;
		nextState[State.Underscore.ordinal()]['f'] = State.Id;

	} // end setNextState

	private static boolean isFinal(State state)
	{
		return ( state.compareTo(State.Comma) <= 0 );  
	}
	
	private static void keywordCheck()
	{
		if ( t.equals(add_) )
			state = State.Add;
		else if ( t.equals(sub_) )
			state = State.Sub;
		else if ( t.equals(mul_) )
			state = State.Mul;
		else if ( t.equals(div_) )
			state = State.Div;
		else if ( t.equals(or_) )
			state = State.Or;
		else if ( t.equals(and_) )
			state = State.And;
		else if ( t.equals(inv_) )
			state = State.Inv;
		else if ( t.equals(neg_) )
			state = State.Neg;
		else if ( t.equals(lt_) )
			state = State.Lt;
		else if ( t.equals(le_) )
			state = State.Le;
		else if ( t.equals(gt_) )
			state = State.Gt;
		else if ( t.equals(ge_) )
			state = State.Ge;
		else if ( t.equals(eq_) )
			state = State.Eq;
		else if ( t.equals(neq_) )
			state = State.Neq;
		else if ( t.equals(push_) )
			state = State.Push;
		else if ( t.equals(iff_) )
			state = State.Iff;
		else if ( t.equals(goto_) )
			state = State.Goto;
		else if ( t.equals(call_) )
			state = State.Call;
		else if ( t.equals(return_) )
			state = State.Return;
	} // end keywordCheck

	public static void getToken()

	// Extract the next token using the driver of the FA.
	// If an invalid token is found, issue an error message.

	{
		int i = driver();
		if ( state == State.Id )
			keywordCheck();
		else if ( i == 0 )
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

	// Sets the nextState array.
	// Sets the input and output streams to "inFile" and "outFile", respectively.
	// Sets the current input character "a" to the first character on
	// the input stream.

	{
		setNextState();

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
	} // end setLex

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
			{
				if ( state == State.Id )
					keywordCheck();
				displayln( t+"   : "+state.toString() );
			}
			else if ( i == 0 )
				displayln( t+"  -- Invalid Token");
		} 

		closeIO();
	} // end main
} 
