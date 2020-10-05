
/**
 * <p>Title: The QueueADT Interface</p>
 *
 * <p>Description: Defines the behaviors of a basic ArrayQueue.</p>
 * 
 * @author Instructor
 */
public interface ArrayQueueADT<T> {
	/**  Adds one item to the rear of the queue. */
	void enqueue(T d) throws RuntimeException;

	/**  Removes and returns the item at the front of the queue. */
	T dequeue() throws RuntimeException;

	/**  Returns without removing the item at the front of the queue. */
	T front() throws RuntimeException;

	/**  Returns the number of items in the queue. */
	int getSize();

	/**  Returns true if the queue is empty, else false. */
	boolean isEmpty();

	/**  Returns true if the queue is full, else false. */
	boolean isFull();
}