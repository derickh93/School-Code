import java.util.HashMap;


public class MultipleStatements extends SList {

	Statement statement;
	SList slist;
	
	MultipleStatements(Statement s, SList l){
		statement = s;
		slist = l;
	}
	
	@Override
	void printParseTree(String indent) {
		String indent1 = indent+" ";
		LexicalAnalyzer.displayln(indent+indent.length()+" <s list>");
		LexicalAnalyzer.displayln(indent1+indent1.length()+" <statement>");
		statement.printParseTree(indent1);
		slist.printParseTree(indent1);
	}

	@Override
	boolean TypeEval(HashMap<String, String> state) {
		if(statement.TypeEval(state)==false || slist.TypeEval(state)==false){
			System.out.println("error");
			return false;
		}
		return true;
	}

	@Override
	void m(HashMap<String, String> state, HashMap<String, Val> interpret) {
		statement.m(state, interpret);
		slist.m(state, interpret);
	}

}
