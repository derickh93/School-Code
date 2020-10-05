/**
 * Title: CSC130hansrajd.lab3a
 * Filename: DateException.java
 * Date Written: October 3, 2017
 * Due Date: October 7, 2017
 * Description: Exception Class for use by the Date class. 
 * 
 *@author Derick Hansraj and Wenjie Cao
 */
public class DateException extends Exception {
	public DateException() {
		super("Invalid value for Date");
	}
	public DateException(String message) {
		super(message);
	}
}
