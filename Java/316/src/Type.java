import java.util.HashMap;


public class Type extends VarDecl{
	
	String keyword;
	IDList idl;
	
	Type(String k, IDList l){
		keyword = k;
		idl = l;
	}

	@Override
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		LexicalAnalyzer.displayln(indent1+indent1.length()+" <var decl>");
		indent1 = indent1 + " ";
		LexicalAnalyzer.displayln(indent1+indent1.length()+ " "+keyword);
		idl.printParseTree(indent1);
	}

	@Override
	void M(HashMap<String, String> state) {
		idl.M(state, keyword);
	}
	
	Type BooleanClone(){
		return new Type("boolean", idl);
	}
	
	Type IntClone(){
		return new Type("int", idl);
	}
	
	Type FloatClone(){
		return new Type("float", idl);
	}
}
