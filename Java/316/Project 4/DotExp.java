import java.util.*;

class DotExp extends BinaryExp
{
	DotExp(Exp e1, Exp e2)
	{
		super(e1, e2);
	}

	Val Eval(HashMap<String,Val> state)
	{
		Val exp1Val = exp1.Eval(state);

		if ( exp1Val == null )
			return null;
		if ( !(exp1Val instanceof ClassObj) )
		{
			System.out.println( "Error: dot operator cannot be applied to " + exp1Val );
			return null;
		}
		if ( exp2 instanceof FunCall ) // field getter functions
		{
			String funNameId = ((FunCall)exp2).funName.id;
			Val val = ((ClassObj)exp1Val).fields.get(funNameId);
			if ( val != null )
				return val.cloneVal();
			else
			{
				System.out.println( "Error: " + ((ClassObj)exp1Val).className + " does not have " + funNameId + " field" );
				return null;
			}
		}
		else // This error case is valid for Project 3 only.
		{
			System.out.println( "Error: 2nd argument of dot operator must be function call" );
			return null;
		}
	}
}
