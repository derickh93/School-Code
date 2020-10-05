package week9;

public interface ArrayListADT <T>{
	void insert(T insertItem);
	int search(T searchItem);
	Object remove(T removeItem);
	boolean isEmtpy();
	int getSize();
	int getCapacity();
	
}
