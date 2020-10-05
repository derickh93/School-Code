/**
 * Title: CSC130hansrajd.lab5a
 * Filename: Queue.java
 * Date Written: October 19, 2017
 * Due Date: October 21, 2017
 * Description: This class creates a queue object. There is a method to get the size.
 * There are also methods to add and remove data and to make the queue empty.. There are
 * also methods to test if the queue is full or empty. There is a toString to output the queue.
 * 
 *@author Derick Hansraj and Wenjie Cao
 */

public class Queue <T> implements QueueADT<T>{
	//instance variables
	private T[] data;
	private int size;
	private int front, rear;
	public static final int CAPACITY = 100;

	/**
	 * default constructor - creates an empty queue
	 */
	@SuppressWarnings("unchecked")
	public Queue() {
		data = (T[]) new Object[CAPACITY];
		size = 0;
		front = 0;
	}

	/**
	 * parameterized constructor - creates a queue with a user specified size.
	 * @param size -the size of the queue
	 */
	@SuppressWarnings("unchecked")
	public Queue(int size) {
		data = (T[]) new Object[size];
		this.size = 0;
		front = 0;
	}
	
	/**
	 * enqueue method - adds the specified item to the rear of the queue
	 * @param d -a reference to the item to be added to the queue
	 * @throws QueueException -Thrown if the queue is full
	 */
	public void enqueue(T d) throws QueueException {
		if(isFull())
			throw new QueueException("Queue is full");
		int rear = (front + size) % data.length;
		data[rear] = d;
		size++;
	}

	/**
	 * dequeue method - removes the item at the front of the queue
	 * @return a reference to the item removed from the front of the queue
	 * @throws an QueueException -Thrown if the queue is empty
	 */
	public T dequeue() throws QueueException {
		if(isEmpty())
			throw new QueueException("Queue is empty");
		T info = data[front];
		front = (front + 1) % data.length;
		size--;
		return info;
	}
	
	/**
	 * front method - returns a reference to the item at the front of the queue
	 * without removing it from the queue
	 * @return a reference to the item at the front of the queue
	 * @throws QueueException -Thrown if the queue is empty  
	 */
	public T front() throws QueueException {
		if(isEmpty())
			throw new QueueException("Queue is empty");
		return data[front];
	}
	
	/**
	 * rear method - returns a reference to the item at the rear of the queue
	 * without removing it from the queue
	 * @return a reference to the item at the rear of the queue
	 * @throws QueueException -Thrown if the queue is empty  
	 */
	public T rear() throws QueueException {
		if(isEmpty())
			throw new QueueException("Queue is empty");
		return data[(front + size - 1) % data.length];
	}

	/**
	 * isFull method - determines whether or not the queue is Full
	 * @return true if the queue is Full; false if the queue is not Full
	 */
	public boolean isFull() {
		return size == data.length;
	}

	/**
	 * isEmpty method - determines whether or not the queue is empty
	 * @return true if the queue is empty; false if the queue is not empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * getSize method - returns a count of the number of items in the queue
	 * @return the number of items in the queue
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * makeEmpty method - This method deletes an entire Queue that is not empty.
	 */
	public void makeEmpty () {
		while(!isEmpty()){
			this.dequeue();
		}
	}

	/**
	 * toString method - returns a String representing the state of the queue
	 * @return a string containing all items in the queue
	 */
	public String toString() {
		String str = "The number of items in the queue is: " + size + "\nThe queue contains the following: \n" ;
		if(isEmpty())
			str = "Queue is empty! Maximum number of items that can be stored is " + data.length;
		if(!isEmpty()) {
			for(int i = 0; i < size; i++) {
				str +="[" + data[(front + i) % data.length] + "]";
			}
		}
		return str;
	}
}
