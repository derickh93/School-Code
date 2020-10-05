package lab3;
import cardgames.*;

/**
 * Title: Lab 3: Exploring Programmer Designed Class 
 * Description: Program to take a deck of cards and 
 * then shuffles it. It then displays the number and 
 * suit of the card. Then the number value of the card. 
 * Finally the point value of the card.
 * @ Author Derick Hansraj
 *
 */

public class Lab3App {
/**
 * 
 */
	public static void main(String[] args) {
		
// We took the imported information and created a new GUI. We then stored the new GUI in variable theWindow.
		GUI theWindow = new GUI();
		Deck theDeck = new Deck();
		
// This statement assigns shuffleDeck to the variable theDeck and executes it.
		theDeck.shuffleDeck();
		
// Declares cardOne as Card variable type. Then executes dealCard method on theDeck and stores it in cardOne.
		Card cardOne;
		cardOne = theDeck.dealCard();
		
// This executes the toString method on cardOne and then stores it in newCard. We then display it.		
		String newCard = cardOne.toString();
		System.out.println("The suit value is:" + newCard);
		
// This executes the showCard method on cardOne which displays it on GUI theWindow.	
		theWindow.showCard( cardOne );
		
//This executes getValue  on cardOne and stores it as an integer in nextCard. Then it displays it.
		int nextCard = cardOne.getValue();
		System.out.println("The value is:" + nextCard);
		
		int secondCard = cardOne.getPointValue();
		System.out.println("The point value is:" + secondCard);
	}

}
