/**
 * <p>
 * Title: The StackEmptyException Class
 * 
 * Description: Defines the properties and behaviors of the StackEmptyException class.
 * 
 * @author Derick Hansraj
 */
public class StackEmptyException extends Exception {

	/**
	 * default constructor -- Constructs a new StackEmptyException with a default error message string.
	 */
	public StackEmptyException(){
		super("Stack Empty Exception...");
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