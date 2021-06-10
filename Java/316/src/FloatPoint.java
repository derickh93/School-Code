import java.util.HashMap;


public class FloatPoint extends Primary{
	
	float f;
	
	FloatPoint(float d){
		f = d;
	}

	@Override
	void printParseTree(String indent) {
		LexicalAnalyzer.displayln(indent+indent.length()+" <primary> "+f);
	}

	@Override
	Type TypeEval(HashMap<String, String> state) {
		return new Type("float", null);
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		// TODO Auto-generated method stub
		return new FloatVal(f);
	}
}
