import java.util.Random;

import CSC130lib.Queue;

/**
 * Title: CSC130hansrajd.Program3
 * Filename: Departure.java
 * Date Written: November 17, 2017
 * Due Date: November 27, 2017
 * Description: This class creates a Departure object.
 * 
 *@author Derick Hansraj
 */
public class Departure extends Thread {

	//instance variables
	private Queue<Airline> queue;
	private int time = 4000;
	private boolean running;
	Random random = new Random();


	/**
	 * default constructor - creates a Departure object with the time it took to takeoff.
	 */
	public Departure(int time) {
		this.time = time;
	}

	/**
	 * getQueue method -- returns what's stored in the instance variable queue
	 * 
	 * @return the state of the instance variable queue
	 */
	public Queue<Airline> getQueue() {
		return queue;
	}

	/**
	 * getTime method -- returns what's stored in the instance variable time
	 * 
	 * @return the state of the instance variable time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * run method -- Starts the thread and keeps it running until the main program
	 * calls the stopRunning method.
	 */
	public synchronized void run() {

		while(running) {
			int takeoffTime = SimulationTime.timeTillNext(5);

			try {
				sleep(SimulationTime.timeTillNext(random.nextInt(5)+1));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String airlineId = SimulationTime.AIRLINES[random.nextInt(17)+1] + random.nextInt(100)+1;
			long timeExited = System.currentTimeMillis();
			Airline airline = new Airline(airlineId, timeExited);
			queue.enqueue(airline);
			System.out.println("Minute " + takeoffTime
					+ " - Added " + airline.toString() + " to departure Queue" + "\n" + "Random wait time before next departure: " + takeoffTime
					+ " mins");
			try {
				sleep(takeoffTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * stopRunning method -- Stops the thread from running by changing the running state to false
	 */
	public synchronized void stopRunning(){
		running = false;
	}

	/**
	 * toString method -- this method returns the state of the Departure object
	 * 
	 * @return a reference to a string object that contains the values stored in
	 *         the instance variables
	 */
	public String toString() {
		return "Queue: " + queue + " Takeoff Time: " + time;

	}
}
