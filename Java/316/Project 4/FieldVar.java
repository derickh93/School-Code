class FieldVar
{
	String id;

	FieldVar(String s)
	{
		id = s;
	}

	void semanticCheck()
	{
		SemanticChecker.currentClassDefEntry.fields.add(id);
	}
}