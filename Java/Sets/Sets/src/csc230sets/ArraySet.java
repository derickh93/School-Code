package csc230sets;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;


/*
 * Set implemented using an array. 
 * CSC 230 SP 18 Prof. S. Ochani
 * 
 */


/*
 * Student Author: Derick Hansraj
 * N#: N00827531
 * 
 */


public class ArraySet<E> 
{

	private int numOfItems; 
	private E[] contents;

	/*
	 * parameterized constructor, make array of size passed in
	 * hint: make array of type Object then typecast to E[]
	 */
	public ArraySet(int initialSize) 
	{
		contents = (E[])(new Object[initialSize]);
	}

	/*
	 * default constructor, make array of size 5
	 */
	public ArraySet() 
	{
		contents = (E[])(new Object[5]);
	}

	// return number of items in array, not total size of array
	public int size()
	{
		return numOfItems;
	}

	/*
	 * add the item to this set, expand capacity of array
	 * if needed
	 */
	public void add (E item) {
		if(numOfItems == contents.length)
			expandCapacity();
		if(!contains(item)) {
			contents[numOfItems] = item;
			numOfItems++;
		}
	}

	/*
	 * "expands" contents array
	 * 
	 */
	private void expandCapacity() {
		E[] temp = (E[])(new Object[contents.length*2]);
		for(int i = 0; i < contents.length; i++)
			temp[i] = contents[i];
		contents = temp;
	}

	/*
	 * returns true if the item is in this set, false otherwise
	 */
	public boolean contains(E item) {
		if(numOfItems == 0)
			return false;

		for(int i = 0; i < contents.length && contents[i] != null;i++){
			if(contents[i].equals(item))
				return true;
		}
		return false;


	}

	/*
	 * add all items from set parameter to this set.
	 */
	public void addAll (ArraySet<E> set){
		if(set.size() == 0)
			return;
		if((numOfItems == contents.length) || (contents.length - numOfItems) < set.size())
			expandCapacity();
		for(int i = 0;i < set.size();i++) {
			add(set.contents[i]);
		}
	}

	/*
	 * returns item at specified 0 based position
	 * Throws a NoSuchElementException if the set is empty or position out of range
	 */
	private E get(int pos) throws NoSuchElementException {
		if(numOfItems == 0 || pos < 0 || pos >= numOfItems)
			throw new NoSuchElementException();
		return contents[pos];


	}

	/*
	 * Removes the specified item from the set and returns it.
	 * Throws a NoSuchElementException if the set is empty with message of "empty set"
	 * and a NoSuchElementException with message of "no such item"
	 * if the item is not in the set.
	 */

	public E remove (E item) throws NoSuchElementException {
		int i = 0;
		if (numOfItems == 0)
			throw new NoSuchElementException("Empty set");
		if(!contains(item))
			throw new NoSuchElementException("No such item");

		while(!contents[i].equals(item))
			i++;

		contents[i] = contents[numOfItems - 1];
		contents[numOfItems - 1] = null;

		numOfItems--;
		return item;

	}

	/*
	 * Removes a random item from the set and returns it. Throws
	 * a NoSuchElementException if the set is empty with message of "empty set".
	 */

	public E removeRandom() throws NoSuchElementException {
		if (numOfItems == 0)
			throw new NoSuchElementException("Empty set");
		Random random = new Random();
		int pos = random.nextInt((numOfItems-1) - 0 + 1) + 0;
		E temp = remove(get(pos));

		return temp;


	}

	/*
	 * Returns a new set that is the union of this set and the
	 * parameter set.
	 * DO NOT modify this set
	 */
	public ArraySet<E> union(ArraySet<E> set) {
		ArraySet<E> temp = new ArraySet<E>();
		temp.addAll(this);
		temp.addAll(set);
		return temp;


	}

	/*
	 * equals method needs to be finished
	 */

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		ArraySet<E> other = (ArraySet<E>)obj;

		if (numOfItems != other.numOfItems)
			return false;

		// all above statements are correct, finish code starting here
		int i = 0;
		while(i < numOfItems){
			if(!other.contains(contents[i]))
				return false;
			i++;
		}

		return true; // this can be changed depending on how you write your code
	}


	/*
	 * returns a new set that has items in this set but not in parameter set
	 * Example:
	 * A {1,2,3}
	 * B {2,3}
	 * A.difference(B) will give set of 1
	 */
	public ArraySet<E> difference(ArraySet<E> set) {
		ArraySet<E> temp = new ArraySet<E>();
		for(int i = 0; i < contents.length;i++) {
			if(!set.contains(contents[i]))
				temp.add(contents[i]);
		}
		return temp;
	}


	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < contents.length && contents[i] != null;i++) {
			str.append(contents[i] + " ");
		}
		return str.toString();
	}
}
