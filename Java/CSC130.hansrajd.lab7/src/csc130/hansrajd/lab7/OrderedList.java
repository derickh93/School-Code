package csc130.hansrajd.lab7;

/**
 * <p>Title: ArrayListClass</p>
 *
 * <p>Description: Defines those properties and behaviors that are common to all lists.
 * The classes are designed so that objects of any type can be stored. Users of this
 * class (and its subclasses) should note that although the lists can store an "item"
 * of any type, they are required to override the equals method, defined
 * in the Object class, in their "item" class.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * @author Darci Burdge & Derick Hansraj
 * @version 1.0
 */
public class OrderedList <T> extends ArrayListClass <T>{
	public OrderedList() {
		super();
	}

	public OrderedList(int size) {
		super(size);
	}

	public <T extends Comparable<T>> int search(T[] items, T searchItem) {
		boolean found = false;
		int left = 0, right = items.length - 1, middle = 0;
		int index = -1, compResult = 0;
		while (left <= right && !found) {
			middle = (left + right) / 2;
			compResult = searchItem.compareTo(items[middle]);
			if (compResult == 0) {
				found = true;
				index = middle;
			}
			else if (compResult > 0)
				left = middle + 1;
			else
				right = middle - 1;
		}
		return index;
	}


	/**
	 * insert method - inserts an item in the list
	 * @param insertItem is a reference to the item to be inserted
	 */
	public void insert(T insertItem) {
		items[numItems] = insertItem;
		numItems++;
	}

	/**
	 * remove method - removes an item from the list
	 * @param removeItem is a reference to an item whose key-field has been initialized.
	 * If the item is found it is removed; otherwise the list remains unchanged
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public T remove(T removeItem) {
		int current = this.search(removeItem);
		int num = numItems - current;
		if(this.search(removeItem) >= 0) {
			while(current < num) {
				items[current] = items[current + 1];
				current++;
			}
			numItems--;
		}
		return (T) this.toString();
	}
}
