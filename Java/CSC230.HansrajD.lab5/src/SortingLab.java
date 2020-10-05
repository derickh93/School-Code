
public class SortingLab {

	public static void selectionSort(int [] arr) {
		int startLocation = 0;
		int min = arr[0];
		int indexMin = 0;
		int arrLen = arr.length;
		while(startLocation < arrLen - 1) {
			if(min > arr[startLocation]) {
				min = arr[startLocation+1];
				indexMin = startLocation + 1;
				swap(arr,indexMin,startLocation+1);
			}
			startLocation++;
		}
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < arr.length; i++) {
			str.append(arr[i] + " ");
		}
	}

	public static void bubbleSort(int[] arr) {
		for(int i = 0; i < arr.length - 1;i ++) {
			for(int j = 1; j < arr.length-1;j ++) {
				if(arr[j-1] > arr[j]) {
					swap(arr,j-1,j);
				}
			}
		}
	}

	public static void swap(int [] arr , int indexOldMin, int indexNewMin) {
		int temp;
		temp = arr[indexOldMin];
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
