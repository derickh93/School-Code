package math;

import java.util.Scanner;

public class Addition {

	public static void main (String[ ]args){
		Scanner sc = new Scanner(System.in);
		int avinash=0, coolboy=0;
		
		System.out.println("Enter a number: ");
		avinash = sc.nextInt();
		System.out.println("Enter another number: ");
		coolboy = sc.nextInt();
		System.out.println(avinash + " + " + coolboy + " = " + (avinash+coolboy));
		System.out.println(avinash + " * " + coolboy + " = " + (avinash*coolboy));
		System.out.println(avinash + " - " + coolboy + " = " + (avinash-coolboy));
		System.out.println(avinash + " / " + coolboy + " = " + (avinash/coolboy));

	}
}
