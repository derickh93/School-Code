import java.util.*;
/**
Angel Guevara, Jr.
CS316 Project 4
Fall 2009
*/
public class Emission extends LexAnalyzer
{
	/*Global Variables**/
	private static Hashtable<String, Hashtable<Integer, Integer>> functions = new Hashtable<String,Hashtable<Integer,Integer>>();
	private static Hashtable<String,Hashtable<Integer,ArrayList<String>>> functionParams = new Hashtable<String, Hashtable<Integer,ArrayList<String>>>();
	private static int counter=1;
	private static String currentFName="";
	private static int currentFNum=0;
		
	/**
	 * < fun defs > → { < fun def > }<sup>+</sup>
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void funDefs()
	{
		int start = counter;
		displayln("\tgoto " + start);
		funDef();
		while (a != -1 && (state == State.Id || state == State.Id_i ||	state == State.Id_e || state == State.Id_el ||state == State.Id_els))
			funDef();
		if (a != -1 && state == State.Div)
		{
		    getToken();
		    displayln(start + ":");
		    ArrayList<String> prgm = expr();
		    for (String temp : prgm)	displayln(temp);
		}//if
	}//funDefs 
	
	/**
	 * < fun def > → < header > < body >
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void funDef()
	{
		counter++;
		header();
		body();
	}//funDef
	
	/**
	 * < header > → < fun name > "(" < parameter list > ")"
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void header()
	{
		String functionName = funName();
		Hashtable<Integer,Integer> f;
		int numParams = parameterList(functionName);
		displayln(counter + ":");
        if (functions.containsKey(functionName))	f = functions.get(functionName);
        else
        {
            f = new Hashtable<Integer,Integer>();
            functions.put(functionName,f);
        }//else
        f.put(numParams,counter);
        currentFName = functionName;
        currentFNum = numParams;
	}//header
	
	/**
	 * < fun name > → < id >
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static String funName()
	{
		String name = "";	
	    while (state == State.Id || state == State.Id_e || state == State.Id_el|| state == State.Id_els || state == State.Id_i)
		{
			name += t;
			getToken();
		}//while
		if (state == State.If || state == State.Else)	errorMsg("id");
		return name;
	}//funName

	/**
	 * < parameter list > → ε | < id > {"," < id >}
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static int parameterList(String funName)
	{
	    ArrayList<String> parameters = new ArrayList<String>();
	    Hashtable<Integer,ArrayList<String>> functions;
	    if (functionParams.containsKey(funName))	functions = functionParams.get(funName);
	    else	     
	    {
	        functions = new Hashtable<Integer,ArrayList<String>>();	    
	        functionParams.put(funName,functions);
	    }//else
		if (state == State.LParen)
		{
			getToken();
			while (state == State.Id || state == State.Id_e|| state == State.Id_el || state == State.Id_els|| state == State.Id_i)
			{
				parameters.add(t);
				getToken();
				if (state == State.Comma)	getToken();
			}//while
			if (state == State.RParen)	getToken();
			else	errorMsg(")");
		}//if
		else	errorMsg("(");
	    functions.put(parameters.size(),parameters);
        return parameters.size();
	}//parameterList
	
	/**
	 * < body > → "{" < Exp > "}"
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void body()
	{
		if (state == State.LBrace)
		{
			getToken();	
		    ArrayList<String> bodyBlock = Exp();
			if (state == State.RBrace)	getToken();
			else	errorMsg("}");
			for(String temp : bodyBlock)	displayln(temp);
		    displayln("\treturn");
		}//if
		else	errorMsg("{");
	}//body
	
	/**
	 * < Exp > → "if" "(" < expr > ")" < Exp > "else" < Exp > | < expr >
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static ArrayList<String> Exp()
	{
    	ArrayList<String> block = new ArrayList<String>();
		if (state == State.If)
		{
			getToken();
			if (state == State.LParen)
			{				
				getToken();
		        counter++;
		        int elselbl = counter;
		        counter++;
		        int outlbl = counter;
		        ArrayList<String> tBlock, eBlock;
		        tBlock = new ArrayList<String>();
		        eBlock = new ArrayList<String>();				
				block.addAll(expr());
				if (state == State.RParen)	getToken();
				else	errorMsg(")");
    			tBlock = Exp();
				if(state == State.Else)
				{
					getToken();
					eBlock = Exp();
				}//if
				else	errorMsg("else");
				block.add("\tif_f goto " + elselbl); 			
				block.addAll(tBlock);                    		
				block.add("\tgoto " + outlbl);       			
				block.add(elselbl + ":");                		
				block.addAll(eBlock);                    		
				block.add(outlbl + ":");                 		
			}//if
			else	errorMsg("(");
		}//if
		else	return expr();
		return block;	
	}//Exp
	
	/**
	 * < expr > → < bool term > { "||" < bool term > }
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static ArrayList<String> expr()
	{
		ArrayList<String> block = new ArrayList<String>();	    
		block.addAll(boolTerm());		
		while (state == State.Or)
		{
			getToken();
			block.addAll(boolTerm());
			block.add("\tOR");
		}//while
        return block;
	}//expr
	
	/**
	 * < bool term > → < bool primary > { "&&" < bool primary > }
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static ArrayList<String> boolTerm()
	{
		ArrayList<String> block = new ArrayList<String>();
		block.addAll(boolPrimary());
		while (state == State.And)
		{
			getToken();
			block.addAll(boolPrimary());
			block.add("\tand");
		}//while
        return block;
	}//boolTerm
	
	/**
	 * < bool primary > → < E > [ < rel op > < E > ]
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static ArrayList<String> boolPrimary()
	{
		ArrayList<String> block = new ArrayList<String>();	
		block.addAll(E());
		if(state==State.Lt || state==State.Le || state==State.Gt || state==State.Ge || state==State.Eq || state==State.Neq)
			relOp(block);
		return block;
	}//boolPrimary
	
	/**
	 * < rel op > → "<" | "<=" | ">" | ">=" | "==" | "!="
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static void relOp(ArrayList<String> block)
	{
		State previousState = state;							
		getToken();         									
		block.addAll(E());  									
		if(previousState==State.Lt)			block.add("\tLT");
		else if(previousState==State.Le)	block.add("\tLE");
		else if(previousState==State.Gt)	block.add("\tGT");
		else if(previousState==State.Ge)	block.add("\tGE");
		else if(previousState==State.Eq)	block.add("\tEQ");
		else if(previousState==State.Neq)	block.add("\tNEQ");
		else	errorMsg("<, <=, >, >=, ==, or !=");
	}//relOp
	
	/**
	 * < E > → < term > { (+|-) < term > }
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static ArrayList<String> E()
	{
		ArrayList<String> block = new ArrayList<String>();
		block.addAll(term());     								
		while (state == State.Add || state == State.Sub)		
		{
		    State op = state;     								
			getToken();           								
			block.addAll(term()); 								
			if(op==State.Add)		block.add("\tADD");
			else if(op==State.Sub)	block.add("\tSUB");
		}//while
		return block;
	}//E

	/**
	 * < term > → < primary > {(*|/) < primary > }
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static ArrayList<String> term()
	{
		ArrayList<String> block = new ArrayList<String>();
		block.addAll(primary());								
		while (state == State.Mul || state == State.Div)		
		{
            State op = state;
			getToken();
			block.addAll(primary());							
			if(op==State.Mul)		block.add("\tMUL");
			else if(op==State.Div)	block.add("\tDIV");
		}//while
        return block;
	}//term

	/**
	 * <br>< primary > → < id > | < int > | < float > | < float E > | < fun E > | "(" < expr > ")" | - < primary > | ! < primary >
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static ArrayList<String> primary()
	{
		ArrayList<String> block = new ArrayList<String>();
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
					temp += t;  								
					getToken(); 								
				}
				if (state == State.LParen) 						
				{
					int callSig = eList(block);				    
					if (functions.containsKey(temp))			
					{
					    Hashtable<Integer,Integer> func = functions.get(temp);	
					    if (func.containsKey(callSig))
					        block.add("\tcall " + func.get(callSig) + ", " +callSig);
                    }//if
				}//if
				else 											
				{
				    int pNum = functionParams.get(currentFName).get(currentFNum).indexOf(temp);	
				    block.add("\tpush #" + (pNum+1));				
				}//else
				break;
			case Int:
				while (state == State.Int)
				{
					temp += t;
					getToken();
				}//while    		
			case Period:
				if (state == State.Period)
				{
					temp += t;
					getToken();
				}//if
			case Float:
			case FloatE:
				while (state == State.Float || state == State.FloatE)
				{
					temp += t;
					getToken();
				}//while
                block.add("\tpush " + temp);
				break;
			case LParen:
				getToken();
				block.addAll(expr());
				if (state != State.RParen)	errorMsg(")");
				else	getToken();
				break;
			case Inv:
				getToken();
				block.addAll(primary());
			    block.add("\tINV");
			    break;
			case Sub:
				getToken();
				block.addAll(primary());
                block.add("\tNEG");
				break;
			case If:
			case Else:
			default:
				errorMsg("ID, INT, FLOAT, FLOAT E, FUN E, -, !, or (");
		}//swich
		return block;
	}//primary
	
	/**
	 * < E list > → ε | < expr > { "," < expr > }
	 * 
	 * @param indent - Used to properly indent displayed syntactic category names
	 */
	public static int eList(ArrayList<String> block)
	{
		int count = 0;
		if (state == State.LParen)
		{
			getToken();
			if (state != State.RParen)
			{
				block.addAll(expr());
				count++;
				while (state == State.Comma)
				{
					getToken();
					block.addAll(expr());
					count++;
				}//while
			}//if
			if (state == State.RParen)	getToken();
			else	errorMsg(")");
		}//if
        return count;
	}//eList
//-----------------------------------------------------------------------------------------------
	/**
	 * Displays an appropriate error message
	 * 
	 * @param expected = the token that was expected
	 */
	public static void errorMsg(String expected)
	{
		displayln("ERROR: " + "\"" + t + "\" found, but \"" + expected + "\" was expected.\tState: " + state);
	}//errorMsg
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String input,output;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter input file name: ");
		input = s.next();
		System.out.println("Enter output file name: ");
		output = s.next();
		setLex(input,output);
		getToken();
		funDefs();
		closeIO();
	}//main
}//Emission
