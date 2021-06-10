import java.util.HashMap;


public class Block extends Statement {
	
	SList slist;
	
	Block(SList s){
		slist = s;
	}
	
	@Override
	void printParseTree(String indent) {
		indent+=" ";
		LexicalAnalyzer.displayln(indent+indent.length()+" <block>");
		slist.printParseTree(indent+" ");
	}

	@Override
	boolean TypeEval(HashMap<String, String> state) {
		if(slist.TypeEval(state)==true){
			return true;
		}
		System.out.println("Error");
		return false;
	}

	@Override
	void m(HashMap<String, String> state, HashMap<String, Val> interpret) {
		slist.m(state, interpret);
	}

}
