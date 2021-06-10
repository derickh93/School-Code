import java.util.HashMap;


public class While extends Statement {

	Statement statement;
	Expr exp;
	
	While(Statement s, Expr e){
		statement = s;
		exp = e;
	}
	
	@Override
	void printParseTree(String indent) {
		LexicalAnalyzer.displayln(indent+" "+(indent+" ").length()+" while");
		LexicalAnalyzer.displayln(indent+" "+(indent+" ").length()+" <expr>");
		exp.printParseTree(indent+" ");
		LexicalAnalyzer.displayln(indent+" "+(indent+" ").length()+" <statement>");
		statement.printParseTree((indent+" "));
	}

	@Override
	boolean TypeEval(HashMap<String, String> state) {
		Type exprEval = exp.TypeEval(state);
		boolean statem = statement.TypeEval(state);
		if (exprEval == null || statem == false) {
			System.out.println("error");
			return false;
		} else if (exprEval != null) {
			if (exprEval.keyword.equals("boolean")) {
				return true;
			}
		} 
		System.out.println("Error: non boolean return in while statement");
		return false;
	}

	@Override
	void m(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val expEval = exp.Eval(state, interpret);
		while(expEval != null && ((BoolVal)expEval).val){
			statement.m(state, interpret);
			expEval = exp.Eval(state, interpret);
		}
	}
}
