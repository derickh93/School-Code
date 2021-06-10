
class Gt extends RelOp{
	String id;
	
	Gt(String ident)
	{
		id = ident;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " >");
	}

}
