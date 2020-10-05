package ht;


public class SimpleHashTable<K, V> {
	
	// instance vars:
	// create an array of SimpleEntry called hashtable
	// counter for collisions
	// counter for how many times you expanded capacity
	// will need get methods for those counters
	private SimpleEntry [] hashTable;
	private int collisions;
	private int expand;
	private final double threshold = .75;
	private int capacity = 5;
		
	public int getCollisions() {
		return collisions;
	}

	public int getExpand() {
		return expand;
	}

	// default constructor, instantiate the array to size 5
	public SimpleHashTable() {
		// the syntax is simillar to the one used in the Set lab
		hashTable = new SimpleEntry[capacity];
	}
	
	// call hashcode method of key, compress it, then store key, value at that position
	// count how many collisions you had, up to you if you want to overwrite or not
	// need to expandcapacity if needed
	public void put(K key, V value) {
		if(hashTable[key.hashCode() % hashTable.length] != null)
			collisions++;
		hashTable[key.hashCode() % hashTable.length] = (SimpleEntry) value;	
	}
	
	// return Value based on key or throw NoSuchElementException if it doesn't exist 
	public V get(K key) {
		for(int i = 0; i < hashTable.length;i ++) {
			if(hashTable[i].getKey().equals(key))
				return (V) key;
		}
		return null;
		
	}
	
	// this is the expandcapacity method
	// expand array and recompress the values to find new position
	private void reshash() {
		
	}
}