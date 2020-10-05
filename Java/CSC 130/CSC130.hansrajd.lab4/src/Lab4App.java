/**
 * <p>
 * Title: The Lab4App Class
 * </p>
 * 
 * <p>
 * Description: .
 * </p>
 * 
 * <p>
 * </p>
 * 
 * @author Derick Hansraj and Brandon
 */
public class Lab4App {
	public static void main (String [] args) throws StackFullException, StackEmptyException {
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
		System.out.println(discardPile.removeTopCards(new Card(17)));
	}

}
