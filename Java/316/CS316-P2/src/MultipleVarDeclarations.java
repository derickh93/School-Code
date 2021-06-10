
public class MultipleVarDeclarations extends VarDeclarations {
	VarDec varDec;
	VarDeclarations varDeclarations;
	
	MultipleVarDeclarations(VarDec d, VarDeclarations v){
		varDec = d;
		varDeclarations = v;
	}

	@Override
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		lexArithArray.displayln(indent + indent.length() + " <Var Dec> ");		
		varDec.printParseTree(indent1);
		lexArithArray.displayln(indent1 + indent1.length() + " <VarDeclarations>");
		varDeclarations.printParseTree(indent1);
		
	}
}
