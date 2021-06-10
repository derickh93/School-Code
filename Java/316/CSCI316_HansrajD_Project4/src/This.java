import java.util.*;

class This extends Exp
{	
	static final This thisExp = new This();

	void semanticCheck()
	{
		SemanticChecker.currentBodyListEntry+=("this ");
	}
	
	Val Eval(HashMap<String,Val> state) // to be implemented in Project 4
	{
		if (state.get(thisExp) == null)
			return null;
		else {
			Val val = state.get(thisExp);
			return val;
		}		
	}
}