package matrices;
/**
 * <b>Title:</b> Lab 1:<br> 
 * <b>Filename:</b> Matrix.java<br>
 * <b>Date Written:</b> February 7, 2017<br>
 * <b>Due Date:</b> February 18, 2017<br>
 * <p>
 * <b>Description:</b><br>
 *  Implements a Matrix class using 2-dimensional M x N integer array.<br>
 * </p>
 * <p>
 *  It performs the following operations: <br>
 *   Addition, Subtraction: <br>
 *   - the matrices must have the same dimensions<br>
 *   Multiplication: <br>
 *   - the number of columns in the 1st matrix must match the number of rows in the 2nd<br>
 *   Equality:<br>
 *   - determines if the matrices are identical<br>
 *   Transposition: <br>
 *   - rotates a matrix so the rows become columns and the columns become rows<br>
 *   Scalar Multiplication: <br>
 *   - the entire matrix is multiplied by a scalar value<br>
 * </p>
 *@author Derick Hansraj
 */
 
public class Matrices {
	private int m;
	private int n;
	private int[][] temp;
	public Matrices(int m, int n) {
		this.m = m;		
		this.n = n;	
		temp = new int [m][n];
	}
	public void addMatrice(int[][] temp2) {
		int [][] temp3 = new int [m][n];
		for (int i = 0; i < temp.length; i++) {
		    for (int j = 0; j < temp[i].length; j++) {
		    	temp3[i][j] = temp[i][j] + temp2[i][j];
		    }
		}
	}
	public void subMatrice(){
		
	}
	public void equality(){
		
	}
	public void transposition() {
		
	}
	public void multMatrice() {
		
	}
	public void scalarMatrice() {
		
	}
	public String toString(){
		String s1 = new String();
		for (int i = 0; i < temp.length; i++) {
		    for (int j = 0; j < temp[i].length; j++) {
		        s1 += temp[i][j] + " ";
		    }
		    s1 += "\n";
		}
		return s1;
	}
	
}
