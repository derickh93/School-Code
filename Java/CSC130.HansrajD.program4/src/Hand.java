
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Title: The Hand Class
 * Description: Defines the properties and behaviors of a GoFish Hand.
 * Copyright: Copyright (c) 2010
 * @author F. Graham && Derick Hansraj
 */
public class Hand {

	/**
	 * LinkList of GoFish Cards
	 */
	private LinkedList<GoFishCard> hand = new LinkedList<GoFishCard>();

	/**
	 * Default constructor
	 */
	public Hand() {

	}

	/**
	 * Returns the number of cards in the hand
	 * @return the number of cards in the hand
	 */
	public int getCount() {
		return hand.size();
	}

	/**
	 * Returns the hand as LinkedList of GoFish cards
	 * @return the hand as a LinkedList of GoFish Cards
	 */
	public LinkedList<GoFishCard> getHand() {
		return hand;
	}

	/**
	 * Returns <i>true</i> if this rank is the hand
	 * @param rank - the rank to search for
	 * @return Returns <i>true</i> if this rank is the hand,<br> 
	 * <i>false</i> otherwise
	 */
	public boolean hasRank(int rank) {
		int pos = 0;
		while(pos < hand.size()) {
			if(hand.get(pos).getRank() == rank)
				return true;
			pos++;
		}
		return false;
	}

	/**
	 * Returns a string representation of the hand
	 */
	public String toString() {
		return hand.toString();
	}

	/**
	 * Finds and returns all cards of the specified rank
	 * @param rank - the rank to search for
	 * @return all of the cards of that rank as a LinkedList of GoFish Cards
	 */
	public LinkedList<GoFishCard> findRank(int rank) {
		int pos = 0;
		LinkedList<GoFishCard> list = new LinkedList<GoFishCard>();
		while(pos < hand.size()) {
			if(hand.get(pos).getRank() == rank) {
				list.add(hand.get(pos));
			}
			pos++;
		}
		return list;
	}

	/**
	 * Adds a Card to the hand, the hand is sorted by rank
	 * @param card - a GoFish Card
	 */
	public void insertByRank(GoFishCard card) {
		boolean b = false;
		int pos = 0;
		if(card == null)
			throw new   NullPointerException();

		if(hand.isEmpty()) {
			hand.add(pos, card);
			b = true;
		}
		while(pos < hand.size() && !b) {
			if(hand.get(pos).compareByRank(card) > 0) {
				hand.add(pos, card);
				b = true;
			}
			else
				pos++;
		}
		if(!b)
			hand.addLast(card);
	}

	/**
	 * Adds a LinkList of Cards to the hand, the hand is sorted by rank
	 * @param otherHand LinkedList of GoFish Cards
	 */
	public void insertHand(Collection<? extends GoFishCard> otherHand) {
		for(int i = 0; i < otherHand.size(); i ++){
			this.insertByRank(((LinkedList<GoFishCard>) otherHand).get(i)); }
	}

	/**
	 * Determines if the hand is empty
	 * @return - Returns <i>true</i> if the hand is empty, <i>false</i> otherwise
	 */
	public boolean isEmpty() {
		return hand.size() == 0;

	}

	/**
	 * Returns 1 if a book (all 4 cards of a particular suit) is 
	 * in the hand and removes the book from the hand
	 * @return the number of books (all 4 cards of a particular suit) in the hand
	 */
	public int evaluate() {
		int num = 0;
		LinkedList<GoFishCard> result = new LinkedList<GoFishCard>();
		for(int i = 0; i < 13; i++) {
			if(this.countRank(i) == 4) { 
				result = this.getCards(i);
				this.removeCard(i);
				num = 1;
				System.out.println(" \nBook: " + result);
			}
		}
		return num;
	}

	/**
	 * Counts the number of cards of a particular rank in the hand
	 * @param rank - the rank to count
	 * @return the number of cards of that rank
	 */
	public int countRank(int rank) {
		int num = 0;
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).getRank() == rank)
				num ++;
		}
		return num;
	}

	/**
	 * Returns the card at the specified position in this list.
	 * @param index the index of the list
	 * @return
	 */
	public GoFishCard getCardAt(int index) {
		return hand.get(index);
	}

	/**
	 * Returns a list of cards of a specified rank
	 * @param rank - the rank to search for 
	 * @return the cards as LinkedList of GoFish Cards
	 */
	public LinkedList<GoFishCard> getCards(int rank) {
		LinkedList<GoFishCard> list = new LinkedList<GoFishCard>();
		for(int i = 0; i < hand.size(); i++) { 
			if(hand.get(i).getRank() == rank)
				list.add(hand.get(i));
		}    
		return list;
	}

	public void removeCard(int rank){
		for(int i = hand.size() - 1; i >= 0; i--) {
			if(hand.get(i).getRank() == rank)
				hand.remove(i);	
		}
	}
}