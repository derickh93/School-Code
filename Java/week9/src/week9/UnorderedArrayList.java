package week9;

public class UnorderedArrayList <T> extends ArrayList<T>{
	T insertItem;

	public UnorderedArrayList() {
		super();
	}
	
	public UnorderedArrayList(int size) {
		super(size);
	}

	public void insert(T insertItem) {
		if(size == data.length)
			doubleCapacity();
		data[size] = insertItem;
		size++;
	}

	public int search(T searchItem) {
		int loc = -1;
		boolean found = false;
		for(int i = 0; i < size && !found; i++) {
			if(data[i].equals(searchItem)) {
				found = true;
				loc = 1;
			}
		}
		return loc;
	}

	public T remove(T removeItem) {
		int current = this.search(removeItem);
		int num = size - current;
		if(this.search(removeItem) >= 0) {
			while(current < num) {
				data[current] = data[current + 1];
				current++;
			}
			size--;
		}
		return (T) this.toString();
	}

}