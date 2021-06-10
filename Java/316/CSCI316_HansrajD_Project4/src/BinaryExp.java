abstract class BinaryExp extends FunExp
{
	Exp exp1;
	Exp exp2;

	BinaryExp(Exp e1, Exp e2)
	{
		exp1 = e1;
		exp2 = e2;
	}
}