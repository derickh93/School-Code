/**
 * <p>Title: The LinkedQueue Class</p>
 *
 * <p>Description: Defines the properties and behaviors of a linked queue.</p>
 *
 * @author Instructor
 */

public class LinkedQueue<T> implements LinkedQueueADT<T> {
	private class Node<E> {

		//instance variables
		private E data;
		private Node<E> next;

		/**
		 * default constructor - creates an empty queue
		 */
		public Node() {
			data = null;
			next = null;
		}

		/**
		 * parameterized constructor - creates a queue with an object that has a reference 
		 * the next object being null
		 * @param d - data to be stored in the queue
		 */
		public Node(E d) {
			data = d;
			next = null;
		}

		/**
		 * parameterized constructor - creates an queue with an object that has a reference
		 * specified by the user
		 * @param d - data to be stored in the queue
		 * @param n - the reference that the data is linked to.
		 */
		public Node(E d, Node<E> n) {
			data = d;
			next = n;
		}
	}
	//instance variables
	private Node<T> front, rear;
	private int size;

	/**
	 * enqueue method - adds the specified item to the rear of the queue
	 * @param d - a reference to the item to be added to the queue
	 */
	public void enqueue(T d) {
		if (isEmpty())
			front = rear = new Node<T>(d);
		else {
			rear.next = new Node<T>(d);
			rear = rear.next;
		}
		size++;
	}

	/**
	 * dequeue method - removes the item at the front of the queue
	 * @return a reference to the item removed from the front of the queue
	 * @throws an RuntimeException if the queue is empty
	 */
	public T dequeue() throws RuntimeException {
		if (isEmpty())
			throw new RuntimeException("Queue empty exception...");
		T d = front.data;
		front = front.next;
		if (front == null)
			rear = null;
		size--;
		return d;
	}

	/**
	 * front method - returns a reference to the item at the front of the queue
	 * without removing it from the queue
	 * @return a reference to the item at the front of the queue
	 * @throws an RuntimeException if the queue is empty  
	 */
	public T front() throws RuntimeException {
		if (isEmpty())
			throw new RuntimeException("Queue empty exception...");
		return front.data;
	}

	/**
	 * getSize method - returns a count of the number of items in the queue
	 * @return the number of items in the queue
	 */
	public int getSize() {
		return size;
	}

	/**
	 * isEmpty method - determines whether or not the queue is empty
	 * @return true if the queue is empty; false if the queue is not empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * toString method - returns a String representing the state of the queue
	 * @return a string containing all items in the queue
	 */
	public String toString() {
		String str = "[";
		Node<T> trav = front;
		while (trav != null) {
			str += trav.data + ((trav.next == null) ? "" : ",");
			trav = trav.next;
		}
		str += "]";
		return str;
	}
}