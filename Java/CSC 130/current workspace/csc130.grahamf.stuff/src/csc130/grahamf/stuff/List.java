package csc130.grahamf.stuff;

public class List {
	private Automobile[] auto;
	private int numItems;
	public final int MAX_SIZE = 10;
	
	public List(){
		numItems = 0;
		auto = new Automobile[MAX_SIZE];
	}
	public List(int size) throws IllegalException{
		if(size > 0){
			numItems = 0;
			auto = new Automobile[size];
		}
		else 
			throw new IllegalException();
	}	
	public void insert(Automobile a){
		if(isFull())
			increaseCapacity();
		auto[numItems] = a;
		numItems++;
	}
	public void delete(Automobile a) throws IllegalException{
		if(isEmpty())
			throw new IllegalException("List empty exception");
		for(int i=0; i < numItems; i++)
			if(auto[i].equals(a) && auto[i].getPrice()==a.getPrice()
				&& auto[i].getColor().equals(a.getColor())){
				for(int j=i; j < numItems-1; j++)
					auto[j] = auto[j+1];
				numItems--;
			}					
	}
	public boolean isFull(){
		return numItems == auto.length;
	}
	public boolean isEmpty(){
		return numItems == 0;
	}
	private void increaseCapacity(){
		Automobile[] newArray = new Automobile[auto.length*2];
		for(int i = 0; i<numItems; i++)
			newArray[i] = auto[i];
		auto = newArray;
	}
	public String toString(){
		String s1 = new String();
		for(Automobile i : auto)
			s1 += i + "\n";
		return s1;
	}
}
