import java.util.HashMap;


public class LessThanEqual extends BoolPrimary {

	String sign;
	E e_, e__;
	
	LessThanEqual(String s, E e, E e1){
		sign = s;
		e_ = e;
		e__ = e1;
	}
	
	@Override
	void printParseTree(String indent) {
		//LexicalAnalyzer.displayln(indent+indent.length()+" <E>");
		e_.printParseTree(indent);
		LexicalAnalyzer.displayln(indent+indent.length()+" "+sign);
		//LexicalAnalyzer.displayln(indent+indent.length()+" <E>");
		e__.printParseTree(indent);
	}
	
	Type TypeEval(HashMap<String, String> state) {
		Type e_Eval = e_.TypeEval(state);
		if(e_Eval != null){
			Type e__Eval = e__.TypeEval(state);
			if(e__Eval!= null){
				if((e_Eval.keyword.equals("int") || e_Eval.keyword.equals("float")) && (e__Eval.keyword.equals("int") || (e__Eval.keyword.equals("float")))){
					return e_Eval.BooleanClone();
				}
			}else{
				return null;
			}
		}
		System.out.println("boolean cannot be applied to <=");
		return null;
	}

	@Override
	Val Eval(HashMap<String, String> state, HashMap<String, Val> interpret) {
		Val e_Eval = e_.Eval(state, interpret);
		Val e__Eval = e__.Eval(state, interpret);
		if(e_Eval == null || e__Eval == null){
			return null;
		}
		Class e_Class = e_Eval.getClass();
		Class e__Class = e__Eval.getClass();
		if(e_Class == IntVal.class && e__Class == IntVal.class){
			if(((IntVal)e_Eval).val <= ((IntVal)e__Eval).val){
				return new BoolVal(true);
			}else{
				return new BoolVal(false);
			}
		}else if(e_Class == IntVal.class && e__Class == FloatVal.class){
			if(((IntVal)e_Eval).val <= ((FloatVal)e__Eval).val){
				return new BoolVal(true);
			}else{
				return new BoolVal(false);
			}
		}else if(e_Class == FloatVal.class && e__Class == IntVal.class){
			if(((FloatVal)e_Eval).val <= ((IntVal)e__Eval).val){
				return new BoolVal(true);
			}
			else{
				return new BoolVal(false);
			}
		}else if(e_Class == FloatVal.class && e__Class == FloatVal.class){
			if(((FloatVal)e_Eval).val <= ((FloatVal)e__Eval).val){
				return new BoolVal(true);
			}else{
				return new BoolVal(false);
			}
		}else{
			return null;
		}
	}
}
