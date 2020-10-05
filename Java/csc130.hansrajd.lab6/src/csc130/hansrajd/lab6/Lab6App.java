package csc130.hansrajd.lab6;

/**
 * <p>Title: Driver Class - Lab6App</p>
 *
 * <p>Copyright: Copyright (c) 2014</p>
 *
 * @author F. Graham & Derick Hansraj
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
			Connection c = DriverManager.getConnection("jdbc:sqlite:I:/grahamf/products.db");
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
	
	@SuppressWarnings("unused")
	public static void main (String [] args) {
		UnorderedArrayList<Product> list1 = new UnorderedArrayList<Product>();
		System.out.println(list1.toString());
		
		UnorderedArrayList<Product> list2 = new UnorderedArrayList<Product>(5);
		System.out.println(list2.toString());
		
		Product p1 = new Product("456u78", 10 ,5.0);
		Product p2 = new Product("355d98", 7 ,25.0);
		Product p3 = new Product("243j58", 3 ,10.0);
		Product p4 = new Product("264j45", 15 ,13.50);
		Product p5 = new Product("653o09", 9 ,16.75);
		
		list2.insert(p1);
		System.out.println(list2.toString());
		list2.insert(p2);
		System.out.println(list2.toString());
		list2.insert(p3);
		System.out.println(list2.toString());
		list2.insert(p4);
		System.out.println(list2.toString());
		list2.insert(p5);
		System.out.println(list2.toString());
		
		Product p6 = new Product("264j45", 0 ,0);
		System.out.println(list2.search(p6));
		
		Product p7 = new Product("344d97", 0 ,0);
		System.out.println(list2.search(p7));
		
		Product p8 = new Product("456u78", 0 ,0);
		System.out.println(list2.search(p7));
		
		Product p9 = new Product("653o09", 0 ,0);
		System.out.println(list2.search(p7));
		
		System.out.println(list1.search(p7));
		
		Product p10 = new Product("355d98", 0 ,0);
		System.out.println(list2.remove(p10));
		
		Product p11 = new Product("344d97", 0 ,0);
		System.out.println(list2.remove(p11));
		
		Product p12 = new Product("456u78", 0 ,0);
		System.out.println(list2.remove(p12));
		
		Product p13 = new Product("653o09", 0 ,0);
		System.out.println(list2.remove(p13));
		
		Product p14 = new Product("264j45", 0 ,0);
		System.out.println(list2.remove(p14));
		
		Product p15 = new Product("344d97", 0 ,0);
		System.out.println(list1.remove(p15));
		
		//Questions: The program runs with no errors after commenting out the equals method.The search method 
		//no longer finds the desired product id. The search method is comparing two products by using
		//the equals method from the object class. The result of this is incorrect output which also happens to prevent the remove 
		//method from properly working.
	}
}