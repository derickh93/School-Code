package proj1;
import java.util.Scanner;
/**
 * Title:proj1
 * Description: This program will take the user input which is the full name.
 * Then it will break it down and save it into variables.
 * Then it will ask for user midterm grade which it also stores.
 * Then it will ask for user final grade which will be stored again.
 * Once all three inputs are entered it will display user name. Followed by
 * the amount of characters in the user first name, last name and full name.
 * Then the users first and last initial. Next display the users midterm grade
 * and final grade. Finally it will display the users average grade.
 * @author Derick Hansraj
 *
 */
public class Project1 {

	/**Main is the method that executes when you run a class.
	 * 
	 * @param args The program is executed with the help of the scanner.
	 */
		
    public static void main(String [] args ) {
		Scanner s = new Scanner(System.in);
	/** @param The scanner s goes to the next line after each input.
	 */
		String firstName;
		String lastName;
		String name;
		String firstInitial;
		String lastInitial;
		int countFirst;
		int countLast;
		int countFull;
		int positionOfSpace;
		String midGrade;
		String finalGrade;
		double average;
		int mg;
		int fg;
		double dmg;
		double dfg;

		System.out.println( " Please enter your first name, followed by a space, followed by your last name: ");
		name = s.nextLine();
		positionOfSpace = name.indexOf(' ');
		firstName = name.substring(0, positionOfSpace);
		lastName = name.substring(positionOfSpace + 1);
		countFull = name.length() - 1;
		countFirst = firstName.length();
		countLast = lastName.length();
		firstInitial = firstName.substring(0, 1);
		lastInitial = lastName.substring(0, 1);
		
		System.out.println( " Please enter your midterm grade: ");
		midGrade = s.nextLine();
		mg = Integer.parseInt(midGrade);
			
		System.out.println( " Please enter your final exam grade: ");
		finalGrade = s.nextLine();
		fg = Integer.parseInt(finalGrade);
		
		dmg = (double) mg;
		dfg = (double) fg;
		average = ((dmg + dfg) / 2);
			
		System.out.println("Name: " + lastName + ", " + firstName);
		System.out.println("");
		System.out.println("There are " + countFirst + " letters in your first name");
		System.out.println("There are " + countLast + " letters in your last name");
		System.out.println("There are " + countFull + " letters in your full name");		
		System.out.println("");
		System.out.println("Your initials are " + firstInitial + " and " + lastInitial);
		System.out.println("");
		System.out.println("Your midterm grade is " + mg);
		System.out.println("Your final exam grade is " + fg);
		System.out.println("");
		System.out.println("Your exam average is " + average);
	}	
}