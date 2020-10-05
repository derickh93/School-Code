import java.util.InputMismatchException;
import java.util.Scanner;
import lab3.Date;
import lab3.DateException;

/**
 * Title: CSC130hansrajd.lab3
 * Filename: Lab3App.java
 * Date Written: February 28, 2017
 * Due Date: March 4, 2017
 * Description: Lab3App Class - This class uses the methods created in the Date.java class to 
 * test the various methods on the input.
 * @author <Derick Hansraj>
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
							System.out.println("Valid date: " + adate.toString() + "\nWriting date to file...");
							done4 = true;
							done5 = true;
						}

						catch (DateException ex) {
							System.out.println("DateException: " + ex.getMessage() + scan.nextLine()); 
							done4 = true;
							done2 = false;
							done3 = false;
						}
					}
				}
				System.out.println("Would you like to enter another date? (Y/N)");
				option = scan.next();
				if(option.equalsIgnoreCase("y")) {
					done2 = false;
					done3 = false;
					done4 = false;
					done5 = false;
					done = false;
				}
				else
					done = true;
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
