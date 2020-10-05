
/**
 * Title: csc130.hansrajd.lab7
 * Filename: OrderedLinkedList.java
 * Date Written: November 21, 2017
 * Due Date: December 2, 2017
 * Description: Defines the methods and behaviors of a OrderedLinkedList. There is the methods
 * to add, search, and remove items while keeping the list in order. There are also methods called 
 * contain, remove, getAtPosition, indexOf, set, getSmallest, getLargest and subList.
 * 
 * @author F. Graham  & Wenjie Cao & Derick Hansraj
 **/
import java.util.Iterator;

public class OrderedLinkedList<T extends Comparable<T>> extends LinkedList<T>{

	/**
	 * insert method - inserts an item in the list in the correct order
	 * @param insertItem is a reference to the item to be inserted
	 */
	@Override
	public void insert(T insertItem) {
		if(insertItem == null)
			throw new   NullPointerException();
		Node<T> trav = head;
		boolean done = false;
		while(trav.getNext()!= null && !done) {
			if(trav.getNext().getData().compareTo(insertItem)<0)
				trav = trav.getNext();
			else
				done= true;
		}
		trav.setNext(new Node<T>(insertItem, trav.getNext()));
		++numItems;
	}

	/**
	 * search method - determines whether or not searchItem is in the list by use of a linear search
	 * @param searchItem is a reference to an item whose key-field has been initialized
	 * @return an integer which represents the location in the list where searchItem
	 * was found; if searchItem is not found, -1 is returned
	 */
	@Override
	public int search(T searchItem) {
		if(searchItem == null)
			throw new   NullPointerException();
		int location = -1;
		Node<T> trav = head;
		boolean found = false, done = false;
		int index = 0;
		while(!found && !done){
			if(trav.getNext() == null|| trav.getNext().getData().compareTo(searchItem)>0)
				done = true;
			else
				if(trav.getNext().getData().compareTo(searchItem) == 0){
					found = true;
					location = index;
				}
				else{
					trav = trav.getNext();
					index++;
				}				
		}
		return location;
	}

	/**
	 * remove method - removes an item from the list
	 * @param removeItem is a reference to an item whose key-field has been initialized.
	 * If the item is found it is removed; otherwise the list remains unchanged
	 * @return 
	 */
	@Override
	public T remove(T removeItem) {
		if(removeItem == null)
			throw new   NullPointerException();
		T theData = null;
		Node<T> trav = head;
		boolean found = false;
		while(!found && trav.getNext() != null){
			if(trav.getNext().getData().compareTo(removeItem) == 0){
				found = true;
				theData = trav.getNext().getData();
				trav.setNext(trav.getNext().getNext());
				--numItems;
			}
			else{
				trav = trav.getNext();
			}				
		}
		return theData;
	}

	/**
	 * merge method - merges two orderedlinkedlist together by creating a new list
	 * @param anotherList is a reference to a list that will be merged with another.
	 * @return a orderedlinkedlist representing both list merged.
	 */

	public OrderedLinkedList<T> merge(OrderedLinkedList<T> anotherList){
		Node<T> first = anotherList.head;
		T insertItem;
		while(anotherList!=null) {
			first = first.getNext();
			T firstData = first.getData();

			insertItem = anotherList.remove(firstData);

			if(insertItem == null)
				throw new   NullPointerException();
			Node<T> trav = head;
			boolean done = false;
			while(trav.getNext()!= null && !done) {
				if(trav.getNext().getData().compareTo(insertItem)<0)
					trav = trav.getNext();
				else
					done= true;
			}
			trav.setNext(new Node<T>(insertItem, trav.getNext()));
			++numItems;}


		return this;
	}

	/**
	 * contains method - determines whether or not the list contains an item.
	 * @param searchItem is a reference to an item to search for.
	 * @return true if an item is in the list, false otherwise
	 */
	public boolean contains(T searchItem) {
		if(this.search(searchItem)== -1)
			return false;
		else
			return true;
	}

	/**
	 * remove method - removes an item from the list using its index in the list
	 * @param index is a index to remove an item from.
	 * @return the item removed if a valid index was given
	 */
	public T remove(int index) {
		Node<T> trav = head;
		int listIndex = 0;
		T theData = null;
		boolean found = false;
		while(!found && trav.getNext() != null){
			if(listIndex == index){
				found = true;
				theData = trav.getNext().getData();
				trav.setNext(trav.getNext().getNext());
				--numItems;
			}
			else{
				trav = trav.getNext();
				listIndex++;
			}				
		}
		return theData;
	} 

	/**
	 * getAtPosition method - returns an item located at the given index
	 * @param index is a reference to a position to search for an object.
	 * @return an object located at the desired index without removing it from the list
	 * @throws ListException
	 */
	public T getAtPosition(int index) throws ListException {
		Node<T> trav = head;
		int count = 0;
		T theData = null;
		boolean found = false;
		while(!found && trav.getNext() != null){
			if(count == index){
				found = true;
				theData = trav.getNext().getData();
				trav.setNext(trav.getNext().getNext());
			}
			else{
				trav = trav.getNext();
				count++;
			}				
		}
		return theData;
	}

	/**
	 * indexOf method - returns the index of the item if it is in the list
	 * @param searchItem - An item to search for in the list
	 * @return The index of the item if it is located in the list otherwise it will throw an exception.
	 * @throws NullPointerException
	 */
	public int indexOf(T searchItem) throws NullPointerException {
		if(searchItem == null)
			throw new   NullPointerException();
		int location = -1;
		Node<T> trav = head;
		boolean found = false, done = false;
		int index = 0;
		while(!found && !done){
			if(trav.getNext() == null|| trav.getNext().getData().compareTo(searchItem)>0)
				done = true;
			else
				if(trav.getNext().getData().compareTo(searchItem) == 0){
					found = true;
					location = index;
				}
				else{
					trav = trav.getNext();
					index++;
				}				
		}
		return index;
	}

	/**
	 * set method - replaces an object at the chosen index with a desired object
	 * @param index - the index to place the new item
	 * @param newItem - the new item to be placed in the list
	 * @return The item removed from the list at the chosen index.
	 */
	public T set(int index, T newItem) throws ListException, NullPointerException {
		Node<T> trav = head;
		int count = 0;
		T theData = null;
		boolean found = false;
		while(!found && trav.getNext() != null){
			if(count == index){
				found = true;
				theData = trav.getNext().getData();
				trav.getNext().setData(newItem);
			}
			else{
				trav = trav.getNext();
				count++;
			}				
		}
		return theData;
	}

	/**
	 * getSmallest method - returns the smallest item in the list.
	 * @return The smallest item in the list, otherwise null
	 * @throws NullPointerException
	 */
	public T getSmallest() {
		if(head.getNext() == null)
			throw new   NullPointerException();
		else
			return head.getNext().getData();

	}

	/**
	 * getLargest method - returns the largest item in the list
	 * @return The largest item in the list, otherwise null 
	 */
	public T getLargest() {
		Node<T> trav = head;
		int count = 0;
		T theData = null;
		boolean found = false;
		while(!found){
			if(trav.getNext() == null) {
				theData = trav.getData();
				found = true;
			}
			else
				trav = trav.getNext();
		}
		return theData;
	}

	/**
	 * subList method - returns a sublist of the chosen index from a desired list
	 * @param fromIndex - the starting location of the new list
	 * @param toIndex - the ending location of the new list.
	 * @return A new list with the items chosen from the previous list and index.
	 * @throws ListException
	 */
	public LinkedList<T> subList(int fromIndex, int toIndex) throws ListException {
		LinkedList<T>list = new OrderedLinkedList<T>();
		Node<T> trav = head;
		T theData = null;
		int location = 0;
		while(fromIndex <= toIndex){
			if(location == fromIndex) {
				theData = trav.getNext().getData();
				list.insert(theData);
				trav.setNext(trav.getNext().getNext());
				numItems--;
				fromIndex++;
				location++;
			}	
			else {
				trav = trav.getNext();
				location++;		
			}
		}
		return list;
	}

	public Iterator<T> iterator() {
		return null;

	}

}



