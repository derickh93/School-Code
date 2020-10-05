package lab3;

/**
 * <p>
 * Title: CSC130hansrajd.lab3
 * </p>
 *
 * <p>
 * Description:Date Class - This class contains the methods to create
 * a Date object. It has a default constructor, a parameterized constructor, setter methods,
 * getter methods, a leapyear method and a toString method.
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author Derick Hansraj & Instructor
 * @version 1.0
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
		if (getMonth() == 1 || getMonth() == 3 || getMonth() == 5|| getMonth() == 7 || getMonth() == 8 || getMonth() == 10|| getMonth() == 12 && day >=1 && day <=31)
			dDay = day;
		else if ((getMonth() == 4 || getMonth() == 6 || getMonth() == 9 || getMonth() == 11) && day >=1 && day <=30)
			dDay = day;
		else if (getMonth() == 2 && day <= 29 && day >= 1)
			dDay = day;
		else
			throw new DateException("Invalid Day: Please reenter the day.");
	}

	/**
	 * setYear - stores year in dYear
	 * 
	 * @param year
	 *            the value to be stored in dYear
	 * @throws DateException 
	 */
	public void setYear(int year) throws DateException {
		if(year >= 1752 && year <= 2017) {
			if(leapYear(year) == true && getMonth() == 2 && getDay() == 29)
				dYear = year;
			else if(leapYear(year) == false && getMonth() == 2 && getDay() == 29)
				throw new DateException("Invalid LeapYear: " + getMonth() + "-" + getDay() + "-" + year);
			else 
				dYear = year;
		}
		else 
			throw new DateException("Invalid Year: " + getMonth() + "-" + getDay() + "-" + year);
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
	 * @param y -year to be tested as a leap year
	 */
	private boolean leapYear(int y) {
		if (y % 4 == 0) {
			if (y % 100 != 0) 
				return true;
			else if (y % 400 == 0)
				return true;
		}
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