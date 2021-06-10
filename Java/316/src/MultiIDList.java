import java.util.HashMap;


public class MultiIDList extends IDList{

	IDList idl;
	String ID;
	
	MultiIDList(IDList d, String s){
		idl = d;
		ID = s;
	}
	
	@Override
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		LexicalAnalyzer.displayln(indent+indent.length()+" <id list>");
		LexicalAnalyzer.displayln(indent1+indent1.length()+" "+ ID);
		idl.printParseTree(indent1);
	}

	@Override
	void M(HashMap<String, String> state, String type) {
		if(state.containsKey(ID)==false){
			state.put(ID, type);
			idl.M(state, type);
		}else{
			System.out.println("Variable "+ID+" has already been assigned");
			ParseTree.errorFound = true;
		}
	}

}
