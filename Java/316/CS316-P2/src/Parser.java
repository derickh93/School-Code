
/**

This class is a top-down, recursive-descent parser for the following syntactic categories:

<assignment list> --> <assignment> | <assignment> <assignment list>
<assignment> --> <id> = <E> ";"
<E> --> <term> | <term> + <E> | <term> - <E>
<term> --> <primary> | <primary> * <term> | <primary> / <term>
<primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")" 

The definitions of the tokens are given in the lexical analyzer class file "lexArithArray.java".

The following variables and functions of the "lexArithArray" class are used:

static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token
static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

An explicit parse tree is constructed in the form of nested class objects.
 
The main function will display the parse tree in linearly indented form.
Each syntactic category name labeling a node is displayed on a separate line, 
prefixed with the integer i representing the node's depth and indented by i blanks. 

**/


public abstract class Parser extends lexArithArray
{
	static boolean errorFound = false;

	public static Program program(){
	// <program > --> <var declarations> <statement>
		System.out.println("--Excute varDeclrations()--");
		VarDeclarations varDeclarations = varDeclarations();
		System.out.println("--Execute statement()--");
		Statement statement= statement();
		
		return new Program(varDeclarations, statement);
		
	
	}
	private static Statement statement() {
		System.out.println("testing");
		System.out.println(t);
		System.out.println(state);
		// <statement> --> <assignment> | <increment> | <decrement> | <block> | <cond> | <while> | <do>
		switch (state){
		
		case Id:
			System.out.println("aaaa");
//			Assignment assignment = assignment();
			String id=t;
			System.out.println("end of statement " + t );
//			return new assignment(id,expr);
			return assignment();
		case Incr:
			
			return increment();//null;
		case Decr:
			
			return decrement();//null;
		case LBrace:
			
			return block();//null;
		case Keyword_if:
		//	cond();
			return cond();//null;
		case Keyword_while:
			System.out.println("keyword while -testing");
			String idw=t;
//			keyWhile();
			return keyWhile();
		case Keyword_do:
			//keyDo();
			return keyDo();//null;
			
		default:
			return null;
		}
		
		
	}
	public static VarDeclarations varDeclarations(){
		//<var declarations> --> <var dec> ";" | <var dec> ";" <var declarations>
		System.out.println("--Execute varDec()--");
		VarDec varDec = varDec();

		if(state==State.Semicolon){
			getToken();
			System.out.println(state);
//			if(state==State.Keyword_int || state==State.Keyword_float || state ==State.Keyword_boolean){
			if(t.equals("int") || t.equals("float") || t.equals("boolean")){
				getToken();
				System.out.println(t);
				System.out.println("akadlfjsadlfjsadlfjlsafsfsdf");
				VarDeclarations varDeclarations = varDeclarations();
				return new MultipleVarDeclarations(varDec, varDeclarations);
			}
			else{
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");
//			varDec.printParseTree("");
			return new SingleVar(varDec);
			}
		
		}//if
		else
			errorMsg(1);
		return null;
	}//class
	
	public static VarDec varDec(){
		//<var dec> --> <type> <id list>
		System.out.println("--Execute type()--");
		Type type = type();
//		type.printParseTree("");
		System.out.println("--Execute idList()--");
		IdList idList= idList();
//		getToken();
		return new VarDec(type, idList);
	}
	
	public static Type type(){
		//<type> -> "int" | "float" | "boolean"
	
		switch (state){
		
		case Keyword_int:
			System.out.println(t);
			String id = t;
			getToken();
			return new TypeInt(id);
		
		case Keyword_float: 
			
		case Keyword_boolean:	
		
		default:
		//	errorMsg(2);
			return null;
		}
		
	}
	public static IdList idList(){
		// <id list > --> <id> | <id> "," <id list>
		System.out.println("<<Inside idList>>");
		if(state == State.Id){
			System.out.println(t);
			String id = t;
			getToken();
			if(state == State.Comma){
				System.out.println(t);
				getToken();
			
				IdList idlist=idList();

//				System.out.println(t);
//				return new idList();
//				getToken();
//				idList();
				return new MultipleIdList(id,idlist);
			}
			else{ 
				return new SingleID(id);
			}
		}else{
		//	errorMsg(3);
		
		}
		return null;
		
	}
	
	public static Increment increment(){
		//<increment> --> <id> "++" ";"
		if ( state == State.Id )
		{
			String id = t;
			getToken();
			if ( state == State.Incr )
			{
				getToken();
				if ( state == State.Semicolon )
				{
					getToken();
					return new Increment(id);
				}
				else
					errorMsg(4);
			}
			else
				errorMsg(3);
		}
		else
			errorMsg(5);
		return null;
	}
	public static Decrement decrement(){
		//<decrement> --> <id> "--" ";"
		if ( state == State.Id )
		{
			String id = t;
			getToken();
			if ( state == State.Decr )
			{
				getToken();
				if ( state == State.Semicolon )
				{
					getToken();
					return new Decrement(id);
				}
				else
					errorMsg(4);
			}
			else
				errorMsg(3);
		}
		else
			errorMsg(5);
		return null;
	}
	
	public static Block block(){
		//<block> --> "{" <s list> "}"
		System.out.println(t);
		System.out.println("block");
		if ( state == State.LBrace )
		{
			System.out.println("LBrace " + t);
			System.out.println(state);
			String id = t;
			getToken();
			System.out.println(t);
			System.out.println("going to slist");
			SList sList = sList();
			System.out.println("end of Lbrace");
			if ( state == State.RBrace )
			{
				System.out.println("here");
				getToken();
//				Block block = new Block(id,sList);
				return new Block(id, sList);
			}
			else{
				System.out.println("or here");
			//	errorMsg(3);
			}
		}
		else
			errorMsg(5);
		return null;
	}
	public static Cond cond(){
		if ( state == State.Keyword_if)
		{
			String id = t;
			getToken();
			if ( state == State.LParen ){
				getToken();
				Expr expr = expr();
				if ( state == State.RParen ){
					getToken();
					Statement statement=statement();
					if(state == State.Keyword_else){
						getToken();
						statement();
					}
//					else{
//						Cond cond= new Cond(statement);
//						return cond;
								
					
				}
				else
					return new Cond(id);
			}
		}
		else
			errorMsg(5);
		return null;
	}
	public static KeyWhile keyWhile(){
		System.out.println("while -test");
		System.out.println(state);
		if ( state == State.Keyword_while )
		{
			System.out.println(t);
			String id = t;
			getToken();
			System.out.println(t);
			if ( state == State.LParen )
			{
				
				getToken();
				System.out.println(t);
				Expr expr = expr();
				System.out.println("goign to rparen");
				System.out.println(t);
				if ( state == State.RParen )
				{
					getToken();
					System.out.println(t);
					Statement statement =statement();
//					KeyWhile key = new KeyWhile(id);
					return new KeyWhile(statement,expr);
				}
				else
					errorMsg(4);
			}
			else
				errorMsg(3);
		}
		else
			errorMsg(5);
		return null;
	}
	public static KeyDo keyDo(){
		if ( state == State.Keyword_do )
		{
			String id = t;
			getToken();
			Statement statement = statement();
			if ( state == State.Keyword_while )
			{
				getToken();
				if ( state == State.LParen )
				{
					getToken();
					Expr expr = expr();
					if(state == State.RParen){
						getToken();
						KeyDo key = new KeyDo(id);
						return key;
					}
					else
						errorMsg(4);
				}
				else
					errorMsg(4);
			}
			else
				errorMsg(3);
		}
		else
			errorMsg(5);
		return null;
	}
	
	public static Expr expr(){
		//<expr> --> <boolTerm> | <boolTerm> || <expr>
		System.out.println("expr-testing");
		BoolTerm boolTerm = boolTerm();
		if(state==State.Or){
			Expr expr = expr();
			System.out.println("multibool???");
			return new MultipleBoolTerm(boolTerm,expr);
		}
		else
			return new SingleBoolTerm(boolTerm);//boolTerm;
	}
	public static BoolTerm boolTerm(){
		//<boolTerm> --> <boolPrimary> | <boolPrimary> && <boolTerm>
		System.out.println("boolterm");
		BoolPrimary boolPrimary = boolPrimary();
		if(state==State.And){
			BoolTerm boolTerm = boolTerm();
			return new MultipleBoolPrimary(boolPrimary,boolTerm);
		}
		else{
			System.out.println("---------------------------------x");
			return new SingleBoolPrimary(boolPrimary);
		}
	}
	public static BoolPrimary boolPrimary(){
		//<boolPrimary> --> <E> [<rel op> <E> ]
		System.out.println("boolprimary");
//		String id = t;
		E e=E();
		System.out.println(state);
		if(state == State.Lt|| state == State.Le || state == State.Gt || state == State.Ge || state == State.Eq || state == State.Neq ){
			RelOp relOp=relOp();
			E e1 = E();
			return new OptionalRelOpE(relOp,e1);
		}
//		else{
//		
//			E();
//		}
		return new OnlyE(e);
	}

	public static RelOp relOp(){
		System.out.println("relop");
		//<rel op> --> "<" | "<=" | ">" | ">=" | "==" | "!="
		switch(state){
		
		case Lt:
			System.out.println("<");
	//		Lt lt = new Lt(t);
			getToken();
			System.out.println(t);
			return new Lt(t);
		case Le:
			Le le = new Le(t);
			getToken();
			return le;
			
		case Gt:
			Gt gt = new Gt(t);
			getToken();
			return gt;
		case Ge:
			Ge ge = new Ge(t);
			getToken();
			return ge;
			
		case Eq:
			Eq eq = new Eq(t);
			getToken();
			return eq;
			
		case Neq:
			Neq neq = new Neq(t);
			getToken();
			return neq;
			
		default:
			return null;
			
		
		}
	}
	
	public static SList sList(){
		//<s list> --> <statement> | <statement> <s list>
			System.out.println("statement testing");
			Statement statement = statement();

			if ( state == State.Keyword_while || state == State.Keyword_do || state == State.Keyword_if || state == State.Id )
			{	
				SList sList = sList();
				return new MultipleStatement(statement, sList);
				
			}
//			else{
//				
//				System.out.println("whoops");
////				statement();
//				
//			}
			return new SingleStatement(statement);
	}
	
//	public static AssignmentList assignmentList()
//	
//	// <assignment list> --> <assignment> | <assignment> <assignment list>
//	
//	{
//		Assignment assignment = assignment();
//		if ( state == State.Id )
//		{
//			AssignmentList assignmentList = assignmentList();
//			return new MultipleAssignment(assignment, assignmentList);
//		}
//		else
//			return assignment;
//	}

	public static Assignment assignment()
	
	// <assignment> --> <id> = <Expr> ";"
	
	{
		System.out.println("assignment-testing");
		if ( state == State.Id )
		{
			System.out.println("assign " + t);
			String id = t;
			getToken();
			if ( state == State.Assign )
			{
				System.out.println(t);
				getToken();
				System.out.println(t);
				Expr expr = expr();
				if ( state == State.Semicolon )
				{
					System.out.println(t);
					getToken();
					System.out.println("end of assign " + t);
					return new Assignment(id, expr);
				}
				else
					errorMsg(4);
			}
			else
				errorMsg(3);
		}
		else
			errorMsg(5);
		return null;
	}

	public static E E()

	// <E> --> <term> | <term> + <E> | <term> - <E>

	{   System.out.println("at E");
		Term term = term();
		System.out.println(t);
		if ( state == State.Add )
		{	System.out.println(t);		
			getToken();
			E e = E();
			return new AddE(term, e);
		}
		else if ( state == State.Sub )
		{
			getToken();
			E e = E();
			return new SubE(term, e);
		}
		else
			return new SingleTerm(term);
	}

	public static Term term()

	// <term> --> <primary> | <primary> * <term> | <primary> / <term>

	{	System.out.println("at term");
		System.out.println(t);
		Primary primary = primary();
		if ( state == State.Mul )
		{			
			getToken();
			Term term = term();
			return new MulTerm(primary, term);
		}
		else if ( state == State.Div )
		{
			getToken();
			Term term = term();
			return new DivTerm(primary, term);
		}
		else
			return new SinglePrimary(primary);
	}

	public static Primary primary()

	// <primary> --> <id> | <int> | <float> | <floatE> | <boolLiteral> | "(" <E> ")"|
    // 						-<primary> | !<primary>
	{
		switch ( state )
		{
			case Id:
				System.out.println("at primary id");	
				System.out.println(t);
				Id id = new Id(t);
				getToken();
				System.out.println(t);
				System.out.println("end of primary id " +t);
				return id;
				
			case Int:

				Int intElem = new Int(Integer.parseInt(t));
				getToken();
				return intElem;

			case Float: case FloatE:

				Floatp floatElem = new Floatp(Float.parseFloat(t));
				getToken();
				return floatElem;
				
			
			case LParen:
				
				getToken();
				E e = E();
				if ( state == State.RParen )
				{
					getToken();
					Parenthesized paren = new Parenthesized(e);
					return paren;
				}
				else
				{
					errorMsg(1);
					return null;
				}
			case Keyword_boolean:
				getToken();
//				BoolLiteral();
				
			case Neq:
				getToken();
				primary();
			
			case Inv:
				getToken();
				primary();
			
			default:

			//	errorMsg(2);
				return null;
		}
	}
	

//	public static BoolLiteral(){
//		
//		if( state== State.true){
//			getToken();
//			return 
//		}
//	}
	
	public static void errorMsg(int i)
	{
		errorFound = true;
		
		display(t + ": unexpected symbol where");

		switch( i )
		{
		case 1:	displayln(" arith op or ) expected"); return;
		case 2: displayln(" id, int, float, or ( expected"); return;
		case 3:	displayln(" = expected"); return;
		case 4:	displayln(" ; expected"); return;
		case 5:	displayln(" id expected"); return;		
		}
	}

	public static void main(String argv[])
	{
		// argv[0]: input file containing the source code of an assignment list
		// argv[1]: output file to display the parse tree
		
		setLex( argv[0], argv[1] );
		
		getToken();

	//	AssignmentList assignmentList = assignmentList(); // build a parse tree
		Program program = program();
		if ( ! t.isEmpty() )
			displayln(t + "  -- unexpected symbol");
		else if ( ! errorFound )
		{   System.out.println("end of file");
			program.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file
		}
		closeIO();
	}
}
