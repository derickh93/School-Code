import java.util.HashMap;

public class Cond extends Statement {

	Statement statement;
	Expr exp;

	Cond(Statement s, Expr e) {
		statement = s;
		exp = e;
	}

	@Override
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		LexicalAnalyzer.displayln(indent1 + indent1.length() + " if");
		LexicalAnalyzer.displayln(indent1 + indent1.length() + " <expr>");
		exp.printParseTree(indent1);
		LexicalAnalyzer.displayln(indent1 + indent1.length() + " <statement>");
		statement.printParseTree(indent1);
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
		System.out.println("Error non boolean return in if_else");
		return false;
	}

	@Override
	void m(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val expEval = exp.Eval(state, interpret);
		if(expEval != null && ((BoolVal)expEval).val){
			statement.m(state, interpret);
		}
	}
}
