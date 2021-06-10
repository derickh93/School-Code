
class MultipleBoolTerm extends Expr {

	BoolTerm boolTerm;
	Expr expr;

	MultipleBoolTerm(BoolTerm b, Expr e)
	{
		boolTerm = b;
		expr = e;
	}
 
	void printParseTree(String indent)
	{		
		boolTerm.printParseTree(indent);
		expr.printParseTree(indent);
	}

}
