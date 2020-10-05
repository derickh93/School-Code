package lab4;
public class Lab4App {
	@SuppressWarnings("unchecked")
	public static void main (String [] args) throws StackFullException, StackEmptyException{
		DiscardPile discardPile = new DiscardPile();
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
		System.out.println(discardPile.toString());
		discardPile.removeTopCards(new Card(20));
		System.out.println(discardPile.toString());
		while(!discardPile.isEmpty()) {
			discardPile.pop();
		}
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
		discardPile.removeTopCards(new Card(50));
		System.out.println(discardPile.toString());



	}

}
