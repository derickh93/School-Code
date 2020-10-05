package lab4;
/**
 * <p>
 * Title: The StackFullException Class
 * 
 * Description: Defines the properties and behaviors of the StackFullException class.
 * 
 * @author F. Graham, Derick Hansraj
 */
public class StackFullException extends RuntimeException {
	/**
	 *  default constructor -- Constructs a new StackFullException with a default error
	 *  message string.
	 */
	public StackFullException(){
		super("Exception : Stack is full");
	}
	/**
	 * parameterized constructor -- Constructs a new StackFullException with the parameter 
	 * as the error message string.
	 * @param msg The string passed as the error message string.
	 */
	public StackFullException(String msg){
		super(msg);
	}
}