package queue;

public class QueueEmptyException extends RuntimeException{
	public QueueEmptyException() {
		super("Queue Underflow Exception...");
	}
		
		public QueueEmptyException(String arg) {
			super(arg);
		}
}
