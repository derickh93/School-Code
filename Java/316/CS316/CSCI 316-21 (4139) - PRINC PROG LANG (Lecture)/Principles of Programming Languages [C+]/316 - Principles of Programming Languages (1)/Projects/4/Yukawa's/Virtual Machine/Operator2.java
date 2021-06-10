class Operator2 extends Instruction
{
	State opcode;

	void execute()
	{
		if ( opcode == State.Inv )
		{
			((ElemBool) VM.opStack[VM.top]).val = !((ElemBool) VM.opStack[VM.top]).val;
			VM.top++;
		}
		else if ( opcode == State.Neg )
		{
			Number num = ((Num) VM.opStack[VM.top]).val;
			String numType = num.getClass().getName();
			if ( numType.endsWith("Integer") )
				((Num) VM.opStack[VM.top]).val = -(Integer)num;
			else
				((Num) VM.opStack[VM.top]).val = -(Double)num;

			VM.top++;
		}
		else 
		{
			float topElem = ((Num) VM.opStack[VM.top]).val.floatValue();
			float topElem1 = ((Num) VM.opStack[VM.top-1]).val.floatValue();
			boolean b;

			switch( opcode )
			{
			case Lt:
				b = topElem1 < topElem; break;
			case Le:
				b = topElem1 <= topElem; break;
			case Gt:
				b = topElem1 > topElem; break;
			case Ge:
				b = topElem1 >= topElem; break;
			case Eq:
				b = topElem1 == topElem; break;
			default: // opcode = Neq
				b = topElem1 != topElem; break;
			}

			ElemBool eb = new ElemBool();
			eb.val = b;
			VM.opStack[VM.top-1] = eb;
		}

		VM.top--;
		VM.pc++;
	}
}
