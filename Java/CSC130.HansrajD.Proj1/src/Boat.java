/**
 * Title: CSC130hansrajD.Proj1
 * Filename: Boat.java
 * Date Written: September 25, 2017
 * Due Date: October 02, 2017
 *  Description:
 * This class defines a Boat object. There are methods inherited from the Vehicle class. There is a parameterized constructor. 
 * There is a method to return a boat and there is a toString method to return the state of the object.
 *@author Derick Hansraj
 */
public class Boat extends Vehicle{

	//instance variables
	static int count;

	/**
	 * parameterized constructor - creates a Boat object with user desired parameters
	 * 
	 * @param manufacturer -determines the manufacturer of the Boat object
	 * @param model -determines the model of the Boat object
	 * @param customerId -determines the customer id of the Boat object
	 */
	public Boat(String manufacturer, String model, String customerId) {
		super(manufacturer, model, customerId);
		this.setAvailable(true);
		count++;
	}

	/**
	 * returnVehicle -A method that returns a Boat and calculates the cost depending on the parameters.
	 * 
	 * @param transaction -A string that determines the type of transaction.
	 * @param id -A string to determine the customers id.
	 */
	public double returnVehicle(String transaction, String id, int hours, int num) {
		double cost = 0;
		if(transaction.equalsIgnoreCase("rent") && hours == 1) {
			setAvailable(true);
			this.setCustomerId(id);
			cost = 115;
		}
		else if(transaction.equalsIgnoreCase("rent") && hours == 2) {
			setAvailable(true);
			this.setCustomerId(id);
			cost = 225;
		}
		else if(transaction.equalsIgnoreCase("rent") && hours == 4) {
			setAvailable(true);
			this.setCustomerId(id);
			cost = 345;
		}
		else if(transaction.equalsIgnoreCase("rent") && hours == 8) {
			setAvailable(true);
			this.setCustomerId(id);
			cost = 495;
		}
		else if(transaction.equalsIgnoreCase("rent") && hours > 8) {
			setAvailable(true);
			this.setCustomerId(id);
			cost = (495 + ((hours - 8) * 115));
		}
		return cost;
	}	

	/**
	 * toString - returns the Boat object as a string
	 * 
	 * @return a String containing the manufacturer, model, customer id, and availability of the Boat object.
	 */
	public String toString() {
		return "manufacturer=" + getManufacturer() + ", " + "model=" + getModel() + ", "  + "available=" +available+ ", "  + "renter=" + getCustomerId() + " [count: " + count + "]";
	}
}
