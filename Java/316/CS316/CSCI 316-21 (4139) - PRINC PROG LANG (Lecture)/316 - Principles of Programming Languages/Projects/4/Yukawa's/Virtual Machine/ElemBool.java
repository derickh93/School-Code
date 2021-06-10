class ElemBool extends Elem
{
	boolean val;

	public String toString()
	{
		return val+"";
	}

	Elem cloneElem()
	{
		ElemBool b = new ElemBool();
		b.val = val;
		return b;
	}
}
