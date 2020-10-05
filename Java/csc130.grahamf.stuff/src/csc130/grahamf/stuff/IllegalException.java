package csc130.grahamf.stuff;

public class IllegalException extends RuntimeException {
	public IllegalException(){
		super("Something really, really bad happened exception");
	}
	public IllegalException(String msg){
		super(msg);
	}
}
