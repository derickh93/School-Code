class Iff extends Instruction
{
	int target;

	void updateLabel()
	{
		target = VM.labelMap.get(target);
	}

	void execute()
	{
		ElemBool b = (ElemBool) VM.opStack[VM.top];
		if ( b.val == false )
			VM.pc = target;
		else
			VM.pc++;
		VM.top--;
	}
}
