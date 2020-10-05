
/**
 * Title: csc130.hansrajd.lab7
 * Filename: LinkedListADT.java
 * Date Written: November 21, 2017
 * Due Date: December 2, 2017
 * Description: Defines the methods that will be used in a LinkedList.
 * @author F. Graham  & Wenjie Cao & Derick Hansraj
 **/
import java.util.Iterator;

public interface LinkedListADT<T> {
	/**
	 * empty method - determines whether or not the list is empty
	 * @return <i>true</i> if the list is empty; <i>false</i> otherwise
	 */
	public boolean isEmpty();

	/**
	 * getSize method - returns the number of items in the list
	 * @return the number of items in the list
	 */
	public int getSize();

	/**
	 * makeEmpty method - makes the list empty and sets numItems to 0
	 */
	public void makeEmptpy();

	/**
	 * toString method - returns the state of the object as a String
	 * @return a String containing the items in the list
	 */
	public String toString();

	/**
	 * search method - determines whether or not the searchItem (key)is in the list
	 * @param searchItem is a reference to an item whose key-field has been initialized
	 * @return an integer which represents the location in the list where searchItem
	 * was found; if searchItem is not found, -1 is returned
	 */
	public abstract int search(T searchItem) throws NullPointerException;

	/**
	 * insert method - inserts an item into the list
	 * @param insertItem is a reference to the item to be inserted
	 */
	public abstract void insert(T insertItem) throws NullPointerException;

	/**
	 * remove method - removes an item from the list
	 * @param removeItem is a reference to an item whose key-field has been initialized.
	 * @return an Object If the item is found it is removed; otherwise null is returned
	 */
	public abstract T remove(T removeItem) throws NullPointerException;  

	/**
	 * merge method - merge two list into one
	 * @param anotherList is a list which is merged to the list
	 * @return a new list which is merged from two lists is returned
	 */
	public abstract LinkedListADT<T> merge(LinkedListADT<T> anotherList) throws NullPointerException; 

	/**
	 * contains method - check if searched item exists in the list
	 * @param searchItem is the one want to check existence in the list
	 * @return return true if searchItem is found in the list
	 */
	public boolean contains(T searchItem) throws NullPointerException;

	/**
	 * remove method - removes an item by its index in the list
	 * @param index is the index num in the list
	 * @return an Object If the item is found it is removed; otherwise null is returned
	 */
	public T remove(int index) throws ListException;



	// Additional methods that could be implemented
	/*
    public boolean contains(T searchItem) throws NullPointerException;
    public T remove(int index) throws ListException;
    public T getAtPosition(int index) throws ListException;
    public int indexOf(T searchItem) throws NullPointerException;
    public T set(int index, T newItem) throws ListException, NullPointerException;
    public T getSmallest();
    public T getLargest();
    LinkedListADT<T> subList(int fromIndex, int toIndex) throws ListException;
    public Iterator<T> iterator();
	 */ 
}