package lab7;

/**
 * <p>Title: The Lab7App class</p>
 *
 * <p>Description: This class will be used to thoroughly test the 
 * various methods in the PokerHand class.  The seven cards specified 
 * will be used to test each method.</p>
 *
 * @author Derick Hansraj
 */
public class Lab7App
{
    /**
     * <p> Name: main method </p>
     * 
     * @param args values to be sent to the method
     */
	public static void main(String[] args)
	{
		testFourOfAKind();
		testFlush();
		testThreeOfAKind();
		testPair();
		testLowCard();
    }
    
    /**
     * <p> Name: testFourOfAKind method </p>
     * 
     * <p> Description: tests the fourOfAKind method in the PokerHand class.</p>
     */
	public static void testFourOfAKind()
	{
		// cards to be used to test the various methods
		// in the PokerHand class
		Card twoClubs = new Card(2);
		Card twoDiamonds = new Card(15);
		Card twoHearts = new Card(28);
		Card twoSpades = new Card(41);
		Card threeClubs = new Card(3);
		Card fourClubs = new Card(4);
		Card fiveClubs = new Card(5);
		PokerHand hand1 = new PokerHand(twoClubs, twoDiamonds, twoHearts, twoSpades);
		System.out.println("Testing FourofAKind method: \n" + hand1.toString());
		if (hand1.fourOfAKind())
			System.out.println("Four of A Kind");
		else 
			System.out.println("Not Four of A Kind");
		System.out.println("");
		hand1.replaceCard(2, threeClubs);
		System.out.println("Replaced the 2 of diamonds with the 3 of clubs: \n" + hand1.toString());
		if (hand1.fourOfAKind())
			System.out.println("Four of A Kind");
		else 
			System.out.println("Not Four of A Kind");
	}
	
    /**
     * <p> Name: testFlush method </p>
     * 
     * <p> Description: tests the flush method in the PokerHand class.</p>
     */
	public static void testFlush()
	{
		// cards to be used to test the various methods
		// in the PokerHand class
		Card twoClubs = new Card(2);
		Card twoDiamonds = new Card(15);
		Card twoHearts = new Card(28);
		Card twoSpades = new Card(41);
		Card threeClubs = new Card(3);
		Card fourClubs = new Card(4);
		Card fiveClubs = new Card(5);
		System.out.println("");
		PokerHand hand3 = new PokerHand(twoClubs, twoDiamonds, twoHearts, twoSpades);
		hand3.replaceCard(2, threeClubs);
		System.out.println("Testing flush method: \n" + hand3.toString());
		if (hand3.flush())
			System.out.println("Flush");
		else 
			System.out.println("Not a flush");
		System.out.println("");
		hand3.replaceCard(2, threeClubs);
		hand3.replaceCard(3, fourClubs);
		hand3.replaceCard(4, fiveClubs);
		System.out.println("Replaced the 2 of hearts with the 4 of clubs and \nthe 2 of spades with the 5 of clubs: \n" + hand3.toString());
		if (hand3.flush())
			System.out.println("Flush");
		else 
			System.out.println("Not a flush");
		
	}
	
    /**
     * <p> Name: testThreeOfAKind method </p>
     * 
     * <p> Description: tests the threeOfAKind method in the PokerHand class.</p>
     */
	public static void testThreeOfAKind()
	{
		// cards to be used to test the various methods
		// in the PokerHand class
		Card twoClubs = new Card(2);
		Card twoDiamonds = new Card(15);
		Card twoHearts = new Card(28);
		Card twoSpades = new Card(41);
		Card threeClubs = new Card(3);
		Card fourClubs = new Card(4);
		Card fiveClubs = new Card(5);
		System.out.println("");
		PokerHand hand5 = new PokerHand(twoClubs, twoDiamonds, twoHearts, twoSpades);
		hand5.replaceCard(2, threeClubs);
		hand5.replaceCard(3, fourClubs);
		hand5.replaceCard(4, fiveClubs);
		System.out.println("Testing threeOfAKind method:\n" + hand5.toString());
		System.out.println("");
		if (hand5.threeofAKind())
			System.out.println("Three of a kind");
		else 
			System.out.println("Not three of a kind");
		System.out.println("");
		hand5.replaceCard(2, twoDiamonds);
		hand5.replaceCard(3, twoHearts);
		hand5.replaceCard(4, fiveClubs);
		System.out.println(hand5.toString());
		if (hand5.threeofAKind())
			System.out.println("Three of a kind");
		else 
			System.out.println("Not three of a kind");
		System.out.println("");
		hand5.replaceCard(2, twoDiamonds);
		hand5.replaceCard(3, threeClubs);
		hand5.replaceCard(4, twoHearts);
		System.out.println(hand5.toString());
		if (hand5.threeofAKind())
			System.out.println("Three of a kind");
		else 
			System.out.println("Not three of a kind");
		System.out.println("");
		hand5.replaceCard(2, threeClubs);
		hand5.replaceCard(3, twoDiamonds);
		hand5.replaceCard(4, twoHearts);
		System.out.println(hand5.toString());
		if (hand5.threeofAKind())
			System.out.println("Three of a kind");
		else 
			System.out.println("Not three of a kind");
		System.out.println("");
		hand5.replaceCard(2, twoClubs);
		hand5.replaceCard(3, twoDiamonds);
		hand5.replaceCard(4, twoHearts);
		System.out.println(hand5.toString());
		if (hand5.threeofAKind())
			System.out.println("Three of a kind");
		else 
			System.out.println("Not three of a kind");
		
		
	}
	
    /**
     * <p> Name: testPair method </p>
     * 
     * <p> Description: tests the pair method in the PokerHand class.</p>
     */
	public static void testPair()
	{
		// cards to be used to test the various methods
		// in the PokerHand class
		Card twoClubs = new Card(2);
		Card twoDiamonds = new Card(15);
		Card twoHearts = new Card(28);
		Card twoSpades = new Card(41);
		Card threeClubs = new Card(3);
		Card fourClubs = new Card(4);
		Card fiveClubs = new Card(5);
		System.out.println("");
		PokerHand hand7 = new PokerHand(twoClubs, twoDiamonds, twoHearts, twoSpades);
		hand7.replaceCard(2, threeClubs);
		hand7.replaceCard(3, fourClubs);
		hand7.replaceCard(4, fiveClubs);
		System.out.println("Testing pair method:\n" + hand7.toString());
		if (hand7.pair())
			System.out.println("Pair");
		else 
			System.out.println("Not a Pair");
		System.out.println("");
		hand7.replaceCard(2, twoDiamonds);
		hand7.replaceCard(3, fourClubs);
		hand7.replaceCard(4, fiveClubs);
		System.out.println(hand7.toString());
		if (hand7.pair())
			System.out.println("Pair");
		else 
			System.out.println("Not a Pair");
		System.out.println("");
		hand7.replaceCard(2, fourClubs);
		hand7.replaceCard(3, twoDiamonds);
		hand7.replaceCard(4, fiveClubs);
		System.out.println(hand7.toString());
		if (hand7.pair())
			System.out.println("Pair");
		else 
			System.out.println("Not a Pair");
		System.out.println("");
		hand7.replaceCard(2, fourClubs);
		hand7.replaceCard(3, fiveClubs);
		hand7.replaceCard(4, twoDiamonds);
		System.out.println(hand7.toString());
		if (hand7.pair())
			System.out.println("Pair");
		else 
			System.out.println("Not a Pair");
		System.out.println("");
		hand7.replaceCard(1, fiveClubs);
		hand7.replaceCard(2, twoClubs);
		hand7.replaceCard(3, twoDiamonds);
		hand7.replaceCard(4, fourClubs);
		System.out.println(hand7.toString());
		if (hand7.pair())
			System.out.println("Pair");
		else 
			System.out.println("Not a Pair");
		System.out.println("");
		hand7.replaceCard(1, fiveClubs);
		hand7.replaceCard(2, twoClubs);
		hand7.replaceCard(3, fourClubs);
		hand7.replaceCard(4, twoDiamonds);
		System.out.println(hand7.toString());
		if (hand7.pair())
			System.out.println("Pair");
		else 
			System.out.println("Not a Pair");
		System.out.println("");
		hand7.replaceCard(1, fiveClubs);
		hand7.replaceCard(2, fourClubs);
		hand7.replaceCard(3, twoClubs);
		hand7.replaceCard(4, twoDiamonds);
		System.out.println(hand7.toString());
		if (hand7.pair())
			System.out.println("Pair");
		else 
			System.out.println("Not a Pair");
		System.out.println("");
		
		
		
	}
	
    /**
     * <p> Name: testLowCard method </p>
     * 
     * <p> Description: tests lowCard method in the PokerHand class.</p>
     */
	public static void testLowCard()
	{
		// cards to be used to test the various methods
		// in the PokerHand class
		Card twoClubs = new Card(2);
		Card twoDiamonds = new Card(15);
		Card twoHearts = new Card(28);
		Card twoSpades = new Card(41);
		Card threeClubs = new Card(3);
		Card fourClubs = new Card(4);
		Card fiveClubs = new Card(5);
		PokerHand hand9 = new PokerHand(twoClubs, twoDiamonds, twoHearts, twoSpades);
		hand9.replaceCard(2, threeClubs);
		hand9.replaceCard(3, fourClubs);
		hand9.replaceCard(4, fiveClubs);
		System.out.println("Testing lowCard method:\n" + hand9.toString());
		System.out.println("The low card is: " + hand9.lowCard());
		
	}
}