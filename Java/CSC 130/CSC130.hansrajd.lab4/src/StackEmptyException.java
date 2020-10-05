/**
 * <p>
 * Title: The StackEmptyException Class
 * </p>
 * 
 * <p>
 * Description: This class defines the behaviors for when a StackEmptyException is thrown.
 * </p>
 * 
 * <p>
 * </p>
 * 
 * @author Derick Hansraj and Brandon
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