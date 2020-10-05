/**
 * Title: CSC130hansrajd.lab8
 * Filename: Lab8App.java
 * Date Written: December 5, 2017
 * Due Date: December 9, 2017
 * Description: Test the methods from the Product, Packet, ArrayList, 
 * OrderedArrayList and and UnorderedArrayList classes.
 * @author F. Graham & Derick Hansraj & Wenjie Cao
 **/
import java.io.File;

public class Lab8App {

	public static void main(String[] args) {
		Lab8App recursion = new Lab8App();

		System.out.println(sum(1,7));
		
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
		
		recursion.hanoi(31);
		System.out.println(recursion.pow(5, 2));
		LinkedList<Integer> ol1 = new LinkedList<Integer>();
		int[] num1 = {0,1,6,13,14};
		for(int i:num1) ol1.insert(i);
		System.out.println(ol1);
		reverse(ol1.head.getNext());
		System.out.println("\n"+ convert(65535 ,16));

		
		System.out.println("======================"); 
		System.out.println("Start of lab 8"); 
		System.out.println("======================"); 
		System.out.println("Sum(1 to 8) = " + recursion.sum(1, 8)); 
		System.out.println("Sum(1 to 7) = " + recursion.sum(1, 7)); 
		System.out.println("Sum(1 to 5) = " + recursion.sum(1, 5)); 
		System.out.println("3^2 = " + recursion.pow(3,2)); 
		System.out.println("2^16 = " + recursion.pow(2,16)); 
		// convert 12 to base 16 – result should be C 
		System.out.println("12 in base 16 = " + recursion.convert(12,16)); 
		System.out.println("12 in base 2 = " + recursion.convert(12,2));  
		System.out.println("511 in base 16 = " + recursion.convert(511,16)); 
		System.out.println("65535 in base 16 = " + recursion.convert(65535,16));
	}
	
	/**
	 * strToNum method -- uses recursion to convert a string to an integer
	 * @param - a string to be changed to an int
	 * @return - a int representing the string parameter
	 */
	public int strToNum(String str) { 
		if(str.length() < 1) 
			return 0; 
		else 
			return ((str.charAt(str.length() - 1) - '0') + (10 * strToNum(str.substring(0, str.length() - 1)))); 
	}
	
	/**
	 * countNodes method -- counts the amount of nodes in the list
	 * @param - a node that will traverse a list
	 * @return an int representing the amount of nodes in the list
	 */
	private <T> int countNodes(Node<T> trav){ 
		if(trav == null) 
			return 0; 
		return 1 + countNodes(trav.getNext()); 
	}
	
	/**
	 * findMin method -- finds the smallest int in an array
	 * @param array - array to search for an int
	 * @param size - the size of the array
	 * @param index - the index to search for an int
	 * @return an int representing the smallest int in the array
	 */
	public int findMin(int array[], int size, int index) { 
		if(index == size - 1) 
			return array[index]; 
		int result = findMin(array, size, index + 1); 
		if (array[index] < result) 
			return array[index]; 
		else 
			return result; 
	}
	
	/**
	 * isPalindrome method -- determines whether or not the given string is a palindrome
	 * @param str - string to be searched through
	 * @param low - the lowest parameter to start searching
	 * @param high - the highest parameter to start searching
	 * @return true if the string is a palindrome, false otherwise
	 */
	public boolean isPalindrome(String str, int low, int high) { 
		if(high <= low) 
			return true; 
		else if (str.charAt(low)!= str.charAt(high)) 
			return false; 
		else 
			return isPalindrome(str,low+1,high-1); 
	}
	
	/**
	 * reverseString method -- Reverses a string parameter using recursion 
	 * @param s -a string parameter
	 * @return a string in reverse order using recursion
	 */
	public String reverseString(String s){ 
		if(s.length() == 0) 
			return s; 
		return reverseString(s.substring(1)) + s.charAt(0); 
	}
	
	/**
	 * traverse method -- using recursion to search through directory
	 * @param file - The file directory to be searched through
	 */
	public static void traverse(File file) { 
		if(file.isDirectory()) { 
			System.out.println(file); 
			String dirContents[] = file.list(); 
			if (dirContents != null) 
				for (String directory : dirContents) 
					traverse(new File(file, directory)); 
		} 
	}
	
	/**
	 * hanoi method -- calculates the amount of moves to solve the towers of hanoi
	 * @param discs - amount of disc to solve for
	 */
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
	
	/**
	 * sum method -- sums all the numbers between the two parameters
	 * @param num - the first int to start summing
	 * @param num2 - the second int to start summing
	 * @return the sum of all numbers between the two parameters
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
	
	/**
	 * pow method -- finds the answer using the base and power of the chosen ints
	 * @param x - the base number
	 * @param y - the exponent number
	 * @return the answer to a number raised to a power.
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
	 */
	
	/**
	 * reverse method -- returns the elements of a linkedlist in reverse order.
	 * @param current - the node to start traversing with
	 */
	public static <T> void reverse(Node<T> current) {
        if (current != null) {
            reverse(current.getNext());
            System.out.print(current.getData() + " ");
        }
    }
	
	/*
	 * 5. Write a recursive method to convert a number, n, to a base, b, and return result as a String.
	 */
	
	/**
	 * convert method -- converts a number from base 10 to a desired base.
	 * @param num - the number to be changed
	 * @param base - the base to convert a desired number to
	 * @return a string representing the number converted to a desired base
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