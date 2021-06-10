import java.util.LinkedList;

class Node<T>{
	T data;
	Node<T> next;
	
	Node(T data){
		this.data = data;
	}
}
public class SinglyLinkedList<T>{
	Node<T> head = null;
	int count = 0;
	
	SinglyLinkedList() {
	}
	
	SinglyLinkedList(Node<T> head){
		this.head = head;
		count++;
	}
	
	public void insertFront(T data) {
		Node<T> newNode = new Node<T>(data);
		newNode.next = head;
		head = newNode;
		count++;
	}
	
	public void insertRear(T data) {
		Node<T> current = head;

		if(count == 0) {
			insertFront(data);
		}
		else{
			while(current.next != null) {
				current = current.next;
			}
			Node<T> temp = new Node<T>(data);
			temp.next = current.next;
			current.next = temp;
		}
		count++;
	}
	
	public void removeFront() {
		if(count == 0)
			System.out.println("The list is empty!");
		else {
				head = head.next;
			count--;
		}
	}

	public void removeRear() {
		
	}
	
	public int getCount() {
		return count;
	}
	
	public void printList() {
		Node<T> current = head;
		StringBuilder str = new StringBuilder();
		str.append("(Head)-->");
		if(count == 0)
			str.append("NULL");
		else {
		while(current.next != null) {
			str.append(current.data + "-->");
			current = current.next;
		}
		str.append(current.data + "-->(NULL)");
		}
		System.out.println(str.toString());
	}

	public void reverseList() {
		LinkedList<String> ll;
		Node<T> current = head;
		int tempCount = count;

		for(int i = 0; i < tempCount;i++) {
			insertRear(current.data);
			removeFront();
			System.out.println(current.data);
			current = current.next;
		}
		insertRear(current.data);
		removeFront();
		
		/**
		 * addFirst()	Adds an item to the beginning of the list.	
addLast()	Add an item to the end of the list	
removeFirst()	Remove an item from the beginning of the list.	
removeLast()	Remove an item from the end of the list	
getFirst()	Get the item at the beginning of the list	
getLast()	Get the item at the end of the list
		 */

	}
	
	public static void main(String args[]) {
		SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
		System.out.println("The list size is: " + sll.count);
		sll.insertFront(7);
		sll.insertFront(11);
		sll.insertFront(17);
		sll.insertFront(6);
		sll.insertFront(9);
		sll.insertFront(5);
		sll.printList();
		System.out.println("The list size is: " + sll.count);
		sll.removeFront();
		sll.printList();


	}
}
