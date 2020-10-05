/**
 * 
 * Title: The DiscardPile Class
 * 
 * Description: Defines the properties and behaviors of a pile of cards discarded by the user.
 * 
 * @author Derick Hansraj and Brandon
 */
public class DiscardPile extends Stack<Card>{
	/**
	 * default constructor -- Constructs a new Stack to hold discarded cards and has 
	 * a capacity of 100 objects.
	 */
	public DiscardPile() {
		super();

	}

	/**
	 * returnArray -- This method will take an array or type Card and return it.
	 *  
	 * @param a -- The card array that we are returning.
	 * @return an array of type Card.
	 */
	public Card[] returnArray(Card[] a) {

		for(int i = 0; i < a.length;i++) { 
			System.out.println(a[i]);
		}
		return a;
	}

	/**
	 * removeTopCards -- This method will allow the user to request that all cards up to
	 *  and including theCard be removed from the discard pile and returned in an array.  
	 * @param theCard -- The card that we are searching for.
	 * @return an array of type Card with the cards removed from the stack.
	 * @throws StackEmptyException, RuntimeException
	 */
	public Card[] removeTopCards(Card theCard) throws StackEmptyException {

		Stack<Card> tempStack = new Stack<Card>();
		Card[] tempArr = null;
		boolean search = true;
		int count = 0;
		try {
			if(this.isEmpty())
				throw new StackEmptyException();

			while(!this.isEmpty() && search) {
				if(!this.peek().equals(theCard)) { 
					tempStack.push(this.pop());
				}
				else {
					tempStack.push(this.pop());
					search = false;	
				}

			}

			if(this.isEmpty()) {
				while(!tempStack.isEmpty()) {
					this.push(tempStack.pop());
				}
				throw new RuntimeException("The card was not found");
			}

			tempArr = new Card[tempStack.getSize()];

			while(!tempStack.isEmpty()) {
				tempArr[count] = tempStack.pop();
				count++;
			}
		} catch (StackEmptyException ex) {
			System.out.println("StackEmptyException: " + ex.getMessage());
		} catch (RuntimeException ex) {
			System.out.println("StackException: " + ex.getMessage());
		}
		return returnArray(tempArr);
	}
}
