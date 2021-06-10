
class MultipleBoolPrimary extends BoolTerm{
	BoolPrimary boolPrimary;
	BoolTerm boolTerm;

	MultipleBoolPrimary(BoolPrimary p, BoolTerm b)
	{
		boolPrimary = p;
		boolTerm = b;
	}
 
	void printParseTree(String indent)
	{		
		boolPrimary.printParseTree(indent);
		boolTerm.printParseTree(indent);
	}
}
