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
}