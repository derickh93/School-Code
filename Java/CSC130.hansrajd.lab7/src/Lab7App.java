
/**
 * Title: csc130.hansrajd.lab7
 * Filename: Lab7App.java
 * Date Written: November 21, 2017
 * Due Date: December 2, 2017
 * Description: Test the methods from the Product, Packet, ArrayList, 
 * OrderedArrayList and and UnorderedArrayList classes.
 * @author F. Graham  & Wenjie Cao & Derick Hansraj
 **/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;


public class Lab7App {

	/**
	 * getData method -- gets the products from an SQLite database
	 * @return the an array of products
	 */
	public static Product[] getData(){		
		Statement stmt = null;
		int records = 0;
		Product[] products = null; 
		try {
			Class.forName("org.sqlite.JDBC");
			String local = System.getProperty("user.dir");
			Connection c = DriverManager.getConnection("jdbc:sqlite:" + local + "/libraries/products.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");			

			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM products;");
			ResultSetMetaData rsmd = rs.getMetaData();			

			for(int i = 1; i <= rsmd.getColumnCount(); i++)	{
				System.out.print(String.format("%-12s", rsmd.getColumnLabel(i)) + "\t");
				System.out.print(rsmd.getColumnTypeName(i) + "\t");
				System.out.println(rsmd.getPrecision(i));
			}

			rs = stmt.executeQuery("select count (*) AS totalRecords from products");
			int totalRecords = rs.getInt("totalRecords");
			System.out.println("Records: " + totalRecords);

			rs = stmt.executeQuery("SELECT * FROM products;");
			if(rs != null){
				products = new Product[totalRecords];
				while (rs.next()) {
					String prodId = rs.getString("prodId");
					int quantity = rs.getInt("quantity");
					double price = rs.getFloat("price");

					System.out.println(String.format("%3s %-6s %3d %6.2f",
							records, prodId, quantity, price));	
					products[records++] = new Product(prodId, quantity, price);
				}
				System.out.println();
			}

			stmt.close();
			c.commit();
			c.close();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException se){
			System.err.println(se.getClass().getName() + ": " + se.getMessage());
		}
		return products;
	}
	/**
	 * testSearch -This method searchs for a Product from a database. If found it returns the index, else 
	 * it returns a message stating the Product was not found.
	 * @param key -The Product we are searching for.
	 * @param list -The list in which we are searching for the key.
	 * @return -A string stating whether or not the Product was found.
	 */

	public static String testSearch(Product key, LinkedList<Product> list) {
		String str = "Searching for product " + key.getId();
		int pos = list.search(key);
		if(pos >= 0)
			str += "\nProduct id: " + key.getId() + " found at index position: " + pos + "\n";
		else
			str += "\nProduct " + key.getId() + " was not found in the list\n";

		return str;

	}

	/**
	 * testRemove -This method removes a Product from the list if it is found.
	 * @param key -The product we are removing from the list.
	 * @param list -The list we are searching for the Product.
	 * @return -A string stating whether or no the Product was removed.
	 */

	public static String testRemove(Product key, LinkedList<Product> list) {
		String str = "Removing product " + key.getId();
		Product temp = list.remove(key);

		if(temp != null) {
			str += "\nProduct " + key.getId() + 
					" removed from list\n" + list.toString();
		}
		else 
			str += "\nProduct " + key.getId() + " not found in the list\n";

		return str;	
	}

	/**
	 * orderPacket -This method creates an OrderedArrayList and scans in data from 
	 * packet.dat. Each line is inserted into the OrderedArrayList in order by the line
	 * number by use of the compareTo method in the Packet Class.
	 * @throws FileNotFoundException -if the file could not be found.
	 */
	public static void orderPacket() throws FileNotFoundException  {
		LinkedList<Packet> packetOne = new UnorderedLinkedList<Packet>();
		Scanner scan = new Scanner(new File("packet.dat"));
		String line = new String();
		int pos = 0;
		while (scan.hasNextLine()) {
			pos = scan.nextInt();
			line = scan.nextLine();
			Packet p1 = new Packet(pos, line);
			packetOne.insert(p1);
			System.out.println(packetOne);
		}
	}

	public static void main (String[] args) throws FileNotFoundException {
		//Create two new UnorderedLinkedList
		LinkedList<Product> list1 = new UnorderedLinkedList<Product>();
		LinkedList<Product> list2 = new UnorderedLinkedList<Product>();

		//Display the two UnorderedLinkedLists
		System.out.println(list1.toString());
		System.out.println(list2.toString());

		//Storing the data obtained with getData() into an array of Products
		Product[] products = getData();
		for(int i = 0; i < products.length; i++) {
			list2.insert(products[i]);
			System.out.println(list2.toString());
		}
		//Testing the testSearch method
		System.out.println(testSearch(new Product("264j45",0,0), list2));
		System.out.println(testSearch(new Product("344d97",0,0), list2));
		System.out.println(testSearch(new Product("344d97",0,0), list1));
		System.out.println(testSearch(new Product("456u78",0,0), list2));
		System.out.println(testSearch(new Product("653o09",0,0), list2));

		//Testing the testRemove method
		System.out.println(testRemove(new Product("355d98",0,0), list2));
		System.out.println(testRemove(new Product("344d97",0,0), list1));
		System.out.println(testRemove(new Product("456u78",0,0), list2));
		System.out.println(testRemove(new Product("653o09",0,0), list2));

		//QUESTION: The equals method in the product class is used to compare the product id of 
		//each product object. Once the equals method is removed we are unable to compare the objects
		//by their product id. It then uses the equals method in the object class which is not accurate.

		//Test orderPacket method on packet.dat file
		orderPacket();

		//Create two new OrderedLinkedList
		LinkedList<Product> list3 = new OrderedLinkedList<Product>();
		LinkedList<Product> list4 = new OrderedLinkedList<Product>();
		//Display the two OrderedLinkedLists
		System.out.println(list3.toString());
		System.out.println(list4.toString());

		//Storing the data obtained with getData() into an array of Products
		for(int i = 0; i < products.length; i++) {
			list4.insert(products[i]);
			System.out.println(list4.toString());
		}
		//Testing the testSearch method
		System.out.println(testSearch(new Product("264j45",0,0), list4));
		System.out.println(testSearch(new Product("344d97",0,0), list4));
		System.out.println(testSearch(new Product("344d97",0,0), list3));
		System.out.println(testSearch(new Product("456u78",0,0), list4));
		System.out.println(testSearch(new Product("653o09",0,0), list4));

		//Testing the testRemove method
		System.out.println(testRemove(new Product("355d98",0,0), list4));
		System.out.println(testRemove(new Product("344d97",0,0), list3));
		System.out.println(testRemove(new Product("456u78",0,0), list4));
		System.out.println(testRemove(new Product("653o09",0,0), list4));

		//Creating new OrderedLinkedList to test with
		OrderedLinkedList<Integer> ol1 = new OrderedLinkedList<Integer>();
		OrderedLinkedList<Integer> ol2 = new OrderedLinkedList<Integer>();
		int[] num1 = {0,1,6,13,14};
		int[] num2 = {3,4,5,15};
		for(int i:num1) ol1.insert(i);
		for(int i:num2) ol2.insert(i);
		System.out.println(ol1);
		System.out.println(ol2);

		//Testing contains method
		System.out.println("Checking if ol1 contains 6: " + ol1.contains(6));
		System.out.println("Checking if ol2 contains 6: " + ol2.contains(6));

		//Testing remove(index) method
		System.out.println("The number removed from index 2 is : " + ol1.remove(2));
		System.out.println(ol1);

		//Testing getAtPosition(index) method
		System.out.println("The number searched for at index 3 is : " + ol1.remove(3));			
		System.out.println("The number searched for at index 1 is : " + ol2.remove(1));			

		//Testing indexOf(searchItem) method
		System.out.println(ol1);
		System.out.println("Attempt to remove 13 from the first list, located at index: " + ol1.indexOf(13));	
		System.out.println(ol2);
		System.out.println("Attempt to remove 5 from the second list, located at index: " + ol2.indexOf(5));
		System.out.println(ol1);
		System.out.println(ol2);

		//Testing the set method
		System.out.println(ol1);
		System.out.println("Testing the set method with index of 1 and item 5 " + ol1.set(1, 5));
		System.out.println(ol1);

		//Testing the getSmallest method
		System.out.println(ol1);
		System.out.println("Testing smallest on list 1: " + ol1.getSmallest());
		System.out.println(ol2);
		System.out.println("Testing smallest on list 2: " + ol2.getSmallest());

		//Testing the getLargest method
		System.out.println(ol1);
		System.out.println("Testing largest on list 1: " + ol1.getLargest());
		System.out.println(ol1);

		//Testing sublist Method
		System.out.println("Testing the sublist method with index 0 and 2 \n" + ol1.subList(0, 2));
		
		//Testing merge method
		ol1.insert(1);
		System.out.println(ol1.toString());
		System.out.println(ol2.toString());
		System.out.println("Testing Merge method: \n" + ol1.merge(ol2));





	}
}
