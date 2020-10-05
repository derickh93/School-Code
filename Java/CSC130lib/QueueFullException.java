package CSC130lib;

public class QueueFullException extends RuntimeException{
	public QueueFullException() {
		super("Queue Overflow Exception...");
	}
		
		public QueueFullException(String arg) {
			super(arg);
		}
}
