/**
 * Assignment #7
 * CSCI 370
 * @author Derick Hansraj
 * 12/4/2019
 */
package edu.qc.seclass;

public class BuggyClass {

	public static int buggyMethod1(int x, int y) {

		int result = x/y;

		if (result > 0) {
			return result;
		}
		else {
			x++;
			y++;
			result*= x;
			result *= y;
		}
		
		return 0;
	}

	public static int buggyMethod2(int x, int y) {
		int result = x/y;

		if (result > 1) {
			return result;
		}
		else {
			x++;
			y++;
			result*= x;
			result *= y;
		}
		
		return 0;
	}

	public static int buggyMethod3(int x, int y) {
		if(y != 0)
			x = x/y;
		y = x-y;
		return x/y;
	}

	public static void buggyMethod4(int x, int y) {
		/**	
		 It is impossible to both get 100% statement coverage that reveals a fault 
		 and also get a 100% branch coverage that does not reveal the fault.  Full branch coverage
		 means that there is also full statement coverage. This does not work both ways. There can be 
		 100% statement coverage without 100% branch coverage. Therefore if we do not have a fault at 100% branch 
		 coverage we cannot have one at 100% statement coverage.This contradicts what we want to find.
		 */
	}

	public static void buggyMethod5(int x, int y) {
		/**
        	It is also impossible to create this method because to both get 100% statement coverage while not
        	avoiding the division by 0 is impossible. We are not allowed to make any drastic changes to the code. If statements
        	would not work because there would leave the case of not having 100% statement coverage.
		 */
	}

}
