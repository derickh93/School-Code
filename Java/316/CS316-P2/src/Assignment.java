import java.util.*;

class Assignment extends Statement
{
	String id; // variable on the left side of the assignment
	Expr expr;       // expression on the right side of the assignment

	Assignment(String s, Expr e)
	{
		id = s;
		expr = e;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";		
		lexArithArray.displayln(indent1 + " "+ indent1.length() + " <assignment>");
		indent1=indent1 + " ";
		lexArithArray.displayln(indent1 + " "+ indent1.length() + " " + id);
		lexArithArray.displayln(indent1 + " "+ indent1.length() + " =");
		lexArithArray.displayln(indent1 + " "+ indent1.length() + " <expr>");
		expr.printParseTree(indent1);
	}
	
//	void M(HashMap<String,Val> state) // function to interpret this assignment
//	{
//		Val eVal = e.Eval(state); // evaluate expression e
//		if ( eVal != null )
//			state.put(id, eVal); // assign the value eVal to id
//
//		/* For practical reason of efficiency, the error state is not implemented.
//		   Rather, the error state is implicitly assumed whenever Eval returns null representing
//		   the runtime error value. */
//	}
//	
//	void emitInstructions()
//	{
//		e.emitInstructions();
//		lexArithArray.displayln("pop " + id);
//	}
}
