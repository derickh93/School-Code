/**
 * @author Your Name
 * @class CS 316
 * @teacher Professor Yukawa
 * @type Project 4 - Top-Down Parser with Instruction Emission
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


public abstract class instrEmission extends LexAnalyzer
{

	private static int totalLoops = 0;
	
	private static String VMinstruction[] = new String[13];

	private static void setOperatorToVMinstruction()
	{
		VMinstruction[State.Add.ordinal()] = "add";
		VMinstruction[State.Sub.ordinal()] = "sub";
		VMinstruction[State.Mul.ordinal()] = "mul";
		VMinstruction[State.Div.ordinal()] = "div";
		VMinstruction[State.Or .ordinal()] = "or" ;
		VMinstruction[State.And.ordinal()] = "and";
		VMinstruction[State.Inv.ordinal()] = "inv";
		VMinstruction[State.Lt .ordinal()] = "lt" ;
		VMinstruction[State.Le .ordinal()] = "le" ;
		VMinstruction[State.Gt .ordinal()] = "gt" ;
		VMinstruction[State.Ge .ordinal()] = "ge" ;
		VMinstruction[State.Eq .ordinal()] = "eq" ;
		VMinstruction[State.Neq.ordinal()] = "neq";
	}

	private static void emitOperator(State currentOperation)
	{
		displayln( "    " + VMinstruction[currentOperation.ordinal()] );
	}

	public static void statement()

	// <statement> --> <assignment> | "{" <S list> "}" | 
    //                 "if" "(" <expr> ")" <statement> [ "else" <statment> ] |
	//				   "while" "(" <expr> ")" <statement> |
	//				   "for" "(" <assignment> <expr> ")" <statement>
	
	{
		switch( state )
		{
			case Id:
			{
				assignment();
				return;
			} // end case Id
			
			case LBrace:
			{
				getToken();
				SList();
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
				int elseLoop;
				int currentLoopNumber;
				getToken();
				if( state == State.LParen)
				{
					getToken();
					expr();
					if ( state == State.RParen )
					{
						displayln("    if_f goto " + ++totalLoops);
						currentLoopNumber = totalLoops;
						getToken();
						statement();
						elseLoop= totalLoops;
						if(state == State.Else)
						{
							displayln("    goto " + ++totalLoops);
							displayln(currentLoopNumber + ":");
							getToken();
							statement();
							displayln((elseLoop + 1) + ":");
						}
						else
						{
							displayln(currentLoopNumber + ":");
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
				int currentLoopNumber;
				getToken();
				if( state == State.LParen)
				{
					displayln(++totalLoops + ":");
					getToken();
					expr();
					if ( state == State.RParen )
					{
						displayln("    if_f goto " + ++totalLoops);
						currentLoopNumber = totalLoops;
						getToken();
						statement();
						displayln("    goto " + (currentLoopNumber - 1));
						displayln(currentLoopNumber + ":");
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
				int currentLoopNumber;
				getToken();
				if( state == State.LParen)
				{
					getToken();
					assignment();
					displayln(++totalLoops + ":");
					expr();
					if ( state == State.RParen )
					{
						displayln("    if_f goto " + ++totalLoops);
						currentLoopNumber = totalLoops;
						getToken();
						statement();
						displayln("    goto " + (currentLoopNumber - 1));
						displayln(currentLoopNumber + ":");
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

	public static void assignment()

	// <assignment> --> <id> "=" <expr> ";"

	{
		if ( state == State.Id )
		{
			String id = t;
			getToken();
			if ( state == State.Assign )
			{
				getToken();
				expr();
				if(state == State.Semicolon)
				{
					displayln("    pop  "+id);
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
	}

	public static void SList()

	// <SList> --> { <statement> }+

	{
		statement();
		
		while (state == State.Id || state == State.LBrace || state == State.If || state == State.While || state == State.For)
		{
			statement();
		}
	} // end SList

	public static void expr()

	// <expr> --> <boolTerm> { "||" <boolTerm> }

	{
		boolTerm();
		while ( state == State.Or )
		{
			getToken();
			boolTerm();
			emitOperator(State.Or);
		}
	} // end expr

	public static void boolTerm()

	// <boolTerm> --> <boolPrimary> { "&&" <boolPrimary> }

	{
		boolPrimary();
		while ( state == State.And )
		{
			getToken();
			boolPrimary();
			emitOperator(State.And);
		}
	} // end boolTerm


	public static void boolPrimary()

	// <boolPrimary> --> <E> [ <relop> <E> ]

	{
		E();
		
		if(state == State.Lt || state == State.Le || state == State.Gt ||  state == State.Ge || state == State.Eq || state == State.Neq)
		{
			State currentOperation = state;
			relop();
			E();
			emitOperator(currentOperation);
		}
	} // end boolPrimary
	
	public static void relop()

	// <relop> --> "<" | "<=" | ">" | ">=" | "==" | "!="

	{
		getToken();	
	} // end rel op

	public static void E()

	// <E> --> <term> { (+|-) <term> }

	{
		State currentOperation;

		term();
		
		while ( state == State.Add || state == State.Sub )
		{
			currentOperation = state;
			getToken();
			term();
			emitOperator(currentOperation);
		}
	} // end E


	public static void term()

	// <term> --> <primary> { (*|/) <primary> }

	{
		State currentOperation;

		primary();
		
		while ( state == State.Mul || state == State.Div )
		{
			currentOperation = state;
			getToken();
			primary();
			emitOperator(currentOperation);
		}
	} // end term


	public static void primary()

	// <primary> --> <id> | <int> | <float> | <floatE> | 
	//               "(" <expr> ")" | - <primary> | ! <primary>
	
	{
		if (state == State.Id || state == State.Int || state == State.Float || state == State.FloatE)
		{
			displayln("    push  "+t); 
		    getToken();
		}
		
		else if ( state == State.LParen )
		{
			getToken();
			expr();
			if ( state == State.RParen )
			{
				getToken();
			}
			else
			{
				errorMsg(1); // ) expected
			}
		}
		
		else if ( state == State.Sub )
		{
			getToken();
			primary();
			displayln("neg");
		}
		
		else if ( state == State.Inv )
		{
			getToken();
			primary();
			emitOperator(State.Inv);
		}
		else
		{
			errorMsg(2);
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
	{
		setLex( argv[0], argv[1] );
		
		setOperatorToVMinstruction();

		getToken();
		statement();
		
		if ( ! t.isEmpty() )
			displayln(t + "  -- unexpected symbol");

		closeIO();
	} // end main
}
