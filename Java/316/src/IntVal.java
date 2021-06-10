
public class IntVal extends Val{
	
	int val;
	
	IntVal(int i){
		val = i;
	}

	@Override
	Val cloneVal() {
		// TODO Auto-generated method stub
		return new IntVal(val);
	}

	@Override
	float floatVal() {
		// TODO Auto-generated method stub
		return (float)val;
	}

	@Override
	boolean isZero() {
		// TODO Auto-generated method stub
		return val == 0;
	}	
	
	public String toString(){
		return val+"";
	}
}
