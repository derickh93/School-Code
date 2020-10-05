/**
 * <p>Title: LinkedQueueADT Interface</p>
 *
 * <p>Description: Defines the behaviors of a basic LinkedQueue.</p>
 * 
 * @author Instructor
 */
public interface LinkedQueueADT<T> {

	/**  Adds one item to the rear of the queue. */
	void enqueue(T d);

	/**  Removes and returns the item at the front of the queue. */
	T dequeue() throws RuntimeException;

	/**  Returns without removing the item at the front of the queue. */
	T front() throws RuntimeException;

	/**  Returns the number of items in the queue. */
	int getSize();

	/**  Returns true if the queue is empty, else false. */
	boolean isEmpty();
}