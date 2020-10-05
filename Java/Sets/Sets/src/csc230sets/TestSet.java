package csc230sets;
/*
 * Set implemented using an array. 
 * CSC 230 SP 18 Prof. S. Ochani
 * 
 */

import java.util.NoSuchElementException;

/*
 * Student Author: Derick Hansraj
 * N#: N00827531
 * 
 */
public class TestSet {

	public static void main(String[] args) {


		// Make sure to test all methods of your set

		//Create new ArraySet using parameterized constructor and default constructor
		ArraySet as = new ArraySet(10);
		ArraySet as2 = new ArraySet();

		//Testing add method
		as.add(1);
		as.add(2);
		as.add(3);
		as.add(6);
		as.add(5);
		as.add(23);

		as2.add(2);
		as2.add(3);

		//Testing size method
		System.out.println("Size of as: " + as.size() + "\nSize of as2: " + as2.size());

		//Testing contains method
		System.out.println("as contains 2: " + as.contains(2) + "\nas2 contains 5: " + as2.contains(5));

		//Testing addall method
		as.addAll(as2);
		as2.addAll(as);
		System.out.println("Testing addall on as : " + as.toString() +"\nTesting addall on as2: " + as2.toString());

		//Testing remove method

		try {

			System.out.println("Testing remove 5 on as: " + as.remove(5));
			System.out.println("Testing remove 33 on as2: " + as2.remove(12));

		} catch (NoSuchElementException name) {
			System.out.println("NoSuchElementException: " + name.getMessage());
		}

		//Testing removeRandom method
		System.out.println("Testing removeRandom on as: " + as.removeRandom());

		//Testing union method
		as.add(7);
		as.add(56);

		as2.add(45);
		as2.add(8);
		System.out.println("Testing union method on as: " + as.union(as2));

		//Testing equals method
		System.out.println("Testing equals method: " + as.equals(as2));
		System.out.println("as to string: " + as.toString());
		System.out.println("as2 tostring: " + as2.toString());
		as.addAll(as2);
		as2.addAll(as);
		System.out.println("as to string: " + as.toString());
		System.out.println("as2 tostring: " + as2.toString());


		System.out.println("Testing equals method: " + as2.equals(as));

		//Testing difference method
		as2.remove(6);
		System.out.println("Testing difference method: " + as.difference(as2));

	}
}
