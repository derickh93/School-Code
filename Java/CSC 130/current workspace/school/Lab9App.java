package lab9;

/**
 * <p>Title: The Lab9App class</p>
 *
 * <p>Description: takes a deck of cards, shuffles it, deals 7 cards to make
 * a hand. The hand is printed. Then a copy of the hand is made and printed.
 * the copy's first card (in position 0) is replaced with 4 of hearts, copy 
 * of hand is printed. then original hand is printed. Then card 1 (card in 
 * position 0) is replaced with a deck card.</p>
 * 
 * @author Kuldeep Singh Hare
 */
public class Lab9App {

	/**
	 * main method - executes the following code when run
	 * @param args - arguments to be passed as parameters to the 
	 * main method
	 */
	public static void main(String[] args) {
		
		// create and shuffle the deck
		Deck theDeck = new Deck();
		theDeck.shuffleDeck();
		
		// instantiate a Hand object
		Hand myHand = new Hand();
		
		// insert 7 cards in the hand
		Card theCard;
		for (int i = 0; i < 7; i++) {
			
			theCard = theDeck.dealCard();
			myHand.insertCard(theCard);				
		}
		
		System.out.println("Display the cards in the hand: \n" 
				+ myHand.toString());
			
		String copyCards = new String();
		for (int i = 0; i < 7; ++i) {
			copyCards = copyCards + myHand.getCards()[i] + "\n";
		}
		System.out.println("Get a copy of the cards array, and display " 
				+ "the contents of the copy: \n" + copyCards);
			
		Card fourHearts = new Card(30);
		Card[] myCopy = myHand.getCards();
		myCopy[0] = fourHearts;
		String copyCards2 = new String();
		for (int i = 0; i < 7; ++i) {
			copyCards2 = copyCards2 + myCopy[i] + "\n";
		}
		System.out.println("Changing the first card in the copy to the " 
				+ "4 of hearts: \n" + copyCards2);
		
		System.out.println("Display the cards in the hand: \n" 
				+ myHand.toString());
				
		Card newCard = theDeck.dealCard();
		myHand.replaceCard(0, newCard);
		System.out.println("Replacing the card in position 0 with a deck"
				+ " card and display the cards in the hand: \n" + myHand.toString());
		
		Card aceSpades = new Card(40);
		if (myHand.searchCard(aceSpades))
			System.out.println("The hand has the " + aceSpades + ".");
		else 
			System.out.println("The hand does not have the " + aceSpades + ".\n");
		
		System.out.println("There are " + myHand.findNumFaceCards() 
			+ " face card(s) in the hand.\n");
					
		Card newCard2 = theDeck.dealCard();
		myHand.replaceLowCard(newCard2);
		System.out.println("Replacing the lowest card with a deck card and display "
				+ "the cards in the hand: \n" + myHand.toString());
		
	}
	
}