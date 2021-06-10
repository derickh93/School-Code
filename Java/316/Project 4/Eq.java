import java.util.*;

class Eq extends CompExp
{
	Eq(Exp e1, Exp e2)
	{
		super(e1, e2);
	}

	Val Eval(HashMap<String,Val> state)
	{
		Val exp1Val = exp1.Eval(state);
		Val exp2Val = exp2.Eval(state);

		if ( exp1Val == null || exp2Val == null )
			return null;

		return new BoolVal( exp1Val.equalVal(exp2Val) );
	}
}