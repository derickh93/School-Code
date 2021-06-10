import java.util.HashMap;


public class MulE extends Term{
	
	Primary primary;
	Term term;
	
	MulE(Primary prim, Term t){
		primary = prim;
		term = t;
	}

	@Override
	void printParseTree(String indent) {
		String indent1 = indent+" ";
		LexicalAnalyzer.displayln(indent+indent.length()+" <term>");
		primary.printParseTree(indent1);
		LexicalAnalyzer.displayln(indent1+indent1.length()+" *");
		term.printParseTree(indent1);
	}

	@Override
	Type TypeEval(HashMap<String, String> state) {
		Type pEval = primary.TypeEval(state);
		if(pEval != null){
			Type termEval = term.TypeEval(state);
			if(termEval != null){
				if(pEval.keyword.equals("int") && termEval.keyword.equals("int")){
					return pEval.IntClone();
				}
				else if(pEval.keyword.equals("int") && termEval.keyword.equals("float")){
					return termEval.FloatClone();
				}
				else if(pEval.keyword.equals("float") && termEval.keyword.equals("int")){
					return pEval.FloatClone();
				}else if(pEval.keyword.equals("float") && termEval.keyword.equals("float")){
					return pEval.FloatClone();
				}else{
					System.out.println("* only works on int and float");
					return null;
				}
			}else{
				return null;
			}
		}
		return null;
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val termEval = term.Eval(state, interpret);
		Val primEval = primary.Eval(state, interpret);
		if(termEval == null || primEval == null){
			return null;
		}
		Class termClass = termEval.getClass();
		Class primClass = primEval.getClass();
		if(termClass == IntVal.class && primClass == IntVal.class){
			((IntVal)termEval).val = ((IntVal)termEval).val * ((IntVal)primEval).val;
			return ((IntVal)termEval);
		}else if(termClass == IntVal.class && primClass == FloatVal.class){
			((FloatVal)primEval).val = ((IntVal)termEval).val * ((FloatVal)primEval).val;
			return ((FloatVal)primEval);
		}else if(termClass == FloatVal.class && primClass == IntVal.class){
			((FloatVal)termEval).val = ((FloatVal)termEval).val * ((IntVal)primEval).val;
			return ((FloatVal)termEval);
		}else if(termClass == FloatVal.class && primClass == FloatVal.class){
			((FloatVal)primEval).val = ((FloatVal)termEval).val * ((FloatVal)primEval).val;
			return ((FloatVal)primEval);
		}else{
			return null;
		}
	}
}
