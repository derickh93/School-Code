import java.util.*;

class Null extends Exp
{	
	static final Null nullExp = new Null();

	void semanticCheck()
	{
	}
	
	Val Eval(HashMap<String,Val> state)
	{
		return NullObj.nullObj;
	}
}