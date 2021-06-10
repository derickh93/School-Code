import java.util.*;

public abstract class Interpreter extends Parser
{
	public static HashMap<String, Val> varState = new HashMap<String, Val>(); 
	              // program state, i.e., virtual memory for variables
		
	
	public static void main(String argv[])
	{
		// argv[0]: input file containing an assignment list
		// argv[1]: output file displaying the parse tree
		// argv[2]: output file displaying the numbers of constructed objects and
		//          the visited objects in order of the depth-first traversal
		
		setIO( argv[0], argv[1] );
		setLex();

		getToken();

		AssignmentList assignmentList = assignmentList(); // build a parse tree
		if ( !t.isEmpty() )
			displayln(t + " : Syntax Error, unexpected symbol where id expected");
		else if ( !errorFound )
		{
			assignmentList.printParseTree("");       // print the parse tree in linearly indented form in argv[1] file
			assignmentList.M(varState);              // interpret the assignment list
			System.out.println(varState.toString()); // print the program state on the terminal
		}

		assignmentList.traversal(); // perform depth-first traversal from assignmentList

		closeIO();
	}
}