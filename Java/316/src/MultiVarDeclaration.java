import java.util.*;

class MultiVarDeclaration extends VarDeclaration{
	
	VarDeclaration var_declaration;
	VarDecl vardecl;
	
	MultiVarDeclaration(VarDeclaration v, VarDecl vd){
		var_declaration = v;
		vardecl = vd;
	};

	void printParseTree(String indent) {
		String indent1 = indent + " ";
		LexicalAnalyzer.displayln(indent1+indent1.length()+" <var declarations>");
		vardecl.printParseTree(indent1);
		var_declaration.printParseTree(indent1);
	}

	@Override
	void M(HashMap<String, String> state) {
		vardecl.M(state);
		var_declaration.M(state);
	};
}
