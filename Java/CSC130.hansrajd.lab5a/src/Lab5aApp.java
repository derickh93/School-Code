
/***
 * Title: Lab 5: Lab5aApp Class
 * 
 * Description: This will test the various methods created in the
 * ArrayQueue class. It will create 2 objects to test the methods.
 * 
 * @author Derick Hansraj
 *
 */
public class Lab5aApp {
	public static void main(String [] args) throws QueueException{
		/**Creating two ArrayQueue objects and testing various methods to 
		ensure that they work correctly under all desired conditions. The lines 
		that cause an error have been commented out.
		 */
		ArrayQueue q1 = new ArrayQueue();
		System.out.println(q1.toString());

		ArrayQueue q2 = new ArrayQueue(3);
		System.out.println(q2.toString());
		q2.enqueue(new Integer(4));
		System.out.println(q2.toString());
		q2.enqueue(new Integer(5));
		System.out.println(q2.toString());
		q2.enqueue(new Integer(6));
		System.out.println(q2.toString());
		//q2.enqueue(new Integer(7));

		System.out.println("The queue is empty: " + q1.isEmpty());
		System.out.println("The queue is empty: " + q2.isEmpty());

		//System.out.println(q1.front());
		//System.out.println(q1.rear());
		System.out.println("The front of q2 contains: " + q2.front());
		System.out.println("The rear of q2 contains: " + q2.rear());

		//System.out.println(q1.dequeue());

		while(!q2.isEmpty()) {
			System.out.println(q2.dequeue());
		}
		System.out.println("Queue q1 is empty: " + q1.isEmpty());
		System.out.println("Queue q2 is empty: " + q2.isEmpty());

		System.out.println(q1.isEmpty());
		System.out.println(q2.isEmpty());

		System.out.println(q1.toString());
		System.out.println(q2.toString());



	}
}
