import java.util.Scanner;
import java.util.Stack;

public class Strings {
	public static void main(String[] args) {
		//instance variables
		String reverse;
		String words;
		int count = 0;
		StringBuilder output = new StringBuilder();
		Stack<Character> stack = new Stack<Character>();
		Stack<Character> stack2 = new Stack<Character>();
		Scanner scanner = new Scanner(System.in);
		reverse = scanner.next();
		for(int i = 0; i < reverse.length();i++) {
			stack.push(reverse.charAt(i));
			
			if("AEIOUaeiou".indexOf(reverse.charAt(i)) != -1)
				count++;

		}
		while(!stack.isEmpty()) {
			output.append(stack.pop());
		}
		System.out.println(output);
		
		char first = reverse.charAt(0);
		String pigLatin = reverse.substring(1) + first + "ay";
		System.out.println(pigLatin);
		System.out.println(count);
		if(reverse.equalsIgnoreCase(output.toString()))
			System.out.println("palindrome");
		else
			System.out.println("not palindrome");
		
		words = scanner.next();
		int wordCount = 0;
		String trim = words.trim();
		System.out.println(words.split("\\w+").length);	
	}
}