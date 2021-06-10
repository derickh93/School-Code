/**
 * @(#)ParseE.java
 *
 * ParseE application
 *
 * @author 
 * @version 1.00 2009/12/5
 */
 import java.util.*;
 
public class ParseE extends LexAnalyzer
{
	private static int count = 0;
	private static int varCount = 0;
	private static String funNameHolder=null;
	private static HashMap  funNames_L = new HashMap ();
	private static HashMap  funNames_P = new HashMap ();
	private static HashMap  varHolder = new HashMap ();
	
	private static String stateToOperator[] = new String[13];

	private static void setStateToOperator()
	{
		stateToOperator[State.Add.ordinal()] = "add";
		stateToOperator[State.Sub.ordinal()] = "sub";
		stateToOperator[State.Mul.ordinal()] = "mul";
		stateToOperator[State.Div.ordinal()] = "div";
		stateToOperator[State.Or .ordinal()] = "or" ;
		stateToOperator[State.And.ordinal()] = "and";
		stateToOperator[State.Inv.ordinal()] = "inv";
		stateToOperator[State.Lt .ordinal()] = "lt" ;
		stateToOperator[State.Le .ordinal()] = "le" ;
		stateToOperator[State.Gt .ordinal()] = "gt" ;
		stateToOperator[State.Ge .ordinal()] = "ge" ;
		stateToOperator[State.Eq .ordinal()] = "eq" ;
		stateToOperator[State.Neq.ordinal()] = "neq";
	}
	
	private static boolean searchTable(Hashtable a,String b)
	{
		boolean value = false;
		
			if (a.containsKey(b))
			{
				value=true;
			}		
		
		return value;
	}				// search through a hashtable.

	private static void emitOperator(State op)
	{
		displayln("     "+stateToOperator[op.ordinal()]);
	}
	public static void funDefs()
	{
		displayln("     "+"goto 1");
		count++;
		
			while ( state == State.Id )
			{
				funDef();
			}
			if (state==State.Div)
			{
			displayln("1: ");
			getToken();
			expr();	
			}
			
			
		
	}

	public static void funDef()

	// <fun def> --> <header> <body>

	{

		displayln( ++count+":");
		header();
		body();
	}

	public static void header()

	// <header> --> <fun name> "(" <parameter list> ")"
	// <fun name> --> <id>

	{
		 
		if ( state == State.Id )
		{
			funNameHolder=t;
			
			if ( ! funNames_L.containsKey(t)  )
			{
				funNames_L.put(t,count);
			}
			
			getToken();
			if ( state == State.LParen )
			{
				getToken();
				parameterList();
				if ( state == State.RParen )
					
					getToken();

				else
					errorMsg(1);
			}
			else errorMsg(2);
		}
		else errorMsg(3);
	}

	public static void parameterList()

	// <parameter list> --> epsilon | <id> {"," <id>}

	{
		int locCount=0;

		if ( state == State.Id )
		{
			locCount++;

			getToken();
			while ( state == State.Comma )
			{
				getToken();
				if ( state == State.Id )
				{
					getToken();
					locCount++;
				}
				else
					errorMsg(3);
			}
		}
		if ( ! funNames_P.containsKey(funNameHolder)  )
		{
				funNames_P.put(funNameHolder,locCount);
		}
		
	}

	public static void body()
	
	// <body> --> "{" <Exp> "}"

	{

		if ( state == State.LBrace )
		{
			getToken();
			Exp();
			if ( state == State.RBrace )
			{
				displayln("     "+"return");
				getToken();
				varHolder.clear();
			}

			else
				errorMsg(4);
		}
		else
			errorMsg(5);
	}

	public static void Exp()

	// <Exp> --> "if" "(" <expr> ")" <Exp> "else" <Exp> | <expr>

	{
		
		if ( state == State.If )
		{
			int loccount=0;
			
			getToken();
			if ( state == State.LParen )
			{
				getToken();
				expr();
				if ( state == State.RParen )
				{
					displayln("     "+"if_f goto " + ++count);
					loccount = count;
					getToken();
					Exp();
					if ( state == State.Else )
					{
						displayln("     "+"goto "+ ++count);
						displayln(loccount + ":");
						getToken();
						Exp();
						loccount++;
						displayln(loccount + ":");
						
					}
				}
				else
					errorMsg(1);
			}
			else
				errorMsg(2);
		}
		else
			expr();
	}

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
	
	}

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
	}

	public static void boolPrimary()

	// <boolPrimary> --> <E> [ <relop> <E> ]

	{

		E();
		if ( state.compareTo(State.Lt) >= 0 &&
                     state.compareTo(State.Neq) <= 0 )

		// state = Lt, Le, Gt, Ge, Eq, or Neq

		{
			State op = state;
			getToken();
			E();
			emitOperator(op);
		}
	}

	public static void E()

	// <E> --> <term> { (+|-) <term> }

	{

		term();
		while ( state == State.Add || state == State.Sub )
		{
			State op = state;
			getToken();
			term();
			emitOperator(op);
		}
	}

	public static void term()

	// <term> --> <primary> { (*|/) <primary> }

	{
		primary();
		
		while ( state == State.Mul || state == State.Div )
		{
			State op = state;
			getToken();
			primary();
			emitOperator(op);
		}
		
	}

	public static void primary()

	// <primary> --> <id> | <int> | <float> | <floatE> | <fun E> |
        //               "(" <expr> ")" | - <primary> | ! <primary>
	// <fun E> --> <fun name> "(" <E list> ")"
	// <fun name> --> <id>

	{
		
		int locCount=0;
		int size;
		size=varHolder.size();
		

		switch ( state )
		{
			case Id:

			String id = t;

				 	
				getToken();

				if ( state == State.LParen )
				{
					getToken();
					Elist();
					if ( state == State.RParen )
						getToken();
					else
						errorMsg(1);
				}
				else

					if( ! varHolder.containsKey(id)) 
					{
						varHolder.put(id,++size);
						displayln( "     "+"push# "+ varHolder.size() );	
						return;
					}
					else
						displayln("     "+"push# "+ varHolder.get(id) );
						
						if(funNames_L.containsKey(id))
		 				{
		 					displayln("     "+"call "+funNames_L.get(id)+
		 											","+funNames_P.get(id));
				 		}
						return ;
			

							
			

			case Int: case Float: case FloatE:

				displayln("     "+"push: "+ t);
		     	getToken();
				return;

			case LParen:

				getToken();
				expr();
				if ( state == State.RParen )
					getToken();
				else
					errorMsg(1);
				return;

			case Sub:

				getToken();
				primary();
				displayln("     "+"neg");
				return;

			case Inv:

				getToken();
				primary();
				emitOperator(State.Inv);
				return;
				
			
				

			default:

				errorMsg(7);
				return;
		}

					
	}

	public static void Elist()

	// <E list> --> epsion | <expr> {"," <expr>}

	{
		if ( state != State.RParen )
		{
			expr();
			while ( state == State.Comma )
			{
				getToken();
				expr();
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
		
		setStateToOperator();

		getToken();
		funDefs();
		if ( ! t.isEmpty() )
			displayln(t + "  -- unexpected symbol");

		closeIO();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
