import java.util.Date;
/**
 * Title: CSC130hansrajd.lab2
 * Filename: SalariedEmployee.java
 * Date Written: September 26, 2017
 * Due Date: September 30, 2017
 * Description: SalariedEmployee Class - This class contains the methods to create
 * an HourlyEmployee object. It contains accesser and getter methods for the SalariedEmployees
 * salary. It contains methods to show the pay, display the SalariedEmployee status and a compareTo 
 * method to compare the salary of two objects.There is also a toString method to display the current 
 * state of the object.
 * @author <Derick Hansraj>
 */
public class SalariedEmployee extends Employee {

	//instance variables
	protected double salary;

	/**
	 * parameterized constructor -- The SalariedEmployee is given attributes 
	 * based upon the arguments sent. 
	 * @param n - the name of the employee.
	 * @param ssno - the social security number of the employee.
	 * @param id - the employee id number of the employee.
	 * @param gender - the gender of the employee.
	 * @param dob - the date of birth of the employee.
	 * @param salary - the salary of the employee.
	 * **/
	public SalariedEmployee(String n, String ssno, String id, String gender, Date dob, double salary) {
		super(n, ssno,id, gender, dob);
		this.salary = salary;
	}

	/**
	 * toString --
	 * Returns a string representing the current state of the SalariedEmployee object.
	 * @return the name, ssno, id, gender, dob, and salary as a string.
	 */
	public String toString(){
		return super.toString()+ "\n"+  "Annual salary: " + salary;
	}

	/**
	 * getSalary --
	 * Returns the salary of the SalariedEmployee.
	 * @return the salary stored in the instance variable salary.
	 */
	public double getSalary() {
		return salary;

	}

	/**
	 * setSalary method --
	 * stores the user-specified value in salary.
	 * @param s the salary to be stored in salary.
	 */
	public void setSalary(double s) {
		salary = s;
	}

	/**
	 * pay method --
	 * calculates the pay of the HourlyEmployee using the rate and hours worked
	 * @return the pay of the HourlyEmployee.
	 */
	public double pay() {
		return salary;
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
		if (getSalary() > ((Employee)o).pay())
			return 1;
		else if (getSalary() == ((Employee)o).pay())
			return 0;
		else 
			return -1;
	}
}