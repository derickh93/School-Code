import java.util.HashMap;


public class SingleIDList extends IDList{

	String s;
	
	SingleIDList(String st){
		s = st;
	}
	
	
	@Override
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		LexicalAnalyzer.displayln(indent+indent.length()+" <id list>");
		LexicalAnalyzer.displayln(indent1+indent1.length()+" "+ s);
	}


	@Override
	void M(HashMap<String, String> state, String type) {
		if(state.containsKey(s)==false){
			state.put(s, type);
		}else{
			System.out.println("Variable "+s+" has already been assigned");
			ParseTree.errorFound = true;
		}
	}

}
