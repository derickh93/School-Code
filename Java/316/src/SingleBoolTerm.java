import java.util.HashMap;

public class SingleBoolTerm extends Expr {

	BoolTerm t;

	SingleBoolTerm(BoolTerm b) {
		t = b;
	}

	@Override
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		LexicalAnalyzer.displayln(indent1 + indent1.length() + " <boolTerm>");
		t.printParseTree(indent + " ");
	}

	@Override
	Type TypeEval(HashMap<String, String> state) {
		Type tEval = t.TypeEval(state);
		if (tEval != null) {
			if (tEval.keyword.equals("boolean")) {
				return tEval.BooleanClone();
			}else if(tEval.keyword.equals("int")){
				return tEval.IntClone();
			}
			else if(tEval.keyword.equals("float")){
				return tEval.FloatClone();
			}
		}
		System.out.println("Error");
		return null;
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val termEval = t.Eval(state, interpret);
		if(termEval != null){
			Class termClass = termEval.getClass();
			if(termClass == BoolVal.class){
				return ((BoolVal)termEval);
			}
			else if(termClass == IntVal.class){
				return ((IntVal)termEval);
			}
			else if(termClass == FloatVal.class){
				return ((FloatVal)termEval);
			}
		}
		return null;
	}
}
