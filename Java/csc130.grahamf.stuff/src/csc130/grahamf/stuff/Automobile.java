package csc130.grahamf.stuff;

public class Automobile {
	private double price;
	private String make, model, color;
	private int year;
	
	public Automobile() {
		color = "Black";
		year = 2005;
		make = "Infiniti";
		model = "G35";
	}
	public Automobile(String make, String model, String color, int year, double cost) {
		this.color = color;
		this.year = year;
		this.make = make;
		this.model = model;
		price = cost;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Price: " + price + ", Make: " + make + ", Model: " + model + ", Year: " + year + ", Color: " + color;
	}
	public boolean equals(Automobile a){
		if((this.year == a.year)
			&& (this.model.equalsIgnoreCase(a.model))
			&&(this.make.equalsIgnoreCase(a.make)))
			return true;
		return false;
		
		
	}
}
