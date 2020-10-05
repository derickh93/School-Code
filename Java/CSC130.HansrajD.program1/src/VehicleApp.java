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
	static Vehicle [] v =  new Vehicle[10];


	static void getVehicles() {
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
					v[num] = new Automobile(manufacturer, model, id);
					System.out.println(v[num]);
					num++;
				}
				else if(line.charAt(0) == 'B') {
					String id = s.next();
					String manufacturer = s.next();
					String model = s.next();
					v[num] = new Boat(manufacturer, model, id);
					System.out.println(v[num]);
					num++;
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
		System.out.println("\nReading transactions data file...");
		String line = null;
		Scanner scan = null;
		int num = 0;
		try {
			scan = new Scanner(new File("transactions.txt"));
			// read and output each line
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				Scanner s = new Scanner(line).useDelimiter("\\s*,\\s*");
				int[] arr = new int[line.length() - 2];
				boolean b = true;
				int num1;
				int num2;
				String transaction = s.next();
				String id = s.next();
				String var3 = s.next();
				while(b) {
					if(num > v.length)
						num = 0;
					if(transaction.equals("rent")) {
						if(id.charAt(0) != v[num].getVehicleId().charAt(0))
							num++;
						else if(id.charAt(0) == 'A' && v[num].getVehicleId().charAt(0) == 'A') { 
							if(v[num].isAvailable() == false) {
								System.out.println("Didnt rent automobile");
								num++;
								b = false;
							}
							else if(v[num].isAvailable() == true) {
								v[num].rentVehicle(transaction, id, var3);
								System.out.println("Renting Automobile");
								num++;
								b = false;
							}
							else {
								System.out.println("AUTOMOBILE UNAVAILABLE\n");
								b = false;
							}
						}
						else if(id.equals("B") && v[num].getVehicleId().charAt(0) == 'B') { 
							if(v[num].isAvailable() == false) {
								System.out.println("Didnt rent Boat");
								num++;
								b = false;
							}
							else if(v[num].isAvailable() == true) {
								v[num].rentVehicle(transaction, id, var3);
								System.out.println("Renting Boat");
								num++;
								b = false;
							}
							else {
								System.out.println("BOAT UNAVAILABLE\n");
								b = false;
							}
						}
						else {
							System.out.println("INVALID VEHICLE TYPE\n");
							b = false;
						}
					}
					else if(transaction.equals("return")) {
						if(line.length() == 3) {
							num1 = Integer.parseInt(var3);
							arr[0] = num1;
						}
						else if(line.length() == 4) {
							num1 = Integer.parseInt(var3);
							num2 = Integer.parseInt(s.next());
							arr[0] =  num1;
							arr[1] = num2;
						}
						if(v[num].isAvailable() == false && id.equals(v[num].getCustomerId())) {
							System.out.println(v[num].returnVehicle(transaction, id, arr));
							num++;
							b = false;
						}
						else if(id.equals(v[num].getCustomerId())) {
							System.out.println("Vehicle was not rented");
							b = false;
						}
					}
					else {
						System.out.println("invalid transaction type\n");
						b = false;
					}
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
		getVehicles();
		transactions();
	}
}
