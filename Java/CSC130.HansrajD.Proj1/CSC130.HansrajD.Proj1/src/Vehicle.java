
public class Vehicle {
	private String manufacturer;
	private String model;
	private String customerId;
	protected boolean available;
	static int count;
	
	public Vehicle() {
		manufacturer = "";
		model = "";
		customerId = "";
		available = false;
		count = 0;
	}
	
	public Vehicle(String manufacturer, String model, String customerId) {
		this.manufacturer = manufacturer;
		this.model = model;
		this.customerId = customerId;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public void rentVehicle() {
		
	}
	
	public void returnVehicle() {
		
	}

	public String toString() {
		return manufacturer + "\n" + model + "\n" + customerId + "\n" + available + "\n" + count;
	}

}
