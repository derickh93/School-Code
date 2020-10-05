package CSC130lib;

public class StackEmptyException extends RuntimeException{
	public StackEmptyException() {
		super("Stack Empty Exception...");
	}
	
	public StackEmptyException(String message) {
		super(message);
	}

}
