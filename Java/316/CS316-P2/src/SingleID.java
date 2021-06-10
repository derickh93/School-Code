
class SingleID extends IdList{
	String id;

	SingleID(String ident){
	id = ident;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		lexArithArray.displayln(indent + indent.length() + " <id list> ");
		lexArithArray.displayln(indent1 + indent1.length() + " " + id);
	}
}
