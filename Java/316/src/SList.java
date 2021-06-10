import java.util.HashMap;


abstract class SList {
	
	abstract void printParseTree(String indent);
	abstract boolean TypeEval(HashMap<String, String> state);
	abstract void m(HashMap<String, String> state, HashMap<String, Val> interpret);
}
