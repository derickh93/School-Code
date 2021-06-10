/*
 * LexAnalyzer.java
 * Created on March 23, 2008, 7:50 PM
 *
 */

package lexicalanalyzer;

import java.io.*;

/**
 *
 * @author Minhan Song
 */
public class LexAnalyzer{
    
    public enum State 
    { 
      // non-final states

            Start,
            Period,
            E,
            EPlusMinus,
            Bar,
            Ampersand,

      // final states

            Id,
            Int,
            Float,
            FloatE,
            Add,
            Sub,
            Mul,
            Div,
            LParen,
            RParen,
            LBrace,
            RBrace,
            Semicolon,
            Or,
            And,
            Inv,
            Neq,
            Assign,
            Eq,
            Lt,
            Le,
            Gt,
            Ge,
            If_I,
            If,
            Else_E,
            Else_El,
            Else_Els,
            Else,
            While_W,
            While_Wh,
            While_Whi,
            While_Whil,
            While,

            UNDEF
    }
    
    public static String t; // holds an extracted token
    public static State state; // the current state of the FA
    private static int a; // the current input character
    private static char c; // used to convert the variable "a" to 
                           // the char type whenever necessary
    private static BufferedReader inStream;
    private static PrintWriter outStream;

    /*  Returns the next character on the input stream.
     */
    private static int getNextChar() {
        try {
                return inStream.read();
        } catch(IOException e) {
            e.printStackTrace();
            return -1;
        }
    } //end getNextChar

    /*  Returns the next non-whitespace character on the input stream.
     *  Returns -1, end-of-stream, if the end of the input stream is reached.
     */
    private static int getChar() {
            int i = getNextChar();
            while ( Character.isWhitespace((char) i) )
                    i = getNextChar();
            return i;
    } // end getChar

    /* This is the driver of the FA. 
     * If a valid token is found, assigns it to "t" and returns 1.
     * If an invalid token is found, assigns it to "t" and returns 0.
     * If end-of-stream is reached without finding any non-whitespace character, returns -1.
     */
    private static int driver() {
        State nextState; // the next state of the FA

        t = "";
        state = State.Start;
        if ( Character.isWhitespace((char) a) ) {
            a = getChar(); // get the next non-whitespace character
            if ( a == -1 ) // end-of-stream is reached
                return -1;
        }

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

    /*  Returns the next state of the FA given the current state and input char;
     *  if the next state is undefined, UNDEF is returned.
     */    
    private static State nextState(State s, char c) {
        if ( s == State.Start ) {
            if ( Character.isLetter(c) ) {
                if ( c == 'i' ) return State.If_I;              // if-i
                else if ( c == 'e' ) return State.Else_E;       // else-e
                else if ( c == 'w' ) return State.While_W;      // while-w
                else
                    return State.Id;
            }
            else if ( Character.isDigit(c) ) return State.Int;
            else if ( c == '+' ) return State.Add;
            else if ( c == '-' ) return State.Sub;
            else if ( c == '*' ) return State.Mul;
            else if ( c == '/' ) return State.Div;
            else if ( c == '(' ) return State.LParen;
            else if ( c == ')' ) return State.RParen;
            else if ( c == '{' ) return State.LBrace;
            else if ( c == '}' ) return State.RBrace;
            else if ( c == ';' ) return State.Semicolon;
            else if ( c == '|' ) return State.Bar;
            else if ( c == '&' ) return State.Ampersand;
            else if ( c == '!' ) return State.Inv;
            else if ( c == '=' ) return State.Assign;
            else if ( c == '<' ) return State.Lt;
            else if ( c == '>' ) return State.Gt;
            else if ( c == '.' ) return State.Period;
            else return State.UNDEF;
        } else if ( s == State.Id ) {
            if ( Character.isLetterOrDigit(c) )
                return State.Id;
            else
                return State.UNDEF;
        } else if ( s == State.Int ) {
            if ( Character.isDigit(c) )
                return State.Int;
            else if ( c == '.' )
                return State.Period;
            else
                return State.UNDEF;
        } else if ( s == State.Period ) {
            if ( Character.isDigit(c) )
                return State.Float;
            else
                return State.UNDEF;
        } else if ( s == State.Float ) {
            if ( Character.isDigit(c) )
                return State.Float;
            else if ( c == 'e' || c == 'E' )
                return State.E;
            else
                return State.UNDEF;
        } else if ( s == State.E ) {
            if ( Character.isDigit(c) )
                return State.FloatE;
            else if ( c == '+' || c == '-' )
                return State.EPlusMinus;
            else
                return State.UNDEF;
        } else if ( s == State.EPlusMinus ) {
            if ( Character.isDigit(c) )
                return State.FloatE;
            else
                return State.UNDEF;
        } else if ( s == State.FloatE ) {
            if ( Character.isDigit(c) )
                return State.FloatE;
            else return State.UNDEF;
        } else if ( s == State.Bar ) {
            if ( c == '|' ) 
                return State.Or;
            else 
                return State.UNDEF;
        } else if ( s == State.Ampersand ) {
            if ( c == '&' )
                return State.And;
            else 
                return State.UNDEF;
        } else if ( s == State.Inv ) {
            if ( c == '=' )
                return State.Neq;
            else 
                return State.UNDEF;
        } else if ( s == State.Assign ) {
            if ( c == '=' )
                return State.Eq;
            else
                return State.UNDEF;
        } else if ( s == State.Lt ) {
            if ( c == '=' ) 
                return State.Le;
            else
                return State.UNDEF;
        } else if ( s == State.Gt ) {
            if ( c == '=' )
                return State.Ge;
            else
                return State.UNDEF;
        } else if ( s == State.If_I ) {             // if-if
            if ( c == 'f' ) return State.If;
            else            return State.Id;
        } else if ( s == State.If ) {
            if ( Character.isLetterOrDigit(c) ) return State.Id;
            else return State.UNDEF;
        } else if ( s == State.Else_E ) {           // else-el
            if ( c == 'l' ) return State.Else_El;
            else            return State.Id;
        } else if ( s == State.Else_El ) {          // else-els
            if ( c == 's' ) return State.Else_Els;
            else            return State.Id;
        } else if ( s == State.Else_Els ) {         // else-else
            if ( c == 'e' ) return State.Else;
            else            return State.Id;
        } else if ( s == State.Else ) {
            if ( Character.isLetterOrDigit(c) ) return State.Id;
            else return State.UNDEF;
        } else if ( s == State.While_W ) {
            if ( c == 'h' ) return State.While_Wh;
            else            return State.Id;
        } else if ( s == State.While_Wh ) {
            if ( c == 'i' ) return State.While_Whi;
            else            return State.Id;
        } else if ( s == State.While_Whi ) {
            if ( c == 'l' ) return State.While_Whil;
            else            return State.Id;
        } else if ( s == State.While_Whil ) {
            if ( c == 'e' ) return State.While;
            else            return State.Id;
        } else if ( s == State.While ) {
            if ( Character.isLetterOrDigit(c) ) return State.Id;
            else return State.UNDEF;
        } else
            return State.UNDEF;
    } // end nextState

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
    } // end getToken

    public static void display(String s) {
        outStream.print(s);
    }

    public static void displayln(String s) {
        outStream.println(s);
    }

    /**
     *  Sets the input and output streams to "inFile" and "outFile", respectively.
     *  Also sets the current input character "a" to the first character on the input stream.
     */
    public static void setLex(String inFile, String outFile)

    

    {
        try {
            inStream = new BufferedReader( new FileReader(inFile) );
            outStream = new PrintWriter( new FileOutputStream(outFile) );
            a = inStream.read();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    } // end setIO

    public static void closeIO() {
        try {
            inStream.close();
            outStream.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    } // end closeIO
       
    /**
     * @param args: The input/output file names must be passed as argv[0] and argv[1].
     */
    public static void main(String[] args) {// The input/output file names must be passed as argv[0] and argv[1].
        
        int i;

        setLex( args[0], args[1] );

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
