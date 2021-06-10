
class MultipleIdList extends IdList {
	String id;
	IdList idList;

	MultipleIdList(String i, IdList l)
	{
		id=i;
		idList=l;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";

		lexArithArray.displayln(indent + indent.length() + " <id list>");
		lexArithArray.displayln(indent1 + indent1.length()+ " " + id);
		idList.printParseTree(indent1);
	}

}
