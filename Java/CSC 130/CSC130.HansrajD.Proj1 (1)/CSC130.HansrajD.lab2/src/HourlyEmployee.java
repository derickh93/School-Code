import java.util.Date;
/**
 * Title: CSC130hansrajd.lab2
 * Filename: HourlyEmployee.java
 * Date Written: September 26, 2017
 * Due Date: September 30, 2017
 * Description: HourlyEmployee Class - This class contains the methods to create
 * an HourlyEmployee object. It contains accesser and getter methods for the employees
 * hours worked and rate of pay. It contains methods to create an email address, show 
 * the pay, display the HourlyEmployee status and a compareTo method to compare
 * the salary of two objects.There is also a toString method to display the current state
 * of the object.
 * @author <Derick Hansraj>
 */
public class HourlyEmployee extends Employee{

	//instance variables
	protected double hours;
	protected double rate;

	/**
	 * parameterized constructor -- The HourlyEmployee is given attributes 
	 * based upon the arguments sent. 
	 * @param n - the name of the employee.
	 * @param ssno - the social security number of the employee.
	 * @param id - the employee id number of the employee.
	 * @param gender - the gender of the employee.
	 * @param dob - the date of birth of the employee.
	 * @param h - the hours worked
	 * @param r - the rate paid per hour
	 * **/
	public HourlyEmployee(String n, String ssno, String id, String gender, Date dob, double h, double r) {
		super(n, ssno, id, gender, dob);
		hours = h;
		rate = r;
	}

	/**
	 * getHours --
	 * Returns the hours worked.
	 * @return the number of hours worked stored in the instance variable hours.
	 */
	public double getHours() {
		return hours;
	}

	/**
	 * getRate --
	 * Returns the rate paid per hour.
	 * @return the amount paid per hour stored in the instance variable rate.
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * setHours method --
	 * stores the user-specified value in hours.
	 * @param h the quantity to be stored in hours.
	 */
	public void setHours(double h) {
		hours = h;
	}

	/**
	 * setRate method --
	 * stores the user-specified value in rate.
	 * @param r the quantity to be stored in rate.
	 */
	public void setRate(double r) {
		rate = r;
	}

	/**
	 * toString --
	 * Returns a string representing the current state of the HourlyEmployee object.
	 * @return the name, ssno, id, gender, dob, dateHired, hours, and rate as a string.
	 */
	public String toString() {
		return super.toString()+ "\n"+  "Hours worked: " + hours + "\n"+ "Rate of pay: " + rate;
	}		

	/**
	 * pay method --
	 * calculates the pay of the HourlyEmployee using the rate and hours worked
	 * @return the pay of the HourlyEmployee.
	 */
	public double pay() {
		return rate * hours;
	}

	/**
	 * display method --
	 * outputs the current state of the HourlyEmployee.
	 */
	public void display() {
		System.out.println(this);
	}

	/**
	 * compareTo method --
	 * compares two objects using the salary.
	 * @param o the object to compare to the HourlyEmployee
	 * @return returns 0 if two objects have the same salary, returns -1 if this is less, returns 1
	 * if this is greater.
	 */
	public int compareTo (Object o) {
		if (pay() > ((Employee)o).pay())
			return 1;
		else if (pay() == ((Employee)o).pay())
			return 0;
		else 
			return -1;
	}
}
