import java.util.HashMap;


public class BoolLiteral extends Primary{
	
	String bool;
	
	BoolLiteral(String b){
		bool = b;
	}
	
	@Override
	void printParseTree(String indent) {
		LexicalAnalyzer.displayln(indent+indent.length()+" <primary> "+bool);
	}

	@Override
	Type TypeEval(HashMap<String, String> state) {
		return new Type("boolean", null);
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		if(bool.equals("true")){
			return new BoolVal(true);
		}else if(bool.equals("false")){
			return new BoolVal(false);
		}else{
			return null;
		}
	}
}
