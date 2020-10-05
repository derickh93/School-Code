package csc130lib;

public class ArrayListTesting {
	public static void main (String[] args) {
	ArrayList<String> alist = new UnorderedArrayList<String>();
	ArrayList<Integer> nlist = new UnorderedArrayList<Integer>();
	alist.insert(new String("Hello"));
	System.out.println(alist);
	nlist.insert(new Integer(5));
	System.out.println(nlist);
	System.out.println(alist.search(new String("Hello")));
	System.out.println(nlist.search(new Integer(5)));
	System.out.println(alist.search(new String("World")));
	System.out.println(nlist.search(new Integer(10)));
	
	alist.insert(new String("Hello"));
	nlist.insert(new Integer(1));
	alist.insert(new String("this"));
	nlist.insert(new Integer(2));	
	alist.insert(new String("is"));
	nlist.insert(new Integer(3));
	alist.insert(new String("csc"));
	nlist.insert(new Integer(4));
	alist.insert(new String("130"));
	nlist.insert(new Integer(6));
	System.out.println(alist);
	System.out.println(nlist);


	
	

	}	
}
