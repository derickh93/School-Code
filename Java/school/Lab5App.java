package lab5;

/***
 * Title: Lab5App
 * Description: creates a new dice object called myTurn
 * calls various methods of the dice class on the object myTurn
 * and prints the corresponding results with the appropriate labels
 * 
 * @author Kuldeep Singh Hare
 *
 */
public class Lab5App {

	/**creates a public access, non object creating, program that searches  
	 * for the main function with no return type 
	 * 
	 * @param args the argument is passed to the Dice object 
	 */
	public static void main(String[] args) {
		
		Dice myTurn = new Dice();
		System.out.println("After instantiation: " + myTurn.toString());
		
		System.out.println(" ");
		int dieOneGet = myTurn.getDie1();
		System.out.println("Value of die 1: " + dieOneGet);
		System.out.println("Value of die 2: " + myTurn.getDie2());
		System.out.println("Value of die 3: " + myTurn.getDie3());
		
		System.out.println(" ");
		myTurn.roll();
		System.out.println("After rolling the dice: " + myTurn.toString());
		
		System.out.println(" ");
		System.out.println("The roll total is " + myTurn.calcTotalRoll());
		
		System.out.println(" ");
		System.out.println(myTurn.threeOfAKind());
		
		System.out.println(" ");
		System.out.println("The highest value of the three dice is " + myTurn.findHighestDie());
				
		System.out.println(" ");
		myTurn.rollOneDie(1);
		System.out.println(myTurn.toString());
		myTurn.rollOneDie(2);
		System.out.println(myTurn.toString());
		myTurn.rollOneDie(3);
		System.out.println(myTurn.toString());
		
		
		
	}
}
