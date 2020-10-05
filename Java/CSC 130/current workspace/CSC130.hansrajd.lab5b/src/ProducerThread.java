import javax.swing.JOptionPane;

public class ProducerThread extends Thread {
	private ArrayQueue<String> queue;
	private String[] picture;
	// TODO: constructor
	// initialize the queue and the array
	// using the 2 parameters
	public ProducerThread(@SuppressWarnings("rawtypes") ArrayQueue q, String[] p) {
		queue = q;
		picture = p;
	}
	public void run() {
		// TODO loop for each picture and the picture is not null
		// Note: possible exceptions that can occur are
		// QueueFullException and InterruptedException
		while(picture != null)
		{
			// TODO loop while the queue is full
			while(queue.isFull()) {
				// TODO add an item from the array to the queue
				// display the item added to the queue
				// sleep for 100 - 1000 milliseconds
				for(int i = 0; i < picture.length; i++) {
					queue.enqueue(picture[i]);
					System.out.println(picture[i]);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
			// TODO When the thread stop's running,
			// display a message indicating the thread has stopped
			JOptionPane.showMessageDialog(null, "The thread has stopped");
		
	}
}