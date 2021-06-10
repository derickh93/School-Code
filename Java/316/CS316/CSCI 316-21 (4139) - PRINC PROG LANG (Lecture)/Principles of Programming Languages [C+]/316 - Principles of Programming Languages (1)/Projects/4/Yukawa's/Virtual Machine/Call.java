class Call extends Instruction
{
	int funStart;
	int numOfArgs;

	void updateLabel()
	{
		funStart = VM.labelMap.get(funStart);
	}

	void execute()
	{
		AR ar = new AR();
		ar.base = VM.top-numOfArgs+1;
		ar.returnAdd = VM.pc+1;
		VM.topR++;
		VM.runtimeStack[VM.topR] = ar;
		VM.pc = funStart;
	}
}
