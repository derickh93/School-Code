package ht;

import java.util.Map;

public class SimpleEntry<K, V> implements Map.Entry<K, V> {
	
	// declare two instance vars:
	// key of type K
	// value of type V
	private K key;
	private V value;
	
	// parameterized constructor, you know what to do
	public SimpleEntry(K key, V value) {
		this.key = key;
		this.value = value;	
	}
	
	@Override
	public K getKey() {
		return key;
	}
	
	@Override
	public V getValue() {
		return value;	
	}
	
	// this must set the value to newValue but return the old value
	@Override
	public V setValue(V newValue) {
		V oldValue = value;
		value = newValue;
		return oldValue;
	}
}