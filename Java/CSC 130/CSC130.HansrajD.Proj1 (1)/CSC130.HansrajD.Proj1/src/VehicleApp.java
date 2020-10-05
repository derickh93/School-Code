import java.util.Scanner;
import java.io.*;
/**
 * Title: CSC130HansrajD.Proj
 * Filename: VehicleApp.java
 * Date Written: September 25, 2017
 * Due Date: October 02, 2017
 * Description: Create Vehicle, Automobile, and Boat objects to test the various defined methods with the use of an array.
 * 
 *@author Derick Hansraj
 */ 
public class VehicleApp {

	static void getVehicles() {
		Vehicle [] v =  new Vehicle[10];
		String line = null;
		Scanner scan = null;
		int num = 0;

		System.out.println("Reading vehicles data file...");
		try {
			scan = new Scanner(new File("vehicles.txt"));
			// read and output each line
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				Scanner s = new Scanner(line).useDelimiter("\\s*,\\s*");
				if(line.charAt(0) == 'A') {
					String id = s.next();
					String manufacturer = s.next();
					String model = s.next();
					v[num] = new Automobile(null, manufacturer, model);
					System.out.println("Vehicle [id=" + id + ", " + v[num]);
				}
				else if(line.charAt(0) == 'B') {
					String id = s.next();
					String manufacturer = s.next();
					String model = s.next();
					v[num] = new Boat(null, manufacturer, model);
					System.out.println("Vehicle [id=" + id + ", " + v[num]);
				}					
			}
		} catch (IOException e) {
			System.out.println("Error accessing file");
			e.printStackTrace();
		} finally {
			scan.close();
		}
	}

	public static void transactions() {
		System.out.println("Reading transactions data file...");
		String line = null;
		Scanner scan = null;
		try {
			scan = new Scanner(new File("transactions.txt"));
			// read and output each line
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				Scanner s = new Scanner(line).useDelimiter("\\s*,\\s*");
				String transaction = s.next();
				String vehicleType = s.next();
				String customerID = s.next();
				if(transaction.equals("rent")) {
					if(vehicleType.charAt(0) == 'A') {
						
					}
					else if(vehicleType.charAt(0) == 'B') {

						
					}
					else
						System.out.println("invalid vehicle type");

				}
				else if(transaction.equals("return")) {
					
				}
			}
		} catch (IOException e) {
			System.out.println("Error accessing file");
			e.printStackTrace();
		} finally {
			scan.close();
		}
	}


	public static void main (String[] args) {
		Vehicle [] v =  new Vehicle[10];
		getVehicles();
		transactions();   
	}
}
