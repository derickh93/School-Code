import java.util.HashMap;


public class SingleStatement extends SList {

	Statement statement;
	
	SingleStatement(Statement s){
		statement = s;
	}
	
	@Override
	void printParseTree(String indent) {
		String indent1 = indent+" ";
		LexicalAnalyzer.displayln(indent+indent.length()+" <slist>");
		LexicalAnalyzer.displayln(indent1+indent1.length()+" <statement>");
		statement.printParseTree(indent1);
	}

	@Override
	boolean TypeEval(HashMap<String, String> state) {
		if(statement.TypeEval(state)==true){
			return true;
		}
		System.out.println("Error");
		return false;
	}

	@Override
	void m(HashMap<String, String> state, HashMap<String, Val> interpret) {
		statement.m(state, interpret);		
	}
}
