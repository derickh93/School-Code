
class Block extends Statement {
	String id; // variable on the left side of the assignment
	SList slist;

	Block(String s, SList l)
	{
		id = s;
		slist =l;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
				
		lexArithArray.displayln(indent1 + indent1.length() + " <block>");
//		lexArithArray.displayln(indent1 + indent1.length() + " " + id);
//		lexArithArray.displayln(indent1 + indent1.length() + " <s list>");
		slist.printParseTree(indent1+ " ");

	}
}
