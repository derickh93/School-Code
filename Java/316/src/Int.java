import java.util.HashMap;


public class Int extends Primary{
	
	int number;
	
	Int(int a){
		number = a;
	}

	@Override
	void printParseTree(String indent) {
		LexicalAnalyzer.displayln(indent+indent.length()+" <primary> "+number);
	}

	@Override
	Type TypeEval(HashMap<String, String> state) {
		return new Type("int", null);
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		return new IntVal(number);
	}

}
