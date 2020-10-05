/**
 * Title: CSC130hansrajd.lab5a
 * Filename: QueueADT.java
 * Date Written: October 19, 2017
 * Due Date: October 21, 2017
 * Description: This interface defines the methods in a Queue. 
 * 
 *@author Derick Hansraj and Wenjie Cao
 */
public interface QueueADT<T> {
	
	/**  Adds one item to the rear of the queue. */
	public void enqueue(T d) throws QueueException;
	
	/**  Removes and returns the item at the front of the queue. */
	public T dequeue() throws QueueException;
	
	/**  Returns without removing the item at the front of the queue. */
	public T front()throws QueueException;
	
	/**  Returns without removing the item at the rear of the queue. */
	public T rear()throws QueueException;
	
	/**  Determines whether or not the queue is empty. */
	public boolean isFull();
	
	/**  Determines whether or not the queue is empty. */
	public boolean isEmpty();
	
	/**  Returns a string representing the state of the queue. */
	public int getSize();

}
