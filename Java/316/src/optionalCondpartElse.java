import java.util.HashMap;

public class optionalCondpartElse extends Statement {

	Statement statement, statement1;
	Expr exp;

	optionalCondpartElse(Statement s, Statement s1, Expr e) {
		statement = s;
		statement1 = s1;
		exp = e;
	}

	@Override
	void printParseTree(String indent) {
		LexicalAnalyzer.displayln(indent + " " + (indent + " ").length()
				+ " if");
		LexicalAnalyzer.displayln((indent + " ") + (indent + " ").length()
				+ " <expr>");
		exp.printParseTree(indent + " ");
		LexicalAnalyzer.displayln((indent + " ") + (indent + " ").length()
				+ " <statement>");
		statement.printParseTree(indent + " ");
		LexicalAnalyzer.displayln((indent + " ") + (indent + " ").length()
				+ " else");
		LexicalAnalyzer.displayln((indent + " ") + (indent + " ").length()
				+ " <statement>");
		statement1.printParseTree(indent + " ");
	}

	@Override
	boolean TypeEval(HashMap<String, String> state) {
		Type exprEval = exp.TypeEval(state);
		if(exprEval != null){
			boolean statem = statement.TypeEval(state);
			if(statem == true){
				if(exprEval.keyword.equals("boolean")){
					boolean statem1 = statement1.TypeEval(state);
					if(statem1 == true){
						return true;
					}
					return true;
				}else{
					System.out.println("Error: non boolean return cond_if_else");
					return false;
				}
			}
		}
		System.out.println("Error: non boolean return in cond_if_else");
		return false;
	}

	@Override
	void m(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val expEval = exp.Eval(state, interpret);
		if(expEval != null){
			if(((BoolVal)expEval).val){
				statement.m(state, interpret);
			}else{
				statement1.m(state, interpret);
			}
		}
	}
}
