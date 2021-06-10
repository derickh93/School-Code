/*
* Top-down Emitter based on Parser
* Based on: http://picasso.cs.qc.cuny.edu/cs316/parseArith.java
* With modifications by: Brian Ruslim (bruslim100@qc.cuny.edu)
*/

import java.util.*;

public class Emitter extends LexicalAnalyzer
{

    /**
     * Hashtable used to store functions
     *
     * <function name, <param count , goto line> >
     */     
    private static Hashtable< String, Hashtable< Integer, Integer > > functions 
        = new Hashtable< String, Hashtable< Integer, Integer > >();

    // <function name, <param count, param list>    
    private static Hashtable< String, Hashtable< Integer, ArrayList<String> > >
        functionParams = new Hashtable< String, Hashtable< Integer, 
        ArrayList<String> > >();
    
    // Start Label at:    
    private static int functionCount = 0;
    
    // Variables which keep track of current function being parsed
    private static String currFuncName = "";
    private static int currFuncSig = 0;        

    // Padding Constant
	private static final String TAB = "    ";
	
	/**
	* <fun defs> -> {<fun def>}+ / <expr>
	*/
	private static void fun_defs()
	{
	    int start = functionCount;
        // Line which points to start of exectuion:
		displayln(TAB + "goto " + start);

		do
		{		
	        fun_def();	
	    }while (a != -1 && (state == state.Id || state == State.Id_i ||	state == State.Id_e || state == State.Id_el ||state == State.Id_els));
			
	    // Parse execution line:
		if (a != -1 && state == State.Div)
		{
		    getToken(); //discard Div
		    displayln(start + ":");
		    ArrayList<String> prgm = expr();
		    for (String temp : prgm)
		        displayln(temp);
		}//if
	}//fundefs

	/**
	* <fun def> -> <header> <body>
	*/
	private static void fun_def()
	{
	    ++functionCount;
		header();
		body();    	
	}

	/**
	* <header> -> <fun name> "(" <parameter list> ")"
	*/
	private static void header()
	{
	    // Print jump label:
        displayln(functionCount + ":");
                
		String n = fun_name(); // parse and store the function name
		
		Hashtable<Integer,Integer> f; // Signature -> Jump Label
		
        if (functions.containsKey(n)) // if functions has function name n
            f = functions.get(n);
        else
        {
            f = new Hashtable<Integer,Integer>(); // make new hashtable
            functions.put(n,f); // map it! n -> f
        }
            
  		int s = parameter_list(n); // parse and count number of paramters
  		
        if (f.containsKey(s))
            errDie("Function " + n + " with " + s + " parameter" + (s == 1 ? "" : "s" ) + " has already been declared.");
                
        f.put(s,functionCount);
        
        // Set current function variables:
        currFuncName = n;
        currFuncSig = s;
	}

	/**
	 * <fun name> -> <id>
	 *
	 * @return the function name
	 */
	private static String fun_name()
	{
	    String name = "";	
	    
		while (state == State.Id || state == State.Id_e || state == State.Id_el|| state == State.Id_els || state == State.Id_i)
		{
			name += t;
			getToken();
		}
		if (state == State.If || state == State.Else)
		{
			errDie2("id");
		}
		
		return name;
	}

	/**
	* <parameter list> -> ? | <id> {"," <id>}
	*/
	private static int parameter_list(String funName)
	{
    	// Create empty parameter list
	    ArrayList<String> params = new ArrayList<String>();
	    
	    // Signature -> List of IDs
	    Hashtable<Integer,ArrayList<String>> func;
	    
	    // Get the hashtable for funName
	    if (functionParams.containsKey(funName))	    
            func = functionParams.get(funName);
	    else	     
	    {
	        func = new Hashtable<Integer,ArrayList<String>>();	    
	        functionParams.put(funName,func);
	    }	    		
		
		if (state == State.LParen)
		{
			getToken();     // Discard (
			while (state == State.Id || state == State.Id_e|| state == State.Id_el || state == State.Id_els|| state == State.Id_i)
			{
				params.add(t);
				getToken();
				if (state == State.Comma) // parse out the commas
				    getToken();
			}
			if (state == State.RParen)
				getToken(); // Discard )
			else
				errDie2(")");
		}
		else
			errDie2("(");

	    func.put(params.size(),params); // Map it! Signature -> List of IDs

        return params.size(); // Return Signature
	}

	/**
	* <body> -> "{" <Exp> "}"
	*/
	private static void body()
	{		
		if (state == State.LBrace)
		{
			getToken();     // discard {	
		    ArrayList<String> bodyBlock = exp();
            
			if (state == State.RBrace)
				getToken(); // discard }
			else
				errDie2("}");
				
			for (String temp : bodyBlock)displayln(temp);
		    displayln(TAB + "return");
		}
		else
			errDie2("{");
	}

	/**
	* <Exp> -> "if" "(" <expr> ")" <Exp> "else" <Exp> | <expr>
	*/
	private static ArrayList<String> exp()
	{
	    // block stores a block of emitted code;
    	ArrayList<String> block = new ArrayList<String>();
		if (state == State.If)
		{
			getToken(); //discard if
			if (state == State.LParen)
			{				
				getToken(); //discard (

                // Compute Jump Labels
		        ++functionCount;
		        int elselbl = functionCount; // Else Label
		        ++functionCount;
		        int outlbl = functionCount;  // Out Label (return)
		        		        
		        ArrayList<String> tBlock, eBlock; // True Block, Else Block
		        tBlock = new ArrayList<String>();
		        eBlock = new ArrayList<String>();				
				
				block.addAll(expr());                    // Parse/emit <expr>
				
				// Checking for )
				if (state == State.RParen)
					getToken(); // discard )
				else
					errDie2(")");                
    			
    			tBlock = exp(); // Parse True Block

				if(state == State.Else)
				{
					getToken(); // discard else
					eBlock = exp(); // Parse Else Block			
				}
				else
					errDie2("else");
				
				// Emitted Code...
				block.add(TAB + "if_f goto " + elselbl); // if false goto label
				block.addAll(tBlock);                    // emit true block
				block.add(TAB + "goto " + outlbl);       // skip else block
				block.add(elselbl + ":");                // print else label
				block.addAll(eBlock);                    // emit else block
				block.add(outlbl + ":");                 // print out label
			}//if
			else
				errDie2("(");			
		}//if
		else return expr();	
		return block;	
	}//exp

	/**
	* <expr> -> <boolTerm> { "||" <boolTerm> }
	*/
	private static ArrayList<String> expr()
	{
	    ArrayList<String> block = new ArrayList<String>();	    
		block.addAll(boolTerm());		
		while (state == State.Or)
		{
			getToken(); // discard Or
			block.addAll(boolTerm());
			block.add(TAB + "or");
		}
        return block;
	}

	/**
	* <boolTerm> -> <boolPrimary> { "&&" <boolPrimary> }
	*/
	private static ArrayList<String> boolTerm()
	{
	    ArrayList<String> block = new ArrayList<String>();
		block.addAll(boolPrimary());
		while (state == State.And)
		{
			getToken();
			block.addAll(boolPrimary());
			block.add(TAB + "and");
		}
        return block;
	}

	/**
	* <boolPrimary> -> <E> [ <rel op> <E> ]
	*/
	private static ArrayList<String> boolPrimary()
	{
	    ArrayList<String> block = new ArrayList<String>();	
		block.addAll(E()); // parse E
		switch (state)
		{
			case Lesser:
			case LesserEqual:
			case Greater:
			case GreaterEqual:
			case Equal:
			case NotEqual:
				rel_op(block); // use rel_op to append to block
		}
		return block;
	}

	/**
	* <rel op> -> "<" | "<=" | ">" | ">=" | "==" | "!="
	*/
	private static void rel_op(ArrayList<String> block)
	{
	    State rel = state;  // store relation
		getToken();         // discard relation
		block.addAll(E());  // parse E
		switch (rel)        // parse/emit rel
		{
			case Lesser:
                block.add(TAB + "lt");
				break;
			case LesserEqual:
                block.add(TAB + "le");
				break;
			case Greater:
                block.add(TAB + "gt");
				break;
			case GreaterEqual:
                block.add(TAB + "ge");
				break;
			case Equal:
                block.add(TAB + "eq");
				break;
			case NotEqual:
                block.add(TAB + "neq");
				break;
			default:
				errDie2("< OR <= OR > OR >= OR == OR !=");
		}
	}

	/**
	* <E> --> <term> { (+|-) <term> }
	*
	* @param indent
	*/
	public static ArrayList<String> E()
	{
	    ArrayList<String> block = new ArrayList<String>();
		block.addAll(term());     // parse/emit term
		while (state == State.Plus || state == State.Minus)
		{
		    State op = state;     // store operator
			getToken();           // discard operator
			block.addAll(term()); // parse/emit term
			switch (op)           // parse/emit operator
			{
			    case Plus:
			        block.add(TAB + "add"); // emit add cmd
			        break;
			    case Minus:
			        block.add(TAB + "sub"); // emit sub cmd
			        break;
			}
		}
		return block;
	} // end E

	/**
	* <term> --> <primary> { (*|/) <primary> }
	*
	* @param indent
	*/
	public static ArrayList<String> term()
	{
        ArrayList<String> block = new ArrayList<String>();

		block.addAll(primary());
		while (state == State.Times || state == State.Div)
		{
            State op = state;
			getToken();
			block.addAll(primary());
			switch (op)
			{
			    case Times:
			        block.add(TAB + "mul");
			        break;
			    case Div:
			        block.add(TAB + "div");
			        break;
			}
		}		
        return block;
	} // end term

	/**
	* <primary> --> <id> | <int> | <float> | <fun E> "(" <expr> ")"
	*                    | − <primary> | ! <primary>
	*
	* @param indent
	*/
	public static ArrayList<String> primary()
	{
        ArrayList<String> block = new ArrayList<String>();
		// state == Id, Int, Float, or FloatE
		String temp = "";
		switch (state)
		{
			case Id:
			case Id_i:
			case Id_e:
			case Id_el:
			case Id_els:
				while (state == State.Id || state == State.Id_i ||state == State.Id_e || state == State.Id_el ||state == State.Id_els)
				{
					temp += t;  // store ID in temp variable
					getToken(); // discard ID
				}
				if (state == State.LParen) // Function call
				{
				     // append to block and compute function call signature:
					int callSig = expr_list(block);
					// if function name in temp has be defined or die:
					if (functions.containsKey(temp))
					{
					    // Get map of: signatures -> jump label:
					    Hashtable<Integer,Integer> func = functions.get(temp);
					    // emit call if defined, or die:
					    if (func.containsKey(callSig))
					        block.add(TAB + "call " + func.get(callSig) + ", " +callSig);
			            else
			                errDie("Undefined function: " + temp + " with " + callSig + " parameters");
                    }//if
                    else
                        errDie("Undefined function: " + temp);
				}//if
				else // refrence to ID (parameter)
				{
				    // get the parameter Number to emit
				    int pNum = functionParams.get(currFuncName).get(currFuncSig).indexOf(temp);
				    if (pNum < 0) errDie("Unknown parameter: " + temp);
				    block.add(TAB + "push #" + (pNum+1));				
				}
				break;

			case Int:
				while (state == State.Int)
				{
					temp += t;  // store int part
					getToken(); // discard int
				}    		
			case Period:
				if (state == State.Period)
				{
					temp += t;  // store .
					getToken(); // Discard .
				}
			case Float:
			case FloatE:
				while (state == State.Float || state == State.FloatE)
				{
					temp += t;  // store float/floatE part
					getToken(); // discard float/floatE part
				}
                block.add(TAB + "push " + temp); // emit Number
				break;

			case LParen:
				getToken(); // discard (
				block.addAll(expr()); // parse/emit expr
				if (state != State.RParen)				
					errDie2(")");				
				else				
					getToken(); // discard )
				break;
			
			case Not:
				getToken();
				block.addAll(primary()); // parse/emit primary
			    block.add(TAB + "inv");  // emit inv cmd
			    break;
			case Minus:
				getToken();
				block.addAll(primary());
                block.add(TAB + "neg");
				break;
			case If:
			case Else:
			default:
				errDie2("id OR number OR E OR - OR ! OR (");
		}
		return block;		
	} // end primary

	public static int expr_list(ArrayList<String> block)
	{
	    int count = 0; // defualt expr_list count is 0
		if (state == State.LParen)
		{
			getToken(); // discard )
			if (state != State.RParen)
			{
				block.addAll(expr());      // parse/emit expr
				++count;                   // +1 expression
				while (state == State.Comma)
				{
					getToken();            // Discard ,
					block.addAll(expr());  // parse/emit expr
					++count;               // +1 expression
				}
			}
			if (state == State.RParen)
			{
				getToken(); // Discard )
			}
			else
				errDie2(")");
		}
        return count;
	}

    public static void errDie(String msg)
    {
        displayln("!!== FATAL ERROR (Line: " + currentLine + "): " + msg + 
            " ==!!");
        closeIO();
        System.exit(1);
    }
    
    public static void errDie2(String expected)
    {
		display("!!== FATAL ERROR(Line: " + currentLine + "): ");
		switch (state)
		{
			case If:
			case Else:
				displayln(t + " is a reserved symbol, where " + expected + 
				    " expected ==!!");
				break;
			default:
				displayln(t + " unexpected symbol where " + expected
					+ " expected ==!!");
		}
		closeIO();
		System.exit(1);
	} // end errorMsg

	/**
	* The input/output file names must be passed as argv[0] and argv[1].
	*
	* @param argv
	*/
	public static void main(String argv[])
	{
		setLex(argv[0], argv[1]);
		getToken();
		fun_defs();
		closeIO();
	} // end main
}
