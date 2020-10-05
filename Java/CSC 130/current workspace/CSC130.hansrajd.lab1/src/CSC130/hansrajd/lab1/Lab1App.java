package CSC130.hansrajd.lab1;
/**
 * Title: CSC130hansrajd.lab1
 * Filename: Lab1App.java
 * Date Written: September 14, 2017
 * Due Date: September 16, 2017
 * Description: To use the methods created in the Matrix.java class to 
 * test the various methods on a Matrix.
 * 
 *@author Derick Hansraj
 */

public class Lab1App {

	public static void main(String[] args) {
		//create new multidimensional arrays 
		int[][] a = {{1,2}, {5,3}};
		int[][] b = {{3,2}, {4,5}};
		int[][] c = {{1,2}, {4,3}};
		int[][] d = {{1,2,3}, {4, 5, 6}};
		int[][] e = {{1, 4}, {2, 5}, {3,6}};


		//create new matrices using multidimensional arrays to get a size
		Matrix m = new Matrix(a);
		Matrix m2 = new Matrix(b);
		Matrix m3 = new Matrix(c);
		Matrix m4 = new Matrix(d);
		Matrix m5 = new Matrix(e);



		//Example of matrix addition
		System.out.println("Example of Matrix addition using the following matrices:");
		System.out.println(m.toString());
		System.out.println(m2.toString() + "\nSUM:");
		System.out.println(m.add(m2));
		
		//Example of matrix subtraction
		System.out.println("Example of Matrix subtraction using the following matrices:");
		System.out.println(m.toString());
		System.out.println(m2.toString() + "\nDIFFERENCE:");
		System.out.println(m.subt(m2));

		//Example of matrix multiplication
		System.out.println("Example of Matrix multiplication using the following matrices:");
		System.out.println(m3.toString());
		System.out.println(m2.toString() + "\nPRODUCT:");
		System.out.println(m2.mult(m3));
		
		//Example of scalar multiplication
		System.out.println("Example of scalar multiplication using the following matrices with a scalar value of 2:");
		System.out.println(m3.toString() + "\nSCALAR PRODUCT:");
		System.out.println(m3.scalarMult(2));
		
		//Example of transpose 
		System.out.println("Example of transpose using the following matrix:");
		System.out.println(m.toString() + "\nTRANSPOSE:");
		System.out.println(m.transpose());
		
		//Example of transpose 
		System.out.println("Example of transpose using the following matrix:");
		System.out.println(m4.toString() + "\nTRANSPOSE:");
		System.out.println(m4.transpose());
		
		//Example of equality 
		System.out.println("Example of equality using the following matrices:");
		System.out.println(m.toString());
		System.out.println(m.toString() + "\nEQUALITY:");
		System.out.println(m.equality(m));
		
		//Example of equality 
		System.out.println("\nExample of equality using the following matrices:");
		System.out.println(m4.toString());
		System.out.println(m5.toString() + "\nEQUALITY:");
		System.out.println(m4.equality(m5));

	}
}
