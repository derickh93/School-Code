package csc130lib;

public abstract class ArrayList <T>implements ArrayListADT<T>{
	 protected Object[] data;
	 protected int size;
	 private final int CAPACITY = 100;

	public ArrayList() {
		data = (T[]) new Object[CAPACITY];
		size = 0;
	}

	public ArrayList(int size) {
		if(size < 1) {
			data = (T[]) new Object[CAPACITY];
		}
		data = (T[]) new Object[size];
		this.size = 0;

	}
	public abstract void insert(T insertItem);

	public abstract int search(T searchItem);

	public abstract T remove(T removeItem);

	public boolean isEmtpy() {
		return size == 0;

	}

	public int getSize() {
		return size;
	}

	public int getCapacity() {
		return data.length;
	}
	
	public String toString() {
		String str = "The list of " + size + " items\n";
		for(int i = 0; i < size; i++) {
			str += data[i] + "\n";
		}
		return str;
	}
}
