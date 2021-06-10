import java.util.*;

class DotExp extends BinaryExp
{
	DotExp(Exp e1, Exp e2)
	{
		super(e1, e2);
	}

	void semanticCheck()
	{
		SemanticChecker.currentBodyListEntry+="(. ";
		exp1.semanticCheck();
		exp2.semanticCheck();
		SemanticChecker.currentBodyListEntry+=")";

	}
	
	Val Eval(HashMap<String,Val> state)
	{
		Val exp1Val = exp1.Eval(state);
		Val exp2Val = exp2.Eval(state);


		if ( exp1Val == null )
			return null;
		if ( !(exp1Val instanceof ClassObj) )
		{
			System.out.println( "Error: dot operator cannot be applied to " + exp1Val );
			return null;
		}
		
		 if(exp2 instanceof Id) {
			 Val val = ((ClassObj)exp1Val).fields.get(exp2);	
			 if(val!=null)
				 System.out.println(val);
			 else
				 System.out.println("null");
		}
		
		if(exp2 instanceof FunCall) {
			String funNameId = ((FunCall)exp2).funName.id;

			if(((ClassObj)exp1Val).functions.keySet().contains(funNameId)) {
				String functionParameter = ((FunCall)exp1).funName.id;
				System.out.println(functionParameter);
				Val r;
				//Val paramVal = functionParameter.Eval(state);
				
				System.out.println( ((ClassObj)exp1Val).className + " contains " + funNameId + " function" );
			}
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
		else 
		{
			return null;
		}
	}
}
