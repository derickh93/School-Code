import java.util.List;

/**
 * Questions Marks
Have the function QuestionsMarks(str) take the str string parameter, which will contain single digit numbers,
 letters, and question marks, and check if there are exactly 3 question marks between every pair of two numbers 
 that add up to 10. If so, then your program should return the string true, otherwise it should return the string false.
  If there aren't any two numbers that add up to 10 in the string, then your program should return false as well.

For example: if str is "arrb6???4xxbl5???eee5" then your program should return true because there are exactly 3 question 
marks between 6 and 4, and 3 question marks between 5 and 5 at the end of the string.
Examples
Input: "aa6?9"
Output: false
Input: "acc?7??sss?3rr1??????5"
Output: true
 * @author cooli
 *
 */
public class QuestionMarks {

	 public static boolean QuestionMarks(String str) {
		 boolean valid = true;
		 int sum = 0;
		 if(str.length() < 4)
			 return false;
		 for(int i = 0;i<str.length();i++) {
			if(Character.isDigit(str.charAt(i)) && 
					str.substring(i+1,i+4).equals("???") && 
					Character.isDigit(str.charAt(i+4))) {
				sum+=str.charAt(i);
				sum+= str.charAt(i+4);
						if(sum == 10)
							valid = true;
						else return false;
			}
			
		 }
			return valid;
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
    	System.out.println(QuestionMarks("5???"));
    }
}
