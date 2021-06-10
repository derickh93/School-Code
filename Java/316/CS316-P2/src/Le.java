
class Le extends RelOp {
String id;
	
	Le(String ident)
	{
		id = ident;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " <=");
	}
}
