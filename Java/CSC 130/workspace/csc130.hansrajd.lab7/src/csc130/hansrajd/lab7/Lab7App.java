package csc130.hansrajd.lab7;

/**
 * <p>Title: ArrayListClass</p>
 *
 * <p>Description: Defines those properties and behaviors that are common to all lists.
 * The classes are designed so that objects of any type can be stored. Users of this
 * class (and its subclasses) should note that although the lists can store an "item"
 * of any type, they are required to override the equals method, defined
 * in the Object class, in their "item" class.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * @author Darci Burdge & Derick Hansraj
 * @version 1.0
 */
public class Lab7App {
	public static void main(String [] argss) {
		OrderedList<Product> list1 = new OrderedList<Product>();
		System.out.println(list1.toString());
		
		OrderedList <Product>list2= new OrderedList<Product>();
		System.out.println(list2.toString());
		
		Product p1 = new Product("456u78", 10 , 5.0 );
		list2.insert(p1);
		System.out.println(list2.toString());
		
		Product p2 = new Product("355d98", 7 , 25.0 );
		list2.insert(p2);
		System.out.println(list2.toString());
		
		Product p3 = new Product("243j58", 3 , 10 );
		list2.insert(p3);
		System.out.println(list2.toString());
		
		Product p4 = new Product("264j45", 15 , 13.5 );
		list2.insert(p4);
		System.out.println(list2.toString());
		
		Product p5 = new Product("653o09", 9 , 16.75 );
		list2.insert(p5);
		System.out.println(list2.toString());
		
		System.out.println(list2.search(p5));
	}

}
