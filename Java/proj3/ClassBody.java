class ClassBody
{
	FieldVarList fieldVarList; // value is null if <filed var list> is empty.
        FunDefList funDefList;     // value is null if <fun def list> is empty.

	ClassBody(FieldVarList fvl, FunDefList fdl)
	{
		fieldVarList = fvl;
		funDefList = fdl;
	}

	void semanticCheck()
	{
		if ( fieldVarList != null )
			fieldVarList.semanticCheck();
		SemanticChecker.currentFieldVarList = SemanticChecker.currentClassDefEntry.fields;
		if ( funDefList != null )
			funDefList.semanticCheck();
	}
}