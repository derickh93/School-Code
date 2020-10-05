package week11;

public interface ArrayListADT <T>{
	void insert(T insertItem);
	int search(T searchItem);
	Object remove(T removeItem);
	boolean isEmtpy();
	boolean isFull();
	int getSize();
	int getCapacity();
	
}