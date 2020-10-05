package lab4;
/**
 * <p>
 * Title: The StackADT Class
 * Description: Defines the behaviors of a basic stack.
 * @author F. Graham, Derick Hansraj
 */
public interface StackADT<T> {
	/**
	 * push -- Pushes an object onto the top of this stack.
	 * @param x The object to be stored onto the stack.
	 * @throws StackFullException - if this stack is full
	 */
	void push( T x ) throws StackFullException;
	/**
	 * pop -- Removes and returns the object at the top of this stack.
	 * @return The object at the top of the stack.
	 * @throws StackEmptyException - if this stack is full
	 */
	T pop() throws StackEmptyException;
	/**
	 * peek -- Returns the object at the top of this stack without removing it.
	 * @return The object at the top of the stack.
	 * @throws StackEmptyException - if this stack is full
	 */
	T peek() throws StackEmptyException;
	/**
	 * isEmpty -- Tests if this stack is empty.
	 * @return <i>true</i> if and only if this stack is empty; <i>false</i> otherwise.
	 */
	boolean isEmpty();
	/**
	 * isFull -- Tests if this stack is full.
	 * @return <i>true</i> if and only if this stack is full; <i>false</i> otherwise.
	 */
	boolean isFull();
	/**
	 * getSize -- Returns the number of objects on the stack.
	 * @return The number of objects on the stack.
	 */
	int getSize();
}