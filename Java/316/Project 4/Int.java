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
	}
	
	Val Eval(HashMap<String,Val> state)
	{
		return new IntVal(val);
	}
}