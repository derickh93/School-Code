/**
 * Title: program1
 * Filename: Program1App.java
 * Date Written: Redone April 26, 2017
 * Due Date: March 5, 2017
 * Description: This class will use the methods created in the Sudoku class to read information from a file
 * and evaluate whether or not a Sudoku is represented.
 * @author Derick Hansraj
 */
public class Program1App {
	/**
	 * <p> Name: main method </p>
	 * 
	 * @param args values to be sent to the method
	 */
	public static void main (String [] args) {
		// Create a Sudoku object called s1
		Sudoku s1 = new Sudoku();
		//Fill the Sudoku object from a file
		s1.fillGrid();
		//Display the Sudoku object to the user
		s1.show();
		//Display to the console whether the grid represents a Sudoku or not.
		if(s1.evaluate() == true)
			System.out.println("The grid represents a Sudoku.");
		else
			System.out.println("The grid does not represents a Sudoku.");
	}
}
