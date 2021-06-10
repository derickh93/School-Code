
public class OnlyE extends BoolPrimary {
	
	E e;
	
	OnlyE(E ex){
		e = ex;
	}
	@Override
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		e.printParseTree(indent1);

	}

}
