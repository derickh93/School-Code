import java.util.HashMap;


public class DivE extends Term{
	
	Primary primary;
	Term term;
	
	DivE(Primary t, Term te){
		primary = t;
		term = te;
	}

	@Override
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		LexicalAnalyzer.displayln(indent+indent.length()+" <term>");
		primary.printParseTree(indent1);
		LexicalAnalyzer.displayln(indent1+indent1.length()+" /");
		term.printParseTree(indent1);
	}
	
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
					System.out.println("/ only works on int and float");
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
		Val primEval = primary.Eval(state, interpret);
		Val termEval = term.Eval(state, interpret);
		if(primEval == null || termEval == null){
			return null;
		}
		if(termEval.isZero()){
			System.out.println("Cannot divide by zero");
			return null;
		}
		Class primClass = primEval.getClass();
		Class termClass = termEval.getClass();
		if(termClass == IntVal.class && primClass == IntVal.class){
			((IntVal)termEval).val = ((IntVal)primEval).val / ((IntVal)termEval).val;
			return ((IntVal)termEval);
		}else if(termClass == IntVal.class && primClass == FloatVal.class){
			((FloatVal)primEval).val = ((FloatVal)primEval).val / ((IntVal)termEval).val;
			return ((FloatVal)primEval);
		}else if(termClass == FloatVal.class && primClass == IntVal.class){
			((FloatVal)termEval).val = ((IntVal)primEval).val / ((FloatVal)termEval).val;
			return ((FloatVal)termEval);
		}else if(termClass == FloatVal.class && primClass == FloatVal.class){
			((FloatVal)primEval).val = ((FloatVal)primEval).val / ((FloatVal)termEval).val;
			return ((FloatVal)primEval);
		}else{
			return null;
		}
	}

}
