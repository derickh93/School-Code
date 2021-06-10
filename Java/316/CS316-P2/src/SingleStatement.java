
class SingleStatement extends SList {
	Statement statement;
	
	SingleStatement(Statement s){
		statement =s;
	}
	@Override
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		lexArithArray.displayln(indent + indent.length() + " <s list>");
		lexArithArray.displayln(indent1 + indent1.length() + " <statement>");
		statement.printParseTree(indent1);	
	}

}
