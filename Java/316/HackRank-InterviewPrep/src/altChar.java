import java.util.ArrayList;
import java.util.*;
public class altChar {

	/**
	 * You are given a string containing characters  and  only. Your task is to change it into a string such that there are no matching adjacent characters. To do this, you are allowed to delete zero or more characters in the string.

Your task is to find the minimum number of required deletions.

Example

Remove an  at positions  and  to make  in  deletions.

Function Description

Complete the alternatingCharacters function in the editor below.

alternatingCharacters has the following parameter(s):

string s: a string
Returns

int: the minimum number of deletions required
Input Format

The first line contains an integer , the number of queries.
The next  lines each contain a string  to analyze.

Constraints

Each string  will consist only of characters  and .
Sample Input

5
AAAA
BBBBB
ABABABAB
BABABA
AAABBB
Sample Output

3
4
0
0
4
Explanation

The characters marked red are the ones that can be deleted so that the string does not have matching adjacent characters.
	 * @param s
	 * @return
	 */
	public static int alternatingCharacters(String s) {
	       char curr=s.charAt(0);
	        int count=0;
	        for(int i=1;i<s.length();i++){
	            if(s.charAt(i)==curr) count++;
	            else curr=s.charAt(i);
	        }
	        return count;
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

   
    
    public static boolean QuestionMarks(String str) {
		return false;
    }

    public static void main (String[] args) {
		System.out.println(alternatingCharacters("AAAA"));
		System.out.println(alternatingCharacters("BBBBB"));
		System.out.println(alternatingCharacters("ABABABAB"));
		System.out.println(alternatingCharacters("BABABA"));
		System.out.println(alternatingCharacters("AAABBB"));
		}
}
