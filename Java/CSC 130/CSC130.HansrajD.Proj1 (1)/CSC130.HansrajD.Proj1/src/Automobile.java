/**
 * Title: CSC130hansrajD.Proj1
 * Filename: Automobile.java
 * Date Written: September 25, 2017
 * Due Date: October 02, 2017
 * Description:
 * This class defines a Automobile object. There are methods inherited from the Vehicle class. There is a parameterized constructor. 
 * There is a method to return a automobile and there is a toString method to return the state of the object.
 *@author Derick Hansraj
 */
public class Automobile extends Vehicle{

	//instance variables
	int mileage;
	static int count;

	/**
	 * parameterized constructor - creates a Automobile object with user desired parameters
	 * 
	 * @param manufacturer -determines the manufacturer of the Automobile object
	 * @param model -determines the model of the Automobile object
	 * @param customerId -determines the customer id of the Automobile object
	 */
	public Automobile(String manufacturer, String model, String customerId) {
		super(manufacturer, model, customerId);
		count++;
	}

	/**
	 * returnVehicle -A method that returns a Automobile and calculates the cost depending on the parameters.
	 * 
	 * @param transaction -A string that determines the type of transaction.
	 * @param id -A string to determine the customers id.
	 */
	public double returnVehicle(String transaction, String id, int days, int miles ) {
		double cost = 0;
		if(transaction.equalsIgnoreCase("return") && days <= 7 && miles <= 5000 && miles > 500) {
			setAvailable(true);
			this.setCustomerId(id);
			cost = (days*60) + (.25 * miles);
			count++;
		}
		else if (transaction.equalsIgnoreCase("return") && days <= 7 && miles <= 5000 && miles < 500) {
			setAvailable(true);
			this.setCustomerId(id);
			cost = days * 60;
			count++;
		}
		return cost;
	}	

	/**
	 * toString - returns the automobile object as a string
	 * 
	 * @return a String containing the manufacturer, model, customer id, and availability of the automobile object.
	 */
	public String toString() {
		return "manufacturer=" + getManufacturer() + ", " + "model=" + getModel() + ", "  + "available=" +available + ", "  + "renter=" + getCustomerId() + "][Automobile [mileage=" + mileage + " count: " + count;
	}
}
