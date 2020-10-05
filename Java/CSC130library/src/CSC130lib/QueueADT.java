package CSC130lib;


public interface QueueADT<T> {
	public void enqueue(T d) throws QueueFullException;
	public T dequeue() throws QueueEmptyException;
	public T front()throws QueueEmptyException;
	public T rear()throws QueueEmptyException;
	public boolean isFull();
	public boolean isEmpty();
	public int getSize();

}
