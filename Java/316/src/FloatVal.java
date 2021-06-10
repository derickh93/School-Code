
public class FloatVal extends Val{

	float val;
	
	FloatVal(float f){
		val = f;
	}
	
	@Override
	Val cloneVal() {
		// TODO Auto-generated method stub
		return new FloatVal(val);
	}

	@Override
	float floatVal() {
		// TODO Auto-generated method stub
		return val;
	}

	@Override
	boolean isZero() {
		// TODO Auto-generated method stub
		return val == 0.0f;
	}
	
	public String toString(){
		return val+"";
	}

}
