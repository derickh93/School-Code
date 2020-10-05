import java.util.*;
public class AlgorithmApp {
	public static int sum(int n) {
		if(n == 1)
			return 1;
		return n + sum(n-1);
	}
	
	public static int factorial(int n) {
		if(n < 1)
			return 1;
		return n * factorial(n-1);
	}
	public static void main(String[] args){
		System.out.println(sum(5));
		System.out.println(factorial(5));
		
	}
}
