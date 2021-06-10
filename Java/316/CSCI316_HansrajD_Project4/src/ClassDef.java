class ClassDef
{
	ClassName className;
	ClassName superClassName; // value can be null
	ClassBody classBody;

	ClassDef(ClassName cn, ClassName scn, ClassBody cb)
	{
		className = cn;
		superClassName = scn;
		classBody = cb;
	}

	void semanticCheck()
	{
		SemanticChecker.currentClassName = className.id;
		SemanticChecker.currentClassDefEntry = new ClassDefEntry();
		if ( superClassName == null )
			SemanticChecker.currentClassDefEntry.superClassName = "";
		else
			SemanticChecker.currentClassDefEntry.superClassName = superClassName.id;
		SemanticChecker.symbolTable.put(SemanticChecker.currentClassName, SemanticChecker.currentClassDefEntry);
		classBody.semanticCheck();
	}
}