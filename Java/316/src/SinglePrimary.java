import java.util.HashMap;


public class SinglePrimary extends Term{
	
	Primary primary;
	
	SinglePrimary(Primary p){
		primary = p;
	}

	@Override
	void printParseTree(String indent) {
		LexicalAnalyzer.displayln(indent+indent.length()+" <term>");
		primary.printParseTree(indent+" ");
	}

	@Override
	Type TypeEval(HashMap<String, String> state) {
		Type pEval = primary.TypeEval(state);
		if(pEval != null){
			if(pEval.keyword.equals("int")){
				return pEval.IntClone();
			}
			else if(pEval.keyword.equals("float")){
				return pEval.FloatClone();
			}
			else if(pEval.keyword.equals("boolean")){
				return pEval.BooleanClone();
			}
			else{
				System.out.println("Error");
				return null;
			}
		}
		System.out.println("Error previous state");
		return null;
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val primEval = primary.Eval(state, interpret);
		if(primEval != null){
			Class primClass = primEval.getClass();
			if(primClass == BoolVal.class){
				return ((BoolVal)primEval);
			}
			else if(primClass == IntVal.class){
				return ((IntVal)primEval);
			}else if(primClass == FloatVal.class){
				return ((FloatVal)primEval);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

}
