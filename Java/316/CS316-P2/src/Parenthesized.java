

import java.util.*;

class Parenthesized extends Primary
{
	E e;

	Parenthesized(E exp)
	{
		e = exp;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " <primary>"); 
		e.printParseTree(indent+" ");
	}

//	Val Eval(HashMap<String,Val> state)
//	{
//		return e.Eval(state);
//	}
//	
//	void emitInstructions()
//	{
//		e.emitInstructions();
//	}
}