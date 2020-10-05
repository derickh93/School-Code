package dll;

public class Node<E> {
	
	private E data;
	
	private Node<E> next;
	private Node<E> prev;
	
	
	
	public String toString()
	{
		return data.toString();
	}
}