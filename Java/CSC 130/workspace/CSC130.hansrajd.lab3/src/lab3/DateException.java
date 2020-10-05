package lab3;

/**
 * Title: CSC130hansrajd.lab3
 * Filename: DateException.java
 * Date Written: February 28, 2017
 * Due Date: March 4, 2017
 * <p>Description: Exception Class for use by the Date class</p>
 * @author <Derick Hansraj>
 */

@SuppressWarnings("serial")
public class DateException extends Exception
{
	/**
	 * Initializes an DateException storing an appropriate message.
	 */
	public DateException()
	{
		super("Ivalid value for Date");
	}

	/**
	 * Initializes a DateException storing the type of the
	 * collection (as specified by the user) along with an appropriate message.
	 */
	public DateException(String message)
	{
		super(message);
	}

}
