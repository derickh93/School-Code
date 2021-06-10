
class SingleBoolPrimary extends BoolTerm{
	BoolPrimary boolPrimary;
	
	SingleBoolPrimary(BoolPrimary p)
	{
		boolPrimary = p;

	}
 
	void printParseTree(String indent)
	{	
		String indent1 = indent + " ";
		lexArithArray.displayln(indent1 + " " + indent1.length() + " <boolPrimary>");
		boolPrimary.printParseTree(indent1);

	}
}
