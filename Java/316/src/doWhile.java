import java.util.HashMap;


public class doWhile extends Statement {

	Statement statement;
	Expr exp;
	
	doWhile(Statement s, Expr e){
		statement = s;
		exp = e;
	}
	
	@Override
	void printParseTree(String indent) {
		String indent1 = indent+" ";
		LexicalAnalyzer.displayln(indent1+indent1.length()+" do");
		LexicalAnalyzer.displayln(indent1+indent1.length()+" <statement>");
		statement.printParseTree(indent+" ");
		LexicalAnalyzer.displayln(indent1+indent1.length()+" while");
		LexicalAnalyzer.displayln(indent1+indent1.length()+" <expr>");
		exp.printParseTree(indent+" ");
	}

	@Override
	boolean TypeEval(HashMap<String, String> state) {
		boolean statem = statement.TypeEval(state);
		Type exprEval = exp.TypeEval(state);
		if (exprEval == null || statem == false) {
			System.out.println("Error");
			return false;
		} else if (exprEval != null) {
			if (exprEval.keyword.equals("boolean")) {
				return true;
			}
		} 
		System.out.println("error: Non boolean return in do_while statement");
		return false;
	}

	@Override
	void m(HashMap<String, String> state, HashMap<String, Val> interpret) {
		statement.m(state, interpret);
		Val expEval = exp.Eval(state, interpret);
		while(expEval != null && ((BoolVal)expEval).val){
			statement.m(state, interpret);
			expEval = exp.Eval(state, interpret);
		}
	}
}
