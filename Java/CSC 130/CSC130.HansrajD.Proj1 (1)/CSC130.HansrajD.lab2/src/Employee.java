import java.text.DateFormat;
import java.util.Date;

/**
 * Title: CSC130hansrajd.lab2
 * Filename: Employee.java
 * Date Written: September 26, 2017
 * Due Date: September 30, 2017
 * Description: Employee Class - This class contains the methods to create
 * an Employee object. It contains accesser and getter methods for the employees
 * date of hire and employee number. It also contains methods to create an email address
 * and a toString method to display the current state of the object. There are two
 * abstract methods called pay and display.
 * @author <Derick Hansraj>
 */
public abstract class Employee extends Person{

	//instance variables
	protected Date dateHired = new Date();
	protected String employeeNo;

	/**
	 * default constructor --
	 * Creates a Employee object with default values
	 */
	public Employee() {
		super();
		employeeNo = "012345";
	}

	/**
	 * parameterized constructor -- The employee is given attributes 
	 * based upon the arguments sent. 
	 * @param n - the name of the employee.
	 * @param ssno - the social security number of the employee.
	 * @param id - the employee id number of the employee.
	 * @param gender - the gender of the employee.
	 * @param dob - the date of birth of the employee.
	 * **/
	public Employee(String n, String ssno, String id, String gender, Date dob) {
		super(n, ssno, gender, dob);
		employeeNo = id;
	}

	/**
	 * getEmployeeNo --
	 * Returns the employee id number.
	 * @return the employee id number stored in employeeNo.
	 */
	public String getEmployeeNo() {
		return employeeNo;
	}

	/**
	 * getDateHired --
	 * Returns the date employee was hired.
	 * @return the date hired stored in dateHired.
	 */
	public Date getDateHired() {
		return dateHired;
	}

	/**
	 * setEmployeeNo method --
	 * stores the user-specified value in employeeNo.
	 * @param id the value to be stored in employeeNo.
	 */
	public void setEmployeeNo(String id) {
		employeeNo = id;
	}

	/**
	 * setDateHired method --
	 * stores the user-specified value in dateHired.
	 * @param d the Date to be stored in dateHired.
	 */
	public void setDateHired(Date d) {
		dateHired = d;
	}

	/**
	 * createEmail method --
	 * creates an email address using the specified parameters.
	 * @return a String with the email of the HourlyEmployee
	 */
	public String createEmail() {
		String name = getName();
		char first = name.charAt(0);
		int pos = name.indexOf(' ');
		String last = getName().substring(pos+1);
		String temp = getEmployeeNo();
		String id = temp.substring(2);
		return first+last+id+"@acme.com";

	}

	/**
	 * toString --
	 * Returns a string representing the current state of the Employee object.
	 * @return the name, ssno, id, gender, dob, and dateHired as a string.
	 */
	public String toString() {
		return super.toString() + "\nEmployee no:" + employeeNo + "\nHired on: " + DateFormat.getDateInstance().format(dateHired);
	}

	/**
	 * pay method --
	 * abstract method
	 */
	public abstract double pay();

	/**
	 * display method --
	 * abstract method
	 */
	public abstract void display();

}
