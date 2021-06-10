/*
Yachao Liu
CS316 - Prof. Yukawa
Project 3 - Top-Down Parser And Instructions Emission, Part I

I have used LexAnalyzer.java by Prof. Yukawa
and used and modified parseE.java by Prof. Yukawa as well. 
*/

/*
This class is a top-down, recursive-descent parser for the following
syntactic categories:

<statement> --> <assignment> | "{" <sList> "}"
<assignment> --> <id> "=" <expr> ";"
<sList> --> { <statement> }+
<expr> --> <boolTerm> { "||" <boolTerm> }
<boolTerm> --> <boolPrimary> { "&&" <boolPrimary>
<boolPrimary> --> <E> [ <relOp> <E> ]
<relOp> --> "<" | "<=" | ">" | ">=" | "==" | "!="
<E> --> <term> { (+|-) <term> }
<term> --> <primary> { (*|/) <primary> }
<primary> --> <id> | <int> | <float> | <floatE> | "(" <expr> ")" | - <primary> | ! <primary>
 

The definitions of the tokens are given in the lexical analyzer 
class file "LexAnalyzer.java". 

The program will do top-down parsing and emit the following 
stack-based virtual machine instructions:

  push
  add          +
  sub          binary -
  mul          *
  div          /
  neg          unary -
  or 		   ||
  and	       &&
  inv		   !
  lt		   <	
  le		   <=
  gt		   >
  ge		   >=
  eq 		   ==
  neq		   !=

The following variables and functions of the "LexAnalyzer" class are used:

static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token
static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

*/


import java.io.*;

public abstract class parseE extends LexAnalyzer
{
	private static String stateToOperator[] = new String[13];

	private static void setStateToOperator()
	{   //add the operator states
		stateToOperator[State.Add.ordinal()] = "add";
		stateToOperator[State.Sub.ordinal()] = "sub";
		stateToOperator[State.Mul.ordinal()] = "mul";
		stateToOperator[State.Div.ordinal()] = "div";
		stateToOperator[State.Or.ordinal()] = "or";
		stateToOperator[State.And.ordinal()] = "and";
		stateToOperator[State.Inv.ordinal()] = "inv";
		stateToOperator[State.Lt.ordinal()] = "lt";
		stateToOperator[State.Le.ordinal()] = "le";
		stateToOperator[State.Gt.ordinal()] = "gt";
		stateToOperator[State.Ge.ordinal()] = "ge";
		stateToOperator[State.Eq.ordinal()] = "eq";
		stateToOperator[State.Neq.ordinal()] = "neq";
	}

	private static void emitOperator(State op)
	{
		displayln( stateToOperator[op.ordinal()] );
	}
	
	public static void statement(){
		if(state == State.LBrace){						//either left brace or assignment
			getToken();
			sList();									//it is an sList
			if(state == State.RBrace)
				getToken();
			else
				errorMsg(3);
		}//if left brace
		else if(state == State.Id) assignment();		//if is id go to assignment
		else errorMsg(4);
	}//void Statement
	
	public static void assignment(){
		String temp = t;								//store the id
		
		getToken();
		if(state == State.Assign){						//if =
			getToken();
			expr();
			if(state == State.Semicolon){				//end with semicolon pop
				displayln("pop " + temp);
				getToken();
			}//if state is ;
			else errorMsg(5);	
		}//if state is =
		else errorMsg(6);
	}//void assignment
	
	public static void sList(){
		statement();									//one or more statements
		while(state == State.Id || state == State.LBrace)
			statement();
	}//void sList

	public static void expr(){
		State op;
		
		boolTerm();
		while(state == State.Or){						//iterative
			op = state;
			getToken();
			boolTerm();
			emitOperator(op);							//emit or
		}//while state is or
		
	}//void expr
	
	public static void boolTerm(){
		State op;
		
		boolPrimary();
		while(state == State.And){						//iterative
			op = state;
			getToken();
			boolPrimary();
			emitOperator(op);							//emit and
		}//while state is and
		
	}//void boolTerm
	
	public static void boolPrimary(){
		E();
		if(state == State.Lt || state == State.Le || state == State.Gt ||
		   state == State.Ge || state == State.Eq || state == State.Neq){
			relOp();
		}// if state is <, <=, >, >=, ==, !=
				
	}//void boolPrimary
	
	public static void relOp(){
		State op = state;
		getToken();
		E();
		emitOperator(op);
	}//void relOp
	
	public static void E()

	// <E> --> <term> { (+|-) <term> }

	{
		State op;

		term();
		while ( state == State.Add || state == State.Sub )
		{
			op = state;
			getToken();
			term();
			emitOperator(op);
		}
	} // end E

	public static void term()

	// <term> --> <primary> { (*|/) <primary> }

	{
		State op;

		primary();
		while ( state == State.Mul || state == State.Div )
		{
			op = state;
			getToken();
			primary();
			emitOperator(op);
		}
	} // end term

	public static void primary()

	// <primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")" | - <primary>

	{
		if ( state.compareTo(State.Id) >= 0 &&
		     state.compareTo(State.FloatE) <= 0 )

		// state == Id, Int, Float, or FloatE

		{
			displayln("push  "+t); 
		     	getToken();
		}
		else if ( state == State.LParen )
		{
			getToken();
			expr();
			if ( state == State.RParen )
				getToken();
			else
				errorMsg(1);
		}
		else if ( state == State.Sub )
		{
			getToken();
			primary();
			displayln("neg");
		}
		else if (state == State.Inv){
			getToken();
			primary();
			displayln("inv");
		}
		else
			errorMsg(2);
	} // end primary

	public static void errorMsg(int i)
	{
		display(t + ": unexpected symbol where");

		if ( i == 1 )             // called by primary()
			displayln(" arith op or ) expected");
		else if ( i == 2 )        // called by primary()
			displayln(" id, int, float, (, or - expected");
		else if(i == 3)			 // called by statement()
			displayln(" } expected");
		else if(i == 4)			// called by statement()
			displayln(" { or id expected");
		else if(i == 5)			//called by assignment()
			displayln(" ; expected");
		else if(i == 6)			//called by assignment()
			displayln(" = expected");
	} // end errorMsg

	public static void main(String argv[])

	// The input/output file names must be passed as argv[0] and argv[1].

	{
		setLex( argv[0], argv[1] );
		setStateToOperator();

		getToken();
		statement();									//start with statement
		if ( ! t.isEmpty() )
			displayln(t + "  -- unexpected symbol");

		closeIO();
	} // end main
}