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
		if ( (!SemanticChecker.currentFieldVarList.contains(id) &&
		     !SemanticChecker.currentParameterList.contains(id)) || (!SemanticChecker.currentFieldVarList.contains(id) &&
				     !SemanticChecker.currentBodyListEntry.contains(id)))
		{
			IO.displayln( "Error: variable "+id+" in "+
			               SemanticChecker.currentClassName+"."+SemanticChecker.currentFunName+" is not declared" );
			SemanticChecker.semanticErrorFound = true;
		}
		else {
			SemanticChecker.currentBodyListEntry+=(id + " ");
		}
	}

	Val Eval(HashMap<String,Val> state) // to be implemented in Project 4
	{
		if (state.get(id) == null)
			return null;
		else {
			Val val = state.get(id);
			return val.cloneVal();
		}		
	}
}