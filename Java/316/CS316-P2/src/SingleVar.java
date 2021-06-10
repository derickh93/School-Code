
class SingleVar extends VarDeclarations{
	VarDec varDec;

	SingleVar(VarDec t)	{
		varDec = t;
	}
//
	void printParseTree(String indent)
	{	
//		String indent1 = " ";
		lexArithArray.displayln(indent + indent.length() + " <var declarations>");
		varDec.printParseTree(indent+" ");
	}
}
