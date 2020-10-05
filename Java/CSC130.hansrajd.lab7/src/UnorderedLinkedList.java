
/**
 * Title: csc130.hansrajd.lab7
 * Filename: UnorderedLinkedList.java
 * Date Written: November 21, 2017
 * Due Date: December 2, 2017
 * Description: A list of objects with a connection to the next objects in the list in an unordered manner.
 * @author F. Graham  & Wenjie Cao & Derick Hansraj
 **/
public class UnorderedLinkedList<T extends Comparable<T>> extends LinkedList<T>{

	/**
	 * insert method - inserts an item in the list in the correct order
	 * @param insertItem is a reference to the item to be inserted
	 */
	@Override
	public void insert(T insertItem) {
		if(insertItem == null)
			throw new   NullPointerException();
		Node<T> trav = head;
		while(trav.getNext() != null)
			trav = trav.getNext();
		trav.setNext(new Node<T>(insertItem));
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
		boolean found = false;
		int index = 0;
		while(!found && trav.getNext() != null){
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
}