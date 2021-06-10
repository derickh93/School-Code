import java.util.ArrayList;
import java.util.List;

public class RotLeft {


    
    public static List<Integer> rotLeft(List<Integer> a, int d) {
        // Write your code here
            List<Integer> tempList = new ArrayList<Integer>(a.size());
            for(int i = 0; i < a.size();i++){
                tempList.set(Math.floorMod(-4+i, 5),a.get(i));
            }
            return tempList;
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
