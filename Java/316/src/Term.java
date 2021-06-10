import java.util.HashMap;


abstract class Term {

	abstract void printParseTree(String indent);
	abstract Type TypeEval(HashMap<String, String> state);
	abstract Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret);
}
