import java.util.*;

/**
* Implementation of a generic bag in Java using a HashMap
* where each item in the map (x, n) stores item x and the number of occurences n
**/

public class Bag<T>
{
	//Initialise instantiate hashmap to implement bag
	private HashMap<T,Integer> map; 
	
	public Bag(){
		//Initialise the hasmap in the no-args constructor
		//this keyword refers to the current class we are in
		this.map = new HashMap<T,Integer>();  
	}
	
	/****
	* Method to add item x of type T to Bag
	*****/
	public void add(T x) 
	{	//Check if the map contains this item already
		if(map.containsKey(x)) 
		{	//If it does see how many times this element occurs
			int numElem = map.get(x);
			//Increment by one the number of times this element occurs
			//i.e. add another of these elements to the bag
			map.put(x, (numElem+1)); 
		}
		else
		{	//If the element does not exist create a new item in the map
			//with the number of items set to one
			map.put(x, 1);
		}
	}
	
	/****
	* Method to get frequency of item x with type T in Bag
	* where x is not null 
	*****/
	public int freq(T x)
	{	// If the element is in the bag
		if(map.containsKey(x)) 
		{	//Return the value where the item x is the key i.e. the number 
			//of occurences of that element
			return map.get(x);
		}
		else
		{	//If this element is not in the bag return 0
			return 0; 
		}
	}
	
	//void remove(T,x) - not to be coded
	
	/****
	* Method to get most frequent item x with type T in Bag
	* where x is not null and if more than one item is the most 
	* frequent any of these elements can be returned
	*****/
	public T mode()
	{	//Check is the map emptpy
		if(map.isEmpty())
		{	//Return null if it is
			return null;
		}
		else
		{	//If not emptpy intialise an integer to store the num of occurences of the most 
			// frequent item  
			Integer largestFrequency = new Integer(0);
			//Also initialise a variable to store the key with the largest frequency 
			T largestKey = null;
			
			//Iterate through the keys in the bag/map
			for(T key : map.keySet()){
				//Store the current item frequency
				Integer currentFrequency = map.get(key);
				//If the current is greater than the largest 
				if(currentFrequency > largestFrequency){
					//Set the largest frequency to the current frequency 
					largestFrequency = currentFrequency;
					//And set the largest key to the current key
					largestKey = key;
				}
			}
			//Return the largest key
			return largestKey;
		}
	}
}