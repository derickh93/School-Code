package lab4;
public class Lab4App {
	@SuppressWarnings("unchecked")
	public static void main (String [] args) throws StackFullException, StackEmptyException{
		//Create a new DiscardPile object called discardPile
		DiscardPile discardPile = new DiscardPile();
		//Push cards to discardPile
		discardPile.push(new Card(8));
		discardPile.push(new Card(32));
		discardPile.push(new Card(48));
		discardPile.push(new Card(2));
		discardPile.push(new Card(17));
		discardPile.push(new Card(20));
		discardPile.push(new Card(25));
		discardPile.push(new Card(50));
		discardPile.push(new Card(19));
		discardPile.push(new Card(41)); 
		//Prints discardPile to a string
		System.out.println(discardPile.toString());
		//Remove cards until card 20 is found
		discardPile.removeTopCards(new Card(20));
		//Prints discardPile to a string
		System.out.println(discardPile.toString());
		//Remove all the cards in discardPile until it is empty
		while(!discardPile.isEmpty()) {
			discardPile.pop();
		}
		//Push cards to discardPile
		discardPile.push(new Card(8));
		discardPile.push(new Card(32));
		discardPile.push(new Card(48));
		discardPile.push(new Card(2));
		discardPile.push(new Card(17));
		discardPile.push(new Card(20));
		discardPile.push(new Card(25));
		discardPile.push(new Card(50));
		discardPile.push(new Card(19));
		discardPile.push(new Card(41)); 
		//Remove cards until card 50 is found
		discardPile.removeTopCards(new Card(50));
		//Prints discardPile to a string
		System.out.println(discardPile.toString());
	}
}