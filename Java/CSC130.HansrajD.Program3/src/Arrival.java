import java.util.Random;

import CSC130lib.Queue;

/**
 * Title: CSC130hansrajd.Program3
 * Filename: Arrival.java
 * Date Written: November 17, 2017
 * Due Date: November 27, 2017
 * Description: This class creates a Arrival object.
 * 
 *@author Derick Hansraj
 */
public class Arrival extends Thread {

	//instance variables
	private Queue<Airline> queue =  new Queue<Airline>();
	private int time = 5000;
	private boolean running = true;
	Random random = new Random();


	/**
	 * default constructor - creates a Arrival object with the time it took to Land.
	 */
	public Arrival(int time) {
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


			try {
				sleep(SimulationTime.timeTillNext(1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int landingTime = SimulationTime.timeTillNext(4);
			String airlineId = SimulationTime.AIRLINES[random.nextInt(17)+1] + random.nextInt(100)+1;
			long timeEntered = System.currentTimeMillis();
			Airline airline = new Airline(airlineId, timeEntered);
			queue.enqueue(airline);
			System.out.println("Minute " + SimulationTime.timeInSec(System.currentTimeMillis()-Program3App.startTime) 
			+ " - Added flight" + airline.getFlightId() + " to arrival Queue" + 
			"\n" + "Random wait time before next arrival: " + landingTime
			+ " mins");

			try {
				sleep(SimulationTime.timeTillNext(4000));
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
	 * toString method -- this method returns the state of the Arrival object
	 * 
	 * @return a reference to a string object that contains the values stored in
	 *         the instance variables
	 */
	public String toString() {
		return "Queue: " + queue + " Landing Time: " + time;

	}
}
