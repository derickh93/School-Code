import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * File: Lab4App.java
 * Description: Reads data from a csv file and creates a simple hash table.
 * @author Derick Hansraj
 * N#: N00827531
 */



public class Lab4App {

	public static void main (String [] args) throws FileNotFoundException {
		String first;
		String last;
		String email;
		LastName lName;
		Person person;
		SimpleHashTable<LastName,Person> hash = new SimpleHashTable<LastName,Person>();
		Scanner scanner = new Scanner(new File("Mock_Data.csv"));
		scanner.nextLine();
		while(scanner.hasNext()){
			String [] line = scanner.nextLine().split(",");
			first = line[0];
			last = line[1];
			email = line[2];
			person = new Person(first,last,email);
			lName = new LastName(last);
			
			hash.put(lName, person);
		}
		scanner.close();
		
		System.out.println(hash.toString());
		System.out.println(hash.getCollisions());
		System.out.println(hash.getExpand());
	}
}
