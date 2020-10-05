/**
 * Title: CSC130hansrajd.Program3
 * Filename: Airline.java
 * Date Written: November 17, 2017
 * Due Date: November 27, 2017
 * Description: This class creates a Airline object.
 * 
 *@author Derick Hansraj
 */
public class Airline {
	//instance variables
	private String flightId;
	private long entered;
	private long exited;

	/**
	 * default constructor - creates a Airline with a flightId and time entered.
	 */
	public Airline(String flightId, long entered) {
		this.flightId = flightId;
		this.entered = entered;
	}

	/**
	 * getFlightId method -- returns what's stored in the instance variable flightId
	 * 
	 * @return the state of the instance variable flightId
	 */
	public String getFlightId() {
		return flightId;
	}

	/**
	 * getEntered method -- returns what's stored in the instance variable entered
	 * 
	 * @return the state of the instance variable entered
	 */
	public long getEntered() {
		return entered;
	}

	/**
	 * setEntered method -- modifies the value of the instance variable entered
	 * 
	 * @param entered a number representing the time entered
	 */
	public void setEntered(long entered) {
		this.entered = entered;
	}

	/**
	 * setExited method -- modifies the value of the instance variable exited
	 * 
	 * @param exited a number representing the time exited
	 */
	public void setExited(long exited) {
		this.exited = exited;
	}

	/**
	 * toString method -- this method returns the state of the Airline object
	 * 
	 * @return a reference to a string object that contains the values stored in
	 *         the instance variables
	 */
	public String toString() {
		return "Flight ID: " + flightId;

	}
}
