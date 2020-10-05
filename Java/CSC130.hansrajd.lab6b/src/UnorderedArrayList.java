/**
 * Title: CSC130hansrajd.lab6b
 * Filename: UnorderedArrayList.java
 * Date Written: November 16, 2017
 * Due Date: November 18, 2017
 * Description: This class creates an UnorderedArrayList. There is a default and parameterized constructor.
 * There are methods inherited from the ArrayList class.There are also methods created to insert, search and 
 * remove objects. 
 * 
 *@author Derick Hansraj and Wenjie Cao
 */
public class UnorderedArrayList<T extends Comparable<T>>extends ArrayList<T>{
	T insertItem;

	/**
	 * default constructor - creates an list of size 100; the number of items
	 * in the list is initialized to 0
	 */
	public UnorderedArrayList() {
		super();
	}

	/**
	 * parameterized constructor - allows the user to specify the maximum size of the list.
	 * The list created can store at most size items; the number of items in the list is
	 * initialized to 0.
	 * @param size indicates the maximum size of the list as specified by the user
	 */
	public UnorderedArrayList(int size) {
		super(size);
	}

	/**
	 * insert method - inserts an item in the list
	 * @param insertItem is a reference to the item to be inserted
	 */
	public void insert(T insertItem) {
		if(size == data.length)
			doubleCapacity();
		data[size] = insertItem;
		size++;
	}

	/**
	 * doubleCapacity method - doubles the capacity of the array
	 */
	protected void doubleCapacity() {
		T[] temp = (T[]) data;
		data = (T[]) new Object[2 * data.length];
		for(int i = 0; i < temp.length; i++) {
			data[i] = temp[i];
		}
	}

	/**
	 * search method - determines whether or not searchItem is in the list
	 * @param searchItem is a reference to an item whose key-field has been initialized
	 * @return an integer which represents the location in the list where searchItem
	 * was found; if searchItem is not found, -1 is returned
	 */
	public int search(T searchItem) {
		int loc = -1;
		boolean found = false;
		for(int i = 0; i < size && !found; i++) {
			if(data[i].equals(searchItem)) {
				found = true;
				loc = i;
			}
		}
		return loc;
	}

	/**
	 * remove method - removes an item from the list
	 * @param removeItem is a reference to an item whose key-field has been initialized.
	 * If the item is found it is removed; otherwise the list remains unchanged
	 * @return 
	 */
	public T remove(T removeItem) {
		int num = search(removeItem);
		if(num >= 0) {
			for(int i = num; i < size-1; i++) {
				data[i] = data[i+1];
			}
			size--;
		}
		else 
			removeItem = null;
		return removeItem;
	}

}
