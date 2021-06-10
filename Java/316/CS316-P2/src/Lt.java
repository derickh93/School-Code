import java.util.*;

class Lt extends RelOp{

	String id;
	
	Lt(String ident)
	{
		id = ident;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " " + id);
	}

}
