import java.util.*;

class And extends BoolExp
{
	And(Exp e1, Exp e2)
	{
		super(e1, e2);
	}

	Val Eval(HashMap<String,Val> state)
	{
		Val exp1Val = exp1.Eval(state);
		Val exp2Val = exp2.Eval(state);

		if ( exp1Val == null || exp2Val == null )
			return null;
		if ( !(exp1Val instanceof BoolVal) )
		{
			System.out.println( "Error: & operator cannot be applied to " + exp1Val.toString() );
			return null;
		}
		if ( !(exp2Val instanceof BoolVal) )
		{
			System.out.println( "Error: & operator cannot be applied to " + exp2Val.toString() );
			return null;
		}

		((BoolVal)exp1Val).val = ((BoolVal)exp1Val).val && ((BoolVal)exp2Val).val;
		return exp1Val;
	}
}