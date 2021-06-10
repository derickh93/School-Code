import java.util.HashMap;

abstract class BinaryExp extends FunExp
{
	Exp exp1;
	Exp exp2;

	BinaryExp(Exp e1, Exp e2)
	{
		exp1 = e1;
		exp2 = e2;
	}

	void semanticCheck()
	{
		exp1.semanticCheck();
		exp2.semanticCheck();
	}
	
	Val Eval(HashMap<String,Val> state)
	{
		if(true)
			return exp1.Eval(state);
		else
			return exp2.Eval(state);
	}
}