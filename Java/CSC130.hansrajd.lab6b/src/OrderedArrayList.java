/**
 * Title: CSC130hansrajd.lab6b
 * Filename: OrderedArrayList.java
 * Date Written: November 16, 2017
 * Due Date: November 18, 2017
 * Description: This class creates an OrderedArrayList. There is a default and parameterized constructor.
 * There are methods inherited from the ArrayList class.There are also methods created to insert, search and 
 * remove objects. 
 * 
 *@author Derick Hansraj and Wenjie Cao
 */
public class OrderedArrayList<T extends Comparable<T>>extends ArrayList<T>{

	/**
	 * default constructor - creates an list of size 100; the number of items
	 * in the list is initialized to 0
	 */
	public OrderedArrayList() {
		super();
	}

	/**
	 * parameterized constructor - allows the user to specify the maximum size of the list.
	 * The list created can store at most size items; the number of items in the list is
	 * initialized to 0.
	 * @param size indicates the maximum size of the list as specified by the user
	 */
	public OrderedArrayList(int size) {
		super(size);
	}

	/**
	 * insert method - inserts an item in the list in the correct order
	 * @param insertItem is a reference to the item to be inserted
	 */
	public void insert(T insertItem) {
		if(isFull()) {
			doubleCapacity();
		}
		int loc = 0;
		boolean found = false;
		for(int i = 0; i < size && !found; i++) {
			if((data[i]).compareTo(insertItem) > 0) {
				for(int j = size; j > i; j--)
					data[j] = data[j-1];
				loc = i;
				found = true;
			}
			else 
				loc++;
		}
		data[loc] = insertItem;
		size++;
	}

	/**
	 * search method - determines whether or not searchItem is in the list by use of a linear search
	 * @param searchItem is a reference to an item whose key-field has been initialized
	 * @return an integer which represents the location in the list where searchItem
	 * was found; if searchItem is not found, -1 is returned
	 */
	public int search(T searchItem) {
		int loc = -1;
		if(isEmpty())
			return loc;
		boolean found = false;
		int left = 0, right = size;
		while(left <= right && !found) {
			int mid = (left+right) / 2;
			int value = searchItem.compareTo(data[mid]);
			if(value == 0) {
				loc = mid;
				found = true;
			}
			else if(value > 0)
				left = mid+1;
			else 
				right = mid-1;
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
		T info = null;
		int loc = search(removeItem);
		if(loc >= 0) {
			info = (T) data[loc];
			for(int i = loc; i < size-1; i++ ) 
				data[i] = data[i+1];

			data[size-1] = null;
			size--;
		}
		return info;
	}
}
