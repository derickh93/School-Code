package lab4;

/**
 * <p>Title: The Card class</p>
 *
 * <p>Description: Description: This class will represent a single playing card 
 * that has a value between 1 and 13 and a suit (clubs, diamonds, hearts or 
 * spades).  A card can return its value, suit, point value or reference to a 
 * String containing the card’s value and suit.  It can check to see if two 
 * cards have the same value or the same suit. </p>
 *
 * @author <Derick Hansraj>
 */
public class Card
{
    // instance variables
    private int value;
    private String suit;

    /**
     * parameterized Card constructor --
     * gets called when an object of the Card class is instantiated sending a number 
     * as an argument -- it determines the value and suit of the card based upon the 
     * number received
     * @param num a number that gets converted to a value between 1 and 13
     * and one of the four suits (clubs, diamonds, hearts, or spades)
     */
    public Card(int num) {
        int suitNumber;
        value = num % 13;
        if (value == 0)
            value = 13;
        suitNumber = num / 13;
        if (suitNumber == 0)
            suit = new String("clubs");
        else 
			if (suitNumber == 1)
				suit = new String("diamonds");
			else 
				if (suitNumber == 2)
					suit = new String("hearts");
				else 
					if (suitNumber == 3)
						suit = new String("spades");
					else
						suit = new String("ERROR");
    }

    /**
     * getValue method -- returns what's stored in the instance variable value
     * @return the state of the instance variable value
     */
    public int getValue() {
        return value;
    }

    /**
     * getSuit method -- returns what's stored in the instance variable suit
     * @return a reference to a String that contains the state of the instance variable suit
     */
    public String getSuit()  {
        return suit;
    }

    /** equalValue method -- determines if the otherCard's value is the same
     * as this card's value
     * @param otherCard a reference to the Card object (assumes the object has been 
     * instantiated) to compare to this Card object
     * @return true if the values are equal, false if the values are not equal
     */
    public boolean equalValue(Card otherCard) {
    	if (toString().equals(value))
    		return true;
    		else 
    		return false;
    }

    /**
     * equalSuit method -- determines if the otherCards's suit is the same as
     * this card's suit
     * @param otherCard a reference to the Card object (assumes the object has been 
     * instantiated) to compare to this Card object
     * @return true if the suits are the same, false if they are not
     */
    public boolean equalSuit(Card otherCard) {
    	return false;
    }

    /**
     * determinePointValue method -- this method returns the point value for the
     * card -- 10 for a face card, the actual value for cards 1 through 10
     * @return the point value of the card
     */
    public int determinePointValue() {
    	if (value > 10 )
    		return 10;
    	else 
    		return value;
    }

	/**
	 * isAFaceCard -- this method returns true if the card is a face card
	 * (jack, queen, or king), or false otherwise
	 * @return whether the card is a face card
	 */

	public boolean isAFaceCard() {
		if (value > 10) 
			return true;
		else
			return false;		
	}

	/**
	 * isRed - this method returns true if the card is a red card (diamonds,
	 * hearts), or false otherwise
	 * @return whether the card is a red card
	 */

	public boolean isRed() {
		if ((suit.equals("hearts") || (suit.equals("diamonds"))))
			return true;
		else 
			return false;
				
	}
	
    
    /**
     * toString method -- this method returns the state of the card object
     * @return a reference to a String object that contains the value and the
     * suit of the card
     */
    public String toString() {
		String Ace = new String( "Ace" + " of " + suit );
		String Jack = new String( "Jack" + " of " + suit );
		String Queen = new String( "Queen" + " of " + suit );
		String King = new String( "King" + " of " + suit );
		String returnMe = new String(value + " of " + suit);
		if (value == 1)
			return Ace;
		else if (value == 11)
			return Jack;
		else if (value == 12)
			return Queen;
		else if (value == 13)
			return King;
		else
			return returnMe;
    }
}
