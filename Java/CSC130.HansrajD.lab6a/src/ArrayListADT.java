/**
 * Title: CSC130hansrajd.lab6a
 * Filename: ArrayListADT.java
 * Date Written: November 2, 2017
 * Due Date: November 4, 2017
 * Description: This is an interface class that contains the method stubs for an ArrayList. There 
 * are methods to test whether the ArrayList is full, empty, size, doubleCapacity, makeEmpty and toString.
 * There are three abstract methods: search, insert, and remove.
 * 
 *@author Derick Hansraj and Wenjie Cao
 */
public interface ArrayListADT<T> {
    /**
     * empty method - determines whether or not the list is empty
     * @return <i>true</i> if the list is empty; <i>false</i> otherwise
     */
    public boolean isEmpty();

    /**
     * full method - determines whether or not the list is full
     * @return <i>true</i> if the list is full; <i>false</i> otherwise
     */
    public boolean isFull();

    /**
     * getSize method - returns the number of items in the list
     * @return the number of items in the list
     */
    public int getSize();

    /**
     * getCapacity method - returns the maximum size of the list
     * @return the maximum size of the list
     */
    public int getCapacity();

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
    public abstract int search(T searchItem);

    /**
     * insert method - inserts an item into the list
     * @param insertItem is a reference to the item to be inserted
     */
    public abstract void insert(T insertItem);

    /**
     * remove method - removes an item from the list
     * @param removeItem is a reference to an item whose key-field has been initialized.
     * @return an Object If the item is found it is removed; otherwise null is returned
     */
    public abstract T remove(T removeItem);

}