import java.util.*;

class Mul extends ArithExp
{
	Mul(Exp e1, Exp e2)
	{
		super(e1, e2);
	}
	
	void semanticCheck()
	{
		SemanticChecker.currentBodyListEntry+="(* ";

		exp1.semanticCheck();
		exp2.semanticCheck();
		SemanticChecker.currentBodyListEntry+=")";

	}

	Val Eval(HashMap<String,Val> state)
	{
		Val exp1Val = exp1.Eval(state);
		Val exp2Val = exp2.Eval(state);

		if ( exp1Val == null || exp2Val == null )
			return null;
		if ( !exp1Val.isNumber() )
		{
			System.out.println( "Error: * operator cannot be applied to " + exp1Val.toString() );
			return null;
		}
		if ( !exp2Val.isNumber() )
		{
			System.out.println( "Error: * operator cannot be applied to " + exp2Val.toString() );
			return null;
		}
		
		// The result will be float if one or both of the arguments are float.
		
		Class exp1Class = exp1Val.getClass();
		Class exp2Class = exp2Val.getClass();
		
		if ( exp1Class == IntVal.class && exp2Class == IntVal.class )
		{
			((IntVal)exp1Val).val = ((IntVal)exp1Val).val * ((IntVal)exp2Val).val;
			return exp1Val;
		}
		else if ( exp1Class == IntVal.class ) // exp2Class == FloatVal.class
		{
			((FloatVal)exp2Val).val = ((IntVal)exp1Val).val * ((FloatVal)exp2Val).val;
			return exp2Val;
		}
		else // exp1Class == FloatVal.class
		{
			((FloatVal)exp1Val).val = ((FloatVal)exp1Val).val * exp2Val.floatVal();
			return exp1Val;
		}
	}
}
