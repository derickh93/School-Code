package CSC130.hansrajd.lab1;
/**
 * Title: CSC130hansrajd.lab1
 * Filename: Matrix.java
 * Date Written: February 10, 2017
 * Due Date: February 18, 2017
 * Description:
 * Implements a Matrix class using 2-dimensional M x N integer array.
 * It performs the following operations: 
 * Addition, Subtraction: 
 * - the matrices must have the same dimensions
 * Multiplication: 
 * - the number of columns in the 1st matrix must match the number of rows in the 2nd
 * Equality:
 * - determines if the matrices are identical
 * Transposition: 
 * - rotates a matrix so the rows become columns and the columns become rows
 * Scalar Multiplication:
 * - the entire matrix is multiplied by a scalar value
 *@author Derick Hansraj
 */

final public class Matrix {

	//instance variables
	private int m;
	private int n;             
	private double[][] matrix1;

	/**
	 * parameterized constructor -- The Matrix is given attributes 
	 * based upon the arguments sent. 
	 * @param m - the rows in the Matrix.
	 * @param n - the columns in the Matrix.
	 * **/
	public Matrix(int m, int n) {
		this.m = m;
		this.n = n;
		matrix1 = new double[m][n];
	}

	/**
	 * parameterized constructor -- The Matrix is given attributes 
	 * based upon the arguments sent. 
	 * @param matrix1 - the matrix with specified rows and columns.
	 * **/
	public Matrix(double[][] matrix1) {
		m = matrix1.length;
		n = matrix1[0].length;
		this.matrix1 = new double[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				this.matrix1[i][j] = matrix1[i][j];
			}
		}
	}

	/**
	 * addMatrix method --
	 * adds the two selected Matrix together.
	 * @param matrix2 one of the Matrix to be added.
	 * @return a Matrix which is the result of the addition of two selected Matrix
	 */
	public Matrix addMatrix(Matrix matrix2) {
		Matrix first = this;
		Matrix temp = new Matrix(m, n);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				temp.matrix1[i][j] = first.matrix1[i][j] + matrix2.matrix1[i][j];
			}
		}
		return temp;

	}

	/**
	 * subMatrix method --
	 * subtracts the two selected Matrix from each other.
	 * @param matrix3 one of the Matrix to be subtracted.
	 * @return a Matrix which is the result of the subtraction of two selected Matrix
	 */
	public Matrix subMatrix(Matrix matrix3) {
		Matrix first = this;
		Matrix temp = new Matrix(m, n);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				temp.matrix1[i][j] = first.matrix1[i][j] - matrix3.matrix1[i][j];
			}
		}
		return temp;
	}

	/**
	 * equalMatrix method --
	 * determines whether or not the two Matrix are equal.
	 * @param matrix4 one of the Matrix to compared.
	 * @return true or false depending on whether two Matrix are equal.
	 */	
	public boolean equalMatrix(Matrix matrix4) {
		Matrix first = this;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (first.matrix1[i][j] != matrix4.matrix1[i][j]) return false;
			}
		}
		return true;
	}

	/**
	 * multMatrix method --
	 * multiplies two Matrix together.
	 * @param matrix5 one of the Matrix to be multiplied.
	 * @return a Matrix which is the result of the multiplication of two selected Matrix.
	 */	
	public Matrix multMatrix(Matrix matrix5) {
		Matrix first = this;
		Matrix temp = new Matrix(first.m, matrix5.n);
		if(first.n != matrix5.m)
			return null;
		for (int i = 0; i < temp.m; i++) {
			for (int j = 0; j < temp.n; j++) {
				for (int k = 0; k < first.n; k++) {
					temp.matrix1[i][j] += (first.matrix1[i][k] * matrix5.matrix1[k][j]);
				}
			}
		}
		return temp;
	}

	/**
	 * scalMatrix method --
	 * multiplies the Matrix with a scalar value.
	 * @param num the quantity to multiply the Matrix by.
	 * @return a Matrix which is the result of the scalar multiplication of two selected Matrix
	 */
	public Matrix scalMult(int num) {
		Matrix first = this;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				first.matrix1[i][j] = ((first.matrix1[i][j]) * 2);
			}
		}
		return first;       
	}

	/**
	 * transpose method --
	 * flips the rows and columns in the Matrix.
	 * @return a Matrix which is the result the selected Matrix being transposed.
	 */
	public Matrix transpose() {
		Matrix first = new Matrix(n, m);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				first.matrix1[j][i] = this.matrix1[i][j];
			}
		}
		return first;
	}

	/**
	 * toString --
	 * Returns a string representing the current state of the Matrix object.
	 * @return the Matrix as a string of its rows and columns.
	 */
	public String toString() {
		String s1 = new String();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(this.matrix1[i][j] + " ");
			}
			System.out.println();
		}
		return s1;
	}
}
