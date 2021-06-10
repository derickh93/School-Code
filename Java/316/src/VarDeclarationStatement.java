import java.util.*;

class VarDeclarationStatement extends Program{
	
	VarDeclaration vardeclaration;
	Statement statement;
	
	VarDeclarationStatement(VarDeclaration vdec, Statement st){
		vardeclaration = vdec;
		statement = st;
	}
	
	void printParseTree(String indent){
		LexicalAnalyzer.displayln(indent+indent.length()+" <program>");
		vardeclaration.printParseTree(indent);
		LexicalAnalyzer.displayln(indent+" "+(indent+" ").length()+" <statement>");
		statement.printParseTree(indent+" ");
	}
	
	void M(HashMap<String, String> state){
		vardeclaration.M(state);
		Iterator iterator = state.keySet().iterator();
		System.out.print("[");
		while(iterator.hasNext()){
			String key = iterator.next().toString();
			System.out.print(key + ":"+state.get(key).toString());
			if(iterator.hasNext()){
				System.out.print(", ");
			}
		}
		System.out.println("]");
		if(ParseTree.errorFound == false){
			statement.TypeEval(state);
		}
	}

	@Override
	void m(HashMap<String, String> state, HashMap<String, Val> interpret) {
		statement.m(state, interpret);
	}
	
}
