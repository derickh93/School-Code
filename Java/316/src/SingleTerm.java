import java.util.HashMap;


public class SingleTerm extends E{
	
	Term term;
	
	SingleTerm(Term t){
		term = t;
	}

	@Override
	void printParseTree(String indent) {
		LexicalAnalyzer.displayln(indent+indent.length()+" <E>");
		term.printParseTree(indent+" ");
	}

	@Override
	Type TypeEval(HashMap<String, String> state) {
		Type termEval = term.TypeEval(state);
		if(termEval != null){
			if(termEval.keyword.equals("int")){
				return termEval.IntClone();
			}
			else if(termEval.keyword.equals("float")){
				return termEval.FloatClone();
			}else if(termEval.keyword.equals("boolean")){
				return termEval.BooleanClone();
			}else{
				System.out.println("error");
				return null;
			}
		}
		System.out.println("Error");
		return null;
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val termEval = term.Eval(state, interpret);
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
			else{
				return null;
			}
		}
		else{
			return null;
		}
	}
}
