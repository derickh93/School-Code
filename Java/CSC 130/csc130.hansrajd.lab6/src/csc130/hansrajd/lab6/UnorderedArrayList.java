package csc130.hansrajd.lab6;
/**
 * <p>Title: UnorderedArrayList Class</p>
 *
 * <p>Description:
 * *
 * @author Derick Hansraj
 */

public class UnorderedArrayList extends ArrayListClass{
	/**
	 * default constructor - creates an list of size 50; the number of items
	 * in the list is initialized to 0
	 */
	public UnorderedArrayList()
	{
		super();
	}

	/**
	 * parameterized constructor - allows the user to specify the maximum size of the list.
	 * The list created can store at most size items; the number of items in the list is
	 * initialized to 0.
	 * @param size indicates the maximum size of the list as specified by the user
	 */
	public UnorderedArrayList(int size)
	{
		super(size);
	}

	/**
	 * search method - determines whether or not searchItem is in the list
	 * @param searchItem is a reference to an item whose key-field has been initialized
	 * @return an integer which represents the location in the list where searchItem
	 * was found; if searchItem is not found, -1 is returned
	 */
	public int search(Object searchItem) {
		int count = 0;
		int pos = -1;
		while(count < numItems) {
			if(items[count].equals(searchItem))
				pos = count;
			count++;
		}
		return pos;
	}

	/**
	 * insert method - inserts an item in the list
	 * @param insertItem is a reference to the item to be inserted
	 */
	public void insert(Object insertItem) {
		items[numItems] = insertItem;
		numItems++;
	}

	/**
	 * remove method - removes an item from the list
	 * @param removeItem is a reference to an item whose key-field has been initialized.
	 * If the item is found it is removed; otherwise the list remains unchanged
	 * @return 
	 */
	public Object remove(Object removeItem) {
		int current = this.search(removeItem);
		int num = numItems - current;
		if(this.search(removeItem) >= 0) {
			while(current < num) {
				items[current] = items[current + 1];
				current++;
			}
			numItems--;
		}
		return this.toString();
	}

}
