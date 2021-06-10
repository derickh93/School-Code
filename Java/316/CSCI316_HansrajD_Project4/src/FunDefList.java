import java.util.LinkedList;

class FunDefList
{
	FunDef funDef;
	FunDefList funDefList; // value can be null
	
	FunDefList(FunDef f, FunDefList fl)
	{
		funDef = f;
		funDefList = fl;
	}

	void semanticCheck()
	{
		SemanticChecker.currentBodyListEntry = new String();

		funDef.semanticCheck();
		SemanticChecker.currentClassDefEntry.funBodyMap.put(funDef.header.funName.id, SemanticChecker.currentBodyListEntry);
		if ( funDefList != null )
			funDefList.semanticCheck();
	}
}