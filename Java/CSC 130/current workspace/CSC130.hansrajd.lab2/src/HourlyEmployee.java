import java.util.Date;

public class HourlyEmployee extends Employee{
	protected double hours;
	protected double rate;

	public HourlyEmployee(String n, String ssno, String id, String gender, Date dob, double h, double r) {
		super(n, ssno, id, gender, dob);
		hours = h;
		rate = r;
	}

	public double getHours() {
		return hours;
	}

	public double getRate() {
		return rate;
	}

	public void setHours(double h) {
		hours = h;
	}

	public void setRate(double r) {
		rate = r;
	}

	public String toString() {
		return super.toString()+ "\n"+  "Hours worked: " + hours + "\n"+ "Rate of pay: " + rate;
	}		

	public double pay() {
		return rate * hours;
	}


	public void display() {
		System.out.println(this);
	}
	
	public int compareTo(Object o) {
		return 0;
		
	}
}
