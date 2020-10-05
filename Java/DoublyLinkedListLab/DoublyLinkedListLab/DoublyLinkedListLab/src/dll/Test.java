/**
 * Title: DoublyLinkedListLab
 * Filename: Test.java
 * Date Written: February 13, 2018
 * Due Date: February 16, 2018
 * Description: Testing the methods of the DoubleLinkedList Class. 
 * 
 *@author Derick Hansraj(N00827531) and Instructor
 */
package dll;

import java.util.NoSuchElementException;

public class Test {

	public static void main(String[] args) {


		DoubleLinkedList<Integer> dll = new DoubleLinkedList<Integer>();

		dll.addToRear(10);
		dll.addToRear(20);
		dll.addToRear(30);
		System.out.println(dll);


		while(dll.size() > 0)
			System.out.println("removing from front " + dll.removeFromFront());
		System.out.println(dll);



		try{
			dll.removeFromFront();
		}
		catch(NoSuchElementException ex)
		{
			System.out.println("error removing from front" + ex.getMessage());
		}

		System.out.println(dll);
		dll.addToFront(10);
		System.out.println(dll);

		dll.addToFront(20);
		System.out.println(dll);

		dll.addToFront(30);




		System.out.println(dll);

		System.out.println("Reverse is :");
		System.out.println(dll.reverseString());

		dll.removeFromRear();

		System.out.println(dll);

		System.out.println("Getting item at pos 1: " + dll.get(1));
		System.out.println(dll);


		try{
			System.out.println("Getting item at pos 4: " + dll.get(4));
		}
		catch(NoSuchElementException ex)
		{
			System.out.println("error " + ex.getMessage());
		}

		
		DoubleLinkedList<Integer> dll2 = new DoubleLinkedList<Integer>();
		dll2.addToFront(5);
		dll2.addToFront(10);
		dll2.addToFront(15);
		dll2.addToFront(20);
		dll2.addToFront(25);
		dll2.addToFront(30);
		dll2.addToFront(35);
		dll2.addToFront(40);

		System.out.println("Using iterator");
		for (Integer i : dll2)
		{
			System.out.println(i);
		}
		
		System.out.println(dll2.toString());

	}

}
