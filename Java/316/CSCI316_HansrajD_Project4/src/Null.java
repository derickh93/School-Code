import java.util.*;

class Null extends Exp
{	
	static final Null nullExp = new Null();

	void semanticCheck()
	{
		SemanticChecker.currentBodyListEntry+=("Null ");
	}
	
	Val Eval(HashMap<String,Val> state)
	{
		return NullObj.nullObj;
	}
}