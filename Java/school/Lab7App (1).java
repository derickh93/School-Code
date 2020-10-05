package lab7;

/**
 * <p>Title: The Lab7App class</p>
 *
 * <p>Description: This class will be used to thoroughly test the 
 * various methods in the PokerHand class.  The seven cards specified 
 * will be used to test each method.</p>
 *
 * @author your name here
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

		PokerHand hand = new PokerHand (twoClubs, twoDiamonds, twoHearts,
				twoSpades);
		System.out.println("Testing fourOfAKind method:" + "\n" + hand.toString());
		String four;

		if (hand.fourOfAKind() == true)
			four = "Four of a kind";
		else
			four = "Not four of a kind";
		System.out.println(four);
		hand.replaceCard(2, threeClubs);
		hand.fourOfAKind();
		if (hand.fourOfAKind() == true)
			four = "Four of a kind";
		else
			four = "Not four of a kind";
		System.out.println("\n" + "Replaced the 2 of diamonds with the 3 of clubs:" + 
				"\n"+ hand.toString() + "\n" + four);
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

		PokerHand hand = new PokerHand (twoClubs, threeClubs,
				twoHearts, twoSpades);

		String flush;
		if (hand.flush() == true)
			flush = "Flush";
		else
			flush = "Not a flush";

		System.out.println("\n" + "Testing flush method:" + "\n"
				+ "\n" + hand.toString() + "\n" + flush);
		hand.replaceCard(3, fourClubs);
		hand.replaceCard(4, fiveClubs);
		hand.toString();

		if (hand.flush() == true)
			flush = "Flush";
		else
			flush = "Not a flush";
		System.out.println("\n" + "Replaced the 2 of hearts with "
				+ "the 4 of clubs and the 2 of spades with the "
				+ "5 of clubs:" + "\n" + hand.toString() + "\n" + flush);
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

		PokerHand hand = new PokerHand (twoClubs, threeClubs,
				fourClubs, fiveClubs);
		PokerHand hand2 = new PokerHand (twoClubs, twoDiamonds,
				twoHearts, fiveClubs);
		PokerHand hand3 = new PokerHand (twoClubs, twoDiamonds,
				threeClubs, twoHearts);
		PokerHand hand4 = new PokerHand (twoClubs, threeClubs,
				twoDiamonds, twoHearts);
		PokerHand hand5 = new PokerHand (threeClubs, twoClubs,
				twoDiamonds, twoHearts);
		String one;
		String two;
		String three;
		String four;
		String five;
		if (hand.threeOfAKind() == true)
			one = "Three of a kind";
		else
			one = "Not three of a kind";
		if (hand2.threeOfAKind() == true)
			two = "Three of a kind";
		else
			two = "Not three of a kind";
		if (hand3.threeOfAKind() == true)
			three = "Three of a kind";
		else
			three = "Not three of a kind";
		if (hand4.threeOfAKind() == true)
			four = "Three of a kind";
		else
			four = "Not three of a kind";
		if (hand5.threeOfAKind() == true)
			five = "Three of a kind";
		else
			five = "Not three of a kind";
		System.out.println("\n" + "Testing threeOfAKind method:" + "\n" + "\n" +
				hand.toString() + "\n" + one);
		System.out.println("\n" + hand2.toString() + "\n" + two);
		System.out.println("\n" + hand3.toString() + "\n" + three);
		System.out.println("\n" + hand4.toString() + "\n" + four);
		System.out.println("\n" + hand5.toString() + "\n" + five);


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

		PokerHand hand = new PokerHand (twoClubs, threeClubs,
				fourClubs, fiveClubs);
		PokerHand hand2 = new PokerHand (twoClubs, twoDiamonds,
				fourClubs, fiveClubs);
		PokerHand hand3 = new PokerHand (twoClubs, fourClubs,
				twoDiamonds, fiveClubs);
		PokerHand hand4 = new PokerHand (twoClubs, fourClubs,
				fiveClubs, twoDiamonds);
		PokerHand hand5 = new PokerHand (fiveClubs, twoClubs,
				twoDiamonds, fourClubs);
		PokerHand hand6 = new PokerHand (fiveClubs, twoClubs,
				fourClubs, twoDiamonds);
		PokerHand hand7 = new PokerHand (fiveClubs, fourClubs,
				twoClubs, twoDiamonds);
		String one;
		String two;
		String three;
		String four;
		String five;
		String six;
		String seven;
		if (hand.pair() == true)
			one = "Pair";
		else
			one = "Not a pair";
		if (hand2.pair() == true)
			two = "Pair";
		else
			two = "Not a pair";
		if (hand3.pair() == true)
			three = "Pair";
		else
			three = "Not a pair";
		if (hand4.pair() == true)
			four = "Pair";
		else
			four = "Not a pair";
		if (hand5.pair() == true)
			five = "Pair";
		else
			five = "Not a pair";
		if (hand6.pair() == true)
			six = "Pair";
		else
			six = "Not a pair";
		if (hand7.pair() == true)
			seven = "Pair";
		else
			seven = "Not a pair";
		System.out.println("\n" + "Testing pair method:" + "\n" + "\n" +
				hand.toString() + "\n" + one);
		System.out.println("\n" + hand2.toString() + "\n" + two);
		System.out.println("\n" + hand3.toString() + "\n" + three);
		System.out.println("\n" + hand4.toString() + "\n" + four);
		System.out.println("\n" + hand5.toString() + "\n" + five);
		System.out.println("\n" + hand6.toString() + "\n" + six);
		System.out.println("\n" + hand6.toString() + "\n" + seven);
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
		
		PokerHand hand = new PokerHand (twoClubs, threeClubs,
				fourClubs, fiveClubs);
		PokerHand hand2 = new PokerHand (threeClubs, fourClubs,
				fourClubs, fiveClubs);
		PokerHand hand3 = new PokerHand (twoClubs, fourClubs,
				twoClubs, fiveClubs);
		PokerHand hand4 = new PokerHand (threeClubs, fourClubs,
				fiveClubs, twoClubs);
		PokerHand hand5 = new PokerHand (threeClubs, twoDiamonds,
				fiveClubs, twoClubs);
		//System.out.println(("Testing lowCard method:" + "\n" + "\n" + 
				hand.toString() + "\n" + hand.lowCard() + "\n" +
				hand2.toString() + //"\n" + hand2.lowCard()); //+ );


	}
}