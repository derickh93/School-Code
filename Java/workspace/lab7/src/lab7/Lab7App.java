package lab7;
import queues.*;
import exceptionclasses.*;
/**
 * <p>Title: Lab7App Class</p>
 *
 * <p>Description: </p>
 *
 * @author Joe Carranza & Derick Hansraj
 */
public class Lab7App {

	public static void main(String[] args) 
	{
		CircularArrayQueue<Product> theQueue = new CircularArrayQueue<Product>(3);
		System.out.println("theQueue contains: " + theQueue.toString() + "\n");

		theQueue.enqueue(new Product("111", 1, 1.99));
		theQueue.enqueue(new Product("222", 2, 2.99));

		System.out.println("theQueue contains: " + theQueue.toString() + "\n");

		System.out.println("Dequeued item: " + theQueue.dequeue() + "\n");
		System.out.println("theQueue contains: " + theQueue.toString() + "\n");

		System.out.println("Dequeued item: " + theQueue.dequeue() + "\n");
		System.out.println("theQueue contains: " + theQueue.toString()  + "\n");

		theQueue.enqueue(new Product("333", 3, 3.99));
		theQueue.enqueue(new Product("444", 4, 4.99));

		System.out.println("theQueue contains: " + theQueue.toString() + "\n");

		System.out.println("Dequeued item: " + theQueue.dequeue());
		System.out.println("Dequeued item: " + theQueue.dequeue() + "\n");
		System.out.println("theQueue contains: " + theQueue.toString() + "\n");

		theQueue.enqueue(new Product("333", 3, 3.99));
		theQueue.enqueue(new Product("444", 4, 4.99));
		System.out.println("theQueue contains: " + theQueue.toString() + "\n");

		theQueue.enqueue(new Product("555", 5, 5.99));
		theQueue.enqueue(new Product("666", 6, 6.99));
		System.out.println("theQueue contains: " + theQueue.toString() + "\n");

		theQueue.enqueue(new Product("777", 7, 7.99));
		theQueue.enqueue(new Product("888", 8, 8.99));
		System.out.println("theQueue contains: " + theQueue.toString() + "\n");

		System.out.println("Dequeued all: ");
		while(!theQueue.isEmpty())
		{
			System.out.println("Dequeued item: " + theQueue.dequeue());
		}

		System.out.println("\n" + "theQueue contains: " + theQueue.toString() + "\n");

		System.out.println("Dequeue on an empty queue:");
		try{
			System.out.println("Dequeue on an empty queue:" + theQueue.dequeue());
		}catch(EmptyCollectionException ex){
			System.out.println(ex.getMessage());
		}

        theQueue = new CircularArrayQueue<Product>(3);
        theQueue.enqueueFront(new Product("111", 1, 1.99));
        theQueue.enqueueFront(new Product("222", 2, 2.99));
        theQueue.enqueueFront(new Product("333", 3, 3.99));
        System.out.println("\ntheQueue contains:" + theQueue.toString());

        while(!theQueue.isEmpty())
        {
            System.out.println("\nDequeued item:" + theQueue.dequeueRear());
            System.out.println("\ntheQueue contains: \n" + theQueue.toString());
        }
    }
}