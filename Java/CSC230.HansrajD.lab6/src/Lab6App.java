/**
 * File: Lab6App.java
 * Description: Application class that test the methods defined in Radix class.
 * @author Derick Hansraj
 * N#: N00827531
 */
public class Lab6App {
	public static void main (String [] args) {
		int arr [] = {8,6,56,87,12,11,5};
		Radix.sort(arr);
		System.out.println(Radix.printArray(arr));

		int arr2 [] = {8,3,13,12,444,11,5};
		Radix.sort(arr2);
		System.out.println(Radix.printArray(arr2));

		int arr3 [] = {8,6,1000,12,11,100,5};
		Radix.sort(arr3);
		System.out.println(Radix.printArray(arr3));

		int arr4 [] = {12,8,6,12,7,11,5,16};
		Radix.sort(arr4);
		System.out.println(Radix.printArray(arr4));
		
		int arr5 [] = {200,80,6,1232,7,11,5,16,800,687,1000,12,151,100,50,8,3,13,132,444,101,15};
		Radix.sort(arr5);
		System.out.println(Radix.printArray(arr5));
	}
}
