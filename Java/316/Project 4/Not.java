import java.util.*;

class Not extends FunExp
{
	Exp exp;

	Not(Exp e)
	{
		exp = e;
	}

	void semanticCheck()
	{
		exp.semanticCheck();
	}

	Val Eval(HashMap<String,Val> state)
	{
		Val expVal = exp.Eval(state);
		if ( expVal == null )
			return null;
		if ( !(expVal instanceof BoolVal) )
		{
			System.out.println( "Error: ! operator cannot be applied to " + expVal.toString() );
			return null;
		}

		((BoolVal)expVal).val = ! ((BoolVal)expVal).val;
			return expVal;
	}
}