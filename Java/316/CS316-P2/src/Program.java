import java.util.*;

class Program {
	VarDeclarations varDeclarations;
	Statement statement;
	
	Program(VarDeclarations v, Statement s){
		varDeclarations = v;
		statement = s;
	}

	
	void printParseTree(String indent) {
//		varDeclarations.printParseTree(indent);
//		statement.printParseTree(indent);
		String indent1 = indent + " ";
		lexArithArray.displayln(indent + indent.length() + " <program> ");		
		varDeclarations.printParseTree(indent1);
		lexArithArray.displayln(indent1 + indent1.length() + " <statement>");
		statement.printParseTree(indent1);
	}
//	abstract void printParseTree(String indent);
//	abstract Val Eval(HashMap<String,Val> state);
//	abstract void emitInstructions();
}
