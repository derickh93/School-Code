import javax.swing.JOptionPane;

public class Program3App {
	public static long startTime;
	public static long simulationTime;
	private static Arrival arrival = new Arrival(5000);
	private static Departure departure =  new Departure(4000);
	private static Runway runway;


	public static void startSimulation(long time) {
		startTime = System.currentTimeMillis();
		simulationTime = time * 60000;
		//TODO: Start each thread
		arrival.start();
		departure.start();
		runway =  new Runway(arrival, departure);
		runway.start();

		// Loop - run simulation for specified time
		while(System.currentTimeMillis() < startTime + simulationTime){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//TODO: stop the loop in each thread – call the stopRunning method

		arrival.stopRunning();
		departure.stopRunning();
		runway.stopRunning();
		try {
			//TODO: interrupt each thread – method from Thread class
			arrival.join();
			departure.join();
			runway.join();

			//TODO: wait for each thread to die – method from Thread class
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		/* TODO:
		Input the length of time to run the simulation (in minutes)
		Create an instance of Program3
		Call the startSimulation method and pass the time to it
		Loop while the Arrival or Departure threads are alive
		Stop the Runway thread from running
		 */

		long startSim = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the amount of time for the simulation"));
		Program3App p3 = new Program3App();
		startSimulation(startSim);
		while(arrival.isAlive() || departure.isAlive()) {

		}

		runway.stopRunning();
	}
}