
/**
 * Title: csc130.hansrajd.lab7
 * Filename: LinkedList.java
 * Date Written: November 21, 2017
 * Due Date: December 2, 2017
 * Description: A list of objects with a connection to the next object in the list.
 * @author F. Graham  & Wenjie Cao & Derick Hansraj
 **/
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class LinkedList<T extends Comparable<T>> {
	protected class Node<E>{
		private E data;
		private Node<E> next;

		public Node(){
			data = null;
			next = null;
		}
		public Node(E d){
			data = d;
			next = null;
		}
		public Node(E d, Node<E> n){
			data = d;
			next = n;
		}
		public Node<E> getNext(){
			return next;
		}
		public void setNext(Node<E> n){
			next = n;
		}
		public E getData() {
			return data;
		}
		public void setData(E data) {
			this.data = data;
		}		
	}
	protected Node<T> head = new Node<T>(); // dummy Node
	protected int numItems;

	public abstract void insert(T insertItem);
	public abstract int search(T searchItem);
	public abstract T remove(T insertItem);

	public int getSize(){
		return numItems;
	}
	public boolean isEmpty(){
		return numItems == 0;
	}

	public String toString(){
		String str = "\n==================================\n"
				+ "The list contains " + numItems + " items.\n"
				+ "==================================\n";
		Node<T> trav = head.next;
		while(trav != null){
			str += trav.data + "\n"; // ((trav.next == null) ? "" : ", ");
			trav = trav.next;
		}
		return str;
	}
	public void makeEmpty(){
		head = new Node<T>();
		numItems = 0;
		System.gc();
	}

	public class ListIterator<E> implements Iterator<E> {

		private Node<E> list;
		private int count, current;

		public ListIterator(Node<E> head, int c){
			list = head;
			count = c;
			current = 0;
		}

		public boolean hasNext() {
			return current < count;
		}

		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			E data = list.data;
			list = list.next;
			current++;
			return data;
		}

		public void remove() {
			if(!hasNext())
				throw new NoSuchElementException();
			list.data = list.next.data;
			list.next = list.next.next;
			current--;
			count--;
		}
	}

	public Iterator<T> iterator() {
		return new ListIterator<T>(head, numItems);
	}

}