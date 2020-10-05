
/**
 * Title: csc130.hansrajd.lab7
 * Filename: ListException.java
 * Date Written: November 21, 2017
 * Due Date: December 2, 2017
 * Description: Defines a default constructor and a parameterized constructor for a ListException object.
 * @author F. Graham  & Wenjie Cao & Derick Hansraj
 **/
public class ListException extends RuntimeException {

	/**
	 * Constructs a new ListException with a default error message string.
	 */
	public ListException() {
		super("List Exception... something bad happened");
	}

	/**
	 * Constructs a new ListException with the parameter as the error message string.
	 * @param msg The string passed as the error message string.
	 */
	public ListException(String msg) {
		super(msg);
	}

}