
class NegPrimary extends Primary {

	int val;

	NegPrimary(int i)
	{
		val = i;
	}
	void printParseTree(String indent) {
		lexArithArray.displayln(indent + indent.length() + " - <primary> " + val);
		
	}

}
