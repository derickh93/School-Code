package week11;

public class TestOrderedArrayList {
	public static void main (String [] args) {
		OrderedArrayList<Integer> ol = new OrderedArrayList<Integer>();
		ol.insert(25);
		System.out.println(ol.toString());
		ol.insert(5);
		System.out.println(ol.toString());
		ol.insert(15);
		System.out.println(ol.toString());
		ol.insert(10);
		System.out.println(ol.toString());
		ol.insert(20);
		System.out.println(ol.toString());
		System.out.println(ol.remove(25) + "\n");
		System.out.println(ol.toString());
		
	}
}
