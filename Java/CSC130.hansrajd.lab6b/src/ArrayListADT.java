/**
 * Title: CSC130hansrajd.lab6b
 * Filename: ArrayListADT.java
 * Date Written: November 16, 2017
 * Due Date: November 18, 2017
 * Description: Description: Defines the behaviors of a basic ArrayList,
 * @author Derick Hansraj & Wenjie Cao
 **/
public interface ArrayListADT <T extends Comparable<T>> {

	/**
	 * insert -- Inserts an object into the ArrayList.
	 * @param insertItem The object to be inserted into the ArrayList.
	 */
	void insert(T insertItem);

	/**
	 * search -- Searches the ArrayList for an item.
	 * @param searchItem The object to be searched for in the ArrayList.
	 */
	int search(T searchItem);

	/**
	 * remove -- Removes an object from the ArrayList.
	 * @param removeItem The object to be removed from the ArrayList.
	 */
	Object remove(T removeItem);

	/**
	 * isEmpty --
	 * determines whether or not the list is empty.
	 */
	boolean isEmpty();

	/**
	 * isFull --
	 * determines whether or not the list is full.
	 */
	boolean isFull();

	/**
	 * getSize --
	 * returns a count of the number of items in this list.
	 */
	int getSize();

	/**
	 * getCapacity --
	 * gets the maximum capacity of items the array can hold.
	 */
	int getCapacity();

}
