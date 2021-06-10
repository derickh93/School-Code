
public class BoolVal extends Val{

	boolean val;
	
	BoolVal(boolean b){
		val = b;
	}
	
	@Override
	Val cloneVal() {
		// TODO Auto-generated method stub
		return new BoolVal(val);
	}

	@Override
	float floatVal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	boolean isZero() {
		// TODO Auto-generated method stub
		return false;
	}

	public String toString(){
		return val+"";
	}
	
}
