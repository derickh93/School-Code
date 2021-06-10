/*
 * Queens College of City University of New York
 * Soham Thakor
 * Project 3
 * Date: 4/25/11
 * 
 * <statement> → <assignment> | <increment> | <decrement> |
              <block> | <cond> | <while> | <do>
<assignment> → <id> "=" <expr> ";"
<increment> → <id> "++" ";" 
<decrement> → <id> "--" ";" 
<block> → "{" <slist> "}"
<cond> → "if" "(" <expr> ")" <statement> [ "else" <statement> ]
<while> → "while" "(" <expr> ")" <statement>
<do> → "do" <statement> "while" "(" <expr> ")"
<slist> → <statement> | <statement> <slist>
<expr> → <boolTerm> | <boolTerm> || <expr>
<boolTerm> → <boolPrimary> | <boolPrimary> && <boolTerm>
<boolPrimary> → <E> [ <rel op> <E> ]
<rel op> → "<" | "<=" | ">" | ">=" | "==" | "!="
<E> → <term> | <term> + <E> | <term> - <E>
<term> → <primary> | <primary> * <term> | <primary> / <term>
<primary> → <id> | <int> | <float> | <floatE> | <boolLiteral> |
            "(" <expr> ")" | - <primary> | ! <primary> 
<boolLiteral> → "False" | "True"
 * */

public abstract class parseArith extends LexicalAnalyzerEC
{

	public static void statement(String s)
	{
		//<statement> → <assignment> | <increment> | <decrement> |<block> | <cond> | <while> | <do>
		
		displayln(s + s.length() + " " +  "<statement>");
		s = s + " ";
		
		if(state == State.LBRACE)
		{
			Block(s);
			return;
		}
		else if(state == State.IF)
		{
			cond(s);
			return;
				
		}
		else if(state == State.WHILE)
		{
		While(s);
		return;
		}
		else if(state == State.DO)
		{
			Do(s);
			return;
		}
		else if(state == State.ID)
		{
			assignment(s);
			return;
		}
		
		else
		{
			errorMsg(7);
			return;
		}
		
	}
	
	public static void assignment(String s)
	{
		// <assignment> --> <id> "=" <expr> ";"
		
		displayln(s + s.length() + " <assignment>");
		s = s + " ";
		
		if(state == State.ID)
		{
			displayln(s + s.length() + " " + t);
			getToken();
		
			if(state == State.ASSIGN)
			{
				displayln(s + s.length() + " " + t);
				getToken();
				expr(s);
				if(state == State.SEMICOLON)
				{
					getToken();
					return;
				}
				else
				{
					errorMsg(6);
					return;
				
				}
			}
			else
			{
				errorMsg(4);
				return;
			}
		}
		else
		{
			errorMsg(2);
			return;
		}
	}

	
	public static void increment(String s){
		
		// <increment> → <id> "++" ";

		
			if(state == State.INCR)
			{
				displayln(s + s.length() + " " + t);
				getToken();
				
				if(state == State.SEMICOLON)
				{
					getToken();
					return;
				}
				else
				{
					errorMsg(6);
					return;
				
				}
			}
			else
			{
				errorMsg(4);
				return;
			}
		}
		
	
		
	
	public static void decrement(String s){
		
		// <decrement> → <id> "--" ";" 
	
		
		
			if(state == State.DECR)
			{
				displayln(s + s.length() + " " + t);
				getToken();
				
				if(state == State.SEMICOLON)
				{
					getToken();
					return;
				}
				else
				{
					errorMsg(6);
					return;
				
				}
			}
			else
			{
				errorMsg(4);
				return;
			}
		}
		
public static void While (String s){
		
		// <while> → "while" "(" <expr> ")" <statement>
		
		displayln(s + s.length() + " " + t);
		s = s + " ";
		getToken();
		if(state == State.LPAREN)
		{
			getToken();
			expr(s);
			if(state == State.RPAREN)
			{
				getToken();
				statement(s);
				return;
			}
			else
			{
				errorMsg(1);
				return;
			}
		}
		else
		{
			errorMsg(3);
			return;
		}
	}
		
		public static void Do(String s){
			
			// <do> → "do" <statement> "while" "(" <expr> ")"
			displayln(s + s.length() + " " + t);
			s = s + " ";
			getToken();
			statement(s);
			
			getToken();
			if(state == State.WHILE){
			getToken();
			if(state == State.LPAREN)
			{
				getToken();
				expr(s);
				if(state == State.RPAREN)
				{
					return;
				}
				else
				{
					errorMsg(1);
					return;
				}
			}
			else
			{
				errorMsg(1);
				return;
			}
			}
			else
				return;
		}
		
	
	
	
	
	
	
	public static void SList(String s)
	{
		// <slist> → <statement> | <statement> <slist>
		
		displayln(s + s.length() + " <SList>");
		s = s + " ";
		
		statement(s);
		
		while(state == State.ID || state == State.LBRACE
				|| state == State.IF || state == State.WHILE || state == State.DO)
		{
			
			displayln(s + s.length() + " <SList>");
			s = s + " ";
			statement(s);
		}
	}
	
	public static void Block(String s)
	{
		//<block> --> "{" <SList> "}"
		displayln(s + s.length() + " <Block>");
		s = s + " ";
		
		if(state == State.LBRACE)
		{
			getToken();
			SList(s);
			if(state == State.RBRACE)
			{
				getToken();
				return;
			}
			else
			{
				errorMsg(5);
				return;
			}
		}
		
	}
	
	
	public static void cond (String s){ 
		// <cond> → "if" "(" <expr> ")" <statement> [ "else" <statement> ]
		displayln(s + s.length() + " " + t);
		s = s + " ";
		getToken();
		if(state == State.LPAREN)
		{
			getToken();
			expr(s);
			if(state == State.RPAREN)
			{
				getToken();
				statement(s);
				
				if(state == State.ELSE)
				{
					displayln(s + s.length() + " " + t);
					s = s + " ";
					getToken();
					statement(s);
					return;
				}
				else
				{
					return;
				
				}
			}
			else
			{
				errorMsg(1);
				return;
			}
		}
		else
		{
			errorMsg(3);  
			return;
		}
	}
	
	public static void expr(String s)
	{
		// <expr> → <boolTerm> | <boolTerm> || <expr>
		
		displayln(s + s.length() + " <expr>");
		s = s + " ";
		
		boolTerm(s);
		
		while(state == State.OR)
		{
			displayln(s + s.length() + " " + t);
			getToken();
			boolTerm(s);
		}
	}
	
	public static void boolTerm(String s)
	{
		// <boolTerm> → <boolPrimary> | <boolPrimary> && <boolTerm>
		
		displayln(s + s.length() + " <boolTerm>");
		s = s + " ";
		
		boolPrimary(s);
		
		while(state == State.AND)
		{
			displayln(s + s.length() + " " + t);
			getToken();
			boolPrimary(s);
		}
	}
	
	public static void boolPrimary(String s)
	{
		// <boolPrimary> → <E> [ <rel op> <E> ]
		
		displayln(s + s.length() + " <boolPrimary>");
		s = s + " ";
		
		E(s);
		
		if(state == State.LT || state == State.LE || state == State.GT
				              || state == State.GE || state == State.EQ || state == State.NEQ)
		{
			
			relOp(s);
			E(s);
			return;
		}
	}
	
	public static void relOp(String s)
	{
		// <relOp> --> "<"|"<="|">"|">="|"=="|"!="
		
		if(state == State.LT)
		{
			displayln(s + s.length() + " " + t);
			getToken();
			return;
		}
		else if(state == State.LE)
		{
		
			displayln(s + s.length() + " " + t);
			getToken();
			return;
		}
		else if(state == State.LT)
		{
			displayln(s + s.length() + " " + t);
			getToken();
			return;
		}
		else if(state == State.GT)
		{
			displayln(s + s.length() + " " + t);
			getToken();
			return;
		}
		else if(state == State.GE)
		{
			displayln(s + s.length() + " " + t);
			getToken();
			return;
		}
		else if(state == State.EQ)
		{
			displayln(s + s.length() + " " + t);
			getToken();
			return;
		}
		else if(state == State.NEQ)
		{
			displayln(s + s.length() + " " + t);
			getToken();
			return;
		}
		else
		{
			errorMsg(8);
		}
	}
    public static void E(String s)

    // <E> → <term> | <term> + <E> | <term> - <E>

    {
        displayln(s + s.length() + " <E>");
        s = s + " ";

        term(s);
        while ( state == State.ADD || state == State.SUB )
        {
            displayln(s+ s.length()+ " "+ t);
            getToken();
            term(s);
        }
    } // end E


    public static void term(String s)

    // <term> → <primary> | <primary> * <term> | <primary> / <term>

    {
        displayln(s+ s.length()+ " <term>");
        s = s+" ";

        primary(s);
        while ( state == State.MUL || state == State.DIV )
        {
            displayln(s+s.length()+" "+t);
            getToken();
            primary(s);
        }
    } // end term


    public static void primary(String s)

    // <primary> → <id> | <int> | <float> | <floatE> | <boolLiteral> |
    // "(" <expr> ")" | - <primary> | ! <primary> 

    {
        display(s+ s.length()+ " <primary>");
        s = s + " ";

        switch( state )
        {
        case ID:
        	displayln(" " + t);
            getToken();
            return;
        	
        case INT:
        	displayln(" "+t);
            getToken();
            return;
        	
        case FLOAT:
        	displayln(" "+t);
            getToken();
            return;
        	
        case FLOATE:

            displayln(" "+t);
            getToken();
            return;
        
        case TRUE:
          displayln("\n");
        	displayln(s+s.length()+"<boolLiteral> "+t);
        	getToken();
        	return;
        	
        case FALSE:
        	
        	displayln("\n");
            displayln(s+s.length()+"<boolLiteral> "+t);
        	getToken();
        	return;
            
            
        case LPAREN:

            displayln("");

            getToken();
            expr(s);
            if ( state == State.RPAREN )
                getToken();
            else
                errorMsg(1);
            return;
            
        case SUB:
        	
        	displayln("");
        	display(s + s.length() + " " + t);
        	getToken();
        	displayln("");
        	primary(s);
        	return;
        	
        case INV:
        	
        	displayln("");
        	displayln(s + s.length() + " " + t);
        	getToken();
        	primary(s);
        	return;
        	

        default:
            errorMsg(2);
            return;
        }
    } // end primary

    public static void errorMsg(int i)
    {
        display(t + " : unexpected symbol where");

        switch( i )
        {
        case 1: displayln(" arith op or ) expected"); return;
        case 2: displayln(" id, int, float, or ( expected"); return;
        case 3: displayln(" ( expected"); return;
        case 4: displayln(" = expected"); return;
        case 5: displayln(" } expected"); return;
        case 6: displayln(" ; expected"); return;
        case 7: displayln(" id, {, if, while, or do expected"); return;
        case 8: displayln(" <, <=, >, >=, ==, != expected"); return;
        
        }
    } // end errorMsg

    public static void main(String argv[])

    // The input/output file names must be passed as argv[0] and argv[1].

    {
        setLex( argv[0], argv[1] );

        String s = ""; // used to properly indent displayed
                                    // syntactic category names

        getToken();
        statement(s);
        if ( ! t.isEmpty() )
            displayln(t + "  : unexpected symbol");

        closeIO();
    } // end main
}


