/**
 * Title: CSC130hansrajd.lab6b
 * Filename: Packet.java
 * Date Written: November 16, 2017
 * Due Date: November 18, 2017
 * Description: This class creates a Packet object. There is a parameterized constructor. There is a method
 * that compares two Packet objects by comparing their position. Finally there is a toString method to return
 * the current state of the object.
 *@author Derick Hansraj and Wenjie Cao
 */
public class Packet implements Comparable<Packet> {
	private String str;
	private Integer pos;

	/**
	 * parameterized constructor - create a packet object with a position and string
	 * @param pos an integer representing the position of the packet object
	 * @param str a string stored in the packet object

	 */
	public Packet(int pos, String str) {
		this.str = str;
		this.pos = pos;
	}

	/**
	 * compareTo method -- compares two packet objects by the use of its pos value
	 * @return returns 0 if the two objects are equal, -1 if the first object is lower than the
	 * second object, and 1 if the first object is bigger.
	 */
	public int compareTo(Packet arg0) {
		int ret = 0;
		if(this.pos == arg0.pos)
			ret = 0;
		else if(this.pos < arg0.pos)
			ret = -1;
		else
			ret = 1;

		return ret;	
	}

	/**
	 * toString method -- creates and returns a String which represents the state of the object
	 * @return a String containing the current value of str.
	 */
	public String toString() {
		return str;
	}
}
