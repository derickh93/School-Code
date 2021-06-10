class PushNum extends Push
{
	Num arg; // The number element to be pushed onto the operand stack

	Elem arg()
	{
		return arg.cloneElem();
	}
}
