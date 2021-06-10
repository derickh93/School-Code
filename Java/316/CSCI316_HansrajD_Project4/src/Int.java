import java.util.*;

class Int extends Exp
{
	int val;
	
	Int(int i)
	{
		val = i;
	}

	void semanticCheck()
	{
		SemanticChecker.currentBodyListEntry+=(Integer.toString(val) + " ");
	}
	
	Val Eval(HashMap<String,Val> state)
	{
		return new IntVal(val);
	}
}