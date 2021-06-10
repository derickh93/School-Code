class Num extends Elem
{
	Number val;

	public String toString()
	{
		return val.toString();
	}

	Elem cloneElem()
	{
		Num n = new Num();
		n.val = val;
		return n;
	}

	Number plus(Number n)
	{
		String thisType = this.val.getClass().getName();
		String nType = n.getClass().getName();

		if ( thisType.endsWith("Integer") && nType.endsWith("Integer") )
			 return (Integer)n + (Integer)val;

		else if ( thisType.endsWith("Double") && nType.endsWith("Integer") )
			return (Integer)n + (Double)val;

		else if ( thisType.endsWith("Integer") && nType.endsWith("Double") )
			return (Double)n + (Integer)val;

		else //if ( thisType.endsWith("Double") && nType.endsWith("Double") )
			return (Double)n + (Double)val;
	}

	Number minus(Number n)
	{
		String thisType = this.val.getClass().getName();
		String nType = n.getClass().getName();

		if ( thisType.endsWith("Integer") && nType.endsWith("Integer") )
			 return (Integer)n - (Integer)val;

		else if ( thisType.endsWith("Double") && nType.endsWith("Integer") )
			return (Integer)n - (Double)val;

		else if ( thisType.endsWith("Integer") && nType.endsWith("Double") )
			return (Double)n - (Integer)val;

		else //if ( thisType.endsWith("Double") && nType.endsWith("Double") )
			return (Double)n - (Double)val;
	}

	Number times(Number n)
	{
		String thisType = this.val.getClass().getName();
		String nType = n.getClass().getName();

		if ( thisType.endsWith("Integer") && nType.endsWith("Integer") )
			 return (Integer)n * (Integer)val;

		else if ( thisType.endsWith("Double") && nType.endsWith("Integer") )
			return (Integer)n * (Double)val;

		else if ( thisType.endsWith("Integer") && nType.endsWith("Double") )
			return (Double)n * (Integer)val;

		else //if ( thisType.endsWith("Double") && nType.endsWith("Double") )
			return (Double)n * (Double)val;
	}

	Number div(Number n)
	{
		String thisType = this.val.getClass().getName();
		String nType = n.getClass().getName();

		if ( thisType.endsWith("Integer") && nType.endsWith("Integer") )
			 return 1.0*(Integer)n / (Integer)val;

		else if ( thisType.endsWith("Double") && nType.endsWith("Integer") )
			return (Integer)n / (Double)val;

		else if ( thisType.endsWith("Integer") && nType.endsWith("Double") )
			return (Double)n / (Integer)val;

		else //if ( thisType.endsWith("Double") && nType.endsWith("Double") )
			return (Double)n / (Double)val;
	}
}
