
class Eq extends RelOp{
	String id;
	
	Eq(String ident)
	{
		id = ident;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " ==");
	}

}
