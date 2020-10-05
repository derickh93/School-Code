import java.util.LinkedList;

/**
 * File: Radix.java
 * Description:Defines the Radix sort.
 * @author Derick Hansraj
 * N#: N00827531
 * source: http://zparacha.com/minimum-maximum-array-value-java
 * 		   https://www.geeksforgeeks.org/radix-sort/
 */
public class Radix {


	private static int getMax(int[] a) {

		// returns the maximum number from the array
		// almost exactly the same as getmin from the selection and bubble sort lab but this time you need the max value

		int max = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i] > max) {
				max = a[i];
			}
		}
		return max;
	}

	public  static void sort(int[] arrayToBeSorted)
	{
		// You will need at least the following variables:

		// an array of linked lists of size 10, up to you to determine what type
		// you may use the built in linked list from java.util
		LinkedList<Integer> [] list = new LinkedList[10];

		// http://steveo.us/howtos/java-ds.html
		// https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html


		// a var to keep track of the current radix
		int r = 1;



		/* 
		  basic steps for radix sort using linked lists:

		  go through numbers in arrayToBeSorted
		  start with the right most digit in the number (least significant digit), based on the digit add the number (not just the digit)
		  in the appropriate list in the linked list array. Do this with all the numbers.

		  Once done remove items from the linked list array in order and put them in an array

		  Go through that array again but now use the digit to the left (next significant digit)

		  repeat process as many times as the length of the max number

		 */

		for(int len = 0;len < list.length; len++) {
			list[len] = new LinkedList<Integer>();
		}

		int max = getMax(arrayToBeSorted);
		int length = (int) (Math.log10(max) + 1);

		for(int l = 0; l < length;l++) {

			int index = 0;
			for(int i = 0; i < arrayToBeSorted.length;i++) {
				index = (arrayToBeSorted[i] / r) % 10;
				list[index].add(arrayToBeSorted[i]);
			}

			int currIndex = 0;
			for(int e = 0; e < list.length;e++) {
				while(list[e].size() != 0) {
					arrayToBeSorted[currIndex] = list[e].removeFirst();
					currIndex++;
				}
			}
			r *= 10;
		}
	}	

	public static String printArray(int [] arr) {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < arr.length;i ++) {
			str.append(arr[i] + " ");
		}
		return str.toString();
	}
}
