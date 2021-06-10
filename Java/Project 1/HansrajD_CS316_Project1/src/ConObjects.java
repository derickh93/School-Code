import java.util.Collections;
import java.util.LinkedList;


/**
 * <p>Title: ConObjects Class</p>
 *
 * <p>Description: Defines a ConObjects class capable of storing a 
 * linked list of Strings. This linked list sorts the strings by their name. </p>
 *
 * @author Derick Hansraj
 */
public class ConObjects {
	LinkedList <String>objects = new LinkedList <String>();
	
	  /**
	   * insert method - inserts a string into the linked list objects
	   * @param insert the value to be stored in the linkedlist
	   */
	public void insert(String insert) {
		objects.add(insert);
		Collections.sort(objects);
	}
	
	  /**
	   * printList method - prints the linked list
	   */
	public String printList() {
		return objects.toString();
	}
	
	  /**
	   * size method - prints the size of the linked list objects

	   */
	public int size() {
		return objects.size();
	}
	
	  /**
	   * data method - returns the data stored in the desired input
	   * @param index the index to print
	   */
	public String data(int index) {
		return objects.get(index);
	}
}
