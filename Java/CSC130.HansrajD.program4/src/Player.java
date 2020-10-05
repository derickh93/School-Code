import java.util.LinkedList;


/**
 * Title: The Player Class
 * Description: Defines the properties and behaviors of a GoFish Player.
 * Copyright: Copyright (c) 2010
 * @author F. Graham && Derick Hansraj
 */
public class Player {
	private Hand hand = new Hand();
	private String name;
	public int points;

	/**
	 * Parameterized constructor
	 * @param n - the name of the player
	 */
	public Player(String n) {
		name = n;
	}

	/**
	 * Returns the player's name
	 * @return - the player's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name
	 * @param name - the new value for name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns all of the cards of the specified rank as a LinkedList
	 * @param rank - the rank to search for
	 * @return - the cards of the specified rank as a LinkedList
	 */
	public LinkedList<GoFishCard> getCards(int rank) {
		return this.hand.findRank(rank);	
	}

	/**
	 * Sets the hand
	 * @param hand
	 */
	public void setHand(Hand hand) {
		this.hand = hand;
	}

	/**
	 * Returns the number of books the player has
	 * @return the number of books the player has
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Returns the string representation of the player
	 * @return the string representation of the player
	 */
	public String toString() {
		return this.hand.toString();
	}

	/**
	 * Returns the string representation of the hand
	 * @return the string representation of the hand 
	 */
	public String showHand() {
		return this.hand.toString();
	}

	/**
	 * Returns <i>true</> if the player has a specified rank
	 * @param rank - the rank to search for
	 * @return - Returns <i>true</> if the player has a specified rank, <i>false</> otherwise
	 */
	public boolean hasRank(int rank) {
		return this.hand.hasRank(rank);
	}

	/**
	 * Adds a card to the hand
	 * @param card - the card to add
	 */
	public void addCard(GoFishCard card) {
		hand.insertByRank(card);
	}

	/**
	 * Adds a LinkList of Cards to the hand
	 * @param otherHand - the LinkedList of cards
	 */
	public void addCards(LinkedList<GoFishCard> otherHand) {
		hand.insertHand(otherHand);
	}

	/**
	 * returns the card at a specified index in the hand
	 * @param index - the position of the card
	 * @return - the card at that position
	 */
	public GoFishCard getCardAt(int index) {
		return hand.getCardAt(index);
	}

	/**
	 * Returns the number of cards the player has
	 * @return Return the number of cards the player has
	 */
	public int getTotalCards() {
		return hand.getCount();
	}

	/**
	 * Returns the cards of a specified rank as a Linkedlist
	 * @param rank - the rank to search for
	 * @return - the Linked List of cards
	 */
	public LinkedList<GoFishCard> getCard(int rank) {
		return hand.getCards(rank);
	}

	/**
	 * Removes a card from the players hand
	 * @param rank - the card to be removed.
	 */
	public void removeCard(int rank) {		
		hand.removeCard(rank);
	}

	/**
	 * Returns the players hand
	 * @return - The players hand
	 */
	public Hand getHand() {
		return this.hand;
	}
}