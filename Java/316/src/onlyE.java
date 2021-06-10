import java.util.HashMap;


public class onlyE extends BoolPrimary {

	E e;
	
	onlyE(E e_){
		e = e_;
	}
	
	@Override
	void printParseTree(String indent) {
		e.printParseTree(indent);
	}

	@Override
	Type TypeEval(HashMap<String, String> state) {
		Type eEval = e.TypeEval(state);
		if(eEval != null){
			if(eEval.keyword.equals("int")){
				return eEval.IntClone();
			}
			else if(eEval.keyword.equals("float")){
				return eEval.FloatClone();
			}
			else if(eEval.keyword.equals("boolean")){
				return eEval.BooleanClone();
			}
			else{
				System.out.println("Unauthorized variable");
				return null;
			}
		}
		System.out.println("Error state");
		return null;
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val eEval = e.Eval(state, interpret);
		if(eEval != null){
			Class eClass = eEval.getClass();
			if(eClass == BoolVal.class){
				return ((BoolVal)eEval);
			}
			else if(eClass == IntVal.class){
				return ((IntVal)eEval);
			}
			else if(eClass == FloatVal.class){
				return ((FloatVal)eEval);
			}
			else{
				return null;
			}
		}
		else{
			return null;
		}
	}

}
