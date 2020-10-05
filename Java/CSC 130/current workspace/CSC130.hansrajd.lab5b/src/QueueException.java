/**
 * <p>Title: The QueueException Class</p>
 * <p>Description: Exception class used by the LinkedQueue and ArrayQueue classes.</p>
 * @author Derick Hansraj
 */
@SuppressWarnings("serial")
public class QueueException extends RuntimeException
{
	/**
	 * This parameterized constructor uses the parameterized constructor
	 * in the parent class, RuntimeException, to store the string message.
	 * @param msg a reference to a String object that contains information
	 * about the type of exception that occurred
	 */
	public QueueException(String msg)
	{
		super(msg);
	}
}