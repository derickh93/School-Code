import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * File: SimpleHashTable.java
 * Description: Defines a hashtable that will chain data if there are duplicates.
 * @author Derick Hansraj
 * N#: N00827531
 */

public class HashTable<K, V> {

	// instance vars:
	// create an array of SimpleEntry called hashtable
	// counter for collisions
	// counter for how many times you expanded capacity
	// will need get methods for those counters
	private LinkedList<SimpleEntry<K,V>> [] hashTable;
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

	public int getNumItems() {
		return numItems;
	}

	// default constructor, instantiate the array to size 5
	@SuppressWarnings("unchecked")
	public HashTable() {
		// the syntax is simillar to the one used in the Set lab
		hashTable = new LinkedList[capacity];
	}

	// call hashcode method of key, compress it, then store key, value at that position
	// count how many collisions you had, up to you if you want to overwrite or not
	// need to expandcapacity if needed
	public void put(K key, V value) {
		loadFactor = numItems/hashTable.length;
		if(loadFactor > threshold)
			this.reshash();


		if(hashTable[key.hashCode() % hashTable.length] == null) {
			hashTable[key.hashCode() % hashTable.length] = new LinkedList<SimpleEntry<K,V>>();
			hashTable[key.hashCode() % hashTable.length].add(new SimpleEntry<K,V>(key,value));
		}
		else {
			hashTable[key.hashCode() % hashTable.length].add(new SimpleEntry<K,V>(key,value));
			collisions++;
		}
		numItems++;
	}

	// return Value based on key or throw NoSuchElementException if it doesn't exist 
	@SuppressWarnings("unchecked")
	public V get(K key) {
		int hash = key.hashCode() % hashTable.length;
		V info;
		V info2;
		V value = null;

		for(int i = 0; i < hashTable[hash].size() ; i++) {
			info = (V) key;
			info2 = (V) hashTable[hash].get(i).getKey();

			if(hashTable[hash] == null)
				throw new NoSuchElementException("Item does not exist");
			if(info.equals(info2))
				value = hashTable[hash].get(i).getValue();
		}
		if(value == null)
			throw new NoSuchElementException("Item does not exist");
		return value;
	}

	// this is the expandcapacity method
	// expand array and recompress the values to find new position

	@SuppressWarnings("unchecked")
	private void reshash() {
		LinkedList<SimpleEntry<K, V>> [] temp = new LinkedList[hashTable.length*2];

		V person = null;
		K personInfo = null;;

		for(int i = 0; i < hashTable.length;i++) {
			if(hashTable[i] != null) {

				for(int j = 0; j < hashTable[i].size();j++) {
					person = hashTable[i].get(j).getValue();
					personInfo = hashTable[i].get(j).getKey();

					if(temp[personInfo.hashCode() % temp.length] == null) {
						temp[personInfo.hashCode() % temp.length] = new LinkedList<SimpleEntry<K,V>>();
						temp[personInfo.hashCode() % temp.length].add(new SimpleEntry<K,V>(personInfo,person));
					}
					else {
						temp[personInfo.hashCode() % temp.length].add(new SimpleEntry<K,V>(personInfo,person));
					}
				}
			}
		}
		expand++;
		hashTable = new LinkedList[temp.length];
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
