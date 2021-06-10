import java.util.HashMap;

public class SingleBoolPrimary extends BoolTerm {

	BoolPrimary prim;

	SingleBoolPrimary(BoolPrimary p) {
		prim = p;
	}

	@Override
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		LexicalAnalyzer
				.displayln(indent1 + indent1.length() + " <boolPrimary>");
		/*
		 * indent1 += " ";
		 * LexicalAnalyzer.displayln(indent1+indent1.length()+" <E>"); indent1
		 * += " ";
		 * LexicalAnalyzer.displayln(indent1+indent1.length()+" <term>");
		 */
		prim.printParseTree(indent1 + " ");
	}

	@Override
	Type TypeEval(HashMap<String, String> state) {
		Type pEval = prim.TypeEval(state);
		if (pEval != null) {
			if (pEval.keyword.equals("boolean")) {
				return pEval.BooleanClone();
			}else if(pEval.keyword.equals("int")){
				return pEval.IntClone();
			}
			else if(pEval.keyword.equals("float")){
				return pEval.FloatClone();
			}
		}
		System.out.println("error");
		return null;
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val primEval = prim.Eval(state, interpret);
		if(primEval != null){
			Class primClass = primEval.getClass();
			if(primClass == BoolVal.class){
				return ((BoolVal)primEval);
			}
			else if(primClass == IntVal.class){
				return ((IntVal)primEval);
			}
			else if(primClass == FloatVal.class){
				return ((FloatVal)primEval);
			}else{
				return null;
			}
		}
		else{
			return null;
		}
	}

}
