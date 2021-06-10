
class OptionalRelOpE extends BoolPrimary{
	RelOp relOp;
	E e;
	
	OptionalRelOpE(RelOp r, E ex){
		relOp =r;
		e=ex;
	}

	@Override
	void printParseTree(String indent) {
		relOp.printParseTree(indent);
		e.printParseTree(indent);
		
	}
	
}
