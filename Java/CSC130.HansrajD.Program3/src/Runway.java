/**
 * Title: CSC130hansrajd.Program3
 * Filename: Runway.java
 * Date Written: November 17, 2017
 * Due Date: November 27, 2017
 * Description: This class creates a Runway object.
 * 
 *@author Derick Hansraj
 */
public class Runway extends Thread {

	//instance variables
	private Arrival arrival;
	private Departure departure;
	private boolean running = true;

	/**
	 * default constructor - creates a Runway object with Arrival and Departure objects
	 */
	public Runway(Arrival arrival, Departure departure) {
		this.arrival = arrival;
		this.departure = departure;
	}

	/**
	 * run method -- Starts the thread and keeps it running until the main program
	 * calls the stopRunning method.
	 */
	public synchronized void run() {
		while(running){
			try {
				sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while(!arrival.getQueue().isEmpty()){
				Airline arrivalQueue = arrival.getQueue().dequeue();
				long aEnterTime = SimulationTime.timeInMinutes(arrivalQueue.getEntered() - Program3App.startTime);
				System.out.println("Minute " +  SimulationTime.timeInMinutes(System.currentTimeMillis()- Program3App.startTime)
				+ " - Flight " + arrivalQueue.getFlightId() + " cleared for landing - Entered Queue at " + 
				aEnterTime + " - waited " +
				SimulationTime.timeInMinutes(System.currentTimeMillis() - arrivalQueue.getEntered())
				+ " mins");
				try { 
					sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while(!departure.getQueue().isEmpty()){
				Airline departureQueue = departure.getQueue().dequeue();
				long dEnterTime = SimulationTime.timeInMinutes(departureQueue.getEntered() - Program3App.startTime);
				System.out.println("Minute " + SimulationTime.timeInMinutes(System.currentTimeMillis()- Program3App.startTime)
				+ " - Flight " + departureQueue.getFlightId() + " cleared for take off - Entered Queue at " + 
				dEnterTime+ " - waited " +
				SimulationTime.timeInMinutes(System.currentTimeMillis() - departureQueue.getEntered())
				+ " mins");
				try { 
					sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	 * toString method -- this method returns the state of the Runway object
	 * 
	 * @return a reference to a string object that contains the values stored in
	 *         the instance variables
	 */
	public String toString() {
		return "Arrival: " + arrival + " Departure: " + departure;

	}
}