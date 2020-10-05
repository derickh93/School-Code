
import java.io.IOException;
import java.util.Scanner;

/**
 * Title: The GoFish Class
 * Description: Defines the properties and behaviors of a GoFish game.
 * Copyright: Copyright (c) 2010
 * @author F. Graham && Derick Hansraj
 */
public class GoFish {

	//instance variables
	private Deck deck = new Deck();
	private Player[] player = new Player[2];
	public static String playerName;
	public static int playerOne = 0;
	public static int playerTwo = 1;
	public static int change;
	Scanner scan = new Scanner(System.in);


	public void playGame() {
		//The user inputs their name
		getNames();

		player[0] = new Player(playerName);
		player[1] = new Player("Computer");

		System.out.println("Game starting in cheat mode");

		//The deck is shuffled
		deck.shuffle();

		//The cards are dealed
		dealCards();

		while(!deck.isEmpty()) {
			while(player[playerOne].getTotalCards() > 0 && player[playerTwo].getTotalCards() > 0) {  
				boolean b = false;
				System.out.print("********************************************************" + "\n" + player[playerOne].getName() 
						+"[Book: " + player[playerOne].getPoints() + "]" + "\n" + player[playerOne].showHand() + "\n" + 
						player[playerTwo].getName() + ", do you have any: ");

				//The user ask for a card
				int rankInput = getRank();

				while(!b) {
					if(player[playerOne].hasRank(rankInput))
						b = true;
					else 
					{
						System.out.println("You must choose a rank that you have in your hand. Try again. ");
						rankInput = getRank();
					}
				}

				if (player[playerTwo].hasRank(rankInput)) { 
					System.out.print(player[playerTwo].getName()+ "  \"Yes!!\"");
					player[playerOne].addCards(player[playerTwo].getCard(rankInput));
					player[playerTwo].removeCard(rankInput);
				}

				else {
					System.out.print(player[playerTwo].getName()+ " \"No, Go Fish!!\"");
					player[playerOne].addCard((GoFishCard) deck.deal());
				}

				if(player[playerOne].getHand().evaluate() == 1) {
					System.out.println(player[playerOne].getHand().evaluate());
					player[playerOne].points++;
				}

				System.out.print("\n" + player[playerOne].getName() +" [Book: " + player[playerOne].getPoints() + "]" + "\n" + 
						player[playerOne].showHand() + "\n");

				//This changes the players turn
				changeTurns();
			}
		}
		//This shows the results of the game
		gameResults();
	}

	/**
	 * changeTurn method -- Changes the players turn so that the game continues
	 */
	public void changeTurns() {
		change = playerOne;
		playerOne = playerTwo;
		playerTwo = change;
	}

	/**
	 * dealCards method -- deals the appropriate amount of cards to each player
	 */
	public void dealCards() {
		int count = 0;
		while(count < 7) {
			player[0].addCard((GoFishCard) deck.deal());
			player[1].addCard((GoFishCard) deck.deal());
			count++;	
		}	
	}

	/**
	 * displayHands method -- displays each players hand to the console
	 */
	public void displayHands() {
		System.out.println(player[0].toString());
		System.out.println(player[1].toString());
	}

	/**
	 * getNames method -- Ask the user to input their name
	 */
	public void getNames() {
		System.out.println("Enter your name: ");
		playerName = scan.nextLine();
	}

	/**
	 * getRank method -- Gets the rank that a player is looking for from the other player
	 */
	public int getRank() {
		String rank = scan.nextLine();
		int rankTwo = GoFishCard.convertToRank(rank);
		return rankTwo;
	}

	/**
	 * gameResults method -- Determines which player won the game and display the results to the console
	 */
	public void gameResults() {
		if(player[0].points > player[1].points)
			System.out.println("\n" + player[0].getName() + " is the winner.....");
		else if(player[0].points < player[1].points)
			System.out.println("\n" + "Computer is the winner.....");
		else
			System.out.println("\n" + "The game has drawn....");

	}

	public static void main(String[] args) throws IOException {
		//Create a GoFish object
		GoFish game = new GoFish();
		//Start a GoFish game against the computer
		game.playGame();
	}
}
