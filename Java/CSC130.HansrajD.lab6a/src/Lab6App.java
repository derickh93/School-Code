/**
 * <p>Title: Driver Class - Lab6App</p>
 *
 * <p>Copyright: Copyright (c) 2014</p>
 *
 * @author F. Graham & Derick Hansraj &Wenjie Cao
 * @version 1.0
 */
import java.sql.*;


public class Lab6App {

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
	
	public static String testSearch(Product key, ArrayList<Product> list) {
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
	
	public static String testRemove(Product key, ArrayList<Product> list) {
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
	
	public static void main (String[] args) {
		//Create two new UnorderedArrayList
		ArrayList<Product> list1 = new UnorderedArrayList<Product>();
		ArrayList<Product> list2 = new UnorderedArrayList<Product>(5);
		
		//Display the two UnorderedArrayLists
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
	}
}