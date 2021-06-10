/**

This class is a top-down, recursive-descent parser for the following
syntactic categories:

<fun defs> --> {<fun def>}+
<fun def> --> <header> <body>
<header> --> <fun name> "(" <parameter list> ")"
<fun name> --> <id>
<parameter list> --> epsilon | <id> {"," <id>}
<body> --> "{" <Exp> "}"
<Exp> --> "if" "(" <expr> ")" <Exp> "else" <Exp> | <expr> 
<expr> --> <boolTerm> { "||" <boolTerm> }
<boolTerm> --> <boolPrimary> { "&&" <boolPrimary> }
<boolPrimary> --> <E> [ <rel op> <E> ]
<rel op> --> "<" | "<=" | ">" | ">=" | "==" | "!="
<E> --> <term> { (+|-) <term> }
<term> --> <primary> { (*|/) <primary> }
<primary> --> <id> | <int> | <float> | <floatE> | <fun E> |
                   "(" <expr> ")" | - <primary> | ! <primary>
<fun E> --> <fun name> "(" <E list> ")"
<E list> --> epsion | <expr> {"," <expr>} 

The definitions of the tokens are given in the lexical analyzer 
class file "LexAnalyzer.java". 

The following variables and functions of the "LexAnalyzer" class are used:

static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token
static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

The program will display the parse tree in linearly indented form.
Each syntactic category name labeling a node is displayed on a separate line, 
prefixed with the integer i representing the node's depth and indented by i blanks. 
The string variable "indent" will keep track of the correct number of blanks for indentation and
will be passed to parse functions corresponding to syntactic categories.

**/


public abstract class parser extends LexAnalyzer
{
	private static String indent = "      ";

	public static void funDefs(String indent)

	// <fun defs> --> {<fun def>}+

	{
		displayln(indent+indent.length()+" <fun defs>");
		indent = indent+" ";

		funDef(indent);
		while ( state == State.Id )
			funDef(indent);
	}

	public static void funDef(String indent)

	// <fun def> --> <header> <body>

	{
		displayln(indent+indent.length()+" <fun def>");
		indent = indent+" ";

		header(indent);
		body(indent);
	}

	public static void header(String indent)

	// <header> --> <fun name> "(" <parameter list> ")"
	// <fun name> --> <id>

	{
		display(indent+indent.length()+" <header>");
		indent = indent+" ";

		if ( state == State.Id )
		{
			displayln("  <fun name>  "+t);
			getToken();
			if ( state == State.LParen )
			{
				getToken();
				parameterList(indent);
				if ( state == State.RParen )
					getToken();
				else
					errorMsg(1);
			}
			else errorMsg(2);
		}
		else errorMsg(3);
	}

	public static void parameterList(String indent)

	// <parameter list> --> epsilon | <id> {"," <id>}

	{
		display(indent+indent.length()+" <parameter list>");
		indent = indent+" ";

		if ( state == State.Id )
		{
			display("  "+t);
			getToken();
			while ( state == State.Comma )
			{
				getToken();
				if ( state == State.Id )
				{
					display("  "+t);
					getToken();
				}
				else
					errorMsg(3);
			}
		}
		displayln("");
	}

	public static void body(String indent)
	
	// <body> --> "{" <Exp> "}"

	{
		displayln(indent+indent.length()+" <body>");
		indent = indent+" ";

		if ( state == State.LBrace )
		{
			getToken();
			Exp(indent);
			if ( state == State.RBrace )
				getToken();
			else
				errorMsg(4);
		}
		else
			errorMsg(5);
	}

	public static void Exp(String indent)

	// <Exp> --> "if" "(" <expr> ")" <Exp> "else" <Exp> | <expr>

	{
		displayln(indent+indent.length()+" <Exp>");
		indent = indent+" ";

		if ( state == State.If )
		{
			displayln(indent+indent.length()+" "+"if");
			getToken();
			if ( state == State.LParen )
			{
				getToken();
				expr(indent);
				if ( state == State.RParen )
				{
					getToken();
					Exp(indent);
					if ( state == State.Else )
					{
						displayln(indent+indent.length()+" "+"else");
						getToken();
						Exp(indent);
					}
					else
						errorMsg(6);
				}
				else
					errorMsg(1);
			}
			else
				errorMsg(2);
		}
		else
			expr(indent);
	}

	public static void expr(String indent)

	// <expr> --> <boolTerm> { "||" <boolTerm> }

	{
		displayln(indent+indent.length()+" <expr>");
		indent = indent+" ";

		boolTerm(indent);
		while ( state == State.Or )
		{
			displayln(indent+indent.length()+" "+t);
			getToken();
			boolTerm(indent);
		}
	}

	public static void boolTerm(String indent)

	// <boolTerm> --> <boolPrimary> { "&&" <boolPrimary> }

	{
		displayln(indent+indent.length()+" <boolTerm>");
		indent = indent+" ";

		boolPrimary(indent);
		while ( state == State.And )
		{
			displayln(indent+indent.length()+" "+t);
			getToken();
			boolPrimary(indent);
		}
	}

	public static void boolPrimary(String indent)

	// <boolPrimary> --> <E> [ <relop> <E> ]

	{
		displayln(indent+indent.length()+" <boolPrimary>");
		indent = indent+" ";

		E(indent);
		if ( state.compareTo(State.Lt) >= 0 &&
                     state.compareTo(State.Neq) <= 0 )

		// state = Lt, Le, Gt, Ge, Eq, or Neq

		{
			displayln(indent+indent.length()+" "+t);
			getToken();
			E(indent);
		}
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
	}

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
	}

	public static void primary(String indent)

	// <primary> --> <id> | <int> | <float> | <floatE> | <fun E> |
        //               "(" <expr> ")" | - <primary> | ! <primary>
	// <fun E> --> <fun name> "(" <E list> ")"
	// <fun name> --> <id>

	{

		display(indent+indent.length()+" <primary>");
		indent = indent+" ";

		switch ( state )
		{
			case Id:

				String id = t;
				getToken();
				if ( state == State.LParen )
				{
					displayln("");
					display(indent+indent.length()+" <fun E>");
		 			indent = indent+" ";

					displayln("  <fun name>  "+id);
					getToken();
					Elist(indent);
					if ( state == State.RParen )
						getToken();
					else
						errorMsg(1);
				}
				else
					displayln(" "+id);
				return;

			case Int: case Float: case FloatE:

				displayln(" "+t); 
		     		getToken();
				return;

			case LParen:

				displayln("");
				getToken();
				expr(indent);
				if ( state == State.RParen )
					getToken();
				else
					errorMsg(1);
				return;

			case Sub:

				displayln("");
				displayln(indent+indent.length()+" "+t);
				getToken();
				primary(indent);
				return;

			case Inv:

				displayln("");
				displayln(indent+indent.length()+" "+t);
				getToken();
				primary(indent);
				return;

			default:

				errorMsg(7);
				return;
		}
	}

	public static void Elist(String indent)

	// <E list> --> epsion | <expr> {"," <expr>}

	{
		displayln(indent+indent.length()+" <E list>");
		indent = indent+" ";

		if ( state != State.RParen )
		{
			expr(indent);
			while ( state == State.Comma )
			{
				getToken();
				expr(indent);
			}
		}
	}

	public static void errorMsg(int i)
	{
		displayln("");
		display(t + ": unexpected symbol where");

		switch ( i )
		{
			case 1: displayln(" ) expected"); return;
			case 2: displayln(" ( expected"); return;
			case 3: displayln(" id expected"); return;
			case 4: displayln(" } expected"); return;
			case 5: displayln(" { expected"); return;
			case 6: displayln(" else expected"); return;
			case 7: displayln(" id, int, float, (, -, or ! expected"); return;
		}
	}

	public static void main(String argv[])
	{
		setLex( argv[0], argv[1] );

		getToken();
		funDefs("");
		if ( ! t.isEmpty() )
			displayln(t + "  -- unexpected symbol");

		closeIO();
	}
}
