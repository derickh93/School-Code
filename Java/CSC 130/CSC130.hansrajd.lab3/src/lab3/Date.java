package lab3;

/**
 * Title: CSC130hansrajd.lab3
 * Filename: Date.java
 * Date Written: February 28, 2017
 * Due Date: March 4, 2017
 * Description: Date Class - This class contains the methods to create
 * a Date object. It has a default constructor, a parameterized constructor, setter methods,
 * getter methods, a leapyear method and a toString method.
 * @author <Derick Hansraj>
 */
public class Date {

	private int dMonth; // variable to store the month
	private int dDay; // variable to store the day
	private int dYear; // variable to store the year

	/**
	 * default constructor - sets dMonth=1, dDay=1, and dYear=1900
	 */
	public Date() {
		dMonth = 1;
		dDay = 1;
		dYear = 1900;
	}

	/**
	 * parameterized constructor - sets dMonth, dDay, and dYear to user
	 * specified values
	 * 
	 * @param month
	 *            value to be stored in dMonth
	 * @param day
	 *            value to be stored in dDay
	 * @param year
	 *            value to be stored in dYear
	 * @throws DateException 
	 */
	public Date(int month, int day, int year) throws DateException {
		setMonth(month);
		setDay(day);
		setYear(year);
	}

	/**
	 * setDate - stores month, day, and year in dMonth, dDay, and dYear
	 * respectively be calling each of the setMethods defined
	 * 
	 * @param month
	 *            value to be stored in dMonth
	 * @param day
	 *            value to be stored in dDay
	 * @param year
	 *            value to be stored in dYear
	 * @throws DateException 
	 */
	public void setDate(int month, int day, int year) throws DateException {
		setMonth(month);
		setDay(day);
		setYear(year);
	}

	/**
	 * setMonth - stores month in dMonth
	 * 
	 * @param month
	 *            the value to be stored in dMonth
	 */
	public void setMonth(int month) throws DateException{
		if (month >= 1 && month <= 12)
			dMonth = month;
		else
			throw new DateException("Invalid Month: month out of range");
	}

	/**
	 * setDay - stores day in dDay
	 * 
	 * @param day
	 *            the value to be stored in dDay
	 * @throws DateException 
	 */
	public void setDay(int day) throws DateException {
		if (getMonth() != 2)
			dDay = day;
		else if (getMonth() == 2 && day <= 28 && day >= 1)
			dDay = day;
		else 
			throw new DateException("Invalid Day: day out of range");
	}

	/**
	 * setYear - stores year in dYear
	 * 
	 * @param year
	 *            the value to be stored in dYear
	 * @throws DateException 
	 */
	public void setYear(int year) throws DateException {
		if (year >= 1792 && year <= 2017)
			dYear = year;			
		else 
			throw new DateException("Invalid Year: year out of range");
	}

	/**
	 * getMonth - accessor for dMonth
	 * 
	 * @return returns the value stored in dMonth
	 */
	public int getMonth() {
		return dMonth;
	}

	/**
	 * getDay - accessor for dDay
	 * 
	 * @return returns the value stored in dDay
	 */
	public int getDay() {
		return dDay;
	}

	/**
	 * getYear - accessor for dYear
	 * 
	 * @return returns the value stored in dYear
	 */
	public int getYear() {
		return dYear;
	}

	/**
	 * leapYear - determines whether or not a year is a leap year.
	 * 
	 * @return returns true if the rear is a leap year or false if it is not.
	 */
	private boolean leapYear() {
		if(((getYear() % 400 == 0)  || (getYear() % 4 == 0) && (getYear() % 100 != 0)))
			return true;
		else
			return false;
	}

	/**
	 * toString - returns the month, day, and year in the format: mm-dd-yyyy;
	 * leading zeros are NOT contained within the string
	 * 
	 * @return a String containing the date in month-day-year format
	 */
	public String toString() {
		return (dMonth + "-" + dDay + "-" + dYear);
	}
}