package CSC130.hansrajd.lab1;
/**
 * Title: CSC130hansrajd.lab1
 * Filename: Lab1App.java
 * Date Written: February 10, 2017
 * Due Date: February 18, 2017
 * Description: To use the methods created in the Matrix.java class to 
 * test the various methods on a Matrix.
 * </p>
 *@author Derick Hansraj
 */
public class Lab1App {
	public static void main (String [] args) {

		//instantiating multiple Matrix to test cases from lab sheet
		System.out.println("--------PART 1--------");
		double[][] a = { { 1, 2}, { 5, 3} };
		Matrix A = new Matrix(a);

		double[][] b = { { 3, 2}, { 4, 5} };
		Matrix B = new Matrix(b);

		double[][] c = { { 1, 2}, { 4, 3} };
		Matrix C = new Matrix(c);

		double[][] d = { { 3, 2}, { 4, 5} };
		Matrix D = new Matrix(d);

		double[][] e = { { 1, 2}, { 5, 3} };
		Matrix E = new Matrix(e);

		double[][] f = { { 1, 2, 3}, { 4, 5, 6} };
		Matrix F = new Matrix(f);

		double[][] g= { { 1, 5}, { 2, 3} };
		Matrix G = new Matrix(g);

		double[][] h= { { 1, 4}, { 2, 5}, {3, 6} };
		Matrix H = new Matrix(h);

		//Testing multiple methods on multiple declared Matrix
		System.out.println(A.addMatrix(B));
		System.out.println(A.subMatrix(B));
		System.out.println(C.multMatrix(D));
		System.out.println(C.scalMult(2));
		System.out.println(E.transpose());
		System.out.println(F.transpose());
		System.out.println(A.equalMatrix(E));
		System.out.println(F.equalMatrix(H));
		
		//Part 2 
		System.out.println("----------PART 2---------");
		
		System.out.println("1.");
		System.out.println(A.transpose().transpose());
		System.out.println(A.toString());
		System.out.println("2.");
		System.out.println(A.addMatrix(B).transpose());
		System.out.println(A.transpose().addMatrix(B.transpose()));
		System.out.println("3.");
		System.out.println(A.scalMult(2).transpose());
		System.out.println(A.transpose().scalMult(2));
		System.out.println("4.");
		System.out.println(A.multMatrix(B).transpose());
		System.out.println(B.transpose().multMatrix(A.transpose()));
		System.out.println("5.");
		System.out.println(A.multMatrix(B));
		System.out.println(B.multMatrix(A));
		System.out.println("6.");
		System.out.println(A.multMatrix(B.multMatrix(C)));
		System.out.println(A.multMatrix(B).multMatrix(C));
		System.out.println("7.");
		System.out.println(A.multMatrix(A.addMatrix(C)));
		System.out.println(A.multMatrix(B).addMatrix(A.multMatrix(C)));
		System.out.println("8.");
		System.out.println(A.addMatrix(B).multMatrix(C));
		System.out.println(A.multMatrix(C).addMatrix(B.multMatrix(C)));
		System.out.println("9.");
		System.out.println(A.scalMult(2).multMatrix(B));
		System.out.println(A.multMatrix(B).scalMult(2));
		System.out.println(A.multMatrix(B.scalMult(2)));
		
		//instantiating multiple Matrix to test cases from lab sheet
		System.out.println("--------PART 2 Section B--------");
		double[][] i = { { 1, -2, 3}, { 1, -1, 0} };
		Matrix I = new Matrix(i);

		double[][] j = { { 3, 4}, { 5, -1}, {1, -1} };
		Matrix J = new Matrix(j);

		double[][] k = { { 4, -1, 2}, { -1, 5, 1} };
		Matrix K = new Matrix(k);

		double[][] l = { { -1, 0, 1}, { 0, 2, 1} };
		Matrix L = new Matrix(l);

		double[][] m = { { 3, 4}, { -2, 3}, {0, 1} };
		Matrix M = new Matrix(m);

		double[][] n = { { 2, 3,} };
		Matrix N = new Matrix(n);

		double[][] o = { {2, -1} };
		Matrix O = new Matrix(o);
		
		System.out.println("(a)");
		System.out.println(C.scalMult(3).subMatrix(D.scalMult(4)));
		System.out.println("(b)");
		System.out.println(A.subMatrix(D.addMatrix(C.scalMult(2))));
		System.out.println("(c)");
		System.out.println(A.subMatrix(E));
		System.out.println("(d)");
		System.out.println(A.multMatrix(E));
		System.out.println("(e)");
		System.out.println(B.multMatrix(C).scalMult(3).subMatrix(B.multMatrix(D).scalMult(4)));
		System.out.println("(f)");
		System.out.println(C.multMatrix(B).addMatrix(D));
		System.out.println("(g)");
		System.out.println(G.multMatrix(C));
		System.out.println("(h)");
		System.out.println(F.multMatrix(G));
		System.out.println("(i)");
		System.out.println(C.multMatrix(C));
		System.out.println("(j)");
		System.out.println(C.addMatrix(D));

	}
}
