/**

This program was written and tested with JDK 1.6.0.

This class is a top-down, recursive-descent parser for the following
syntactic categories:

<E> --> <term> { (+|-) <term> }
<term> --> <primary> { (*|/) <primary> }
<primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")" | - <primary> 

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

The following variables and functions of the "LexAnalyzer" class are used:

static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token
static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

**/


import java.io.*;

public abstract class parseE extends LexAnalyzer
{

	private static String stateToOperator[] = new String[13];

	private static void setStateToOperator()
	{
		stateToOperator[State.Add.ordinal()] = "add";
		stateToOperator[State.Sub.ordinal()] = "sub";
		stateToOperator[State.Mul.ordinal()] = "mul";
		stateToOperator[State.Div.ordinal()] = "div";
		stateToOperator[State.Or.ordinal()] = "Or";
		stateToOperator[State.And.ordinal()] = "And";
		stateToOperator[State.Inv.ordinal()] = "Inv";
		stateToOperator[State.Lt.ordinal()] = "Lt";
		stateToOperator[State.Le.ordinal()] = "Le";
		stateToOperator[State.Gt.ordinal()] = "Gt";
		stateToOperator[State.Ge.ordinal()] = "Ge";
		stateToOperator[State.Eq.ordinal()] = "Eq";
		stateToOperator[State.Neq.ordinal()] = "Neq";
	}

	private static void emitOperator(State op)
	{
		System.out.println(stateToOperator[op.ordinal()].toString());
		displayln( stateToOperator[op.ordinal()] );
	}

	// <statement> --> <assignment> | "{" <S list> "}"
	public static void statement(){
		System.out.println("-Statement-");
		State op;
		
		if(state == State.LBrace){
			System.out.println("{");
			op = state;
			getToken();
			SList();
			if(state != State.RBrace)
				errorMsg(4);
			else
				getToken();
		}
		else {
			assignment();
		}
	} // end statement

	// <assignment> --> <id> "=" <expr> ";"
	public static void assignment(){
		System.out.println("-Assignment-");
		State op;

		if(state == State.Id){
			String tempT = t;
			op = state;
			getToken();
			
			if(state == State.Assign){
				op = state;
				getToken();
				expr();
				
				if(state == State.Semicolon)
					displayln("Pop " + tempT);
				else
					errorMsg(3);
			}
		}
	} // end assignment

	// <S list> --> { <statement> }+
	public static void SList(){
		System.out.println("-SList-");
		State op;
		statement();

		while(state == State.Semicolon || state == State.Id){
			if(state == State.Semicolon){
				op = state;
				getToken();
			}
			statement();
		}
	} // end S list

	// <expr> --> <boolTerm> { "||" <boolTerm> }
	public static void expr(){
		System.out.println("-Expr-");
		State op;
		boolTerm();

		while(state == State.Or){
			op = state;
			getToken();
			boolTerm();
			emitOperator(op);
		}
	} // end expr

	// <boolTerm> --> <boolPrimary> { "&&" <boolPrimary> }
	public static void boolTerm(){
		System.out.println("-BoolTerm-");
		State op;
		boolPrimary();
		
		while(state == State.And){
			op = state;
			getToken();
			boolPrimary();
			emitOperator(op);
		}
	} // end boolTerm

	// <boolPrimary> --> <E> [ <rel op> <E> ]
	public static void boolPrimary(){
		System.out.println("-BoolPrimary-");
		State op;
		E();
		if(relOp()){
			op = state;
			getToken();
			E();
			emitOperator(op);
		}
	} // end boolPrimary

	// <rel op> --> "<" | "<=" | ">" | ">=" | "==" | "!="
	public static boolean relOp(){
		System.out.println("-rel op-");
		State op;

		if (state == State.Lt || state == State.Le || state == State.Gt || state == State.Ge ||
				state == State.Eq || state == State.Neq){
			return true;
		}
		return false;
	} // end rel op

	/* <E> --> <term> { (+|-) <term> } */
	public static void E(){
		System.out.println("-E-");
		State op;
		term();

		while ( state == State.Add || state == State.Sub ){
			op = state;
			getToken();
			term();
			emitOperator(op);
		}
	} // end E


	/* <term> --> <primary> { (*|/) <primary> } */
	public static void term(){
		System.out.println("-Term-");
		State op;
		primary();

		while ( state == State.Mul || state == State.Div ){
			op = state;
			getToken();
			primary();
			emitOperator(op);
		}
	} // end term


	/* <primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")" | - <primary> */
	public static void primary(){ 
		State op;
		System.out.println("-Primary-");
		
		if ( state.compareTo(State.Id) >= 0 &&
		     state.compareTo(State.FloatE) <= 0 ){
		// state == Id, Int, Float, or FloatE
			displayln("push  "+t);
			op = state;
		    getToken();
		}
		else if ( state == State.LParen ){
			op = state;
			getToken();
			expr();

			if ( state == State.RParen ) 
				getToken();
			else
				errorMsg(1);
		}
		else if ( state == State.Sub ){
			getToken();
			primary();
			displayln("neg");
		}
		else if ( state == State.Inv ){
			op = state;
			getToken();
			primary();
			emitOperator(op);
		}			
		else
			errorMsg(2);
	} // end primary

	public static void errorMsg(int i){
		display(t + ": unexpected symbol where");

		if ( i == 1 )             // called by primary()
			displayln(" arith op or ) expected");
		else if ( i == 2 )        // called by primary()
			displayln(" id, int, float, (, or - expected");
		else if ( i == 3 )
			displayln(" ; expected");
		else if ( i == 4 )
			displayln(" } expected");
	} // end errorMsg

	public static void main(String argv[]){	// The input/output file names must be passed as argv[0] and argv[1].
		setLex( argv[0], argv[1] );
		setStateToOperator();

		getToken();
		statement();
		//E();
		
		/*if ( ! t.isEmpty() )
			displayln(t + "  -- unexpected symbol");*/

		closeIO();
	} // end main
}
