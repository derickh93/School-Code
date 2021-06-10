import java.util.HashMap;


public class MultiBoolPrimary extends BoolTerm{
	
	BoolPrimary prim;
	BoolTerm term;
	
	MultiBoolPrimary(BoolTerm t, BoolPrimary p){
		prim = p;
		term = t;
	}

	@Override
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		LexicalAnalyzer.displayln(indent1+indent1.length()+" <boolPrimary>");
		prim.printParseTree(indent1+" ");
		LexicalAnalyzer.displayln(indent1+indent1.length()+" &&");
		LexicalAnalyzer.displayln(indent1+indent1.length()+" <boolTerm>");
		term.printParseTree(indent1);
	}

	@Override
	Type TypeEval(HashMap<String, String> state) {
		Type pEval = prim.TypeEval(state);
		if(pEval!=null){
			Type tEval = term.TypeEval(state);
			if(tEval!=null){
				if(pEval.keyword.equals("boolean") && tEval.keyword.equals("boolean")){
					return tEval.BooleanClone();
				}
			}else{
				System.out.println("&& applied to "+pEval.keyword);
				return null;
			}
		}else{
			System.out.println("error");
			return null;
		}
		System.out.println("error");
		return null;
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val primEval = prim.Eval(state, interpret);
		Val termEval = term.Eval(state, interpret);
		if(primEval == null || termEval == null){
			return null;
		}
		Class primClass = primEval.getClass();
		Class termClass = termEval.getClass();
		if(primClass == BoolVal.class && termClass == BoolVal.class){
			if(((BoolVal)primEval).val == true && ((BoolVal)termEval).val == true){
				return primEval;
			}else if(((BoolVal)primEval).val == false && ((BoolVal)termEval).val == true){
				return primEval;
			}else if(((BoolVal)primEval).val == true && ((BoolVal)termEval).val == false){
				return termEval;
			}else{
				return primEval;
			}
		}
		return null;
	}

}
