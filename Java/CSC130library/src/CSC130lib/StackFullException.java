package csc130lib;
/**
 * <p>
 * Title: The StackFullException Class
 * </p>
 * 
 * <p>
 * Description: This class defines the behaviors for when a StackFullException is thrown.
 * </p>
 * 
 * <p>
 * </p>
 * 
 * @author Derick Hansraj and Brandon
 */
public class StackFullException extends RuntimeException {
	/**
	 * Constructs a new StackFullException with a default error message string.
	 */
	public StackFullException(){
		super("Exception : Stack is full");
	}
	/**
	 * Constructs a new StackFullException with the parameter as the error message string.
	 * @param msg The string passed as the error message string.
	 */
	public StackFullException(String msg){
		super(msg);
	}
}