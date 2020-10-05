package SortingLab;
/**
 * File: Lab5App.java
 * Description: Application class that test the methods defined in SortingLab class.
 * @author Derick Hansraj
 * N#: N00827531
 */

public class Lab5App {
	public static void main (String [] args) {
		
		int [] arr = {20,5,3,100,30,40};
		System.out.println("Array before sorting: " + SortingLab.printArray(arr));
		SortingLab.bubbleSort(arr);
		System.out.println("Array after bubbleSort: " + SortingLab.printArray(arr));

		int [] arr2 = {20,5,3,100,30,40};
		System.out.println("\nArray before sorting: " + SortingLab.printArray(arr2));
		SortingLab.selectionSort(arr2);
		System.out.println("Array after selectionSort: " + SortingLab.printArray(arr2));
		
		int [] arr3 = {7,12,93,4,20,4};
		System.out.println("\nArray before sorting: " + SortingLab.printArray(arr3));
		SortingLab.bubbleSort(arr3);
		System.out.println("Array after bubbleSort: " + SortingLab.printArray(arr3));

		int [] arr4 = {7,12,93,4,20,4};
		System.out.println("\nArray before sorting: " + SortingLab.printArray(arr4));
		SortingLab.selectionSort(arr4);
		System.out.println("Array after selectionSort: " + SortingLab.printArray(arr4));
		
		int [] arr5 = {20,5,3,100,30,40,7,12,93,4,20,4};
		System.out.println("\nArray before sorting: " + SortingLab.printArray(arr5));
		SortingLab.bubbleSort(arr5);
		System.out.println("Array after bubbleSort: " + SortingLab.printArray(arr5));

		int [] arr6 = {20,5,3,100,30,40,7,12,93,4,20,4};
		System.out.println("\nArray before sorting: " + SortingLab.printArray(arr6));
		SortingLab.selectionSort(arr6);
		System.out.println("Array after selectionSort: " + SortingLab.printArray(arr6));
	}

}
