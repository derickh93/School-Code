abstract class Push extends Instruction
{
	abstract Elem arg(); // Returns the element to be pushed onto the operand stack

	void execute()
	{
		VM.top++;
		VM.opStack[VM.top] = arg();
		VM.pc++;
	}
}
