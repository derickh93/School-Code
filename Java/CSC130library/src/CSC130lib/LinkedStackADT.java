package CSC130lib;


public interface LinkedStackADT<T> {
	public void push(T d);
	public T pop() throws StackEmptyException;
	public T peek() throws StackEmptyException;
	public int getSize();
	public boolean isEmpty();
}
