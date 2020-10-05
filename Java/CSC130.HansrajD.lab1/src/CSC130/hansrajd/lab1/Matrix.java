package CSC130.hansrajd.lab1;

/**
 * Title: CSC130hansrajd.lab1
 * Filename: Matrix.java
 * Date Written: September 14, 2017
 * Due Date: September 16, 2017
 * Description:
 * Implements a Matrix class using a 2-dimensional array.
 * It performs the following operations: 
 * Addition, Subtraction, Multiplication, Equality, Transposition
 * , and Scalar Multiplication:
 *@author Derick Hansraj
 */

public class Matrix {

	//instance variables
	private int[][] m;

	/**
	 * parameterized constructor - sets the size of the matrix
	 * 
	 * @param m -determines the amount of rows and columns in the Matrix
	 */
	public Matrix(int[][] m) {
		int length = m[0].length;
		boolean valid = true;

		for(int r = 0; r < m.length && valid; r++) {
			if(m[r].length != length) {
				m = null;
				valid = false;
			}
		}
		if(valid) {
			this.m = new int[m.length][m[0].length];
			for(int r =0;r<m.length; r++)
				for(int c=0; c<m[r].length;c++)
					this.m[r][c] = m[r][c];
		}
	}

	/**
	 * add -method that adds two Matrix together if they have the same
	 * amount of rows and columns.
	 * 
	 * @param otherMatrix -A matrix that will be added to another matrix
	 * 
	 * @return A new matrix representing the sum of two specified matrices.
	 */
	public Matrix add(Matrix otherMatrix) {
		int[][] temp = new int[m.length][m[0].length];
		for(int i=0;i < m.length; i++){
			for(int j=0;j < m[i].length; j++){
				temp[i][j] = m[i][j] + otherMatrix.m[i][j];
			}
		}
		Matrix m2 = new Matrix(temp);
		return m2;

	}

	/**
	 * subt -method that differentiates two matrices from each other if they have the same
	 * amount of rows and columns.
	 * 
	 * @param otherMatrix -A matrix that will be deducted from another matrix
	 * 
	 * @return A new matrix representing the difference of two specified matrices.
	 */
	public Matrix subt(Matrix otherMatrix) {
		int[][] temp = new int[m.length][m[0].length];
		for(int i=0;i < m.length; i++){
			for(int j=0;j < m[i].length; j++){
				temp[i][j] = m[i][j] - otherMatrix.m[i][j];
			}
		}
		Matrix m2 = new Matrix(temp);
		return m2;
	}

	/**
	 * mult -method that multiplies two matrices together if the first Matrix has the same
	 * amount of columns as the rows in the second matrix.
	 * 
	 * @param otherMatrix -A matrix that will be multiplied by another matrix.
	 * 
	 * @return A new matrix representing the product of two specified matrices using the rules
	 * of Matrix multiplication.
	 */
	public Matrix mult(Matrix otherMatrix) {
		int m1 = m.length;
		int n1 = m[0].length;
		int m2 = otherMatrix.m.length;
		int n2 = otherMatrix.m[0].length;
		if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
		int[][] c = new int[m1][n2];
		for (int i = 0; i < m1; i++)
			for (int j = 0; j < n2; j++)
				for (int k = 0; k < n1; k++)
					c[i][j] += m[i][k] * otherMatrix.m[k][j];
		Matrix temp = new Matrix(c);
		return temp;
	}

	/**
	 * scalarMult -method that multiplies a matrix by a chosen scalar amount.
	 * 
	 * @param scalar -A number that will be used to get the scalar value of a matrix.
	 * 
	 * @return A new matrix representing the scalar value of a chosen matrix.
	 */
	public Matrix scalarMult(int scalar) {
		int[][] temp = new int[m.length][m[0].length];
		for(int i=0;i < m.length; i++){
			for(int j=0;j < m[i].length; j++){
				temp[i][j] = m[i][j] * scalar;
			}
		}
		Matrix m2 = new Matrix(temp);
		return m2;
	}

	/**
	 * transpose -method that switches the rows and columns of a chosen matrix.
	 * 
	 * @return A new matrix representing the transposed matrix.
	 */
	public Matrix transpose() {
		int m = this.m.length;
		int n = this.m[0].length;
		int[][] b = new int[n][m];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				b[j][i] = this.m[i][j];

		Matrix m2 = new Matrix(b);
		return m2;
	}

	/**
	 * equality -method that determines the equality of two matrices.
	 * 
	 * @param otherMatrix -A matrix that will be compared to another matrix.
	 * 
	 * @return True if the matrices are equal, else false if they are not..
	 */
	public boolean equality(Matrix otherMatrix) {
		boolean temp = false;
		if((m.length != otherMatrix.m.length) || (m[0].length != otherMatrix.m[0].length))
			return temp;
		else if((m.length == otherMatrix.m.length) && (m[0].length == otherMatrix.m[0].length))
		for(int i=0;i < m.length; i++){
			for(int j=0;j < m[i].length; j++){
				if(m[i][j] == otherMatrix.m[i][j])
					temp = true;
				else 
					temp = false;
			}
		}
		return temp;
	}

	/**
	 * toString - returns the matrix as a string
	 * 
	 * @return a String containing the matrix in order
	 */
	public String toString() {
		for(int i=0;i < m.length; i++){
			for(int j=0;j < m[i].length; j++){
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
		return "";
	}

}
