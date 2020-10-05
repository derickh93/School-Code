/**
 * Title: DoublyLinkedListLab
 * Filename: Node.java
 * Date Written: February 13, 2018
 * Due Date: February 16, 2018
 * Description: Defines a basic Node Class. 
 * 
 *@author Derick Hansraj(N00827531) and Instructor
 */
package dll;

public class Node<E> {

	private E data;

	private Node<E> next;
	private Node<E> prev;

	public Node(E data, Node<E> next, Node<E> prev) {
		super();
		this.data = data;
		this.next = next;
		this.prev = prev;
	}



	public E getData() {
		return data;
	}



	public void setData(E data) {
		this.data = data;
	}



	public Node<E> getNext() {
		return next;
	}



	public void setNext(Node<E> next) {
		this.next = next;
	}



	public Node<E> getPrev() {
		return prev;
	}



	public void setPrev(Node<E> prev) {
		this.prev = prev;
	}



	@Override
	public String toString() {
		return "Node [data=" + data + ", next=" + next + ", prev=" + prev + "]";
	}
}