package dll;


import java.util.NoSuchElementException;

public class DoubleLinkedList<E>{


	private Node<E> front;
	private Node<E> rear;

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
			Node<E> node = new Node(data,null,null);
			front = node;
			rear = node;
			size++;
		}
		else {
			Node<E> node = new Node(data,null,rear);
			rear.setNext(node);
			rear = node;
			size++;
		}
	}

	public void addToFront(E data) {
		if(front == null) {
			Node<E> node = new Node(data,null,null);
			front = node;
			rear = node;
			size++;
		}
		else {
			Node<E> node = new Node(data,front,null);
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
		}
		if(rear != null)
			rear.setNext(null);
		return temp;
	}



//fixk this method
	// should throw NoSuchElementException if it can't remove
	public E removeFromFront() {
		E temp;
		if(front.getNext() == null) 
			throw new NoSuchElementException();
		else if (front.getNext() == null) {
			temp = front.getData();
			front.setData(null);
			size--;
		}
		else {
			temp = front.getData();
			front = front.getNext();
			size--;
		}
		return temp;
	}

	// takes in 0 based position, should throw NoSuchElementException if it can't remove
	public E get(int pos) {
		Node<E> trav = front;
		E temp;
		int count = 0;
		if(pos+1 > size)
			throw new NoSuchElementException();
		while(trav.getNext() != null && count != pos) {
			trav = trav.getNext();
			count++;
		}
		temp = trav.getData();
		return temp;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		Node<E> trav = front;
		while(trav.getNext() != null) {
			str.append(trav.getData() + " <==> ");
			trav = trav.getNext();
		}
		str.append(trav.getData());
		return "Elements: " + str + "\nSize: " + size;
	}

	public String reverseString() {
		StringBuilder str = new StringBuilder();
		Node<E> trav = rear;
		while(trav.getPrev() != null) {
			str.append(trav.getData() + " <==> ");
			trav = trav.getPrev();
		}
		str.append(trav.getData());
		return "Elements: " + str + "\nSize: " + size;
	}

}
