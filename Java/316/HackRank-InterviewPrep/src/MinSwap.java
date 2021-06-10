import java.util.List;

public class MinSwap {

	static int minimumSwaps(int[] arr) {
    	int minSwap = 0;
    	int currentIndex = 0;
    	while(currentIndex < arr.length) {
    		int min = getMin(arr,currentIndex);
    		swap(arr,currentIndex,min);
    		minSwap++;
    		currentIndex++;
    	}
    	
    	printArray(arr);
    	return minSwap;
    }

    static void swap(int[] arr,int firstIndex, int secondIndex){
    	int temp = arr[firstIndex];
    	arr[firstIndex] = arr[secondIndex];
    	arr[secondIndex] = temp;
    }


    // Method for getting the minimum value
    public static int getMin(int[] inputArray,int currentIndex){ 
    	int minValue = inputArray[currentIndex]; 
    	int index = 0;
    	for(int i=1+currentIndex;i<inputArray.length;i++){ 
    		if(inputArray[i] < minValue){ 
    			minValue = inputArray[i]; 
    			index = i;
    		} 
    	} 
    	System.out.println("Min index: " + index);
    	return index; 
    } 
    static void printArray(int[] arr) {
    	int i = 0;
    	for (int var : arr) 
    	{ 
    		System.out.println("["+i+"]: " + var);
    		i++;
    	}
    }
    
    static void printArrayList(List<Integer> arr) {
    	int i = 0;
    	for (int var : arr) 
    	{ 
    		System.out.println("["+i+"]: " + var);
    		i++;
    	}
    }
    
    public static void main (String[] args) {
    }
}
