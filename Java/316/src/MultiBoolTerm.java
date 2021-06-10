import java.util.HashMap;


public class MultiBoolTerm extends Expr{
	
	
	BoolTerm t;
	Expr exp;
	
	MultiBoolTerm(BoolTerm b, Expr e){
		t = b;
		exp = e;
	}

	@Override
	void printParseTree(String indent) {
		String indent1 = indent+" ";
		LexicalAnalyzer.displayln(indent1+indent1.length()+" <boolTerm>");
		t.printParseTree(indent1);
		LexicalAnalyzer.displayln(indent1+indent1.length()+" ||");
		LexicalAnalyzer.displayln(indent1+indent1.length()+ " <expr>");
		exp.printParseTree(indent1);
	}

	@Override
	Type TypeEval(HashMap<String, String> state) {
		Type tVal = t.TypeEval(state);
		if(tVal!=null){
			Type exprEval = exp.TypeEval(state);
			if(exprEval!=null){
				if(tVal.keyword.equals("boolean") && exprEval.keyword.equals("boolean")){
					return exprEval.BooleanClone();
				}
			}
			else{
				System.out.println("The expression should return boolean");
				return null;
			}
		}else{
			System.out.println("|| applied to non boolean");
			return null;
		}
		System.out.println("|| applied to non boolean");
		return null;
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val termEval = t.Eval(state, interpret);
		Val exprEval = exp.Eval(state, interpret);
		if(termEval == null || exprEval == null)
			return null;
		Class termClass = termEval.getClass();
		Class exprClass = exprEval.getClass();
		if(termClass == BoolVal.class && exprClass == BoolVal.class){
			if(((BoolVal)termEval).val == true && ((BoolVal)exprEval).val == false){
				return termEval;
			}else if(((BoolVal)termEval).val == false && ((BoolVal)exprEval).val == true){
				return exprEval;
			}else if(((BoolVal)termEval).val == true && ((BoolVal)exprEval).val == true){
				return termEval;
			}else{
				return termEval;
			}
		}
		return null;
	}
	
	
}
