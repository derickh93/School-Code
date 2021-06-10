
class NotPrimary extends Primary{
	String id;

	NotPrimary(String ident)
	{
		id = ident;
	}
	
	void printParseTree(String indent) {
		lexArithArray.displayln(indent + indent.length() + " ! <primary> " + id);
		
	}

}
