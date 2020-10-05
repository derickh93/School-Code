
package lab4;

import java.util.Arrays;

/**
 * <p>
 * Title: The DiscardPile Class
 * 
 * Description: Defines the properties and behaviors of a pile of cards
 * that has been discarded by the user.
 * 
 * @author Derick Hansraj
 * @param <T>
 */
public class DiscardPile extends Stack<Card>{

	/**
	 * default constructor -- Constructs a new Stack to hold discarded cards and has 
	 * a capacity of 100 objects.
	 */
	public DiscardPile(){
		super();
	}
	
	/**
	 * removeTopCards -- This method will allow the user to request that all cards up to
	 *  and including theCard be removed from the discard pile and returned in an array
	 * @param <T>
	 *  
	 *  @param theCard -- The card that we are searching for.
	 * @return 
	 * @throws StackEmptyException 
	 */
	 public Card[] returnArray(Card[] a) {
     
       for (Card x : a) { 
    	   System.out.println(x);
    	 }
	return a;
      }

	 public Card[] removeTopCards(Card theCard) throws StackEmptyException {

		 Stack<Card> tempStack = new Stack<Card>();
		 Card[] tempArr = null;
		 boolean search = true;
		 int count = 0;
		 try {
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
		 } catch (StackEmptyException ex) {
			 System.out.println("StackEmptyException: " + ex.getMessage());
		 } catch (RuntimeException ex) {
			 System.out.println("StackException: " + ex.getMessage());
		 }
		 return returnArray(tempArr);

	 }

}
