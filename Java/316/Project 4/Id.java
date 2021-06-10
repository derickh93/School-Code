import java.util.*;

class Id extends Exp
{
	String id;
	
	Id(String s)
	{
		id = s;
	}

	void semanticCheck()
	{
		if ( !SemanticChecker.currentFieldVarList.contains(id) &&
		     !SemanticChecker.currentParameterList.contains(id) )
		{
			IO.displayln( "Error: variable "+id+" in "+
			               SemanticChecker.currentClassName+"."+SemanticChecker.currentFunName+" is not declared" );
			SemanticChecker.semanticErrorFound = true;
		}
	}

	Val Eval(HashMap<String,Val> state) // to be implemented in Project 4
	{
		return null;
	}
}