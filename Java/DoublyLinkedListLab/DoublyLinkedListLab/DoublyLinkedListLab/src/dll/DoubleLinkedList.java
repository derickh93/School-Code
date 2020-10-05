/**
 * Title: DoublyLinkedListLab
 * Filename: DoubleLinkedList.java
 * Date Written: February 13, 2018
 * Due Date: February 16, 2018
 * Description: Defines the methods of the DoubleLinkedList Class. 
 * 
 *@author Derick Hansraj(N00827531) and Instructor
 */
package dll;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<E> implements Iterable<E> {

	class DoubleLinkedListIterator implements Iterator<E> {
		int currIndex = 0;
		Node<E> current = front;

		public boolean hasNext() {
			return currIndex < size;

		}

		public E next() {
			E temp = null;
			if(!hasNext())
				throw new NoSuchElementException();

			temp = current.getData();
			current = current.getNext();
			currIndex++;
			return temp;

		}
	}


	private Node<E> front;
	private Node<E> rear;

	public Node<E> getFront() {
		return front;
	}

	public void setFront(Node<E> front) {
		this.front = front;
	}

	public Node<E> getRear() {
		return rear;
	}

	public void setRear(Node<E> rear) {
		this.rear = rear;
	}

	private int size;

	public DoubleLinkedList() {
		super();
		front = null;
		rear = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public void addToRear(E data) {
		if(front == null) {
			Node<E> node = new Node<E>(data,null,null);
			front = node;
			rear = node;
			size++;
		}
		else {
			Node<E> node = new Node<E>(data,null,rear);
			rear.setNext(node);
			rear = node;
			size++;
		}
	}

	public void addToFront(E data) {
		if(front == null) {
			Node<E> node = new Node<E>(data,null,null);
			front = node;
			rear = node;
			size++;
		}
		else {
			Node<E> node = new Node<E>(data,front,null);
			front.setPrev(node);
			front = node;
			size++;
		}
	}


	// should throw NoSuchElementException if it can't remove
	public E removeFromRear() {
		E temp;
		if(front == null) 
			throw new NoSuchElementException();
		else {
			temp = rear.getData();
			rear = rear.getPrev();
			size--;
			if(rear == null)
				front = null;
		}
		if(rear != null)
			rear.setNext(null);
		return temp;
	}

	// should throw NoSuchElementException if it can't remove
	public E removeFromFront() {
		if(size == 0) 
			throw new NoSuchElementException();
		if(front.getNext() != null)
			front.setPrev(null);
		Node<E> temp = front;
		front = front.getNext();
		size--;
		if(front == null)
			rear = null;
		return temp.getData();
	}

	// takes in 0 based position, should throw NoSuchElementException if it can't remove
	public E get(int pos) {
		Node<E> trav = front;
		E temp;
		int count = 0;
		if(pos == size || pos < 0)
			throw new NoSuchElementException();
		while(trav.getNext() != null && count != pos) {
			trav = trav.getNext();
			count++;
		}
		temp = trav.getData();
		return temp;
	}

	public String toString() {
		StringBuilder temp = new StringBuilder() ;
		Node<E> trav = front;
		if(size == 0)
			return "List is empty. ";
		while(trav.getNext() != null) {
			temp.append(trav.getData() + " <==> ");
			trav = trav.getNext();
		}
		temp.append(trav.getData());
		return "Elements: " + temp + "\nSize: " + size;
	}

	public String reverseString() {
		StringBuilder str = new StringBuilder();
		Node<E> trav = rear;
		while(trav.getPrev() != null) {
			str.append(trav.getData() + " <==> ");
			trav = trav.getPrev();
		}
		str.append(trav.getData());
		return "Elements: " + str + "\nSize: " + size + "\n";
	}

	@Override
	public Iterator<E> iterator() {
		return new DoubleLinkedListIterator();
	}

}
