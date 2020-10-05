package lab5;
/**
 * Title:lab5
 * Description: 
 * @author Derick Hansraj
 *
 */

public class Lab5App {
	
	public static void main(String [] args) {
		
		Dice die = new Dice();
		
		System.out.println(die.toString());
		System.out.println("");
		System.out.println("Value of die 1: " + die.getDieOne());
		System.out.println("Value of die 2: " + die.getDieTwo());
		System.out.println("Value of die 3: " + die.getDieThree());
		System.out.println("");
		System.out.println("After rolling the die:" + die.roll());
		System.out.println(die.calcTotalRoll());
		System.out.println(die.threeOfAKind());

	}
}
