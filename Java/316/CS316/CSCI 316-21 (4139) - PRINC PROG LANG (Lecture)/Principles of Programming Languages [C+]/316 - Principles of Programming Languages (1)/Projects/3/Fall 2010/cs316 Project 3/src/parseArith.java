/**

This class is a top-down, recursive-descent parser for the following
syntactic categories:

<E> --> <term> { (+|-) <term> }
<term> --> <primary> { (*|/) <primary> }
<primary> --> <id> | <int> | <float> | "(" <E> ")"

The definitions of <id>, <int>, <float> are given in the lexical analyzer 
class file "lexArith.java". The following variables and functions 
of the "lexArith" class are used:

static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token
static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

The program will display the parse tree in linearly indented form.
Each syntactic category name labeling a node is
displayed on a separate line, prefixed with the integer i representing
the node's depth and indented by i blanks. The arithmetic operators are
displayed in the same way. The string variable "indent"
will keep track of the correct number of blanks for indentation and
will be passed to parse functions corresponding to syntactic categories.

**/


public abstract class parseArith extends LexAnalyzer
{
    public static void Statement(String indent)

    // Statement

    {
        displayln(indent+indent.length()+" <statement>");
        indent = indent+" ";
        if(state==State.Id){
        Assignment(indent);
        }
        if(state==State.LBrace){
            getToken();
            S_List(indent);
        }
        if(state==State.While){
        	getToken();
        	if(state==State.LParen){
        		getToken();
        		expr(indent);
        		getToken();
        		if(state==State.RParen){
        			getToken();
        			Statement(indent);
        		}
        		// else errorMsg(1);  
        	}
        	 //else errorMsg(1);  
        }
    } // end Statement
    
    public static void Assignment(String indent)

    // Statement

    {
        displayln(indent+indent.length()+" <assignment>");
        indent = indent+" ";

        displayln(indent+indent.length()+ " " + t);    
        getToken();
        displayln(indent+indent.length()+ " " + t);    
            if(state==State.Assign){
                getToken();
                expr(indent);
                
            if(state==State.Semicolon){
            	getToken();
            	indent=" "+" ";
            	Statement(indent);
            }
           
            }
        
    } // end Statement
    
    public static void S_List(String indent)

    // slist

    {
        displayln(indent+indent.length()+" <SList>");
        indent = indent+" ";

        if(state==State.Id){
        Statement(indent);
        }
        
    } // end Statement
    
    public static void expr(String indent)

    // expr

    {
        displayln(indent+indent.length()+" <expr>");
        indent = indent+" ";

        boolTerm(indent);
        
        
    } // end expr
    
    public static void boolTerm(String indent)

    // boolterm

    {
        displayln(indent+indent.length()+" <boolTerm>");
        indent = indent+" ";

        boolPrimary(indent);
        
        
    } // end expr
    
    public static void boolPrimary(String indent)

    // boolPrimary

    {
        displayln(indent+indent.length()+" <boolPrimary>");
        indent = indent+" ";
        displayln(" " + t);
        
        E(indent);
        if(relop(indent))
        {
        	 displayln(indent+indent.length()+" <relop>");
        	 indent = indent+" ";
        	 displayln(" " + t);
        	 getToken();
        	 E(indent);
        }
        
        
    }   // end expr
    
    public static boolean relop(String indent)
    	//relop
    {
    	displayln(indent+indent.length()+" <relop>");
    	indent = indent+" ";
    	
    	if(state==State.Gt || state==State.Lt || state==State.Le || state==State.Ge || state==State.Eq || state==State.Neq){
    		return true;
    	}
    	else return false;
    }
    
    public static void E(String indent)

    // <E> --> <term> { (+|-) <term> }

    {
        displayln(indent+indent.length()+" <E>");
        indent = indent+" ";

        term(indent);
        while ( state == State.Add || state == State.Sub )
        {
            displayln(indent+indent.length()+" "+t);
            getToken();
            term(indent);
        }
    } // end E


    public static void term(String indent)

    // <term> --> <primary> { (*|/) <primary> }

    {
        displayln(indent+indent.length()+" <term>");
        indent = indent+" ";

        primary(indent);
        while ( state == State.Mul || state == State.Div )
        {
            displayln(indent+indent.length()+" "+t);
            getToken();
            primary(indent);
        }
    } // end term


    public static void primary(String indent)

    // <primary> --> <id> | <int> | <float> | "(" <E> ")"

    {
        display(indent+indent.length()+" <primary>");
        indent = indent+" ";

        switch( state )
        {
        case Id: case Int: case Float: case FloatE:

            displayln(" "+t); 
                 getToken();
            return;

        case LParen:

            displayln("");

            getToken();
            E(indent);
            if ( state == State.RParen )
                getToken();
            else
                errorMsg(1);
            return;

        default:
            errorMsg(2);
            return;
        }
    } // end primary

    public static void errorMsg(int i)
    {
        display(t + ": unexpected symbol where");

        switch( i )
        {
        case 1: displayln(" arith op or ) expected"); return;
        case 2: displayln(" id, int, float, or ( expected"); return;
        }
    } // end errorMsg

    public static void main(String argv[])

    // The input/output file names must be passed as argv[0] and argv[1].

    {
        setLex( argv[0], argv[1] );

        String indent = ""; // used to properly indent displayed
                                    // syntactic category names

        getToken();
        Statement(indent);
        if ( ! t.isEmpty() )
            displayln(t + "  -- unexpected symbol");

        closeIO();
    } // end main
}