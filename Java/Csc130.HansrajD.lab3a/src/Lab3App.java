import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Title: CSC130hansrajd.lab3a
 * Filename: Lab3App.java
 * Date Written: October 3, 2017
 * Due Date: October 7, 2017
 * Description:  This class uses the methods created in the Date.java class to 
 * test the various methods on the input.
 * 
 *@author Derick Hansraj and Wenjie Cao
 */
public class Lab3App {
	public static void main(String[] args) throws DateException {

		// Create a Scanner object and connect it to System.in
		Scanner scan = new Scanner(System.in);

		//create a new Date object
		Date adate = new Date();

		//instance variables
		int month = 0, day = 0, year = 0;
		boolean done = false;
		boolean done2 = false;
		boolean done3 = false;
		boolean done4 = false;
		boolean done5 = false;
		String option = null;



		while (!done) {
			try {
				while(!done5) {
					while(!done2) {
						System.out.print("Enter the month as an integer: ");
						month = scan.nextInt();
						adate.setMonth(month);
						done2 = true;
						done3 = false;
					}
					while(!done3) {
						System.out.print("Enter the day as an integer: ");
						day = scan.nextInt();
						adate.setDay(day);
						done3 = true;
						done4 = false;
					}
					while(!done4) {
						try {
							System.out.print("Enter the year as an integer: ");
							year = scan.nextInt();
							adate.setYear(year);
							done4 = true;
							done5 = true;
							done = true;
							System.out.println(adate.toString());
						}

						catch (DateException ex) {
							System.out.println("DateException: " + ex.getMessage() + scan.nextLine()); 
							done4 = true;
							done2 = false;
							done3 = false;
						}
					}
				}
			}
			catch (InputMismatchException ime) {
				System.out.println("Invalid input entered. Enter an integer" + scan.nextLine());
			}
			catch (DateException ex) {
				System.out.println("DateException: " + ex.getMessage() + scan.nextLine()); 
			}
		}
		scan.close();
	}
}
