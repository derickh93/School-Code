import java.util.*;

class Floatp extends Exp
{
	float val;
	
	Floatp(float f)
	{
		val = f;
	}

	void semanticCheck()
	{
		SemanticChecker.currentBodyListEntry+=(Float.toString(val));
	}
	
	Val Eval(HashMap<String,Val> state)
	{
		return new FloatVal(val);
	}
}