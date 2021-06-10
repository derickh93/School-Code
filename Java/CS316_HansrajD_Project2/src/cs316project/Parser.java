package cs316project;

/**

This class is a top-down, recursive-descent parser for the following syntactic categories:

⟨class def list⟩ → ⟨class def⟩ | ⟨class def⟩ ⟨class def list⟩
⟨class def⟩ → "class" ⟨class name⟩ [ : ⟨class name⟩ ] ⟨class body⟩   // ⟨class name⟩ after ":" is the direct superclass name.
⟨class name⟩ → ⟨id⟩
⟨class body⟩ → "{" ⟨field var list⟩ ⟨fun def list⟩ "}"
⟨field var list⟩ → ε | ⟨field var⟩ ⟨field var list⟩
⟨field var⟩ → ⟨id⟩
⟨fun def list⟩ → ε | ⟨fun def⟩ ⟨fun def list⟩
⟨fun def⟩ → "(" ⟨header⟩ ⟨exp⟩ ")"
⟨header⟩ → "(" ⟨fun name⟩ ⟨parameter list⟩ ")"
⟨fun name⟩ → ⟨id⟩
⟨parameter list⟩ → ε | ⟨parameter⟩ ⟨parameter list⟩
⟨parameter⟩ → ⟨id⟩
⟨exp⟩ → ⟨id⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | "null" | "this" | "(" ⟨fun exp⟩ ")"
⟨fun exp⟩ → ⟨fun call⟩ | ⟨binary exp⟩ | ⟨cond⟩ | ⟨not⟩
⟨fun call⟩ → ⟨fun name⟩ ⟨exp list⟩
⟨exp list⟩ → ε | ⟨exp⟩ ⟨exp list⟩
⟨binary exp⟩ → ⟨arith exp⟩ | ⟨bool exp⟩ | ⟨comp exp⟩ | ⟨dot exp⟩
⟨arith exp⟩ → ⟨arith op⟩ ⟨exp⟩ ⟨exp⟩
⟨bool exp⟩ → ⟨bool op⟩ ⟨exp⟩ ⟨exp⟩
⟨comp exp⟩ → ⟨comp op⟩ ⟨exp⟩ ⟨exp⟩
⟨dot exp⟩ → "." ⟨exp⟩ ⟨exp⟩
⟨cond⟩ → "if" ⟨exp⟩ ⟨exp⟩ ⟨exp⟩
⟨not⟩ → ! ⟨exp⟩
⟨arith op⟩ → + | − | * | /
⟨bool op⟩ → "|" | "&"
⟨comp op⟩ → "<" | "<=" | ">" | ">=" | "="

The definitions of the tokens are given in the lexical analyzer class file "LexAnalyzer.java". 

An explicit parse tree is constructed in the form of nested class objects.
 
The main function will display the parse tree in linearly indented form.
Each syntactic category name labeling a node is displayed on a separate line, 
prefixed with the integer i representing the node's depth and indented by i blanks. 

**/

public abstract class Parser extends LexAnalyzer
{
	static boolean errorFound = false;

		
public static ClassDefList classDefList()
	
	//⟨class def list⟩ → ⟨class def⟩ | ⟨class def⟩ ⟨class def list⟩
	
	{
		ClassDef classDef = classDef();
		

		if ( state == state.Id)
		{
			ClassDefEntry entryOne = new ClassDefEntry(null);
			ClassDefEntry.symbolTable.put(t, entryOne);
			displayln(entryOne.toString());
			ClassDefList classDefList = classDefList();
			return new MultipleClassDef(classDef, classDefList);
		}
		else {

			return classDef;
		}
	}

	
	public static ClassDef classDef()
	
	//⟨class def⟩ → "class" ⟨class name⟩ [ : ⟨class name⟩ ] ⟨class body⟩   // ⟨class name⟩ after ":" is the direct superclass name.
	
	{
		if ( state == State.Keyword_class )
		{
			System.out.println("keyword class: " + t);

			String id = t;

			getToken();
			System.out.println("classname: " + t);
			ClassName className = className();
			getToken();
			ClassName superClassName = null;
			if(state == state.Colon) {
				System.out.println("colon: " + t);
				getToken();
				superClassName = className();
				System.out.println("superclass: " + t);
				getToken();
				ClassBody classBody = classBody();
				return new ClassDef(className, superClassName,classBody);
			}
			ClassBody classBody = classBody();
			return new ClassDef(className,classBody);
		}
		return null;
	}
	
	//⟨class name⟩ → ⟨id⟩
	public static ClassName className() {
		if ( state == state.Keyword_class )
		{
			getToken();
			String id = t;
			return new ClassName(id);
		}
		else
			errorMsg(5);
		return null;
	}
	
	//⟨class body⟩ → "{" ⟨field var list⟩ ⟨fun def list⟩ "}"
	public static ClassBody classBody() {
		if(state == state.LBrace) {
			System.out.println("leftbrace: "+ t);
		}

		FieldVarList fieldVarList = fieldVarList();
		FunDefList funDefList = funDefList();
		if(state == state.RBrace) {
			System.out.println("complete: " + t);
		}
		return null;
	}
	
	//⟨field var list⟩ → ε | ⟨field var⟩ ⟨field var list⟩
	public static FieldVarList fieldVarList() {
		getToken();
		if(state == state.RBrace) {
			EmptyFieldVarList emptyFieldVarList = new EmptyFieldVarList();
			System.out.println("complete: " + t);

			return emptyFieldVarList;
		}
		else if(state == state.Id) {
			FieldVar fieldVar = fieldVar();
			FieldVarList fieldVarList = fieldVarList();
			MultipleFieldVarList multipleFieldVar = new MultipleFieldVarList(fieldVar, fieldVarList);
			return multipleFieldVar;
		}
		else {
			EmptyFieldVarList emptyFieldVarList = new EmptyFieldVarList();
			return emptyFieldVarList;
		}
	}
	
	//⟨field var⟩ → ⟨id⟩
	public static FieldVar fieldVar() {
		if ( state == state.Id )
		{
			System.out.println("field Var: " + t);
			String fieldVar = t;
			return new FieldVar(fieldVar);
		}
		else
			errorMsg(5);
		return null;
	}
	
	//⟨fun def list⟩ → ε | ⟨fun def⟩ ⟨fun def list⟩
	public static FunDefList funDefList() {
		getToken();
		if(state == state.LBrace) {
			EmptyFunDefList emptyFunDefList = new EmptyFunDefList();
			System.out.println("complete: " + t);

			return emptyFunDefList;
		}
		else if(state == state.LParen) {
			FunDef funDef = funDef();
			FunDefList funDefList = funDefList();
			MultipleFunDefList multipleFunDefList = new MultipleFunDefList(funDef, funDefList);
			return multipleFunDefList;
		}
		else {
			EmptyFunDefList emptyFunDefList = new EmptyFunDefList();
			return emptyFunDefList;
		}
	}
	
	//⟨fun def⟩ → "(" ⟨header⟩ ⟨exp⟩ ")"
	public static FunDef funDef() {
		if(state == state.LParen) {
			System.out.println("leftbrace: "+ t);
		}

		Header header = header();
		getToken();
		Exp exp = exp();
		if(state == state.RParen) {
			System.out.println("r paren: " + t);
		}
		return null;
	}
	
	//⟨header⟩ → "(" ⟨fun name⟩ ⟨parameter list⟩ ")"
	public static Header header() {
		if(state == state.LParen) {
			System.out.println("leftbrace: "+ t);
		}

		FunName funName = funName();
		getToken();
		ParameterList parameterList = parameterList();
		if(state == state.RParen) {
			System.out.println("r paren: " + t);
		}
		return null;
	}
	
	//⟨fun name⟩ → ⟨id⟩
	public static FunName funName() {
		getToken();
		if ( state == state.Id )
		{
			System.out.println("fun name: " + t);
			String funName = t;
			return new FunName(funName);
		}
		else
			errorMsg(5);
		return null;
	}
	
	//⟨parameter list⟩ → ε | ⟨parameter⟩ ⟨parameter list⟩
	public static ParameterList parameterList() {
		getToken();
		if(state == state.LBrace) {
			EmptyParameterList emptyParameterList = new EmptyParameterList();
			System.out.println("emptyparam list: " + t);

			return emptyParameterList;
		}
		else if(state == state.LParen) {
			Parameter parameter = parameter();
			ParameterList parameterList = parameterList();
			MultipleParameterList multipleParameterList = new MultipleParameterList(parameter, parameterList);
			return multipleParameterList;
		}
		else {
			EmptyParameterList emptyParameterList = new EmptyParameterList();
			return emptyParameterList;
		}
	}
	
	//⟨parameter⟩ → ⟨id⟩
	public static Parameter parameter() {
		getToken();
		if ( state == state.Id )
		{
			System.out.println("parameter: " + t);
			String parameter = t;
			return new Parameter(parameter);
		}
		else
			errorMsg(5);
		return null;
	}
	
	//⟨exp⟩ → ⟨id⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | "null" | "this" | "(" ⟨fun exp⟩ ")"
	public static Exp exp() {
		if(state == state.Id) {
			
			System.out.println("id: " + t);
			
		}
		else if(state == state.Int) {
			System.out.println("int: " + t);

		}
		else if(state == state.Float) {
			System.out.println("float: " + t);

		}
		else if(state == state.FloatE) {
			System.out.println("floatE: " + t);

		}
		else if(state == state.Keyword_null) {
			System.out.println("null: " + t);

		}
		else if(state == state.Keyword_this) {
			System.out.println("this: " + t);

		}
		else if(state == state.LParen) {
			System.out.println("funexp: " + t);

		}
		getToken();
		return null;
	}
	
	//⟨fun exp⟩ → ⟨fun call⟩ | ⟨binary exp⟩ | ⟨cond⟩ | ⟨not⟩
	public static FunExp funExp() {
		return null;

	}
	
	//⟨fun call⟩ → ⟨fun name⟩ ⟨exp list⟩
	public static FunCall funCall() {
		return null;

	}
	
	//⟨exp list⟩ → ε | ⟨exp⟩ ⟨exp list⟩
	public static ExpList expList() {
		return null;

	}
	
	//⟨binary exp⟩ → ⟨arith exp⟩ | ⟨bool exp⟩ | ⟨comp exp⟩ | ⟨dot exp⟩
	public static BinaryExp binaryExp() {
		return null;

	}
	
	//⟨arith exp⟩ → ⟨arith op⟩ ⟨exp⟩ ⟨exp⟩
	public static ArithExp arithExp() {
		return null;

	}
	
	//⟨bool exp⟩ → ⟨bool op⟩ ⟨exp⟩ ⟨exp⟩
	public static BoolExp boolExp() {
		return null;

	}
	
	//⟨comp exp⟩ → ⟨comp op⟩ ⟨exp⟩ ⟨exp⟩
	public static CompExp compExp() {
		return null;

	}
	
	//⟨dot exp⟩ → "." ⟨exp⟩ ⟨exp⟩
	public static DotExp dotExp() {
		return null;

	}
	
	//⟨cond⟩ → "if" ⟨exp⟩ ⟨exp⟩ ⟨exp⟩
	public static Cond cond() {
		return null;

	}
	
	//⟨not⟩ → ! ⟨exp⟩
	public static Not not() {
		return null;

	}
	
	//⟨arith op⟩ → + | − | * | /
	public static ArithOp arithOp() {
		return null;

	}
	
	//⟨bool op⟩ → "|" | "&"
	public static BoolOp boolOp() {
		return null;

	}
	
	//⟨comp op⟩ → "<" | "<=" | ">" | ">=" | "="
	public static CompOp compOp() {
		return null;

	}
	
	public static void errorMsg(int i)
	{
		errorFound = true;
		
		display(t + " : Syntax Error, unexpected symbol where");

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
		// argv[0]: input file containing an assignment list
		// argv[1]: output file displaying the parse tree
		
		setIO( argv[0], argv[1] );
		setLex();

		getToken();

		ClassDefList classDefList = classDefList(); // build a parse tree
		if ( ! t.isEmpty() )
			errorMsg(5);
		closeIO();
	}
}

