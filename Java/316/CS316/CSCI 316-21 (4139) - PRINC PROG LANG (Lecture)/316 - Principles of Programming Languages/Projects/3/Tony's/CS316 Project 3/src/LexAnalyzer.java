/*
 * CS316 - Project 3
 * -----------------------------------------------------------------------------
 * The objective of this project is to implement a top down parser. This
 * program will read any text file that contains (what is intended to be) 
 * a string in the category <fun defs>. It will then display the parse tree 
 * in linearly indented form: each syntactic category name labeling a node 
 * in the parse tree is displayed on a separate line, prefixed with the integer
 *  i representing the node's depth and indented by i blanks. Note that 
 *  the displayed parse tree records the expansion and contraction of the 
 *  runtime stack caused by the parsing functions' calls, and the times 
 *  the runtime stack reaches the peak size can be spotted.  
 * 
 * @author Tung (Tony) Pham
 * @credit Reused LexAnalyzer give by Prof. Yukawa's sample
 */

import java.io.*;
import java.util.Scanner;

abstract class LexAnalyzer
{
	public enum State 
       	{ 
	  // final states     ordinal number  token accepted 

		Add,             // 0          +
		Sub,             // 1          -
		Mul,             // 2          *
		Div,             // 3          /
		Lt,              // 4          <
		Le,              // 5          <=
		Gt,              // 6          >
		Ge,              // 7          >=
		Eq,              // 8          =
		Id,              // 9          identifiers
		Int,             // 10         integers
		Float,           // 11         floats without exponentiation part
		FloatE,          // 12         floats with exponentiation part
		LParen,          // 13         (
		RParen,          // 14         )

	  // non-final states              string recognized    

		Start,           // 15      the empty string
		Period,          // 16      ".", "+.", "-."
		E,               // 17      float parts ending with E or e
		EPlusMinus,      // 18      float parts ending with + or - in exponentiation part

	  // keyword states

		Define,		 // 19
		If,		 // 20
		Cond,		 // 21
		Else,		 // 22
		And,		 // 23
		Or,		 // 24
		Not,		 // 25

		UNDEF
	}

	// By enumerating the final states first and then the non-final states,
	// test for a final state can be done by testing if the state's ordinal number
	// is less than or equal to that of RParen.

	public static String t; // holds an extracted token
	public static State state; // the current state of the FA
	private static int a; // the current input character
	private static char c; // used to convert the variable "a" to 
	                       // the char type whenever necessary

	private static State nextState[][] = new State[19][128];
 
          // This array implements the state transition function
          // State x (ASCII char set) --> State.
          // The state argument is converted to its ordinal number used as
          // the first array index from 0 through 18. 

	private static String define_ = "define";
	private static String if_     = "if";
	private static String cond_   = "cond";
	private static String else_   = "else";
	private static String and_    = "and";
	private static String or_     = "or";
	private static String not_    = "not";

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
		for (int s = 0; s <= 18; s++ )
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
			nextState[State.Add       .ordinal()][d] = State.Int;
			nextState[State.Sub       .ordinal()][d] = State.Int;
			nextState[State.Period    .ordinal()][d] = State.Float;
			nextState[State.Float     .ordinal()][d] = State.Float;
			nextState[State.E         .ordinal()][d] = State.FloatE;
			nextState[State.EPlusMinus.ordinal()][d] = State.FloatE;
			nextState[State.FloatE    .ordinal()][d] = State.FloatE;
		}

		nextState[State.Start.ordinal()]['+'] = State.Add;
		nextState[State.Start.ordinal()]['-'] = State.Sub;
		nextState[State.Start.ordinal()]['*'] = State.Mul;
		nextState[State.Start.ordinal()]['/'] = State.Div;
		nextState[State.Start.ordinal()]['<'] = State.Lt;
		nextState[State.Start.ordinal()]['>'] = State.Gt;
		nextState[State.Start.ordinal()]['='] = State.Eq;
		nextState[State.Start.ordinal()]['('] = State.LParen;
		nextState[State.Start.ordinal()][')'] = State.RParen;
		nextState[State.Start.ordinal()]['.'] = State.Period;

		nextState[State.Lt       .ordinal()]['='] = State.Le;
		nextState[State.Gt       .ordinal()]['='] = State.Ge;
		
		nextState[State.Add.ordinal()]['.'] = State.Period;
		nextState[State.Sub.ordinal()]['.'] = State.Period;
		nextState[State.Int.ordinal()]['.'] = State.Float;
			
		nextState[State.Float.ordinal()]['E'] = state.E;
		nextState[State.Float.ordinal()]['e'] = state.E;
			
		nextState[State.E.ordinal()]['+'] = State.EPlusMinus;
		nextState[State.E.ordinal()]['-'] = State.EPlusMinus;

	} // end setNextState

	private static boolean isFinal(State state)
	{
		return ( state.compareTo(State.RParen) <= 0 );  
	}
	
	private static void keywordCheck()
	{
		if ( t.equals(define_) )
			state = State.Define;
		else if ( t.equals(if_) )
			state = State.If;
		else if ( t.equals(cond_) )
			state = State.Cond;
		else if ( t.equals(else_) )
			state = State.Else;
		else if ( t.equals(and_) )
			state = State.And;
		else if ( t.equals(or_) )
			state = State.Or;
		else if ( t.equals(not_) )
			state = State.Not;
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
        
        public static void setParser(String inFile, String outFile)

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
	} // end setIO
        
        
        //<fun defs> → {<fun def>}+
        public static void fun_defs(String indent) {
            displayln(indent+indent.length()+" <fun defs>");
            getToken();
            fun_def(indent+" ");
            while(state == State.LParen) {
                fun_def(indent+" ");
            }
            
        }
        //<fun def> → "(" "define" <fun header> <exp> ")"
        public static void fun_def(String indent) {
            displayln(indent+indent.length()+" <fun def>");
            if( state == State.LParen ) {
                getToken();
                if(state == State.Define) {
                    getToken();
                    fun_header(indent+" ");
                    exp(indent+" ");
                    if(state == State.RParen) {
                        getToken();
                    } else {
                        displayln(t+": unexpected symbol where ) expected");
                        displayln(t+"  -- unexpected symbol");
                    }
                } else {
                    displayln(t+": unexpected symbol where define expected");
                    displayln(t+"  -- unexpected symbol");
                }
            } else {
                displayln(t+": unexpected symbol where ( expected");
                displayln(t+"  -- unexpected symbol");
            }
        }
        //<fun header> → "(" <fun name> <parameter list> ")"
        public static void fun_header(String indent) {
            displayln(indent+indent.length()+" <fun header>");
            if( state == State.LParen) {
                getToken();
                fun_name(indent+" ");
                parameter_list(indent+" ");
                if(state == State.RParen) {
                    getToken();
                } else {
                    displayln(t+": unexpected symbol where ) expected");
                    displayln(t+"  -- unexpected symbol");
                }
            } else {
                displayln(t+": unexpected symbol where ( expected");
                displayln(t+"  -- unexpected symbol");
            }
        }
        //<fun name> → <id>
        public static void fun_name(String indent) {
            display(indent+indent.length()+" <fun name>");
            if(state == State.Id) {
                displayln(t);
                getToken();
            } else {
                displayln(t+": unexpected symbol where Id expected");
                displayln(t+"  -- unexpected symbol");
            }
        }
        //<parameter list> → {<id>}
        public static void parameter_list(String indent) {
            display(indent+indent.length()+" <parameter list> ");
            while(state == State.Id) {
                display(t+" ");
                getToken();
            }
            displayln("");
        }
        //<exp> → <id> | <int> | <float> | <floatE> | <list exp>
        public static void exp(String indent) {
            display(indent+indent.length()+" <exp> ");
            if(state == State.Id || state == State.Int || state == State.Float || state == State.FloatE) {
                displayln(t);
                getToken();
            } else if(state == State.LParen) {
                displayln("");
                getToken();
                list_exp(indent+" ");
            } else {
                displayln(t+": unexpected symbol where Id, Int, Float, FloatE, ( expected");
                displayln(t+"  -- unexpected symbol");
            }
        }
        //<list exp> → "(" <list exp inside> ")"
        public static void list_exp(String indent) {
            displayln(indent+indent.length()+" <list exp>");
            list_exp_inside(indent+" ");
            if(state == State.RParen) {
                getToken();
            } else {
                displayln(t+": unexpected symbol where ) expected");
                displayln(t+"  -- unexpected symbol");
            }
        }
        //<list exp inside> → "if" <exp> <exp> <exp> | "cond" <cases> | <fun op> { <exp> }
        public static void list_exp_inside(String indent) {
            displayln(indent+indent.length()+" <list exp inside>");
            if(state == State.If) {
                displayln(indent+" "+(indent.length()+1)+" if");
                getToken();
                exp(indent+" ");
                exp(indent+" ");
                exp(indent+" ");
            } else if(state == State.Cond) {
                displayln(indent+" "+(indent.length()+1)+" cond");
                getToken();
                cases(indent+" ");
            } else {
                fun_op(indent+" ");
                while(state == State.Id || state == State.Int || state == State.Float || state == State.FloatE || state == State.LParen) {
                    exp(indent+" ");
                }
            }
        }
        //<cases> → {<case exp>}+
        public static void cases(String indent) {
            displayln(indent+indent.length()+" <cases>");
            case_exp(indent+" ");
            while(state == State.LParen) {
                case_exp(indent+" ");
            }
        }
        //<case exp> → "(" ("else" | <exp>) <exp> ")"
        public static void case_exp(String indent) {
            displayln(indent+indent.length()+" <case exp>");
            if(state == State.LParen) {
                getToken();
                if(state == State.Else) {
                    displayln("else");
                    getToken();
                } else {
                    exp(indent+" ");
                }
                exp(indent+" ");
            } else {
                displayln(t+": unexpected symbol where ( expected");
                displayln(t+"  -- unexpected symbol");
            }
        }
        //<fun op> → <id> | <arith op> | <bool op> | <comp op>
        //<arith op> → + | − | * | /
        //<bool op> → "and" | "or" | "not"
        //<comp op> → "<" | "<=" | ">" | ">=" | "="
        public static void fun_op(String indent) {
            if(state == State.Id || state == State.Add || state == State.Sub || state == State.Mul || state == state.Div
                    || state == State.And || state == State.Or || state == State.Not || state == State.Lt || state == State.Le
                    || state == State.Gt || state == State.Ge || state == State.Eq) {
                displayln(indent+indent.length()+" "+t);
                getToken();
            } else {
                displayln(t+": unexpected symbol where Id, arith op, bool op or comp op expected");
                displayln(t+"  -- unexpected symbol"); 
            }
        }    
        
    	public static void main(String argv[])

    	// The input/output file names must be passed as argv[0] and argv[1].

    	{
    		int i;
    		if(argv.length == 0) {
            	String ipf, opf;
            	Scanner in = new Scanner(System.in);
            	System.out.print("Enter an input file: ");
                ipf = in.nextLine(); 
                System.out.print("Enter an output file: ");
                opf = in.nextLine();
                in.close();
                setParser( ipf, opf );
            } else if(argv.length == 2) {
            	setParser( argv[0], argv[1] );
            } else if(argv.length > 2) {
            	final String syntax = "Usage: lexArith [inputFile] [outputFile]\n";
            	System.out.println(syntax);
            	return;			
            }
            fun_defs("");
    		closeIO();
    	} // end main
} 
