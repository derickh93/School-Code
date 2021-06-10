class Parameter
{
	String id;
	
	Parameter(String s)
	{
		id = s;
	}

	void semanticCheck()
	{
		SemanticChecker.currentParameterList.add(id);
		SemanticChecker.currentBodyListEntry+=id;
		
	}
}