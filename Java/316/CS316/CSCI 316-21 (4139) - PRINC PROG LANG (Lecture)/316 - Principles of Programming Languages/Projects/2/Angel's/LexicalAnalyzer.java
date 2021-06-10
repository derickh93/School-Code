import java.io.*;

/**
 * CS316 - Fall 2009
 * Project 2 - Lexical Analyzer
 * Due - 10-27-09
 * 
 * @author Angel Guevara, Jr.
 */
public class LexicalAnalyzer 
{

	/**
	 * Enumerates the states of the Finite Automaton
	 */
	public enum State
	{
		//Non-Final States	Ordinal Number
		Start,				//0
		Period,				//1
		E,					//2
		EPlusMinus,			//3
		
		//ADDED:
		Equal,				//4
		Bar,				//5
		Ampersand,			//6
		
		//Final States		Ordinal Number
		ID,					//7
		Int,				//8
		Float,				//9
		FloatE,				//10
		Plus,				//11
		Minus,				//12
		Times,				//13
		Div,				//14
		LParen,				//15
		RParen,				//16
		
		//ADDED:
		LBrace,				//17
		RBrace,				//18
		Comma,				//19
		Or,					//20
		And,				//21
		Not,				//22
		LessThan,			//23
		GreaterThan,		//24
		DoubleEqual,		//25
		GreaterThanEqual,	//26
		LessThanEqual,		//27
		NotEqual,			//28
		ID_i,				//29
		ID_e,				//30
		If,					//31
		ID_el,				//32
		ID_els,				//33
		Else,				//34
		
		UNDEF				//35
	}//State
	
	/**Holds the extracted token*/
	public static String t;
	/**The current state of the Finite Automaton*/
	public static State state;
	/**The current input character*/
	private static int a;
	/**Used to convert the variable "a" to the char type, whenever necessary*/
	private static char c;
	/**Used when reading from the file*/
	private static BufferedReader inStream;
	/**Used when writing to the file*/
	private static PrintWriter outStream;
	
	/**
	 * Returns the next character in the input stream
	 * 
	 * @return next character in the input stream or -1 if an IOE is caught
	 */
	private static int getNextChar()
	{
		try
		{
			return inStream.read();
		}//try
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			return -1;
		}//catch
	}//getNextChar
	
	/**
	 * Finds the next non-whitespace character in the input stream.
	 * 
	 * @return i = the next non-whitespace character or 
	 * 		  -1 = the end of the input stream is reached
	 */
	private static int getChar()
	{
		int i=getNextChar();
		while(Character.isWhitespace((char)i))
		{
			i=getNextChar();
		}//while
		return i;
	}//getChar
	
	/**
	 * The driver of the Finite Automaton.  If a valid token is found,
	 * it assigns the token to "t"
	 * 
	 * @return
	 * 		1 if a valid token is found
	 * 		0 if an invalid token is found
	 * 	   -1 if the end of the input stream is reached without finding any non-whitespace character	
	 */
	private static int driver()
	{
		State nextState;					//The next state of the finite automaton
		t = "";
		state = State.Start;
		if(Character.isWhitespace((char)a))	//If the character is a whitespace...
		{
			a = getChar();					//...get the next non-whitespace character
		}//if
		if(a==-1)							//If the end of the stream is reached...
		{
			return -1;
		}//if
		while(a!=-1)						//While "a" is not the end of the stream...
		{
			c=(char)a;
			nextState=nextState(state,c);
			if(nextState==State.UNDEF)		//The finite automaton will halt
			{
				if(isFinal(state))			//If in a final state...
				{
					return 1;				//...valid token is extracted
				}//if
				else						//otherwise, if "c" was an unexpected character...
				{
					t=t+c;
					a=getNextChar();
					return 0;				//...indicate an invalid token found
				}//else
			}//if
			else							//Otherwise, the finite automaton will go on
			{
				state=nextState;
				t=t+c;
				a=getNextChar();
			}//else
		}//while
		
		//The end of the stream is reached while a token was being extracted
		if(isFinal(state))				//If in a final state...
		{
			return 1;					//...a valid token was extracted
		}//if
		else							//Otherwise...
		{
			return 0;					//...an invalid token was found
		}//else
	}//driver
	
	/**
	 * Returns the next state of the finite automaton, based on its current state
	 * and input character.
	 * 
	 * @param s - current state
	 * @param c - input character
	 * @return the next state, or goes to the undefined state
	 */
	private static State nextState(State s, char c)
	{
		if(s==State.Start)
		{
			if(c=='i')
			{
				return State.ID_i;
			}//if I
			else if(c=='e')
			{
				return State.ID_e;
			}//else if E
			else if(Character.isLetter(c))
			{
				return State.ID;
			}//else if ID
			else if(Character.isDigit(c))
			{
				return State.Int;
			}//else if INT
			else if(c=='+')
			{
				return State.Plus;
			}//else if +
			else if(c=='-')
			{
				return State.Minus;
			}//else if -
			else if(c=='*')
			{
				return State.Times;
			}//else if *
			else if(c=='/')
			{
				return State.Div;
			}//else if /
			else if(c=='(')
			{
				return State.LParen;
			}//else if (
			else if(c=='{')
			{
				return State.LBrace;
			}//else if {
			else if(c==')')
			{
				return State.RParen;
			}//else if (
			else if(c=='}')
			{
				return State.RBrace;
			}//else if }
			else if(c=='<')
			{
				return State.LessThan;
			}//else if <
			else if(c=='>')
			{
				return State.GreaterThan;
			}//else if >
			else if(c=='=')
			{
				return State.Equal;
			}//else if =
			else if(c=='!')
			{
				return State.Not;
			}//else if !
			else if(c=='&')
			{
				return State.Ampersand; 
			}//else if &
			else if(c=='|')
			{
				return State.Bar;
			}//else if |
			else if(c=='.')
			{
				return State.Period;
			}//else if .
			else if(c==',')
			{
				return State.Comma;
			}
			else
			{
				return State.UNDEF;
			}//else UNDEFINED
		}//if START
		
		else if(s==State.ID_i)
		{
			if(c=='f')
			{
				return State.If;
			}//if
			else if(Character.isLetterOrDigit(c))
			{
				return State.ID;
			}//else if
			else
			{
				return State.UNDEF;
			}//else
		}//else if ID_i
		
		else if(s==State.If)
		{
			if(c=='(')
			{
				return State.LParen;
			}//if (
			else if(Character.isLetterOrDigit(c))
			{
				return State.ID;
			}//else if
			else
			{
				return State.UNDEF;
			}//else
		}//else if IF
		
		else if(s==State.ID_e)
		{
			if(c=='l')
			{
				return State.ID_el;
			}//if
			else if(Character.isLetterOrDigit(c))
			{
				return State.ID;
			}//else if
			else
			{
				return State.UNDEF;
			}//else
		}//else if ID_e
		
		else if(s==State.ID_el)
		{
			if(c=='s')
			{
				return State.ID_els;
			}//if S
			else if(Character.isLetterOrDigit(c))
			{
				return State.ID;
			}//else if
			else
			{
				return State.UNDEF;
			}//else
		}//else if ID_el
		
		else if(s==State.ID_els)
		{
			if(c=='e')
			{
				return State.Else;
			}//if
			else if(Character.isLetterOrDigit(c))
			{
				return State.ID;
			}//else if
			else
			{
				return State.UNDEF;
			}//else
		}//else if ID_els
		
		else if(s==State.Else)
		{
			if(c=='{')
			{
				return State.LBrace;
			}//if {
			else if(c=='i')
			{
				return State.ID_i;
			}//else if I
			else if(Character.isLetterOrDigit(c))
			{
				return State.ID;
			}//else if
			else
			{
				return State.UNDEF;
			}//else
		}//else if ELSE
		
		else if(s==State.Equal)
		{
			if(c=='=')
			{
				return State.DoubleEqual;
			}//if =
			else
			{
				return State.UNDEF;
			}//else
		}//else if =
		
		else if(s==State.Ampersand)
		{
			if(c=='&')
			{
				return State.And;
			}//if &
			else
			{
				return State.UNDEF;
			}//else
		}//else if &
		
		else if(s==State.Bar)
		{
			if(c=='|')
			{
				return State.Or;
			}//if |
			else
			{
				return State.UNDEF;
			}//else
		}//else if |
		
		else if(s==State.GreaterThan)
		{
			if(c=='=')
			{
				return State.GreaterThanEqual;
			}//if
			else
			{
				return State.UNDEF;
			}//else
		}//else if >
		
		else if(s==State.LessThan)
		{
			if(c=='=')
			{
				return State.LessThanEqual;
			}//if =
			else
			{
				return State.UNDEF;
			}//else
		}//else if <
		
		else if(s==State.Not)
		{
			if(c=='=')
			{
				return State.NotEqual;
			}//if =
			else
			{
				return State.UNDEF;
			}//else
		}//else if !
				
		else if(s==State.ID)
		{
			if(Character.isLetterOrDigit(c))
			{
				return State.ID;
			}//if
			else
			{
				return State.UNDEF;
			}//else
		}//else if ID
		else if(s==State.Int)
		{
			if(Character.isDigit(c))
			{
				return State.Int;
			}//if
			else if(c=='.')
			{
				return State.Float;
			}//else if
			else
			{
				return State.UNDEF;
			}//else
		}//else if INT
		else if(s==State.Period)
		{
			if(Character.isDigit(c))
			{
				return State.Float;
			}//if
			else
			{
				return State.UNDEF;
			}//else
		}//else if PERIOD
		else if(s==State.Float)
		{
			if(Character.isDigit(c))
			{
				return State.Float;
			}//if
			else if(c=='e'||c=='E')
			{
				return State.E;
			}//else if
			else
			{
				return State.UNDEF;
			}//else
		}//else if FLOAT
		else if(s==State.E)
		{
			if(Character.isDigit(c))
			{
				return State.FloatE;
			}//if
			else if(c=='+'||c=='-')
			{
				return State.EPlusMinus;
			}//else if
			else
			{
				return State.UNDEF;
			}//else
		}//else if E
		else if(s==State.EPlusMinus)
		{
			if(Character.isDigit(c))
			{
				return State.FloatE;
			}//if
			else
			{
				return State.UNDEF;
			}//else
		}//else if EPLUSMINUS
		else if(s==State.FloatE)
		{
			if(Character.isDigit(c))
			{
				return State.FloatE;
			}//if
			else
			{
				return State.UNDEF;
			}//else
		}//else if FLOATE
		else
		{
			return State.UNDEF;
		}//else UNDEFINED
	}//nextState
	
	/**
	 * Checks if the given state is a final state.
	 * 
	 * @param state - the current state
	 * @return - true if in the final state, false otherwise.
	 */
	private static boolean isFinal(State state)
	{
		return(state.compareTo(State.ID)>=0);
	}//isFinal
	
	/**
	 * Extracts the next token using the finite automaton's driver.
	 * If an invalid token is found, an error message will be displayed
	 */
	public static void getToken()
	{
		int i=driver();
		if(i==0)
		{
			displayln(t + " -- Invalid Token");
		}//if
	}//getToken
	
	/**
	 * Prints a message to both the output file and
	 * the command prompt.
	 * 
	 * @param s - the string to be displayed
	 */
	public static void display(String s)
	{
		outStream.print(s);
		outStream.flush();
		System.out.print(s);
	}//display
	
	/**
	 * Prints a message to both the output file and
	 * the command prompt.
	 * 
	 * @param s - the string to be displayed
	 */
	public static void displayln(String s)
	{
		outStream.flush();
		outStream.println(s);
		System.out.println(s);
	}//displayln
	
	/**
	 * Sets the input and output streams and sets the input character
	 * to be the first character in the input stream.
	 * 
	 * @param inFile - the input file's name
	 * @param outFile - the output file's name
	 */
	public static void setLex(String inFile, String outFile)
	{
		try
		{
			inStream = new BufferedReader(new FileReader(inFile));
			outStream = new PrintWriter(new FileOutputStream(outFile));
			a = inStream.read();
		}//try
		catch(FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}//catch
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}//catch
	}//setLex
	
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
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}//catch
	}//closeIO
	
	/**
	 * 
	 * @param args[] the input/output file names 
	 */
	public static void main(String[] args) 
	{
		int i;
		setLex(args[0],args[1]);
		while(a!=-1)					//While "a" is not the end of the stream
		{
			i=driver();					//Extract the next token
			if(i==1)
			{
				displayln(t + " : " + state.toString());
			}//if
			else if(i==0)
			{
				displayln(t + " -- Invalid Token");
			}//else if
		}//while
	}//main
	
}//class lexicalAnalyzer
