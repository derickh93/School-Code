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

	}

}
