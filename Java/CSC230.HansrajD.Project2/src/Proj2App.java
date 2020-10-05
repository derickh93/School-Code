import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * File: Proj2App.java
 * Description: Reads data from a csv file and creates a simple hash table.
 * @author Derick Hansraj
 * N#: N00827531
 */



public class Proj2App {

	public static void main (String [] args) throws FileNotFoundException {
		String id;
		String first;
		String last;
		String email;
		PersonInformation lName;
		Person person;
		HashTable<PersonInformation,Person> hash = new HashTable<PersonInformation,Person>();
		Scanner scanner = new Scanner(new File("proj2data.csv"));
		scanner.nextLine();
		while(scanner.hasNext()){
			String [] line = scanner.nextLine().split(",");
			id = line[0];
			first = line[1];
			last = line[2];
			email = line[3];
			person = new Person(id,first,last,email);
			lName = new PersonInformation(id,last);

			hash.put(lName, person);
		}
		scanner.close();

		try {
			System.out.println(hash.toString());
			System.out.println("Collisions: " + hash.getCollisions());
			System.out.println("Expansions: " + hash.getExpand());
			System.out.println("Num Items: " + hash.getNumItems());

			PersonInformation p1 = new PersonInformation("101","Rembrant");
			System.out.println(hash.get(p1));

			PersonInformation p2 = new PersonInformation("71","Sprowell");
			System.out.println(hash.get(p2));
			
			PersonInformation p3 = new PersonInformation("227","Ferfulle");
			System.out.println(hash.get(p3));
			
			PersonInformation p4 = new PersonInformation("9","Hansraj");
			System.out.println(hash.get(p4));



		} catch (NoSuchElementException e) {
			System.err.println("NoSuchElementException: " + e.getMessage());	
		}
	}
}
