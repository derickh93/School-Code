import java.util.Scanner;

/**
 *
 * This program was written and tested with JDK 1.6.0.
 * <br>
 * <br>This class is a top-down, recursive-descent parser for the following
 * <br>syntactic categories:
 * <br>
 * <br>< E > → < term > { (+|-) < term > }
 * <br>< term > → < primary > { (*|/) < primary > }
 * <br>< primary > → < id > | < int > | < float > | "(" < E > ")"
 * <br>
 * <br>The definitions of < id >, < int >, < float > are given in the lexical analyzer 
 * <br>class file "lexArith.java". The following variables and functions 
 * <br>of the "lexArith" class are used:
 * <br>
 * <br>static String t 					//holds an extracted token
 * <br>static State state 				//the current state of the finite automaton
 * <br>static int getToken() 			//extracts the next token
 * <br>static void display(String s)	
 * <br>static void displayln(String s)	
 * <br>static void setLex(String inFile, String outFile)
 * <br>static void closeIO()
 * <br>
 * <br>The program will display the parse tree in linearly indented form.
 * <br>Each syntactic category name labeling a node is
 * <br>displayed on a separate line, prefixed with the integer i representing
 * <br>the node's depth and indented by i blanks. The arithmetic operators are
 * <br>displayed in the same way. The string variable "indent"
 * <br>will keep track of the correct number of blanks for indentation and
 * <br>will be passed to parsing functions corresponding to syntactic categories.
 */
public abstract class ParseArith extends LexAnalyzer
{
	/**
	 * < fun defs > → { < fun def > }<sup>+</sup>
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void funDefs(String indent)
	{
		displayln(indent+indent.length()+" <fun defs>");
		indent = indent+" ";
		
		funDef(indent);
		while(a!=-1 && (state==State.Id || state==State.Id_i || state==State.Id_e || state==State.Id_el || state==State.Id_els))
		{
			funDef(indent);
		}//while
	}//funDefs 
	
	/**
	 * < fun def > → < header > < body >
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void funDef(String indent)
	{
		display(indent + indent.length() + " <fun def>");
		indent = indent + " ";
		displayln(" " + t);
		
		header(indent);					//<header>
		body(indent);					//<body>
	}//funDef
	
	/**
	 * < header > → < fun name > "(" < parameter list > ")"
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void header(String indent)
	{
		display(indent + indent.length() + " <header>");
		indent = indent + " ";
		displayln(" ");
		
//		getToken();
		if(funName(indent))					//<fun name>
		{
			//getToken();
			if(state==State.LParen)			//"("
			{
				getToken();					//Discard (
				parameterList(indent);		//<parameter list>
				if(state==State.RParen)		//")"
				{
					getToken();				//Discard )
				}//if
				else
				{
					errorMsg("header",")");
				}//else
			}//if
			else
			{
				errorMsg("header","(");
			}//else
		}//if
		else
		{
			errorMsg("header","<fun name>");
		}//else
	}//header
	
	/**
	 * < fun name > → < id >
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static boolean funName(String indent)
	{
		display(" <funName> ");
		indent = indent + " ";	
		
		//getToken();
										//<id>
		while(state==State.Id || state==State.Id_i || state==State.Id_e || state==State.Id_el || state==State.Id_els)
		{
			display(t);
			getToken();					//Discard ID
		}//while
		displayln(" ");
		if(state==State.If || state==State.Else)
		{
			errorMsg("funName","ID");
			return false;
		}//if
		return true;
	}//funName

	/**
	 * < parameter list > → ε | < id > {"," < id >}
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void parameterList(String indent)
	{
		display(indent + indent.length() + " <parameter list>");
		indent = indent + " ";
		
		
//		getToken();
		if(state==State.Id || state==State.Id_i || state==State.Id_e || state==State.Id_el || state==State.Id_els)								//<id>
		{
			display(" " + t);
			getToken();									//Discard ID
			while(state==State.Comma)					//{","
			{
				display(" " + t);
				getToken();								//Discard ,
				display(" " + t);
				/***/
				getToken();
				/***/
			}//while
		}//if
		display("\n");
	}//parameterList
	
	/**
	 * < body > → "{" < Exp > "}"
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void body(String indent)
	{
		display(indent + indent.length() + " <body>");
		indent = indent + " ";
		displayln(" " );
		
		//getToken();
		if(state==State.LBrace)						//"{"
		{
			getToken();								//Discard {
			Exp(indent);							//<Exp>
			if(state==State.RBrace)					//"}"
			{
				getToken();							//Discard }
			}//if
			else
			{
				errorMsg("body","}");
			}//else
		}//if
		else
		{
			errorMsg("body","{");
		}//else
	}//body
	
	/**
	 * < Exp > → "if" "(" < expr > ")" < Exp > "else" < Exp > | < expr >
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void Exp(String indent)
	{
		displayln(indent + indent.length() + " <Exp>");
		indent = indent + " ";
		//displayln(" ");
		
		if(state==State.If)					//"if"
		{
			displayln(indent + indent.length() + " " + t);
			getToken();						//Discard IF
			if(state==State.LParen)			//"("
			{
				getToken();					//Discard (
				expr(indent);				//<expr>
				if(state==State.RParen)		//")"
				{
					getToken();				//Discard )
					Exp(indent);			//<Exp>
					if(state==State.Else)	//"else"
					{
						getToken();			//Discard ELSE
						Exp(indent);		//<Exp>
					}//if
					else
					{
						errorMsg("Exp","ELSE");
					}//else
				}//if
				else
				{
					errorMsg("Exp",")");
				}//else
			}//if
			else
			{
				errorMsg("Exp","(");
			}//else
		}//if
		else
		{
//			getToken();
			expr(indent);					//| <expr>
		}//else
	}//Exp
	
	/**
	 * < expr > → < bool term > { "||" < bool term > }
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void expr(String indent)
	{
		display(indent + indent.length() + " <expr>");
		indent = indent + " ";
		displayln(" ");
		
//		getToken();
		boolTerm(indent);				//<bool term>
		while(state==State.Or)			//{"||"
		{
			getToken();					//Discard ||
			boolTerm(indent);			//<bool term>}
		}//while
	}//expr
	
	/**
	 * < bool term > → < bool primary > { "&&" < bool primary > }
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void boolTerm(String indent)
	{
		display(indent + indent.length() + " <bool term>");
		indent = indent + " ";
		displayln(" ");
		
//		getToken();
		boolPrimary(indent);			//<bool primary>
		while(state==State.And)			//{"&&"
		{
			getToken();					//Discard &&
			boolPrimary(indent);		//<bool term>}
		}//while
	}//boolTerm
	
	/**
	 * < bool primary > → < E > [ < rel op > < E > ]
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void boolPrimary(String indent)
	{
		display(indent + indent.length() + " <bool primary>");
		indent = indent + " ";
		displayln(" ");
		
//		getToken();
		E(indent);				//<E>
		if(relOp(indent))		//[<rel op>
		{
			display(indent+indent.length()+" <rel op>");
			indent = indent+" ";
			displayln(" " + t);
			
			getToken();			//Discard REL OP
			E(indent);			//<E>]
		}//if

	}//boolPrimary
	
	/**
	 * < rel op > → "<" | "<=" | ">" | ">=" | "==" | "!="
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static boolean relOp(String indent)
	{
		//"<" | "<=" | ">" | ">=" | "==" | "!="
		if (state==State.Lt || state==State.Le || state==State.Gt || state==State.Ge || state==State.Eq || state==State.Neq)
		{
			return true;
		}//if
		else
			return false;
	}//relOp
	
	/**
	 * < E > → < term > { (+|-) < term > }
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void E(String indent)
	{
		display(indent + indent.length() + " <E>");
		indent = indent + " ";
		displayln(" ");
		
//		getToken();
		term(indent);			//<term>
								//{(+|-)
		while(state==State.Add || state==State.Sub)
		{
			getToken();			//Discard + or -
			term(indent);		//<term>}
		}//while
	}//E

	/**
	 * < term > → < primary > {(*|/) < primary > }
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void term(String indent)
	{
		display(indent + indent.length() + " <term>");
		indent = indent + " ";
		displayln(" " );
		
//		getToken();
		primary(indent);		//<primary>
								//{(*|/)
		while(state==State.Mul || state==State.Div)
		{
			displayln(indent + indent.length() + " " + t);
			getToken();			//Discard * or /
			primary(indent);	//<primary>}
		}//term
	}//term

	/**
	 * <br>< primary > → < id > | < int > | < float > | < float E > | < fun E > | "(" < expr > ")" | - < primary > | ! < primary >
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void primary(String indent)
	{
		display(indent + indent.length() + " <primary>");
		indent = indent + " ";
		//displayln(" " + t);
		String oldT = t;
		
//		getToken();
									//<id> | <int> | <float> | <float e> |
		if(state==State.Id || state==State.Int || state==State.Float || state==State.FloatE || state==State.Id_e || state==State.Id_el || state==State.Id_els || state==State.Id_i)
		{
			getToken();				//Discard ID or INT or FLOAT or FLOAT E
			if(state==State.LParen)
			{
				displayln("");
				display(indent + " " + (indent.length()+1) + " <fun e> <funName>");				
				displayln(" " + oldT);
				
		//		getToken();				
				if(state==State.LParen)			//"("
				{
					getToken();					//Discard (
					eList(indent+" ");				//<E list>
					if(state==State.RParen)		//")"
					{
						getToken();				//Discard )
					}//if
					else
					{
						errorMsg("fun name",")");
					}//else	
				}
			}
			else
				displayln(" " + oldT);
		}//if
									//- | !
		else if(state==State.Sub || state==State.Inv)
		{
			displayln(" " + oldT);
			getToken();				//Discard - or !
			primary(indent);		//<primary> |
		}//else if
		else if(state==State.LParen)	//"("
		{
			displayln(" " + oldT);
			getToken();					//Discard (
			expr(indent);				//<expr>
			if(state==State.RParen)		//")" |
			{
				getToken();				//Discard )
			}//if
			else
			{
				errorMsg("primary",")");
			}//else
		}//else if
		else
		{
			// TODO: Print Error Messages
//			getToken();
			//funE(indent);				//<funE>
		}//else
	} // end primary
	
	
	// TODO: Remove funE, never called
	/**
	 * < fun E > → < fun name > "(" < E list > ")"
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void funE(String indent)
	{
		display(indent + indent.length() + " <fun e>");
		indent = indent + " ";
		display(" " + t);
		
//		getToken();
		funName(indent);				//<fun name>
		if(state==State.LParen)			//"("
		{
			getToken();					//Discard (
			eList(indent);				//<E list>
			if(state==State.RParen)		//")"
			{
				getToken();				//Discard )
			}//if
			else
			{
				errorMsg("fun name",")");
			}//else
		}//if
		else
		{
			errorMsg("fun name","(");
		}//else
	}//funName
	
	/**
	 * < E list > → ε | < expr > { "," < expr > }
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void eList(String indent)
	{
		display(indent + indent.length() + " <e list>");
		indent = indent + " ";
		displayln(" " + t);
		
		if(state!=State.RParen)
		{
//			getToken();
			expr(indent);				//<expr>
			while(state==State.Comma)	//{","
			{
				getToken();				//Discard ,
				expr(indent);			//<expr>}
			}//while
		}//if
	}//eList
//-----------------------------------------------------------------------------------------------
	/**
	 * Displays an appropriate error message
	 * 
	 * @param i <b>= 1</b> for "arith op or ) expected"
	 * <br><t> <b>i = 2</b> for "id, int, float, or < expected"
	 */
	public static void errorMsg(String method, String expected)
	{
		displayln("ERROR in <" + method + ">: \"" + t + "\" found, but \"" + expected + "\" was expected.\tState: " + state);
	}//errorMsg

	/**
	 * Runs the program
	 * 
	 * @param argv <b>[0] =</b> The input file name
	 * <br><t><b> argv [1] = </b> The output file name 
	 */
	public static void main(String argv[])
	{
		String input, output;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter input file name: ");
		input = s.next();
		System.out.println("Enter output file name: ");
		output = s.next();
		setLex(input,output);
		
		/**Used to properly indent displayed syntactic category names*/
		String indent = "";
		
		getToken();
		funDefs(indent);
		if (!t.isEmpty())
		{
			displayln(t + "  -- unexpected symbol");
		}//if
		closeIO();
	}//main
}//ParseArith