package exceptionclasses;


/**
 * <p>Title: The StackException Class</p>
 *
 * <p>Description: Exception class used by the ArrayStack and LinkedStack classes.</p>
 *
 * @author Instructor
 */
@SuppressWarnings("serial")
public class StackException extends RuntimeException
{
	/**
	 * This parameterized constructor invokes the parameterized constructor
	 * in the parent class, RuntimeException, to store the string message.
	 * @param str a reference to a String object that contains information
	 * about the type of exception that occurred
	 */
	public StackException(String msg)
	{
		super(msg);
	}
}