package lab5;
/**
 * Title:lab5
 * Description:
 * @author Derick Hansraj
 *
 */
public class Dice {
		String dieOne;
		String dieTwo;
		String dieThree;
		private int dice1, dice2, dice3;

	public Dice(){
	
	dice1 = 0;
	dice2 = 0;
	dice3 = 0;
		
	}
	
		public String toString(){
		dieOne = Integer.toString(dice1);
		dieTwo = Integer.toString(dice2);
		dieThree = Integer.toString(dice3);
		String returnMe = new String( "After instantiation: " + dieOne + " " + dieTwo + " " + dieThree);
		return returnMe;
		}
		
		public String getDieOne() {
		return dieOne;
		}
		
		public String getDieTwo() {
			return dieTwo;
		}
			
		public String getDieThree() {
				return dieThree;
		}
		
		public String roll() {
			dice1=(int)(Math.random() * 6 + 1);
			dice2=(int)(Math.random() * 6 + 1);
			dice3=(int)(Math.random() * 6 + 1);
			dieOne = Integer.toString(dice1);
			dieTwo = Integer.toString(dice2);
			dieThree = Integer.toString(dice3);
			String returnMe = new String( "After instantiation: " + dieOne + " " + dieTwo + " " + dieThree);
			return returnMe;
		}
		/**
		* calcTotalRoll - This method will calculate the sum of the values
		* rolled on the three dice
		* @return
		*/
		
		public int calcTotalRoll() {
			return (dice1 + dice2 +dice3);
		}
		/**
		2 * threeOfAKind method - this method will determine if all three
		3 * of the dice have the same value
		4 * @return true if they have the same value, false otherwise
		5 */
		
		public String threeOfAKind() {
			if ((dice1 == dice2) && (dice2 == dice3) && (dice1 == dice3))
			return ("Three of a kind.");
			else
			return ("Not three of a kind.");
			
		}
			
		}
		
		
	
	
