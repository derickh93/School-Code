/**
 * Title: The StackEmptyException Class
 * 
 * Description: This class defines the behaviors for when a StackEmptyException is thrown.
 * 
 * @author Instructor
 */
public class StackEmptyException extends Exception {
	/**
	 * Constructs a new StackEmptyException with a default error message string.
	 */
	public StackEmptyException(){
		super("Exception : Stack is empty");
	}
	/**
	 * Constructs a new StackEmptyException with the parameter as the error message string.
	 * @param msg The string passed as the error message string.
	 */
	public StackEmptyException(String msg){
		super(msg);
	}
}