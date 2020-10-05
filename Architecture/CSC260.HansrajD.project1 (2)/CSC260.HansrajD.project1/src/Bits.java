import java.util.Scanner;

/**
 * Title: CSC260.hansrajD.project1
 * Filename: Bits.java
 * Date Written: January 28, 2017
 * Due Date: February 03, 2017
 * Description:
 * This class represents a program that will take user input that should match an encoded version of "hans." The 
 * desired input is "0163". The first method will take input and if the characters don't meet the requirements the 
 * program will loop until a valid input is entered. There is another method that will check to see if the input is
 * an exact match and return the number of letters that are an exact match. The last method will return the amount 
 * of digits that are a semi-match. The main method will loop until the user enters the correct input. Once the user 
 * enters the correct input a message will be displayed stating that the user has won, along with the amount of attempts it took.
 *@author Derick Hansraj
 */
public class Bits {
	//instance variables
	static String myName;;



	/**
	 * encode -method that ask the user for a string that is 4 characters long and is between 0 and 7.
	 * @return a String representing the valid input desired.
	 */
	static String input() {
		myName = "3052";
		System.out.println("Enter the code for the first 4 letters of your last name:\n");
		Scanner s = new Scanner(System.in);
		String encodedStr = s.nextLine();

		while(encodedStr.length() != 4 || !encodedStr.matches("^[01234567]+$")) {
			System.out.println("Invalid Input \nEnter the code for the first 4 letters of your last name:\n");
			encodedStr = s.nextLine();
		}
		return encodedStr;
	}

	/**
	 * exactMatch -method that tells the user how many of the digits are an exact match.
	 * @param s- String that will be tested for an exact match.
	 * @return an integer representing the number of digits that are an exact match.
	 */
	static int exactMatch(String s) {
		int count = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == myName.charAt(i))
				count++;
		}
		return count;
	}

	/**
	 * semiMatch -method that tells the user the amount of character that match somewhere.
	 * @param s- String that will be tested for characters that match somewhere.
	 * @return an integer representing the number of digits that are an exact match.
	 */
	static int semiMatch(String s) {
		int count = 0;
		for(int i = 0; i < s.length(); i++) {
			for(int j = 0; j < s.length(); j++) 
				if(s.charAt(i) == myName.charAt(j))
					count++;
		}
		return count;
	}


	//main method
	public static void main (String [] args) {
		//instance variables
		int attempts = 1;
		boolean loop = true;

		//while loop that will loop until the user enters the desired input
		while(loop) {
			String param = input();
			if(exactMatch(param) == 4) {
				System.out.println("You have won\nIt only took " + attempts + " attempt. ");
				loop = false;
			}
			else {

				System.out.println("Amount of characters in the correct location: " + exactMatch(param) + "\n"
						+ "Amount of characters that are a match somewhere: " + semiMatch(param) + "\n\nWrong answer, try again!");
				attempts++;
			}
		}
	}
}
