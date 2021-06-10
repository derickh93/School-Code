
class VarDec extends VarDeclarations{
	Type type;
	IdList idlist;
	
	VarDec(Type t, IdList i){
		type = t;
		idlist=i;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		lexArithArray.displayln(indent + indent.length() + " <var dec>");
		type.printParseTree(indent1);
//		lexArithArray.displayln(indent1 + indent1.length() + " <id_list>");
		idlist.printParseTree(indent1);
	}
}
