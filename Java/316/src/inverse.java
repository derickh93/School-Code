import java.util.HashMap;


public class inverse extends Primary {

	Primary prim;
	
	inverse(Primary p){
		prim = p;
	}
	
	@Override
	void printParseTree(String indent) {
		String indent1 = indent+" ";
		LexicalAnalyzer.displayln(indent+indent.length()+" <primary>");
		LexicalAnalyzer.displayln(indent1+indent1.length()+" !");
		prim.printParseTree(indent1);
	}

	@Override
	Type TypeEval(HashMap<String, String> state) {
		Type prime = prim.TypeEval(state);
		if(prime != null){
			if(prime.keyword.equals("boolean")){
				return prime.BooleanClone();
			}
			else{
				System.out.println("Not a boolean");
				return null;
			}
		}
		System.out.println("Nothing came out of primery");
		return null;
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val primEval = prim.Eval(state, interpret);
		if(primEval != null){
			Class primClass = primEval.getClass();
			if(primClass == BoolVal.class){
				if(((BoolVal)primEval).val == false){
					return new BoolVal(true);
				}else{
					return new BoolVal(false);
				}
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

}
