
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class lexArith {
    public enum State
       	{
        // non-final states only

		Start,
        Bar,
        Ampersand,
        Period,
		Assign,
		E,
		Eplusminus,  
        Id_I,
        Id_E,
        Id_EL,
        Id_ELS,


        // final states only

		Add,
		Sub,
        Mul,
        Div,
        Lparen,
        Rparen,
        Lbrace,
        Rbrace,
        Comma,
        Or,
        And,
        Id,
        Int,
        Float,
        Inv,
        Neq,
        Eq,
        Lt,
        Le,
        Gt,
        Ge,
        FloatE,
        If,
        Else,
       
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

	private static int getNextChar() throws IOException

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

    
	private static int getChar() throws IOException

	// Returns the next non-whitespace character on the input stream.
	// Returns -1, end-of-stream, if the end of the input stream is reached.

	{
		int i = getNextChar();
		while ( Character.isWhitespace((char) i) )
			i = getNextChar();
		return i;
	} // end getChar

	private static int driver() throws IOException

	// This is the driver of the FA.
	// If a valid token is found, Assigns it to "t" and returns 1.
	// If an Invalid token is found, Assigns it to "t" and returns 0.
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
					return 0; // Invalid token found
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
			return 0; // Invalid token found
	} // end driver

    
	private static State nextState(State s, char c)

	// Returns the next state of the FA given the current state and input char;
	// if the next state is undefined, UNDEF is returned.

	{
        
		if ( s == State.Start )
		{
			if ( Character.isLetter(c) )
            {
                if(c == 'i')
                    return State.Id_I;
                else if(c == 'e')
                    return State.Id_E;
                else
                    return State.Id;
            }
            else if ( Character.isDigit(c) )
                return State.Int;
            else if ( c == '+' )
                return State.Add;
            else if ( c == '-' )
                return State.Sub;
            else if ( c == '*' )
                return State.Mul;
            else if ( c == '/' )
                return State.Div;
            else if ( c == '(' )
                return State.Lparen;
            else if ( c == ')' )
                return State.Rparen;
            else if ( c == '{')
                return State.Lbrace;
            else if ( c == '}')
                return State.Rbrace;
            else if ( c == ',')
                return State.Comma;
            else if ( c == '|')
                return State.Bar;
            else if ( c == '&')
                return State.Ampersand;
			else if( c == '!')
                return State.Inv;
            else if( c == '=')
                return State.Assign;
            else if(c == '<')
                return State.Lt;
            else if(c == '>')
                return State.Gt;
            else if(c == '.')
                return State.Period;
            else
				return State.UNDEF;
        }
        else if( s == State.Id_I)
        {
           if(c != 'f')
           {
               if(Character.isLetter(c))
                   return State.Id;
               else
               {
                state = State.Id;
                return State.UNDEF;
               }

           }
           else if ( c == 'f' )
               return State.If;
           else
               return State.UNDEF;
        }
        else if(s == State.If)
        {
            if ( Character.isLetterOrDigit(c) )
				return State.Id;
			else
                return State.UNDEF;
        }
        else if(s == State.Id_E)
        {
           if(c != 'l')
           {
               if(Character.isLetter(c))
                   return State.Id;
               else
               {
                state = State.Id;
                return State.UNDEF;
               }

           }
           else if ( c == 'l' )
               return State.Id_EL;
           else
               return State.UNDEF;
        }
        else if(s == State.Id_EL)
        {
           if(c != 's')
           {
               if(Character.isLetter(c))
                   return State.Id;
               else
               {
                   state = State.Id;
                   return State.UNDEF;
               }
           }
           else if ( c == 's' )
               return State.Id_ELS;
           else
               return State.UNDEF;
           
        }
        else if(s == State.Id_ELS)
        {
           if(c != 'e')
           {
               if(Character.isLetter(c))
                   return State.Id;
               else
               {
                state = State.Id;
                return State.UNDEF;
               }

           }
           else if ( c == 'e' )
               return State.Else;
           else
               return State.UNDEF;
        }
        else if(s == State.Else)
        {
            if(Character.isLetterOrDigit(c))
                return State.Id;
            else
                return State.UNDEF;
        }
        else if ( s == State.Id)
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
				return State.Float;
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
			else if ( c == 'E' || c == 'e' )
				return State.E;
			else
				return State.UNDEF;
		}
        
		else if ( s == State.E )
		{
			if ( Character.isDigit(c) )
				return State.FloatE;
			else if ( c == '+' || c == '-' )
				return State.Eplusminus;
			else
				return State.UNDEF;
		}

		else if ( s == State.Eplusminus)
		{
			if ( Character.isDigit(c) )
				return State.FloatE;
			else
				return State.UNDEF;
		}
        
		else if ( s == State.FloatE)
		{
			if ( Character.isDigit(c) )
				return State.FloatE;
			else
				return State.UNDEF;
		}
        else if( s == State.Bar)
        {
            if(c == '|')
                return State.Or;
            else
                return State.UNDEF;
        }
        else if(s == State.Ampersand)
        {
            if(c == '&')
                return State.And;
            else
                return State.UNDEF;
        }
		
		else if(s == State.Assign)
        {
            if(c == '=')
                return State.Eq;
            else
                return State.UNDEF;
        }
        else if(s == State.Inv)
        {
            if(c == '=')
                return State.Neq;
            else
                return State.UNDEF;
        }

        else if(s == State.Lt)
        {
            if(c == '=')
                return State.Le;
            else
                return State.UNDEF;
        }
        else if(s == State.Gt)
        {
            if(c == '=')
                return State.Ge;
            else
                return State.UNDEF;
        }
        else
			return State.UNDEF;
	} // end nextState

	private static boolean isFinal(State state)
	{
        if(state.ordinal() >= State.Add.ordinal())
            return true;
        else
            return false;
    }
        
			


	public static void getToken() throws IOException

	// Extract the next token using the driver of the FA.
	// If an invalId token is found, issue an error message.

	{
		int i = driver();
		if ( i == 0 )
			displayln(t + "  -- InvalId Token");
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

    public static void main(String argv[]) throws IOException

	// The input/output file names must be passed as argv[0] and argv[1].

	{

		int i;
        System.out.println(argv[0] +  "   "   + argv[1]);
		setLex(argv[0], argv[1]);

        

		while ( a != -1 )  //while a is not end-of-stream
		{
			i = driver();  //extract the next token
			if ( i == 1 )
				displayln( t+"   : "+ state.toString() );
			else if ( i == 0 )
				displayln( t+"   -- InvalId Token");
		}

		closeIO();

	} // end main


}
