
class SingleBoolTerm  extends Expr{
	BoolTerm boolTerm;
	

	SingleBoolTerm(BoolTerm b)
	{
		boolTerm = b;

	}
 
	void printParseTree(String indent)
	{	
		String indent1 = indent + " ";
		lexArithArray.displayln(indent1 + " " + indent1.length() + " <boolTerm>");
		boolTerm.printParseTree(indent1);

	}
}
