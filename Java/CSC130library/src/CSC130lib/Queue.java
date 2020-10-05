package CSC130lib;


public class Queue <T> implements QueueADT<T>{
	private T[] data;
	private int size;
	private int front, rear;

	public static final int CAPACITY = 100;


	public Queue() {
		data = (T[]) new Object[CAPACITY];
		size = 0;
		front = 0;
	}

	public Queue(int size) {
		data = (T[]) new Object[size];
		this.size = 0;
		front = 0;
	}

	public void enqueue(T d) throws QueueFullException {
		if(isFull())
			throw new QueueFullException();
		int rear = (front + size) % data.length;
		data[rear] = d;
		size++;
	}


	public T dequeue() throws QueueEmptyException {
		if(isEmpty())
			throw new QueueEmptyException();
		T info = data[front];
		front = (front + 1) % data.length;
		size--;
		return info;
	}

	public T front() throws QueueEmptyException {
		if(isEmpty())
			throw new QueueEmptyException();
		return data[front];
	}

	public T rear() throws QueueEmptyException {
		if(isEmpty())
			throw new QueueEmptyException();
		return data[(front + size - 1) % data.length];
	}

	public boolean isFull() {
		return size == data.length;
	}

	public boolean isEmpty() {
		return size == 0;
	}


	public int getSize() {
		return size;
	}

	public String toString() {
		String str = "";
		if(!isEmpty()) {
			for(int i = 0; i < size; i++) {
				str += "[" + data[(front + i) % data.length] + "]";
			}
		}
		return str;
	}
}
