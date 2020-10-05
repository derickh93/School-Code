/**
 * File: SimpleHashTable.java
 * Description: Defines a simple hashtable that will overwrite data if there is duplicates.
 * @author Derick Hansraj
 * N#: N00827531
 */

public class SimpleHashTable<K, V> {

	// instance vars:
	// create an array of SimpleEntry called hashtable
	// counter for collisions
	// counter for how many times you expanded capacity
	// will need get methods for those counters
	private SimpleEntry<K, V> [] hashTable;
	private int collisions;
	private int expand;
	private final double threshold = .75;
	private int capacity = 5;
	private int numItems;
	private double loadFactor;

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
		loadFactor = numItems/hashTable.length;
		if(loadFactor > threshold)
			this.reshash();


		if(hashTable[key.hashCode() % hashTable.length] != null) {
			System.out.println(key.hashCode() % hashTable.length);
			collisions++;
		}
		else {
			hashTable[key.hashCode() % hashTable.length] = new SimpleEntry(key,value);
			System.out.println(key.hashCode() % hashTable.length);
			numItems++;
		}
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
		SimpleEntry<K, V> [] temp = null;
		temp = new SimpleEntry[hashTable.length*2];

		for(int i = 0; i < hashTable.length;i++) {
			if(hashTable[i] != null)
				temp[hashTable[i].getKey().hashCode() % hashTable.length] 
						= new SimpleEntry(hashTable[i].getKey(),hashTable[i].getValue());
		}
		expand++;
		hashTable = temp;

	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < hashTable.length; i++) {
			str.append("index: " + i + "==>" + hashTable[i] + "\n");
		}

		return str.toString();
	}
}
