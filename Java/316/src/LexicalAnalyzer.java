
/**
 * Author: Himanshu Satwick
 * Class: CS 316
 * Ins: Prof Yukawa
 * Project: Constructing a lexical analyzer of the given DFA.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class LexicalAnalyzer {

	public enum State {
		// non-final states ordinal number

		Start, // 0
		Period, // 1
		E, // 2
		EPlusMinus, // 3
		bar,//4
		ampersand,//5

		// final states

		Id, // 6
		id_i,//7
		id_in,//8
		id_f,//9
		id_fl,//10
		id_flo,//11
		id_floa,//12
		id_w,//13
		id_wh,//14
		id_whi,//15
		id_whil,//16
		id_e,//17
		id_el,//18
		id_els,//19
		id_d,//20
		id_b,//21
		id_bo,//22
		id_boo,//23
		id_bool,//24
		id_boole,//25
		id_boolea,//26
		id_fa,//27
		id_fal,//28
		id_fals,//29
		id_t,//30
		id_tr,//31
		id_tru,//32
		Keyword_if,//33
		Keyword_else,//34
		Keyword_while,//35
		Keyword_boolean,//36
		Keyword_do,//37
		Keyword_float,//38
		Keyword_int,//39
		Semicolon,//40
		Assign,//41
		LBrace,//42
		RBrace,//43
		Eq,//44
		inv,//45
		neq,//46
		Le,//47
		Lt,//48
		Ge,//49
		Gt,//50
		Incr,//51
		Decr,//52
		Comma,//53
		Or,//54
		And,//55
		Keyword_true,//56
		Keyword_false,//57
		Int, // 58
		Float, //59
		FloatE, //60
		Add, //61
		Sub, //62
		Mul, //63
		Div, //64
		LParen, //65
		RParen, //66

		UNDEF
	}

	// By enumerating the non-final states first and then the final states,
	// test for a final state can be done by testing if the state's ordinal
	// number
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
		try {
			return inStream.read();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	} // end getNextChar

	private static int getChar()

	// Returns the next non-whitespace character on the input stream.
	// Returns -1, end-of-stream, if the end of the input stream is reached.

	{
		int i = getNextChar();
		while (Character.isWhitespace((char) i))
			i = getNextChar();
		return i;
	} // end getChar

	private static int driver()

	// This is the driver of the FA.
	// If a valid token is found, assigns it to "t" and returns 1.
	// If an invalid token is found, assigns it to "t" and returns 0.
	// If end-of-stream is reached without finding any non-whitespace character,
	// returns -1.

	{
		State nextState; // the next state of the FA

		t = "";
		state = State.Start;

		if (Character.isWhitespace((char) a))
			a = getChar(); // get the next non-whitespace character
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
				else // "c" is an unexpected character
				{
					t = t + c;
					a = getNextChar();
					return 0; // invalid token found
				}
			} else // The FA will go on.
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

	private static State nextState(State s, char c)

	// Returns the next state of the FA given the current state and input char;
	// if the next state is undefined, UNDEF is returned. If the next state is final
	//them the state name is returned.

	{
		switch (state) {
		case Start:
			if(c == 'i')
				return State.id_i;
			else if(c == 't')
				return State.id_t;
			else if(c == 'b')
				return State.id_b;
			else if(c == 'd')
				return State.id_d;
			else if(c == 'e')
				return State.id_e;
			else if(c == 'f')
				return State.id_f;
			else if(c == 'w')
				return State.id_w;
			else if (Character.isLetter(c))
				return State.Id;
			else if (Character.isDigit(c))
				return State.Int;
			else if (c == '+')
				return State.Add;
			else if (c == '-')
				return State.Sub;
			else if (c == '*')
				return State.Mul;
			else if (c == '/')
				return State.Div;
			else if (c == '(')
				return State.LParen;
			else if (c == ')')
				return State.RParen;
			else if(c == '.')
				return State.Period;
			else if(c == '{')
				return State.LBrace;
			else if(c == '}')
				return State.RBrace;
			else if(c == ',')
				return State.Comma;
			else if(c == ';')
				return State.Semicolon;
			else if(c == '=')
				return State.Assign;
			else if(c == '>')
				return State.Gt;
			else if(c == '<')
				return State.Lt;
			else if(c == '!')
				return State.inv;
			else if(c == '|')
				return State.bar;
			else if(c == '&')
				return State.ampersand;
			else
				return State.UNDEF;
		//Keywords and relates states
		//----------------------
		//check the Keyword_true
		case id_t:
			if(c == 'r')
				return State.id_tr;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_tr:
			if(c == 'u')
				return State.id_tru;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_tru:
			if(c == 'e')
				return State.Keyword_true;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		//----------------------
		//checks the keyword_boolean
		case id_b:
			if(c == 'o')
				return State.id_bo;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_bo:
			if(c == 'o')
				return State.id_boo;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_boo:
			if(c == 'l')
				return State.id_bool;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_bool:
			if(c == 'e')
				return State.id_boole;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_boole:
			if(c == 'a')
				return State.id_boolea;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_boolea:
			if(c == 'n')
				return State.Keyword_boolean;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		//----------------------
		//check for keyword_do
		case id_d:
			if(c == 'o')
				return State.Keyword_do;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		//----------------------
		//checks for keyword_else
		case id_e:
			if(c == 'l')
				return State.id_el;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_el:
			if(c == 's')
				return State.id_els;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_els:
			if(c == 'e')
				return State.Keyword_else;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		//----------------------
		//check for Keyword_while
		case id_w:
			if(c == 'h')
				return State.id_wh;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_wh:
			if(c == 'i')
				return State.id_whi;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_whi:
			if(c == 'l')
				return State.id_whil;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_whil:
			if(c == 'e')
				return State.Keyword_while;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		//---------------------------------------
		//checks for keyword_if
		case id_i:
			if(c == 'f')
				return State.Keyword_if;
			else if(c == 'n')
				return State.id_in;
			else if(Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		//-----------------------------------
		//checks for ketword_float
		case id_f:
			if(c == 'l')
				return State.id_fl;
			else if(c == 'a')
				return State.id_fa;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_fl:
			if(c == 'o')
				return State.id_flo;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_flo:
			if(c == 'a')
				return State.id_floa;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_floa:
			if(c == 't')
				return State.Keyword_float;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		//----------------------------------------
		//checks for keyword_false
		case id_fa:
			if(c == 'l')
				return State.id_fal;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_fal:
			if (c == 's')
				return State.id_fals;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case id_fals:
			if(c == 'e')
				return State.Keyword_false;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		//----------------------------------------
		case Keyword_if:
			if(Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		//--------------------------------------------
		//the final states of every keyword.
		case id_in:
			if(c == 't')
				return State.Keyword_int;
			else if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case Keyword_int:
			if(Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case Keyword_float:
			if(Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case Keyword_while:
			if(Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case Keyword_else:
			if(Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case Keyword_do:
			if(Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case Keyword_boolean:
			if(Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case Keyword_false:
			if(Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case Keyword_true:
			if(Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		//---------------------------------------
		//non keyword states.
		case Id:
			if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case Int:
			if (Character.isDigit(c))
				return State.Int;
			else if (c == '.')
				return State.Float;
			else
				return State.UNDEF;
		case Period:
			if (Character.isDigit(c))
				return State.Float;
			else
				return State.UNDEF;
		case Float:
			if (Character.isDigit(c))
				return State.Float;
			else if (c == 'e' || c == 'E')
				return State.E;
			else
				return State.UNDEF;
		case E:
			if (Character.isDigit(c))
				return State.FloatE;
			else if (c == '+' || c == '-')
				return State.EPlusMinus;
			else
				return State.UNDEF;
		case EPlusMinus:
			if (Character.isDigit(c))
				return State.FloatE;
			else
				return State.UNDEF;
		case FloatE:
			if (Character.isDigit(c))
				return State.FloatE;
			else
				return State.UNDEF;
		case Assign:
			if(c == '=')
				return State.Eq;
			else
				return State.UNDEF;
		case inv:
			if(c == '=')
				return State.neq;
			else
				return State.UNDEF;
		case Gt:
			if(c == '=')
				return State.Ge;
			else
				return State.UNDEF;
		case Lt:
			if(c == '=')
				return State.Le;
			else
				return State.UNDEF;
		case Add:
			if(c == '+')
				return State.Incr;
			else
				return State.UNDEF;
		case Sub:
			if(c == '-')
				return State.Decr;
			else
				return State.UNDEF;
		case bar:
			if(c == '|')
				return State.Or;
			else
				return State.UNDEF;
		case ampersand:
			if (c == '&')
				return State.And;
			else
				return State.UNDEF;
		default:
			return State.UNDEF;
		}
	} // end nextState

	private static boolean isFinal(State state) {
		//checks whether the selected state is greater then Id which is set as
		//lowest final state in enum.
		return (state.compareTo(State.Id) >= 0);
	}

	public static void getToken()

	// Extract the next token using the driver of the FA.
	// If an invalid token is found, issue an error message.

	{
		int i = driver();
		if (i == 0)
			displayln(t + "  -- Invalid Token");
	} // end getToken


	public static void displayln(String s) {
		outStream.println(s);
	}
	
	public static void display(String s) {
		outStream.println(s);
	}

	public static void setLex(String inFile, String outFile)

	// Sets the input and output streams to "inFile" and "outFile",
	// respectively.
	// Also sets the current input character "a" to the first character on
	// the input stream.

	{
		try {
			inStream = new BufferedReader(new FileReader(inFile));
			outStream = new PrintWriter(new FileOutputStream(outFile));
			a = inStream.read();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // end setIO

	public static void closeIO() {
		try {
			inStream.close();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // end closeIO

	public static void main(String argv[])

	// The input/output file names must be passed as argv[0] and argv[1].

	{
		int i;

		setLex(argv[0], argv[1]);

		while (a != -1) // while "a" is not end-of-stream
		{
			i = driver(); // extract the next token
			if (i == 1){
				if(state.compareTo(state.id_i)>=0 && state.compareTo(state.id_tru)<=0){
					displayln(t+"	: "+ state.Id);
				}else{
					displayln(t + "   : " + state.toString());
				}
			}
			else if (i == 0)
				displayln(t + "  -- Invalid Token");
		}

		closeIO();
	} // end main
}
