
class Decrement extends Statement {
	String id; // variable on the left side of the assignment
	

	Decrement(String s)
	{
		id = s;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
				
		lexArithArray.displayln(indent + indent.length() + " <decrement>");
		lexArithArray.displayln(indent1 + indent1.length() + " " + id);
		lexArithArray.displayln(indent1 + indent1.length() + " --");
		
	}
}
