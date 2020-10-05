/**
 * Title: CSC130hansrajD.Proj1
 * Filename: Vehicle.java
 * Date Written: September 25, 2017
 * Due Date: October 02, 2017
 * Description:
 * This class defines a Vehicle object. There is a default constructor and a parameterized constructor. 
 * There are getters and setters for the manufacturer, model, customer id, and availability. There is a 
 * method to rent a vehicle, and there is an abstract method to return a vehicle. Finally there is a toString 
 * method to return the state of the object.
 *@author Derick Hansraj
 */
public abstract class Vehicle {

	//instance variables
	private String manufacturer, model, vehicleId, customerId;
	protected boolean available;
	static int count;

	/**
	 * default constructor - creates a vehicle object with default values
	 * 
	 */
	public Vehicle() {
		manufacturer = "";
		model = "";
		customerId = "";
		available = true;
		count = 0;
	}

	/**
	 * parameterized constructor - creates a vehicle object with user desired parameters
	 * 
	 * @param manufacturer -determines the manufacturer of the vehicle object
	 * @param model -determines the model of the vehicle object
	 * @param customerId -determines the customer id of the vehicle object
	 */
	public Vehicle( String manufacturer, String model, String vehicleId) {
		this.manufacturer = manufacturer;
		this.model = model;
		this.vehicleId = vehicleId;
	}

	/**
	 * getManufacturer -method that returns the manufacturer of the vehicle object.
	 * 
	 * @return A string representing the manufacturer of the vehicle object.
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * setManufacturer -method that changes the manufacturer of the vehicle object.
	 * 
	 * @param manufacturer -A string to set the manufacturer of the vehicle object.
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * getModel -method that returns the model of the vehicle object.
	 * 
	 * @return A string representing the model of the vehicle object.
	 */
	public String getModel() {
		return model;
	}

	/**
	 * setModel -method that changes the model of the vehicle object.
	 * 
	 * @param model -A string to set the model of the vehicle object.
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * getVehicleId -method that returns the vehicle id of the vehicle object.
	 * 
	 * @return A string representing the id of the vehicle object.
	 */
	public String getVehicleId() {
		return vehicleId;
	}

	/**
	 * setVehicleId -method that changes the vehicle Id of the vehicle object.
	 * 
	 * @param manufacturer -A string to set the id of the vehicle object.
	 */
	public void setvehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}


	/**
	 * getCustomerId -method that returns the customer id of the vehicle object.
	 * 
	 * @return A string representing the customer id of the vehicle object.
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * setCustomerId -method that changes the customer id of the vehicle object.
	 * 
	 * @param customerId -A string to set the customer id of the vehicle object.
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * isAvailable -method that determines if the vehicle is available for rent.
	 * 
	 * @return A boolean value stating whether or not the vehicle object is available.
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * setAvailable -method that changes whether or not the vehicle is available for rent.
	 * 
	 * @param available -A boolean to set the availability of the vehicle object.
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	/**
	 * rentVehicle -method that changes a vehicle status from available to rented.
	 * 
	 * @param transaction -A string to determine the type of transaction.
	 * @param vehicle -A string to determine what vehicle will be rented.
	 * @param id -A string to determine who rented the vehicle.
	 */
	public void rentVehicle(String transaction, String vehicle, String id) {
			setAvailable(false);
			setCustomerId(id);
	}

	/**
	 * returnVehicle -A abstract method that returns a vehicle and calculates the cost depending on the type of vehicle.
	 * 
	 * @param transaction -A string that determines the type of transaction.
	 * @param id -A string to determine the customers id.
	 */
	public abstract double returnVehicle(String transaction, String id, int[] temp);

	/**
	 * toString - returns the vehicle object as a string
	 * 
	 * @return a String containing the manufacturer, model, customer id, and availability of the vehicle object.
	 */
	public String toString() {
		return "Vehicle [id= " + vehicleId + ", maunfactuer= " + manufacturer + ", model= " + model + ", available= " + available
				+ ", Customer ID= " + customerId +"]";
	}
}
