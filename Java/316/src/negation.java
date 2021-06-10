import java.util.HashMap;


public class negation extends Primary {

	Primary prim;
	
	negation(Primary p){
		prim = p;
	}
	
	@Override
	void printParseTree(String indent) {
		String indent1 = indent+" ";
		LexicalAnalyzer.displayln(indent+indent.length()+" <primary>");
		LexicalAnalyzer.displayln(indent1+indent1.length()+" -");
		prim.printParseTree(indent1);
	}

	@Override
	Type TypeEval(HashMap<String, String> state) {
		Type prime = prim.TypeEval(state);
		if(prime != null){
			if(prime.keyword.equals("int")){
				return prime.IntClone();
			}
			else if(prime.keyword.equals("float")){
				return prime.FloatClone();
			}else{
				System.out.println("It should be either int or float");
				return null;
			}
		}
		System.out.println("Error previous state");
		return null;
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val primEval = prim.Eval(state, interpret);
		if(primEval != null){
			Class primClass = primEval.getClass();
			if(primClass == IntVal.class){
				((IntVal)primEval).val *= -1;
				return ((IntVal)primEval);
			}else if(primClass == FloatVal.class){
				((FloatVal)primEval).val *= -1;
				return ((FloatVal)primEval);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

}
