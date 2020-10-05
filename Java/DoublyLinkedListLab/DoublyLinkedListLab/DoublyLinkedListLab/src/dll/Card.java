/**
 * Title: DoublyLinkedListLab
 * Filename: Card.java
 * Date Written: February 13, 2018
 * Due Date: February 16, 2018
 * Description: Defines a Card class. 
 * 
 *@author Instructor
 */
package dll;

import java.io.Serializable;

// just here for testing, in case you want to use it


public class Card implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}

	public Card(int value, String suit) {
		super();
		this.value = value;
		this.suit = suit;
	}

	private int value;

	@Override
	public String toString() {
		return "Card [value=" + value + ", suit=" + suit + "]";
	}

	private String suit;


}
