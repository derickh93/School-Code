import java.util.Random;

public class ConsumerThread extends Thread {
	private ArrayQueue<String> queue;
	private ProducerThread producer;
	private int count;
	private PictPanel panel;
	@SuppressWarnings("unused")
	private boolean running;
	Random rand = new Random();
	// TODO: constructor
	// initialize the queue, producer thread, and the panel
	// using the 3 parameters
	public ConsumerThread(ArrayQueue<String> q, ProducerThread p, PictPanel p2) {
		queue = q;
		producer = p;
		panel = p2;
	}
	public void run() {
		// TODO loop while the producer thread isAlive or the queue is not empty
		// Note: possible exceptions that can occur are
		// QueueEmptyException and InterruptedException
		while(producer.isAlive()) {
			// TODO loop while the queue is empty
			while(queue.isEmpty()) {
				// TODO if the queue is not empty
				while(!queue.isEmpty()) {
					// take an item from the queue
					// call the panel's drawPict method and pass the item
					// Use Thread.currentThread().getName() to output
					// the name of this thread and the item it processed
					// add 1 to the count
					// sleep for 1000 to 5000 milliseconds (random)
					panel.drawPict(queue.dequeue());
					System.out.println(Thread.currentThread().getName());
					count++;
					int sleepNum = rand.nextInt((5000 - 1000 + 1) + 1000);
					try {
						Thread.sleep(sleepNum);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		// TODO When the thread stop's running,
		// display its name and the number of items processed
		System.out.println(producer.getName() + "\nAmount of items processed: " + count);
	}
}