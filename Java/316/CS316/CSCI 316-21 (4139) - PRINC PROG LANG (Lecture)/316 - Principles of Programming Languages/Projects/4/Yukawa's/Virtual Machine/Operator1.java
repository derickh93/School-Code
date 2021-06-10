class Operator1 extends Instruction
{
	State opcode;
	int numOfArgs;

	void execute()
	{
		int bottom = VM.top-numOfArgs+1;
		int top = VM.top;
		Number m;
		boolean b;
		
		switch( opcode )
		{
		case Add:
			m = ((Num)VM.opStack[bottom]).val;
			for (int n = bottom+1; n <= top; n++)
				m = ((Num)VM.opStack[n]).plus(m);
			((Num)VM.opStack[bottom]).val = m;
			break;
		case Sub:
			m = ((Num)VM.opStack[bottom]).val;
			for (int n = bottom+1; n <= top; n++)
				m = ((Num)VM.opStack[n]).minus(m);
			((Num)VM.opStack[bottom]).val = m;
			break;
		case Mul:
			m = ((Num)VM.opStack[bottom]).val;
			for (int n = bottom+1; n <= top; n++)
				m = ((Num)VM.opStack[n]).times(m);
			((Num)VM.opStack[bottom]).val = m;
			break;
		case Div:
			m = ((Num)VM.opStack[bottom]).val;
			for (int n = bottom+1; n <= top; n++)
				m = ((Num)VM.opStack[n]).div(m);
			((Num)VM.opStack[bottom]).val = m;
			break;
		case Or:
			b = ((ElemBool)VM.opStack[bottom]).val;
			for (int n = bottom+1; n <= top; n++)
				b = b || ((ElemBool)VM.opStack[n]).val;
			((ElemBool)VM.opStack[bottom]).val = b;
			break;
		case And:
			b = ((ElemBool)VM.opStack[bottom]).val;
			for (int n = bottom+1; n <= top; n++)
				b = b && ((ElemBool)VM.opStack[n]).val;
			((ElemBool)VM.opStack[bottom]).val = b;
			break;
		}

		VM.top = bottom;
		VM.pc++;
	}
}
