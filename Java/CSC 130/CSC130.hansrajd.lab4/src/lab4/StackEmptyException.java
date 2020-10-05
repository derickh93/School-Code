package lab4;
/**
 * <p>
 * Title: The StackEmptyException Class
 * 
 * Description: Defines the properties and behaviors of the StackEmptyException class.
 * 
 * @author F. Graham, Derick Hansraj
 */
public class StackEmptyException extends Exception {
	/**
	 * default constructor -- rank and suit are randomly defined Constructs a new 
	 * StackEmptyException with a default error message string.
	 */
	public StackEmptyException(){
		super("Exception : Stack is empty");
	}
	/**
	 * parameterized constructor -- Constructs a new StackEmptyException with the parameter 
	 * as the error message string.
	 * @param msg The string passed as the error message string.
	 */
	public StackEmptyException(String msg){
		super(msg);
	}
}