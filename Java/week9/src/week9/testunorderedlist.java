package week9;

public class testunorderedlist {
	public static void main (String [] args) {
		UnorderedArrayList<Integer> temp = new UnorderedArrayList<Integer>();
		temp.insert(5);
		System.out.println(temp.toString());
		temp.remove(5);
		System.out.println(temp.toString());
	}
}
