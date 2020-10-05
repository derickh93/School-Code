package SortingLab;
/**
 * File: SortingLab.java
 * Description: SortingLab class that contains selectionSort, bubbleSort, swap, and printArray methods.
 * @author Derick Hansraj
 * N#: N00827531
 */

public class SortingLab {

	public static void selectionSort(int [] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for (int j = i+1; j < arr.length; j++)
				if (arr[j] < arr[min])
					min = j;
			swap(arr,min,i);
		}
	}

	public static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++)
			for (int j = 0; j < arr.length - i - 1; j++)
				if (arr[j] > arr[j+1]) {
					swap(arr,j,j+1);
				}
	}

	public static void swap(int [] arr , int indexOldMin, int indexNewMin) {
		int temp = arr[indexOldMin];
		arr[indexOldMin] = arr[indexNewMin];
		arr[indexNewMin] = temp;
	}

	public static String printArray(int [] arr) {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < arr.length;i ++) {
			str.append(arr[i] + " ");
		}
		return str.toString();
	}
}
