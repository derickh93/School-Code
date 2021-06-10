
class KeyWhile extends Statement {
	Statement statement;
	Expr expr;
	

	KeyWhile(Statement s, Expr ex)
	{
		statement = s;
		expr = ex;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
				
//		lexArithArray.displayln(indent + indent.length() + " while");
		lexArithArray.displayln(indent1 + indent1.length() + " while");
		lexArithArray.displayln(indent1 + indent1.length() + " <expr>");
		expr.printParseTree(indent1);
		statement.printParseTree(indent1);
		
	}
}
