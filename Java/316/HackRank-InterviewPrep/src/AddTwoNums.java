import java.util.LinkedList;

/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode() {}
	 *     ListNode(int val) { this.val = val; }
	 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	 * }
	 */

class AddTwoNums {
	    public static LinkedList<Integer> addTwoNumbers(LinkedList<Integer> l1, LinkedList<Integer> l2) {
	    	StringBuilder str1 = new StringBuilder();
	    	StringBuilder str2 = new StringBuilder();
	    	LinkedList<Integer> temp = new LinkedList<Integer>();
	    	int soln;
	    	StringBuilder ans;

	    	
	    	   for (int i = 0; i < l1.size(); i++) {
	               str1.append(l1.get(i));
	           }
	    	   str1.reverse();
	    	   System.out.println(str1);
	    	   
	    	   for (int i = 0; i < l2.size(); i++) {
	               str2.append(l2.get(i));
	           }
	    	   str2.reverse();
	    	   System.out.println(str2);
	    	   
	    	   soln = Integer.parseInt(str1.toString()) + Integer.parseInt(str2.toString());
	    	   System.out.println(soln);
	    	   
	    	   ans = new StringBuilder(Integer.toString(soln));
	    	   ans.reverse();
	    	   
	    	   
	    	   for (int i = 0; i < ans.length(); i++) {
	    		   char c = ans.charAt(i);
	    		   int num = Character.getNumericValue(c);
	               temp.add(num);
	           }
	    	   
			return temp; 
	    }
    
    public static void main (String[] args) {
    	LinkedList<Integer> l1 = new LinkedList<Integer>();
    	l1.add(2);
    	l1.add(4);
    	l1.add(3);
    	
    	LinkedList<Integer> l2 = new LinkedList<Integer>();
    	l2.add(5);
    	l2.add(6);
    	l2.add(4);
    	System.out.println(l1.toString());
    	System.out.println(l2.toString());
    	System.out.println(addTwoNumbers(l1,l2));

		}
}