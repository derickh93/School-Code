import java.util.*;

abstract class Program {	
	abstract void printParseTree(String indent);
	abstract void M(HashMap<String, String> state);
	abstract void m(HashMap<String, String> state, HashMap<String, Val> interpret);
}
