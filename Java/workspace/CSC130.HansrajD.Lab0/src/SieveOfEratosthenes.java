
/**
 * Title: Lab 0
 * Filename: SieveOfEratosthenes.java
 * Date Written: September 7, 2017
 * Due Date: September 9, 2017
 * 
 * Description: 
 * Displays prime numbers using The Sieve of Eratosthenes.
 * 
 * The user is permitted to enter a value for n, and then all prime numbers in the range 2 to n are displayed.
 * 
 * Algorithm
 * 
 * We start with a table of numbers (e.g., 2,3,4,5,...,n) and progressively 
 * cross off numbers in the table until the only numbers left are primes.
 * Specifically, we begin with the first number, p, in the table, and
 * 1. Declare p to be prime, then display it
 * 2. Cross off all the multiples of that number in the table, starting from p^2
 * 3. Find the next number in the table after p that is not yet crossed off and
 * set p to that number; and then repeat steps 1 to 3.
 * 
 * @author Derick Hansraj
 */
import java.util.Scanner;

public class SieveOfEratosthenes {

	public static void main(String[] args){

		// variables
		int n, p = 0;
		Scanner scanner = new Scanner(System.in);
		String prime = "";

		//User inputs a number
		System.out.println("Please Enter a Number: ");
		n = scanner.nextInt();

		//An array is created to store the numbers 2 thru n
		int[] primeArray = new int[n];
		for(int i = 0; i < n; i++){
			primeArray[i] = 2+i;

		}

		//P is declared to be the first Prime (since we're using the range of 2 through n, the first 
		//index of the array will always be the first prime number) The loop below removes any number 
		//that is divisible by 2 and replace it with a 0 to mark that it is not a prime number.
		p = primeArray[0];
		for(int i = 1; i < n; i++){
			if(primeArray[i] % p == 0)
				primeArray[i] = 0;
		}


		//This for loop runs thru every number after the first in the array, checking each index.
		//If an index of the array is equal to 0, the numbers are already known to not be prime.
		//The loop searches for the next index of the array that does not have a value of 0 and
		//compares it with the rest of the array.
		for(int i = 1; i < n; i++){
			if(primeArray[i] != 0)
				p = primeArray[i];
			for(int j = i + 1; j < n; j++)
				if(primeArray[j] % p == 0)
					primeArray[j] = 0;
		}

		//A string is created to display any number that was not changed to 0 (which is therefore not prime)
		for(int i = 0; i < primeArray.length-1; i++)
		{
			if(primeArray[i] != 0){
				prime += primeArray[i] + " ";
			}
		}

		//Output all the prime numbers between 2 and n
		System.out.println("\nPrime Numbers in the range 2 to" + " " + n + "\n" + prime + "\nEnd of Program");
	}
}
