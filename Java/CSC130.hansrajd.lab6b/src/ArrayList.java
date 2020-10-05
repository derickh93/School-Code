/**
 * Title: CSC130hansrajd.lab6b
 * Filename: ArrayList.java
 * Date Written: November 16, 2017
 * Due Date: November 18, 2017
 * Description: Description: Represents an array implementation of a list. The front of
 * the list is kept at array index 0. This class will be extended
 * to create a specific kind of list.
 * @author Derick Hansraj & Wenjie Cao
 **/

public abstract class ArrayList <T extends Comparable<T>> implements ArrayListADT<T> {
	//instance variables
	protected T[] data;
	protected int size;
	private final int CAPACITY = 100;

	/**
	 * default constructor --
	 * creates an empty list using the default capacity.
	 */
	public ArrayList() {
		data = (T[]) new Comparable[CAPACITY];
		size = 0;
	}

	/**
	 * parameterized constructor --
	 * creates an empty list using the specified capacity.
	 * @param size the initial size of the list as specified by the user
	 */
	public ArrayList(int size) {
		if(size < 1) {
			data = (T[]) new Comparable[CAPACITY];
		}
		data = (T[]) new Comparable[size];
		this.size = 0;

	}

	/**
	 * insert -- Inserts an object into the ArrayList.
	 * @param insertItem The object to be inserted into the ArrayList.
	 */
	public abstract void insert(T insertItem);

	/**
	 * search -- Searches the ArrayList for an item.
	 * @param searchItem The object to be searched for in the ArrayList.
	 */
	public abstract int search(T searchItem);

	/**
	 * remove -- Removes an object from the ArrayList.
	 * @param removeItem The object to be removed from the ArrayList.
	 */
	public abstract T remove(T removeItem);

	/**
	 * isEmpty --
	 * determines whether or not the list is empty.
	 * @return true if this list is empty; false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;

	}

	/**
	 * isFull --
	 * determines whether or not the list is full.
	 * @return true if this list is full; false otherwise
	 */
	public boolean isFull() {
		return size == data.length;
	}

	/**
	 * getSize --
	 * returns a count of the number of items in this list.
	 * @return the number of items currently in the list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * getCapacity --
	 * gets the maximum capacity of items the array can hold.
	 * @return an integer with the maximum capacity that the array can hold.
	 */
	public int getCapacity() {
		return data.length;
	}

	/**
	 * doubleCapacity --
	 * Creates an array that is double the size of the current array 
	 * and then copies over the old contents.
	 */
	protected void doubleCapacity() {
		T[] temp = (T[]) data;
		data = (T[]) new Comparable[2 * data.length];
		for(int i = 0; i < temp.length; i++) {
			data[i] = temp[i];
		}
	}

	/**
	 * toString --
	 * returns a string representation of this list.
	 * @return a reference to a String containing the items in the list
	 */
	public String toString() {
		String str = "The list of " + size + " items\n";
		for(int i = 0; i < size; i++) {
			str += data[i] + "\n";
		}
		return str;
	}
}
