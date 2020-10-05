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
	public static void main(String[] args) {

		// Create a Scanner object and connect it to System.in
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		Date adate = null;
		int month = 0, day = 0, year;
		boolean done = false;
		int step = 0;

		while (!done)
		{
			try {
				switch(step)
				{
				case 0: 
					System.out.print("Enter the month as an integer: ");
					month = scan.nextInt();
				case 1:
					step = 1;
					System.out.print("Enter the day as an integer: ");
					day = scan.nextInt();
				case 2:
					step = 2;
					System.out.print("Enter the year as an integer: ");
					year = scan.nextInt();
					adate = new Date(month, day, year);
					done = true;
				} 
			}
			catch (InputMismatchException ime) {
				System.out.println("Invalid input entered. Enter an integer" + scan.nextLine());
			}
			catch (DateException ex) {
				System.out.println("DateException: " + ex.getMessage() + scan.nextLine()); 
			}
		}
		System.out.println(adate.toString());
	}
}
