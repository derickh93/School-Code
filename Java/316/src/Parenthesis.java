import java.util.HashMap;

public class Parenthesis extends Primary {

	Expr e;

	Parenthesis(Expr e_) {
		e = e_;
	}

	@Override
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		LexicalAnalyzer.displayln(indent + indent.length() + " <primary>");
		LexicalAnalyzer.displayln(indent1 + indent1.length() + " <expr>");
		e.printParseTree(indent1);
	}

	@Override
	Type TypeEval(HashMap<String, String> state) {
		Type expr = e.TypeEval(state);
		if (expr != null) {
			if (expr.keyword.equals("boolean")) {
				return expr.BooleanClone();
			} else if (expr.keyword.equals("int")) {
				return expr.IntClone();
			} else if (expr.keyword.equals("float")) {
				return expr.FloatClone();
			} else {
				System.out.println("should always return boolean, int, float");
				return null;
			}
		}
		System.out.println("Error previous state");
		return null;
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val eEval = e.Eval(state, interpret);
		if(eEval != null){
			Class eClass = eEval.getClass();
			if(eClass == IntVal.class){
				return ((IntVal)eEval);
			}else if(eClass == FloatVal.class){
				return ((FloatVal)eEval);
			}else if(eClass == BoolVal.class){
				return ((BoolVal)eEval);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

}
