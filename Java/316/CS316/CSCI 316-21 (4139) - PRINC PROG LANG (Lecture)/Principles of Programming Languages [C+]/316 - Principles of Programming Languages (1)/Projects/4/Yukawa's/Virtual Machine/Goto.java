class Goto extends Instruction
{
	int target;

	void updateLabel()
	{
		target = VM.labelMap.get(target);
	}

	void execute()
	{
		VM.pc = target;
	}
}
