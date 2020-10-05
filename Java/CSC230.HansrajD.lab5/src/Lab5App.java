
public class Lab5App {
	public static void main (String [] args) {
		int [] arr = {20,5,3,100,30,40};
		SortingLab.bubbleSort(arr);
		System.out.println(SortingLab.printArray(arr));

		int [] arr2 = {20,5,3,100,30,40};
		SortingLab.selectionSort(arr2);
		System.out.println(SortingLab.printArray(arr2));
	}

}
