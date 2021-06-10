import java.util.HashMap;

public class SubE extends E {

	Term term;
	E e;

	SubE(Term t, E e_) {
		term = t;
		e = e_;
	}

	@Override
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		LexicalAnalyzer.displayln(indent + indent.length() + " <E>");
		term.printParseTree(indent1);
		LexicalAnalyzer.displayln(indent1 + indent1.length() + " -");
		e.printParseTree(indent1);
	}

	@Override
	Type TypeEval(HashMap<String, String> state) {
		Type termEval = term.TypeEval(state);
		if (termEval != null) {
			Type eEval = e.TypeEval(state);
			if (eEval != null) {
				if (termEval.keyword.equals("int")
						&& eEval.keyword.equals("int")) {
					return termEval.IntClone();
				} else if (termEval.keyword.equals("int")
						&& eEval.keyword.equals("float")) {
					return termEval.FloatClone();
				} else if (termEval.keyword.equals("float")
						&& eEval.keyword.equals("int")) {
					return termEval.FloatClone();
				} else if (termEval.keyword.equals("float")
						&& eEval.keyword.equals("float")) {
					return termEval.FloatClone();
				} else {
					System.out.println("- only works on int and float");
					return null;
				}
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val termEval = term.Eval(state, interpret);
		Val eEval = e.Eval(state, interpret);
		if(termEval == null || eEval == null){
			return null;
		}
		Class termClass = termEval.getClass();
		Class eClass = eEval.getClass();
		if(termClass == IntVal.class && eClass == IntVal.class){
			((IntVal)termEval).val = ((IntVal)termEval).val - ((IntVal)eEval).val;
			return ((IntVal)termEval);
		}else if(termClass == IntVal.class && eClass == FloatVal.class){
			((FloatVal)eEval).val = ((IntVal)termEval).val - ((FloatVal)eEval).val;
			return ((FloatVal)eEval);
		}else if(termClass == FloatVal.class && eClass == IntVal.class){
			((FloatVal)termEval).val = ((FloatVal)termEval).val - ((IntVal)eEval).val;
			return ((FloatVal)termEval);
		}else if(termClass == FloatVal.class && eClass == FloatVal.class){
			((FloatVal)termEval).val = ((FloatVal)termEval).val - ((FloatVal)eEval).val;
			return ((FloatVal)termEval);
		}else{
			return null;
		}
	}
}
