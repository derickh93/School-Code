
class Neq extends RelOp {
	String id;
	
	Neq(String ident)
	{
		id = ident;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " !=");
	}

}
