package proj2;
import cardgames.*;

public class Project2 {
	public static void main(String[] args)
	{
		GUI theWindow = new GUI();
		Deck theDeck = new Deck();
		theDeck.shuffleDeck();
		Card card1 = theDeck.dealCard();
		System.out.println("Card 1 is: " + card1.toString());
		Card card2 = theDeck.dealCard();
		System.out.println("Card 2 is: " + card2.toString());
		
		theWindow.showCard(card1);
		theWindow.showCard(card2);
		
		if (card1.getValue() == card2.getValue()) 
			System.out.println("Pair");
		else
			System.out.println("Not a pair");
	}
}