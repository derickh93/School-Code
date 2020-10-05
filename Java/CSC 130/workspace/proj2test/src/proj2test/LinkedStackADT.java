package proj2test;

public interface LinkedStackADT<T> {
	void push(T d);
	T pop() throws RuntimeException;
	T peek() throws RuntimeException;
	boolean isEmpty();
	int getSize();
}