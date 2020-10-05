package wenjieproj2;

public interface LinkedStackADT<T> {
/**
* Pushes an object onto the top of this stack.
* @param x The number to be stored onto the stack.
* @throws StackFullException - if this stack is full
*/
public void push( T x ) throws StackEmptyException;
/**
* Removes and returns the object at the top of this stack.
* @return The integer at the top of the stack.
* @throws StackEmptyException - if this stack is full
*/
public T pop() throws StackEmptyException;
/**
* Returns the object at the top of this stack without removing it.
* @return The object at the top of the stack.
* @throws StackEmptyException - if this stack is full
*/
public T peek() throws StackEmptyException;
/**
* Tests if this stack is empty.
* @return <i>true</i> if and only if this stack is empty; <i>false</i> otherwise.
*/
public boolean isEmpty() throws StackEmptyException;

/**
* Returns the number of objects on the stack.
* @return The number of objects on the stack.
*/
public int getSize() throws StackEmptyException;
}