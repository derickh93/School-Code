import java.io.File;

public class Lab8App {

	public static void main(String[] args) {
		Lab8App recursion = new Lab8App();

		System.out.println(recursion.pow(2, 10));
		LinkedList<Integer> ol1 = new LinkedList<Integer>();
		int[] num1 = {0,1,6,13,14};
		for(int i:num1) ol1.insert(i);
		System.out.println(ol1);
		System.out.println(countNodes(ol1.getfront()));



		/**
		Lab8App recursion = new Lab8App();
		System.out.println(recursion.strToNum("12341"));
		System.out.println(recursion.findMin(new int[] {3,2,1,4,5}, 5, 0));
		System.out.println(recursion.isPalindrome("racecar", 0, 6));
		System.out.println(recursion.reverseString("pots&pans"));
		recursion.traverse(new File("J:/")); 
		recursion.hanoi(4);
		
		LinkedList<String> ulist = new LinkedList<String>(); 
		String[] str = {"hello","this","is","a","test"};
		for(String s : str)
			ulist.insert(s);
		System.out.println(ulist);
		Node<String> node = ulist.getfront();
		System.out.println(recursion.countNodes(node));
		**/
	}
	public int strToNum(String str) { 
		if(str.length() < 1) 
			return 0; 
		else 
			return ((str.charAt(str.length() - 1) - '0') + (10 * strToNum(str.substring(0, str.length() - 1)))); 
	}
	private static <T> int countNodes(Node<T> trav){ 
		if(trav == null) 
			return 0; 
		return 1 + countNodes(trav.getNext()); 
	}
	public int findMin(int array[], int size, int index) { 
		if(index == size - 1) 
			return array[index]; 
		int result = findMin(array, size, index + 1); 
		if (array[index] < result) 
			return array[index]; 
		else 
			return result; 
	}
	public boolean isPalindrome(String str, int low, int high) { 
		if(high <= low) 
			return true; 
		else if (str.charAt(low)!= str.charAt(high)) 
			return false; 
		else 
			return isPalindrome(str,low+1,high-1); 
	}
	public String reverseString(String s){ 
		if(s.length() == 0) 
			return s; 
		return reverseString(s.substring(1)) + s.charAt(0); 
	}
	public static void traverse(File file) { 
		if(file.isDirectory()) { 
			System.out.println(file); 
			String dirContents[] = file.list(); 
			if (dirContents != null) 
				for (String directory : dirContents) 
					traverse(new File(file, directory)); 
		} 
	}
	public static void hanoi(int discs){ 
		long count = 0;
		for (int x = 1; x < (1 << discs); x++) { 
			int from = (x & x - 1) % 3; 
			int to = ((x | x - 1) + 1) % 3; 
			count++;
		} 
		System.out.println(count);
	}
	
	/*
	 * 1. Modify the method that calculates the sum of the integers between 1 and N 
	 * shown above. Have the new version match the following recursive definition: 
	 * The sum of 1 to N is the sum of 1 to (N/2) plus the sum of (N/2 + 1) to N. 
	 * Trace your solution using an N of 7.
	 */
	public static int sum(int num, int num2) {
		if(num == num2)
			return num;
		else if(num == num2 - 1)
			return num+num2;
		return sum(num, (num+num2)/2) + sum((num+num2)/2 + 1, num2);
	}
	
	/**
	 * 2.
	 
	Amount of Disc	# of Moves
	2				3
	3				7
	4				15
	5				31
	6				63
	7				127
	8				255
	9				511
	10				1023
	15				32767
	20				1048575
	30				1073741823
	31				0

	
	What happens when you try a value over 30?
	-The answer displayed is 0 because the count for 31 would be over the limit for
		an integer number.
	


	How can the program be modified to speed up the execution?
	- Once i removed the system.out.println() for each move the program was much faster because it
		did not need to display each move.
	 **/

	
	/*
	 * 3. Write a recursive definition of x^y, where x and y are integers and y >= 0. 
	 * In addition, write the recursive method.
	 */
	
	public static int pow(int x, int y) {
		if( y == 0)
			return 1;
		else if(y%2!=0)
			return x* pow(x, (y-1)/2) * pow(x,(y-1)/2);
		else
			return pow(x, y/2) * pow(x, y/2);
		
	}
	
	/*
	 * 4. Write a recursive method to display the contents of a linked-list in reverse order.
	 * 
	 **/
	 
	
	public static void reverse(Node current) {
		if (current != null) {
			reverse(current.getNext());
			System.out.print(current.getData() + " ");
		}
	}


	/*
	 * 5. Write a recursive method to convert a number, n, to a base, b, and return result as a String.
	 */
	
	public static String convert(int num, int base) {
		final char[] table = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		if (num == 0)
			return "";
		else
			return convert(num / base, base) + table[num % base];
	}
}
class Node<E>{
	private E data;
	private Node<E> next;
	
	public Node(){
		data = null;
		next = null;
	}
	public Node(E d){
		data = d;
		next = null;
	}
	public Node(E d, Node<E> n){
		data = d;
		next = n;
	}
	public Node<E> getNext(){
		return next;
	}
	public void setNext(Node<E> n){
		next = n;
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}		
}
class LinkedList<T extends Comparable<T>>{
	
	protected Node<T> head = new Node<T>(); // dummy Node
	protected int numItems;
	
	public Node<T> getfront(){
		return head.getNext();
	}
	public int getSize(){
		return numItems;
	}
	public boolean isEmpty(){
		return numItems == 0;
	}
	public void insert(T insertItem) {
		if(insertItem == null)
			throw new   NullPointerException();
		Node<T> trav = head;
		while(trav.getNext() != null)
			trav = trav.getNext();
		trav.setNext(new Node<T>(insertItem));
		++numItems;
	}
	public String toString(){
		String str = "\n==================================\n"
				+ "The list contains " + numItems + " items.\n"
				+ "==================================\n[";
		Node<T> trav = head.getNext();
		while(trav != null){
			// str += trav.data + "\n"; 
			str += trav.getData() + ((trav.getNext() == null) ? "" : "->");
			trav = trav.getNext();
		}
		return str + "]";
	}
}
