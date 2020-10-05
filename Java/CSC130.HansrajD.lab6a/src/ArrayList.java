/**
 * Title: CSC130hansrajd.lab6a
 * Filename: ArrayList.java
 * Date Written: November 2, 2017
 * Due Date: November 4, 2017
 * Description: This is an abstract class that creates an ArrayList by implementing the methods in the 
 * ArrayListADT. There are methods to test whether the ArrayList is full, empty, size, doubleCapacity, 
 * makeEmpty and toString. There are three abstract methods: search, insert, and remove.
 * 
 *@author Derick Hansraj and Wenjie Cao
 */
public abstract class ArrayList<T> implements ArrayListADT<T> {
	/**
	 * The list - generic array of items
	 */
	protected T[] data;
	/**
	 * The number of items in the list
	 */
	protected int size;
	/**
	 * default maximum capacity
	 */
	private final int CAPACITY = 100;
	
	/**
     * default constructor - creates a list that can store 100 items;<br>
     * the size of the list is initialized to 0
     */
    public ArrayList() {
          size = 0;
          data = (T[])new Object[CAPACITY];
    }

    /**
     * parameterized constructor - allows the user to specify the maximum size of the list.<br>
     * The list created can store at most <i>size</i> items; the size of the list is initialized to 0.
     * @param size indicates the maximum size of the list as specified by the user
     */
    public ArrayList(int size){
          if(size <= 0) {
                System.err.println("The list size must be positive. "
                     + "Creating a list whose size is " + CAPACITY + ".");
                data = (T[])new Object[CAPACITY];
          }
          else
        	  data = (T[])new Object[size];
          size = 0;
    }
    /**
     * empty method - determines whether or not the list is empty
     * @return <i>true</i> if the list is empty; <i>false</i> otherwise
     */	
	public boolean isEmpty() {
		return size == 0;
	}
    /**
     * full method - determines whether or not the list is full
     * @return <i>true</i> if the list is full; <i>false</i> otherwise
     */
	public boolean isFull() {	
		return size == CAPACITY;
	}
    /**
     * getSize method - returns the number of items in the list
     * @return the number of items in the list
     */
	public int getSize() {
		return size;
	}
    /**
     * getCapacity method - returns the maximum size of the list
     * @return the maximum size of the list
     */
	public int getCapacity() {
		return data.length;
	}
    /**
     * makeEmpty method - makes the list empty and sets the size to 0
     */
	public void makeEmptpy() {
			size = 0;
			data = (T[])new Object[data.length];
			System.gc();
	}
    /**
     * toString method - returns the state of the object as a String
     * @return a String containing the items in the list
     */	
	public String toString() {
		String str = "The list contains " + size + " items.\n";
		if(!isEmpty()) {
			for(int i=0; i < size; i++)
				str += data[i] + "\n";
		}
		return str;
	}
	
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