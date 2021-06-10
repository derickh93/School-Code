
/**Queens College of the City University of New York
 * Name: Soham Thakor
 * Project 2: Implementation of Lexical Analyzer
 * Date: 03/27/11
 * Resource: lexArithArray.java from Prof. YUKAWA
 * @author soham
 
Grammer for this Lexical Analyzer:

<letter> → a | b | ... | z | A | B | ... | Z 
<digit> → 0 | 1 | ... | 9 
<int> → {<digit>}+ 
<id> → <letter> { <letter> | <digit> } 
<float> → {<digit>}+ "." {<digit>}  |  "." {<digit>}+ 
<floatE> → <float> (E|e) [+|−] {<digit>}+ 
<add> → + 
<sub> → − 
<mul> → * 
<div> → / 
<incr> → "++" 
<decr> → "−−" 
<or> → "||" 
<and> → "&&" 
<inv> → ! 
<lt> → "<" 
<le> → "<=" 
<gt> → ">" 
<ge> → ">=" 
<eq> → "==" 
<neq> → "!=" 
<assign> → = 
<LParen> → "(" 
<RParen> → ")" 
<LBrace> → "{" 
<RBrace> → "}" 
<semicolon> → ; 

This class implements a DFA that will accept the above tokens.
The DFA has 25 final states represented by enum-type literals:

state     token accepted

Id        identifiers
Int       integers
Float     floats without exponentiation part
FloatE    floats with exponentiation part
Add       +
Incr      ++
Sub       -
Decr      --
Mul       *
Div       /
LParen    (
RParen    )
LBrace    {
RBrace    }
Semicolon ;
Or        ||
And       &&
Inv       !
Neq       !=
Assign    =
Eq        ==
Lt        <
Le        <=
Gt        >
Ge        >=

The DFA also uses 6 non-final states:

state      string recognized

Start      the empty string
Period     float parts ending with "."
E          float parts ending with E or e
EPlusMinus float parts ending with + or - in exponentiation part
Bar        one "|" towards Or final state
Ampersand  one "&" towards And final state

The states are represented by an Enum type called "State".
The function "driver" is the driver to operate the DFA. 
The array "nextState" returns the next state given
the current state and the input character.

To modify this lexical analyzer to recognize a different token set,
the array "nextState", the functions "isFinal" and "setNextState", and 
the enum type "State" need to be modified;
the function "driver" and the other utility functions remain the same.
 
 */

import java.io.*;

public abstract class LexicalAnalyzerEC {
	
	public enum State{
		// non-final states    ordinal number
		START,					// 0
		PERIOD,					// 1
		E,						// 2
		EPLUSMINUS,				// 3
		BAR,					// 4
		AMPERSAND,				// 5
		
		
		// final states
		ID,						// 6
		INT,					// 7
		FLOAT,					// 8
		FLOATE,					// 9
		ADD,					// 10
		SUB,					// 11
		MUL,					// 12
		DIV,					// 13
		LPAREN,					// 14
		RPAREN,					// 15
		LBRACE,					// 16
		RBRACE,					// 17
		SEMICOLON,				// 18
		OR,						// 19
		AND,					// 20
		INV,                    // 21
		NEQ,					// 22
		ASSIGN,					// 23
		EQ,						// 24
		LT,						// 25
		LE,						// 26
		GT,						// 27
		GE,						// 28
		INCR,					// 29
		DECR,					// 30
		IF,						// 31
		ELSE,					// 32
		WHILE,					// 33
		DO,						// 34
		FALSE,					// 35
		TRUE,					// 36
			
		ID_I,								
		ID_E,ID_L,ID_EL,ID_S,ID_ELS,        
		ID_W,ID_H,ID_WH,ID_WHI,ID_WHIL,		
		ID_D,
		ID_T,ID_R,ID_TR,ID_U,ID_TRU,
		ID_F,ID_A,ID_FA,ID_FAL,ID_FALS,
		
		UNDEF
	}
	
	/*By enumerating the non-final states first and then the final states,
      test for a final state can be done by testing if the state's ordinal number,
      is greater than or equal to that of ID. */
	
	public static String t; // holds an extracted token
	public static State state; // the current state of the FA.
	
	private static int a; // the current input character
	private static char c; // used to convert the variable "a" to the char type whenever necessary
	
	private static State nextState[][] = new State[60][128];
	
	/*This array implements the state transition function
	 * State x (ASCII char set) --> State. 
	 * The state argument is converted to its ordinal number used as 
	 * the first array index from 0 through 36.*/
	
	private static BufferedReader inStream;
	private static PrintWriter outStream;
	
	private static int getNextChar(){
		// Returns the next character on the input stream.
		try{
			return inStream.read();
		}
		catch(IOException e){
			e.printStackTrace();
			return -1;
		}
	}// end getNextChar
	
	private static int getChar(){
		//Returns the next non-whitespace character on the input stream.
		//Returns -1, end-of-stream, if the end the input stream is reached.
		int i = getNextChar();
		while(Character.isWhitespace((char)i))
			i = getNextChar();
			return i;
	}// end getChar
	
	private static int driver(){
		/*This is the driver of the FA.
		 * If a valid token is found, assigns it to "t" and returns 1.
		 * If an invalid token is found, assigns it to "t" and returns 0.
		 * If end-of-stream is reached without finding any non-whitespace character, return -1
		 * */
		
		State nextSt; // the next state of the FA.
		t = "";
		state = State.START;
		
		if(Character.isWhitespace((char)a)) 
			a = getChar();	// get the next non-whitespace character
		if(a == -1)
			return -1; // end-of-stream is reached.
		
		while (a != -1){  // do the body id 'a' is not end-of-stream
			c = (char)a;
			nextSt = nextState[state.ordinal()][a];
			if (nextSt == State.UNDEF){ // The FA will halt.

				if(isFinal(state))
					return 1; // valid token extracted
				else // 'c' is an unexpected character
					{
						t=t+c;
						a = getNextChar();
						return 0;
					}
			}
			else // The FA will go on.
				{
					state = nextSt;
					t = t+c;
					a = getNextChar();
				}
			}
		
			//end-of-stream is reached while a token is being extracted
			
			if(isFinal(state))
				return 1;   // valid token extracted
			else return 0;  // invalid token found
	}

	private static boolean isFinal(State state) {
		
		return (state.compareTo(State.ID) >= 0);
	}
	
	public static void getToken()

	// Extract the next token using the driver of the FA.
	// If an invalid token is found, issue an error message.

	{
		int i = driver();
		if ( i == 0 )
			displayln(t+"      :   Invalid Token");
	} // end getToken


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

	private static void setNextState() {
		
		for(int s = 0; s < 60; s++){
			for(int c = 0; c < 128; c++){
				nextState[s][c] = State.UNDEF;
			}
		}
		
		for(char c = 'A'; c <= 'Z'; c++){
			nextState[State.START.ordinal()][c] = State.ID;
			nextState[State.ID   .ordinal()][c] = State.ID;
		}
		
		for(char c = 'a'; c <= 'z'; c++){
			nextState[State.START.ordinal()][c] = State.ID;
			nextState[State.ID   .ordinal()][c] = State.ID;
		}
		
		for (char d = '0'; d <= '9'; d++)
		{
			nextState[State.START     .ordinal()][d] = State.INT;
			nextState[State.ID        .ordinal()][d] = State.ID;
			nextState[State.INT       .ordinal()][d] = State.INT;
			nextState[State.PERIOD    .ordinal()][d] = State.FLOAT;
			nextState[State.FLOAT     .ordinal()][d] = State.FLOAT;
			nextState[State.E         .ordinal()][d] = State.FLOATE;
			nextState[State.EPLUSMINUS.ordinal()][d] = State.FLOATE;
			nextState[State.FLOATE    .ordinal()][d] = State.FLOATE;
			
			nextState[State.IF		.ordinal()][d] = State.ID;
			nextState[State.ID_I	.ordinal()][d] = State.ID;
			nextState[State.DO		.ordinal()][d] = State.ID;
			nextState[State.ID_D	.ordinal()][d] = State.ID;
			nextState[State.ELSE	.ordinal()][d] = State.ID;
			nextState[State.ID_ELS	.ordinal()][d] = State.ID;
			nextState[State.ID_EL	.ordinal()][d] = State.ID;
			nextState[State.ID_E	.ordinal()][d] = State.ID;
			nextState[State.WHILE	.ordinal()][d] = State.ID;
			nextState[State.ID_WHIL	.ordinal()][d] = State.ID;
			nextState[State.ID_WHI	.ordinal()][d] = State.ID;
			nextState[State.ID_WH	.ordinal()][d] = State.ID;
			nextState[State.ID_W	.ordinal()][d] = State.ID;
			nextState[State.TRUE	.ordinal()][d] = State.ID;
			nextState[State.ID_TRU	.ordinal()][d] = State.ID;
			nextState[State.ID_TR	.ordinal()][d] = State.ID;
			nextState[State.ID_T	.ordinal()][d] = State.ID;
			nextState[State.FALSE	.ordinal()][d] = State.ID;
			nextState[State.ID_FALS	.ordinal()][d] = State.ID;
			nextState[State.ID_FAL	.ordinal()][d] = State.ID;
			nextState[State.ID_FA	.ordinal()][d] = State.ID;
			nextState[State.ID_F	.ordinal()][d] = State.ID;
		}

		nextState[State.START.ordinal()]['+'] = State.ADD;
		nextState[State.START.ordinal()]['-'] = State.SUB;
		nextState[State.START.ordinal()]['*'] = State.MUL;
		nextState[State.START.ordinal()]['/'] = State.DIV;
		nextState[State.START.ordinal()]['('] = State.LPAREN;
		nextState[State.START.ordinal()][')'] = State.RPAREN;
		nextState[State.START.ordinal()]['{'] = State.LBRACE;
		nextState[State.START.ordinal()]['}'] = State.RBRACE;
		nextState[State.START.ordinal()][';'] = State.SEMICOLON;
		nextState[State.START.ordinal()]['!'] = State.INV;
		nextState[State.START.ordinal()]['='] = State.ASSIGN;
		nextState[State.START.ordinal()]['<'] = State.LT;
		nextState[State.START.ordinal()]['>'] = State.GT;
		nextState[State.START.ordinal()]['.'] = State.PERIOD;
		nextState[State.START.ordinal()]['|'] = State.BAR;
		nextState[State.START.ordinal()]['&'] = State.AMPERSAND;
		
		nextState[State.START.ordinal()]['i'] = State.ID_I;
		nextState[State.ID_I.ordinal()]['f'] = State.IF;
		
		nextState[State.START.ordinal()]['d'] = State.ID_D;
		nextState[State.ID_D.ordinal()]['o'] = State.DO;
		
		nextState[State.START.ordinal()]['e'] = State.ID_E;
		nextState[State.ID_E.ordinal()]['l'] = State.ID_EL;
		nextState[State.ID_EL.ordinal()]['s'] = State.ID_ELS;
		nextState[State.ID_ELS.ordinal()]['e'] = State.ELSE;
		
		nextState[State.START.ordinal()]['w'] = State.ID_W;
		nextState[State.ID_W.ordinal()]['h'] = State.ID_WH;
		nextState[State.ID_WH.ordinal()]['i'] = State.ID_WHI;
		nextState[State.ID_WHI.ordinal()]['l'] = State.ID_WHIL;
		nextState[State.ID_WHIL.ordinal()]['e'] = State.WHILE;
		
		nextState[State.START.ordinal()]['t'] = State.ID_T;
		nextState[State.ID_T.ordinal()]['r'] = State.ID_TR;
		nextState[State.ID_TR.ordinal()]['u'] = State.ID_TRU;
		nextState[State.ID_TRU.ordinal()]['e'] = State.TRUE;
		
		nextState[State.START.ordinal()]['f'] = State.ID_F;
		nextState[State.ID_F.ordinal()]['a'] = State.ID_FA;
		nextState[State.ID_FA.ordinal()]['l'] = State.ID_FAL;
		nextState[State.ID_FAL.ordinal()]['s'] = State.ID_FALS;
		nextState[State.ID_FALS.ordinal()]['e'] = State.FALSE;
		
		for(char c = 'A'; c <= 'Z'; c++){
			nextState[State.IF		.ordinal()][c] = State.ID;
		    nextState[State.ID_I	.ordinal()][c] = State.ID;
			nextState[State.DO		.ordinal()][c] = State.ID;
			nextState[State.ID_D	.ordinal()][c] = State.ID;
			nextState[State.ELSE	.ordinal()][c] = State.ID;
			nextState[State.ID_ELS	.ordinal()][c] = State.ID;
			nextState[State.ID_EL	.ordinal()][c] = State.ID;
			nextState[State.ID_E	.ordinal()][c] = State.ID;
			nextState[State.WHILE	.ordinal()][c] = State.ID;
			nextState[State.ID_WHIL	.ordinal()][c] = State.ID;
			nextState[State.ID_WHI	.ordinal()][c] = State.ID;
			nextState[State.ID_WH	.ordinal()][c] = State.ID;
			nextState[State.ID_W	.ordinal()][c] = State.ID;
			nextState[State.FALSE	.ordinal()][c] = State.ID;
			nextState[State.ID_FALS	.ordinal()][c] = State.ID;
			nextState[State.ID_FAL	.ordinal()][c] = State.ID;
			nextState[State.ID_FA	.ordinal()][c] = State.ID;
			nextState[State.ID_F	.ordinal()][c] = State.ID;
		}
		
		for(char c = 'a'; c <= 'z'; c++){
			nextState[State.IF.ordinal()][c] = State.ID;
			if(c != 'f')
				nextState[State.ID_I.ordinal()][c] = State.ID;
	
			nextState[State.DO.ordinal()][c] = State.ID;
			if(c != 'o')
				nextState[State.ID_D.ordinal()][c] = State.ID;

			nextState[State.ELSE.ordinal()][c] = State.ID;
			if(c != 'e')
				nextState[State.ID_ELS.ordinal()][c] = State.ID;
			if(c != 's')
				nextState[State.ID_EL.ordinal()][c] = State.ID;
			if(c != 'l')
				nextState[State.ID_E.ordinal()][c] = State.ID;
	
			nextState[State.WHILE.ordinal()][c] = State.ID;
			if(c != 'e')
				nextState[State.ID_WHIL.ordinal()][c] = State.ID;
			if(c != 'l')
				nextState[State.ID_WHI.ordinal()][c] = State.ID;
			if(c != 'i')
				nextState[State.ID_WH.ordinal()][c] = State.ID;
			if(c != 'h')
				nextState[State.ID_W.ordinal()][c] = State.ID;
	
			nextState[State.TRUE.ordinal()][c] = State.ID;
			if(c != 'e')
				nextState[State.ID_TRU.ordinal()][c] = State.ID;
			if(c != 'u')
				nextState[State.ID_TR.ordinal()][c] = State.ID;
			if(c != 'r')
				nextState[State.ID_T.ordinal()][c] = State.ID;
	
			nextState[State.FALSE.ordinal()][c] = State.ID;
			if(c != 'e')
				nextState[State.ID_FALS.ordinal()][c] = State.ID;
			if(c != 's')
				nextState[State.ID_FAL.ordinal()][c] = State.ID;
			if(c != 'l')
				nextState[State.ID_FA.ordinal()][c] = State.ID;
			if(c != 'a')
				nextState[State.ID_F.ordinal()][c] = State.ID;
		}

		nextState[State.INT.ordinal()]['.'] = State.FLOAT;
			
		nextState[State.FLOAT.ordinal()]['E'] = State.E;
		nextState[State.FLOAT.ordinal()]['e'] = State.E;
			
		nextState[State.E.ordinal()]['+'] = State.EPLUSMINUS;
		nextState[State.E.ordinal()]['-'] = State.EPLUSMINUS;
		
		nextState[State.ADD.ordinal()]['+'] = State.INCR;
		nextState[State.SUB.ordinal()]['-'] = State.DECR;
		
		nextState[State.BAR.ordinal()]['|'] = State.OR;
		nextState[State.AMPERSAND.ordinal()]['&'] = State.AND;
		
		nextState[State.INV.ordinal()]['='] = State.NEQ;
		nextState[State.ASSIGN.ordinal()]['='] = State.EQ;
		nextState[State.LT.ordinal()]['='] = State.LE;
		nextState[State.GT.ordinal()]['='] = State.GE;
	
	}

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
	
	public static void display(String s)
	{
		outStream.print(s);
	}

	public static void displayln(String s)
	{
		outStream.println(s);
	}


	public static void main(String argv[])

	// The input/output file names must be passed as argv[0] and argv[1].

	{
		int i;

		setLex( argv[0], argv[1] );

		while ( a != -1 ) // while "a" is not end-of-stream
		{
			i = driver(); // extract the next token
			if ( i == 1 )
				displayln( t+"     :   "+state.toString() );
			else if ( i == 0 )
				displayln( t+"     :   Invalid Token" );
		} 

		for(int x = 0; x<37; x++){
			for(int y=0;y<128;y++){
			if(nextState[x][y] != State.UNDEF)
				System.out.print(nextState[x][y] + " ");
			else System.out.print("  ");
			}
			System.out.println();
			
		}
		closeIO();
	} // end main

}