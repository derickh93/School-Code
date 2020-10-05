import java.util.Date;
/**
 * Title: CSC130hansrajd.lab2
 * Filename: Person.java
 * Date Written: September 26, 2017
 * Due Date: September 30, 2017
 * Description: Person Class - This class contains the methods to create
 * a Person object. It contains accesser and getter methods for the persons
 * name, social, gender and dob.There is also a toString method to display 
 * the current state of the object.
 * @author <Derick Hansraj>
 */
public class Person {
	//instance variables
	private String name;
	private String ssno;
	private String gender;
	private Date dob;

	@SuppressWarnings("deprecation")
	/**
	 * default constructor --
	 * Creates a Person object with default values
	 */
	public Person() {
		name = "John Doe";
		ssno = "012345678";
		gender = "Male";
		dob = new Date(90,01,01);
	}

	/**
	 * parameterized constructor -- The Person is given attributes 
	 * based upon the arguments sent. 
	 * @param name - the name of the employee.
	 * @param ssno - the social security number of the employee.
	 * @param gender - the gender of the employee.
	 * @param dob - the date of birth of the employee.
	 * **/
	public Person(String name, String ssno, String gender, Date dob) {
		this.name = name;
		this.ssno = ssno;
		this.gender = gender;
		this.dob = dob;
	}

	/**
	 * getName --
	 * Returns the name of the Person.
	 * @return the name stored in the instance variable name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * getSSno --
	 * Returns the social security number of the Person.
	 * @return the social security number stored in the instance variable ssno.
	 */
	public String getSSno() {
		return ssno;
	}

	/**
	 * getGender --
	 * Returns the gender of the Person.
	 * @return the gender stored in the instance variable gender.
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * getDateOfBirth --
	 * Returns the date of birth of the Person.
	 * @return the date of birth stored in the instance variable dob.
	 */
	public Date getDateOfBirth() {
		return dob;
	}

	/**
	 * setName method --
	 * stores the user-specified value in name.
	 * @param n the name to be stored in name.
	 */
	public void setName(String n) {
		name = n;
	}

	/**
	 * setSSno method --
	 * stores the user-specified value in ssno.
	 * @param ssno the social security number to be stored in ssno.
	 */
	public void setSSno(String ssno) {
		this.ssno = ssno;
	}

	/**
	 * setGender method --
	 * stores the user-specified value in gender.
	 * @param gender the gender to be stored in gender.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * setDateOfBirth method --
	 * stores the user-specified value in dob.
	 * @param dob the date of birth to be stored in dob.
	 */
	public void setDateOfBirth(Date dob) {
		this.dob = dob;
	}

	/**
	 * toString --
	 * Returns a string representing the current state of the Person object.
	 * @return the name, ssno, gender, and dob as a string.
	 */
	public String toString() {
		return "name: " + name + ", ssno: " + ssno + ", gender: " + gender + ", dob: " + dob;
	}


}
