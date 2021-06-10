import java.util.HashMap;


public class ID extends Primary{
	
	String s;
	
	ID(String st){
		s = st;
	}

	@Override
	void printParseTree(String indent) {
		LexicalAnalyzer.displayln(indent+indent.length()+" <primary> "+ s);
	}

	@Override
	Type TypeEval(HashMap<String, String> state) {
		if(state.containsKey(s)==true){
			if(state.get(s).equals("int")){
				return new Type("int", null);
			}
			else if(state.get(s).equals("float")){
				return new Type("float", null);
			}else if(state.get(s).equals("boolean")){
				return new Type("boolean", null);
			}
			else{
				return null;
			}
		}
		System.out.println("Variable undeclared "+s);
		return null;
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val sVal = interpret.get(s);
		if(sVal != null){
			return sVal.cloneVal();
		}else{
			System.out.println("variable "+s+" does not have a value");
			return null;
		}
	}

}
