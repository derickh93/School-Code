package queue;

public class QueueApp {
	public static void main (String[] args) {
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(5);
		q.enqueue(10);
		System.out.println(q);
	}

}
