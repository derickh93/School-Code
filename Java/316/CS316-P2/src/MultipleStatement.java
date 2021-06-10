
class MultipleStatement extends SList {
	Statement statement;
	SList sList;

	MultipleStatement(Statement s, SList l)
	{
		statement = s;
		sList = l;
	}
 
	void printParseTree(String indent)
	{	
		String indent1 = indent + " ";
		lexArithArray.displayln(indent + indent.length() + " <s list>");
		
		lexArithArray.displayln(indent1 + " " + indent1.length() + " <statement>");	
		statement.printParseTree(indent1);
		sList.printParseTree(indent1 );
	}

}
