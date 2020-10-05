package dll;
/**
 * Title: DoublyLinkedListLab
 * Filename: Test.java
 * Date Written: January 30, 2018
 * Due Date: February 1, 2018
 * Description: Test the methods of the DoubleLinkedList class. 
 * 
 *@author Derick Hansraj(N00827531) and Instructor
 */
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


		System.out.println("\n\n********************Further Testing using Card Class********************\n\n");

		//instantiate a new DoubleLinkedList
		DoubleLinkedList dll2 = new DoubleLinkedList();

		//Add 3 Heart Cards to the list and display it
		for(int i = 2; i < 5; i++) {
			dll2.addToFront(new Card(i, "Hearts"));
		}
		System.out.println(dll2.toString());

		//Add 3 Spade Cards to the list and display it
		for(int i = 4; i < 7; i++) {
			dll2.addToRear(new Card(i, "Spades"));
		}
		System.out.println(dll2.toString());

		//Call reverse string on the list
		System.out.println("Testing the reverse string method:\n" + dll2.reverseString() + "\n");

		//Call the removeFromRear method 3 times
		System.out.println("Testing the removeFromRear method by removing:\n" + dll2.removeFromRear() + "\n");
		System.out.println(dll2);



		//Call the removeFromFront method 3 times
		System.out.println("Testing the removeFromFront method by removing:\n" + dll2.removeFromFront() + "\n");
		System.out.println(dll2);


		//Call the get method twice
		System.out.println("Testing the get method on index 1\n" + dll2.get(1) + "\n");
		System.out.println(dll2);
		try {
			System.out.println("Testing the get method on index 1\n" + dll2.get(4) + "\n");
			System.out.println(dll2);
		}
		catch(NoSuchElementException ex)
		{
			System.out.println("error " + ex.getMessage());
		}


	}
}
