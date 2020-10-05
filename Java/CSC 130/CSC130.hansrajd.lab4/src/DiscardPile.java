/**
 * <p>
 * Title: The DiscardPile Class
 * </p>
 * 
 * <p>
 * Description: Defines the properties and behaviors of a pile of cards discarded by the user.
 * </p>
 * 
 * <p>
 * </p>
 * 
 * @author Derick Hansraj and Brandon
 */
public class DiscardPile  < T extends Stack<Card>{
	/**
	 * DiscardPile default constructor -- gets called when an object of the DiscardPile class
	 * is instantiated. Constructs a new Stack with capacity for 100 objects
	 */
	public DiscardPile() {
		super();
		
	}
	
	public Card[] removeTopCards(Card theCard) throws StackEmptyException {
		Stack<Card> tempStack = new Stack<Card>();
		Card[] tempArr = null;
		boolean search = true;
		int count = 0;
		if(this.isEmpty())
			throw new StackEmptyException();

		while(search) {
			if(this.peek().equals(theCard)) { 
				tempStack.push(this.pop());
				search = false;	
			}
			else
				tempStack.push(this.pop());
		}

		if(tempStack.isEmpty())
			throw new RuntimeException();
		tempArr = new Card[tempStack.getSize()];

		while(!tempStack.isEmpty()) {
			tempArr[count] = tempStack.pop();
			count++;
		}
		return tempArr;
	}
}
