import java.util.HashMap;


public class Decrement extends Cluster{

	String id, t;
	
	Decrement(String i, String d){
		id = i;
		t = d;
	}
	
	@Override
	void printParseTree(String indent) {
		String indent1 = indent+" ";
		LexicalAnalyzer.displayln(indent1+indent1.length()+" <decrement>");
		indent1+=" ";
		LexicalAnalyzer.displayln(indent1+indent1.length()+ " "+id);
		LexicalAnalyzer.displayln(indent1+indent1.length()+" "+t);
	}

	@Override
	boolean TypeEval(HashMap<String, String> state) {
		if(state.containsKey(id)==true){
			if(state.get(id).equals("float")==true || state.get(id).equals("int")==true){
				return true;
			}else{
				System.out.println("The value "+id+" is of type boolean");
				return false;
			}
		}
		System.out.println("Variable "+id+" is not declared");
		return false;
	}

	@Override
	void m(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val idVal = interpret.get(id);
		if(state.get(id).equals("float")){
			interpret.put(id, new FloatVal(idVal.floatVal()-1));
		}else if(state.get(id).equals("int")){
			if(interpret.get(id) == null){
				System.out.println("variable "+id+" does not have a value");
				return;
			}else{
				interpret.put(id, new IntVal(Integer.parseInt((interpret.get(id).toString()))-1));
			}
		}
	}
}