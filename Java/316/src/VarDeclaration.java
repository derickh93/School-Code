import java.util.*; 

abstract class VarDeclaration {
	
	abstract void printParseTree(String indent);
	abstract void M(HashMap<String, String> state);
}
