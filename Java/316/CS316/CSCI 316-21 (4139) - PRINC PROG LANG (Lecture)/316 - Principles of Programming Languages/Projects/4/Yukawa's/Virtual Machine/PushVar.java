class PushVar extends Push
{
	int index; // The index of the variable to be pushed onto the operand stack

	Elem arg()
	{
		return VM.opStack[(VM.runtimeStack[VM.topR]).base+index-1].cloneElem();
	}
}
