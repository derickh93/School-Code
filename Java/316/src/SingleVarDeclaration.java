import java.util.HashMap;


public class SingleVarDeclaration extends VarDeclaration{

	VarDecl single;

	SingleVarDeclaration(VarDecl vl){
		single = vl;
	}
	
	@Override
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		LexicalAnalyzer.displayln(indent1+indent1.length()+" <var declarations>");
		single.printParseTree(indent1);
	}

	@Override
	void M(HashMap<String, String> state) {
		single.M(state);
	}
	
	
	
}
