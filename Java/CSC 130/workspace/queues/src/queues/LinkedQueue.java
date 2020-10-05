package queues;
import exceptionclasses.*;

/**
 * <p>Title: The LinkedQueue Class</p>
 *
 * <p>Description: Defines the properties and behaviors of a linked queue.</p>
 *
 * @author Marvin Yan, Jobar Saddler
 */
public class LinkedQueue<E> implements QueueADT<E>
{
	protected Node<E> front, rear; //references to the first and last nodes

	/**
	 * default constructor - creates an empty queue
	 */
	public LinkedQueue()
	{
		front = rear = null;
	}
	
	/**
	 * enqueue method - adds the specified item to the rear of the queue
	 * @param newItem a reference to the item to be added to the queue
	 */
	public void enqueue (E newItem)
	{
		Node<E> temp = new Node<E>(newItem);
		if (isEmpty())
		{
			front = rear = temp;
		}
		else
		{
			rear.setNext(temp);
			rear = temp;
		}
		
	}

	/**
	 * dequeue method - removes the item at the front of the queue
	 * @return a reference to the item removed from the front of the queue
	 * @throws an EmptyCollectionException if the queue is empty
	 */
	public E dequeue() 
	{
		if (isEmpty())
			throw new QueueException("Queue is empty!");
		
		E temp = front.getItem();
		front = front.getNext();
		
		if (front == null)
			rear = null;
		
		return temp;
	}

	/**
	 * front method - returns a reference to the item at the front of the queue
	 * without removing it from the queue
	 * @return a reference to the item at the front of the queue
	 * @throws an EmptyCollectionException if the queue is empty  
	 */
	public E front() 
	{
		if (isEmpty())
			throw new QueueException("Queue is empty!");
		
		return front.getItem();
	}

	/**
	 * isEmpty method - determines whether or not the queue is empty
	 * @return true if the queue is empty; false if the queue is not empty
	 */
	public boolean isEmpty()
	{
		return (front == null);
	}

	/**
	 * size method - returns a count of the number of items in the queue
	 * @return the number of items in the queue
	 */
	public int size()
	{
		int counter = 0;
		Node<E>temp = front;
		while(temp != null)
		{
			temp = temp.getNext();
			counter++;
		}
		return counter;
	}
	
	/**
	 * search method - returns an integer representing the 1-based position of the item if 
	 * found, else -1
	 * 
	 * @param the item to be searched
	 * @return an integer representing the 1-based position of the item if found, else -1
	 */
	public int search(E item)
	{
		int location = 1;
		Node<E> temp = front;
		while(temp != null)
		{
			if (temp.getItem().equals(item))
			{
				return location;
			}
			temp = temp.getNext();
			location++;
		}
		return -1;
	}
	
	public E removeLast()
	{
		Node<E>temp = front;
		int counter = 1;
		if (size() > 1)
		{
			E last = rear.getItem();
			while(counter != size() - 1)
			{
				temp = temp.getNext();
				counter++;
			}
			rear = temp;
			rear.setNext(null);
			return last;
		}
		else if (size() == 1)
		{
			E last = rear.getItem();
			front = rear = null;
			return last;
		}
		else
			throw new QueueException("Queue is empty!");
	}
	
	/**
	 * toString method - returns a String representing the state of the queue
	 * @return a string containing all items in the queue
	 */
	public String toString()
	{
		String output = "";
		Node<E>temp = front;
		while(temp != null)
		{
			output += temp.getItem() + "\n";
			temp = temp.getNext();
		}
		return output;
	}
}