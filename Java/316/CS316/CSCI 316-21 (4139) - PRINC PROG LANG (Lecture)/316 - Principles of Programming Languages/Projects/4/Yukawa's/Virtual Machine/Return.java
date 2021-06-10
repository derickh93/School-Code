class Return extends Instruction
{
	void execute()
	{
		VM.opStack[(VM.runtimeStack[VM.topR]).base] = VM.opStack[VM.top];
		VM.top = (VM.runtimeStack[VM.topR]).base;
		VM.pc = (VM.runtimeStack[VM.topR]).returnAdd;
		VM.topR--;
	}
}
