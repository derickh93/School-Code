
class Ge extends RelOp {
	String id;
	
	Ge(String ident)
	{
		id = ident;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " >=");
	}

}
