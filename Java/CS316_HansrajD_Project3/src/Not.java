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

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}
}