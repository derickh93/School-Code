import java.awt.RenderingHints.Key;
import java.util.HashMap;


public class Assignment extends Cluster{

	
	String id, assign;
	Expr expr;
	
	Assignment(String a, String s, Expr exp){
		assign = a;
		id = s;
		expr = exp;
	}
	
	@Override
	void printParseTree(String indent) {
		String indent1 = indent+" ";
		LexicalAnalyzer.displayln(indent1+indent1.length()+" <assignment>");
		indent1+=" ";
		LexicalAnalyzer.displayln(indent1+indent1.length()+" "+id);
		LexicalAnalyzer.displayln(indent1+indent1.length()+" "+assign);
		LexicalAnalyzer.displayln(indent1+indent1.length()+" <expr>");
		expr.printParseTree(indent1);
	}

	@Override
	boolean TypeEval(HashMap<String, String> state) {
		if(state.containsKey(id)==true){
			Type exprEval = expr.TypeEval(state);
			if(exprEval != null){
				if(state.get(id).equals("boolean")==true && exprEval.keyword.equals("boolean")){
					return true;
				}
				else if(state.get(id).equals("float")==true && exprEval.keyword.equals("int")){
					return true;
				}
				else if(state.get(id).equals("int")==true && exprEval.keyword.equals("int")){
					return true;
				}
				else if(state.get(id).equals("float")==true && exprEval.keyword.equals("float")){
					return true;
				}
				else{
					System.out.println("The assignment is illegal");
					return false;
				}
			}
			System.out.println("The two expressions does not match");
			return false;
		}
		System.out.println("Variable "+id+" is not declared");
		return false;
	}

	@Override
	void m(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val exprEval = expr.Eval(state, interpret);
		if(exprEval != null){
			Class exprClass = exprEval.getClass();
			if(exprClass == IntVal.class){
				interpret.put(id, exprEval.cloneVal());
			}
			else if(exprClass == FloatVal.class){
				interpret.put(id, exprEval.cloneVal());
			}
			else if(exprClass == BoolVal.class && state.get(id).equals("boolean")){
				interpret.put(id, exprEval.cloneVal());
			}
		}
	}
}
