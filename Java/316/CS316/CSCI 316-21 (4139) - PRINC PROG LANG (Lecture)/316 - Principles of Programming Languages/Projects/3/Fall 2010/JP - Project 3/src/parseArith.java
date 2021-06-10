/**
 * @author Your Name
 * @class CS 316
 * @teacher Professor Yukawa
 * @type Project 3 - Top-Down Parser
 */

/**

This class is a top-down, recursive-descent parser for the following
syntactic categories:

<statement> --> <assignment> | "{" <S list> "}" | 
                "if" "(" <expr> ")" <statement> [ "else" <statment> ] |
	            "while" "(" <expr> ")" <statement> |
   			    "for" "(" <assignment> <expr> ")" <statement>
<assignment> --> <id> "=" <expr> ";"
<SList> --> { <statement> }+
<expr> --> <boolTerm> { "||" <boolTerm> }
<boolTerm> --> <boolPrimary> { "&&" <boolPrimary> }
<boolPrimary> --> <E> [ <relop> <E> ]
<relop> --> "<" | "<=" | ">" | ">=" | "==" | "!="
<E> --> <term> { (+|-) <term> }
<term> --> <primary> { (*|/) <primary> }
<primary> --> <id> | <int> | <float> | <floatE> | 
              "(" <expr> ")" | - <primary> | ! <primary>

The following variables and functions 
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
	public static void statement(String indent)
	
	// <statement> --> <assignment> | "{" <S list> "}" | 
    //                 "if" "(" <expr> ")" <statement> [ "else" <statment> ] |
	//				   "while" "(" <expr> ")" <statement> |
	//				   "for" "(" <assignment> <expr> ")" <statement>
	
	{	
		displayln(indent+indent.length()+" <statement>");
		indent = indent+" ";
		
		switch( state )
		{
			case Id:
			{
				assignment(indent);
				return;
			} // end case Id
			
			case LBrace:
			{
				getToken();
				SList(indent);
				if ( state == State.RBrace )
				{
					getToken();
				}
				else
				{
					errorMsg(6); // } expected
				}
				return;
			} // end case LBrace
			
			case If:
			{
				displayln(indent+indent.length() + " if");
				getToken();
				if( state == State.LParen)
				{
					getToken();
					expr(indent);
					if ( state == State.RParen )
					{
						getToken();
						statement(indent);
						if(state == State.Else)
						{
							displayln(indent+indent.length() + " else");
							getToken();
							statement(indent);
						}
					}
					else
					{
						errorMsg(1); // ) expected
					}
				}
				else
				{
					errorMsg(3); // ( expected
				}
				return;
			}// end case If
			
			case While:
			{
				displayln(indent+indent.length() + " while");
				getToken();
				if( state == State.LParen)
				{
					getToken();
					expr(indent);
					if ( state == State.RParen )
					{
						getToken();
						statement(indent);
					}
					else
					{
						errorMsg(1); // ) expected
					}
				}
				else
				{
					errorMsg(3); // ( expected
				}
				return;
			} // end case While
			
			case For:
			{
				displayln(indent+indent.length() + " for");
				getToken();
				if( state == State.LParen)
				{
					getToken();
					assignment(indent);
					expr(indent);
					if ( state == State.RParen )
					{
						getToken();
						statement(indent);
					}
					else
					{
						errorMsg(1); // ) expected
					}
				}
				else
				{
					errorMsg(3); // ( expected
				}
				return;
			} // end case For	
		
			default:
			errorMsg(4); // id, {, if, while, or for expected	
		} // end switch	
	} // end statement
	
	public static void assignment(String indent)
	
	// <assignment> --> <id> "=" <expr> ";"
	
	{
		displayln(indent+indent.length() + " <assignment>");
		indent = indent+" ";
		
		if(state == State.Id)
		{
			displayln(indent+indent.length() + " " + t);
			getToken();
			if(state == State.Assign)
			{
				displayln(indent+indent.length() + " " + t);
				getToken();
				expr(indent);
				if(state == State.Semicolon)
				{
					getToken();
				}
				else
				{
					errorMsg(7); // ; expected
				}
			}
			else
			{
				errorMsg(5); // = expected
			}
		}
	} // end assignment
		
		
	
	public static void SList(String indent)
	
	// <SList> --> { <statement> }+
	
	{	
		displayln(indent+indent.length()+" <SList>");
		indent = indent+" ";
		
		statement(indent);
		
		while (state == State.Id || state == State.LBrace || state == State.If || state == State.While || state == State.For)
		{
			statement(indent);
		}
	} // end SList
	
	public static void expr(String indent)
	
	// <expr> --> <boolTerm> { "||" <boolTerm> }
	
	{
		displayln(indent+indent.length()+" <expr>");
		indent = " " +indent;
		
		boolTerm(indent);
		
		while(state == State.Or)
		{
			displayln(indent+indent.length()+ " ||");
			getToken();
			boolTerm(indent);
		}
	}//end expr
	
	public static void boolTerm(String indent)
	
	// <boolTerm> --> <boolPrimary> { "&&" <boolPrimary> }
	
	{
		displayln(indent+indent.length()+" <boolTerm>");
		indent = " " +indent;
		
		boolPrimary(indent);
		
		while(state == State.And)
		{
			displayln(indent+indent.length()+ " &&");
			getToken();
			boolPrimary(indent);
		}
	} // end boolTerm
	
	public static void boolPrimary(String indent)

	// <boolPrimary> --> <E> [ <relop> <E> ]

	{
		displayln(indent+indent.length()+" <boolPrimary>");
		indent = indent+" ";
		
		E(indent);
		
		if(state == State.Lt || state == State.Le || state == State.Gt ||  state == State.Ge || state == State.Eq || state == State.Neq)
		{
			relop(indent);
			E(indent);
		}
	} // end boolPrimary
	
	public static void relop(String indent)

	// <relop> --> "<" | "<=" | ">" | ">=" | "==" | "!="

	{
		displayln(indent+indent.length()+" " + t);
		indent = indent+" ";
		getToken();	
	} // end rel op
	
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

	// <primary> --> <id> | <int> | <float> | <floatE> | 
	//               "(" <expr> ")" | - <primary> | ! <primary>

	{
		display(indent+indent.length()+" <primary>");
		indent = indent+" ";
		
		if (state == State.Id || state == State.Int || state == State.Float || state == State.FloatE)
		{
			displayln(" " + t);
			getToken();
		}
		
		else if (state == State.LParen)
		{
			displayln("");
			getToken();
			expr(indent);
			if ( state == State.RParen )
			{
				getToken();
			}
			else
			{
				errorMsg(1); // ) expected
			}
		}
		
		else if (state == State.Sub)
		{
			displayln("");
			displayln(indent+indent.length()+ " " + t);
			getToken();
			primary(indent);
		}
		
		else if (state == State.Inv)
		{
			displayln("");
			displayln(indent+indent.length()+ " " + t);
			getToken();
			primary(indent);
		}
		
		else
		{
			errorMsg(2); // id, int, float, floatE, -, !, or ( expected
		}
	} // end primary
	
	public static void errorMsg(int i)
	{
		display(t + ": unexpected symbol where");

		switch( i )
		{
		case 1: displayln(" ) expected"); return;
		case 2: displayln(" id, int, float, floatE, -, !, or ( expected"); return;
		case 3: displayln(" ( expected"); return;
		case 4: displayln(" id, {, if, while, or for expected"); return;
		case 5: displayln(" = expected"); return;
		case 6: displayln(" } expected"); return;
		case 7: displayln(" ; expected"); return;
		}
	} // end errorMsg

	public static void main(String argv[])

	// The input/output file names must be passed as argv[0] and argv[1].

	{
		setLex( argv[0], argv[1] );

		String indent = ""; // used to properly indent displayed
                                    // syntactic category names

		getToken();
		statement(indent);
		if ( ! t.isEmpty() )
			displayln(t + "  -- unexpected symbol");

		closeIO();
	} // end main
}
