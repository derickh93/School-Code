/*
 * CS316 - Project 2
 * The objective of this project is to implement a lexical analyzer that accepts 
 * the 15 token categories given in Project 1 plus the following seven keywords, 
 * all in lowercase letters only: define, if, cond, else, and, or, not 
 * 
 * @author Tung (Tony) Pham
 */

import java.io.*;
import java.util.*;

public abstract class lexArith {
	
	/**
	 * By enumerating the non-final states first and then the final states,
	 * test for a final state can be done by testing if the state's ordinal 
	 * number is greater than or equal to that of Id.
	 */
	public enum State { 
	  // non-final states     ordinal number

		Start,             // 0
		Period,            // 1
		E,                 // 2
		EPlusMinus,        // 3

	  // final states

		Id,                // 4
		Int,               // 5
		Float,             // 6
		FloatE,            // 7
		Plus,              // 8
		Minus,             // 9
		Times,             // 10
		Div,               // 11
		LParen,            // 12
		RParen,            // 13
		Eq,
		Lt,
		Le,
		Gt,
		Ge,
		Define_D,
		Define_De,
		Define_Def,
		Define_Defi,
		Define_Defin,
		Define,
		If_I,
		If,
		Cond_C,
		Cond_Co,
		Cond_Con,
		Cond,
		Else_E,
		Else_El,
		Else_Els,
		Else,
		And_A,
		And_An,
		And,
		Or_O,
		Or,
		Not_N,
		Not_No,
		Not,
        
		UNDEF
	}

	public static String t; 	// holds an extracted token
	public static State state; 	// the current state of the FA
	private static int a; 		// the current input character
	private static char c; 		// used to convert the variable "a" to 
	                       		// the char type whenever necessary
	
	private static BufferedReader inStream;
	private static PrintWriter outStream;

	/**
	 * Returns the next character on the input stream
	 * @return 	char of next char
	 * 			-1 if error
	 */
	private static int getNextChar() {
		try {
			return inStream.read();
		} catch(IOException e) {
			System.out.println("Error: failed or interrupted I/O operations!");
			System.exit(1);
			return -1;
		}
	}

	/**
	 * Returns the next non-whitespace character on the input stream.
	 * Returns -1, end-of-stream, if the end of the input stream is reached.
	 * 
	 * @return
	 */
	private static int getChar() {
		int i = getNextChar();
		while ( Character.isWhitespace((char) i) )
			i = getNextChar();
		return i;
	}

	/**
	 * This is the driver of the FA. 
	 * If a valid token is found, assigns it to "t" and returns 1.
	 * If an invalid token is found, assigns it to "t" and returns 0.
	 * If end-of-stream is reached without finding any non-whitespace character, returns -1.
	 * 
	 * @return
	 */
	private static int driver() {
		State nextState; // the next state of the FA

		t = "";
		state = State.Start;

		// get the next non-whitespace character
		if ( Character.isWhitespace((char) a) ) a = getChar(); 
		
		// end-of-stream is reached
		if ( a == -1 ) return -1;

		// while "a" is not end-of-stream
		while ( a != -1 ) {
			c = (char) a;
			nextState = nextState( state, c );
			
			// The FA will halt.
			if ( nextState == State.UNDEF ) {
				// valid token extracted
				if ( isFinal(state) )
					return 1; 
				else // "c" is an unexpected character
				{
					t = t+c;
					a = getNextChar();
					return 0; // invalid token found
				}
			}
			// The FA will go on.
			else  {
				state = nextState;
				t = t+c;
				a = getNextChar();
			}
		}

		// end-of-stream is reached while a token is being extracted
		if ( isFinal(state) ) return 1; 	// valid token extracted
		else return 0; 						// invalid token found
	}

	/**
	 * Returns the next state of the FA given the current state and input char;
	 * if the next state is undefined, UNDEF is returned.
	 * 
	 * @param s state
	 * @param c char
	 * @return next state
	 */
	private static State nextState(State s, char c) {
		
		if ( s == State.Start ) {
			if ( Character.isLetter(c) ) {
				if (c == 'd') return State.Define_D;
				else if (c == 'i') return State.If_I;
				else if (c == 'c') return State.Cond_C;
				else if (c == 'e') return State.Else_E;
				else if (c == 'a') return State.And_A;
				else if (c == 'o') return State.Or_O;
				else if (c == 'n') return State.Not_N;
				else return State.Id;
			}
			else if ( Character.isDigit(c) ) return State.Int;
			else if ( c == '+' ) return State.Plus;
			else if ( c == '-' ) return State.Minus;
			else if ( c == '*' ) return State.Times;
			else if ( c == '/' ) return State.Div;
			else if ( c == '(' ) return State.LParen;
			else if ( c == ')' ) return State.RParen;
			else if ( c == '=' ) return State.Eq;
			else if ( c == '<' ) return State.Lt;
			else if ( c == '>' ) return State.Gt;
			else if ( c == '.' ) return State.Period;
			else return State.UNDEF;
		}
		else if ( s == State.Lt ) {
			if ( c == '=' ) return State.Le;
			else return State.UNDEF;
		} 
		else if ( s == State.Gt ) {
			if ( c == '=' ) return State.Ge;
			else return State.UNDEF;
		}
		else if (s == State.Plus ) {
			if ( c == '.' ) return State.Period;
			else  if ( Character.isDigit(c)) return State.Int;
			else return State.UNDEF;
		}
		else if (s == State.Minus) {
			if ( c == '.' ) return State.Period;
			else  if ( Character.isDigit(c)) return State.Int;
			else return State.UNDEF;
		}
		else if ( s == State.Id ) {
			if ( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.UNDEF;
		} 
		else if ( s == State.Int ) {
			if ( Character.isDigit(c) ) return State.Int;
			else if ( c == '.' ) return State.Period;
			else return State.UNDEF;
		}
		else if ( s == State.Period ) {
			if ( Character.isDigit(c) ) return State.Float;
			else return State.UNDEF;
		}
		else if ( s == State.Float ) {
			if ( Character.isDigit(c) ) return State.Float;
			else if ( c == 'e' || c == 'E' ) return State.E;
			else return State.UNDEF;
		}
		else if ( s == State.E ) {
			if ( Character.isDigit(c) ) return State.FloatE;
			else if ( c == '+' || c == '-' ) return State.EPlusMinus;
			else return State.UNDEF;
		}
		else if ( s == State.EPlusMinus ) {
			if ( Character.isDigit(c) ) return State.FloatE;
			else return State.UNDEF;
		}
		else if ( s == State.FloatE ) {
			if ( Character.isDigit(c) ) return State.FloatE;
			else return State.UNDEF;
		}
		else if ( s == State.Define_D ) {
			if ( c == 'e') return State.Define_De;
			else if( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.Id;
		}
		else if ( s == State.Define_De ) {
			if ( c == 'f') return State.Define_Def;
			else if( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.Id;
		}
		else if ( s == State.Define_Def ) {
			if ( c == 'i') return State.Define_Defi;
			else if( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.Id;
		}
		else if ( s == State.Define_Defi ) {
			if ( c == 'n') return State.Define_Defin;
			else if( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.Id;
		}
		else if ( s == State.Define_Defin ) {
			if ( c == 'e') return State.Define;
			else if( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.Id;
		}
		else if ( s == State.Define) {
			if( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.UNDEF;
		}
		else if ( s == State.If_I ) {
			if ( c == 'f' ) return State.If;
			else if( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.Id;
		}
		else if ( s == State.Cond_C ) {
			if ( c == 'o' ) return State.Cond_Co;
			else if( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.Id;
		}
		else if ( s == State.Cond_Con ) {
			if ( c == 'n' ) return State.Cond_Con;
			else if( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.Id;
		}
		else if ( s == State.Else_E ) {
			if ( c == 'l' ) return State.Else_El;
			else if( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.Id;
		} else if ( s == State.Else_El ) {
			if ( c == 's' ) return State.Else_Els;
			else if( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.Id;
		} else if ( s == State.Else_Els ) {
			if ( c == 'e' ) return State.Else;
			else if( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.Id;
		}
		else if ( s == State.And_A ) {
			if ( c == 'n' ) return State.And_An;
			else if( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.Id;
		} else if ( s == State.And_An ) {
			if ( c == 'd' ) return State.And;
			else return State.Id;
		}
		else if ( s == State.Or_O ) {
			if ( c == 'r' ) return State.Or;
			else if( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.Id;
		}
		else if ( s == State.Not_N ) {
			if ( c == 'o' ) return State.Not_No;
			else if( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.UNDEF;
		} else if ( s == State.Not_No ) {
			if ( c == 't' ) return State.Not;
			else if( Character.isLetterOrDigit(c) ) return State.Id;
			else return State.Id;
		}
		else
			return State.UNDEF;
	}

	/**
	 * Check if the state is final state or not
	 * @param state state to check
	 * @return true/false
	 */
	private static boolean isFinal(State state) {
		return ( state.compareTo(State.Id) >= 0 );  
	}
	
	/**
	 * Extract the next token using the driver of the FA.
	 * If an invalid token is found, issue an error message.
	 */
	public static void getToken() {
		int i = driver();
		if ( i == 0 )
			displayln(t + "  -- Invalid Token");
	}

	/**
	 * Display to file
	 * @param s String line
	 */
	public static void display(String s) {
		outStream.print(s);
	}

	/**
	 * Display to file with new line
	 * @param s String line
	 */
	public static void displayln(String s) {
		outStream.println(s);
	}

	/**
	 * Sets the input and output streams to "inFile" and "outFile",respectively.
	 * Also sets the current input character "a" to the first character on
	 * the input stream.
	 * 
	 * @param inFile file to read
	 * @param outFile file to write
	 */
	public static void setLex(String inFile, String outFile) {
		try {
			inStream = new BufferedReader( new FileReader(inFile) );
			outStream = new PrintWriter( new FileOutputStream(outFile) );
			a = inStream.read();
		} catch(FileNotFoundException e) {
			System.out.println("Error: no file by that name found!");
			System.exit(1);
		} catch(IOException e) {
			System.out.println("Error: failed or interrupted I/O operations!");
			System.exit(1);
		}
	}

	/**
	 * Closes the I/O
	 */
	public static void closeIO() {
		try {
			inStream.close();
			outStream.close();
		} catch(IOException e) {
			System.out.println("Error: failed or interrupted I/O operations!");
			System.exit(1);
		}
	}

	/**
	 * Main method which will ask for what file to read and write to
	 * @param argv parameter from command line
	 */
	public static void main(String[] argv) {
		
		if(argv.length == 0) {
			String ipf, opf;
			Scanner in = new Scanner(System.in);
			System.out.print("Enter an input file: ");
	        ipf = in.nextLine(); 
	        System.out.print("Enter an output file: ");
	        opf = in.nextLine();
	        in.close();
	        setLex( ipf, opf );
		} else if(argv.length == 2) {
			setLex( argv[0], argv[1] );
		} else if(argv.length > 2) {
			final String syntax = "Usage: lexArith [inputFile] [outputFile]\n";
			System.out.println(syntax);
			return;			
		}
		
		int i;
		
		// while "a" is not end-of-stream
		while ( a != -1 ) {
			i = driver(); // extract the next token
			if ( i == 1 )
				displayln( t+"   : "+state.toString() );
			else if ( i == 0 )
				displayln( t+"  -- Invalid Token" );
		} 

		closeIO();
	}
	
} 
