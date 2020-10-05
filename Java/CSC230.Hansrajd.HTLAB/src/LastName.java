/**
 * File: LastName.java
 * Description: Stores the last name of a person along with a hashcode method.
 * @author Derick Hansraj
 * N#: N00827531
 */
public class LastName {
	
	private String last;
	
	public LastName(String l) {
		last = l;
	}

	
	public int hashcode() {
		int hashCode = 0;
		int result = 0;
		int c;
		int power = 2;
		
		for(int i = 0 ; i < last.length(); i++) {
			c = last.charAt(i);
			hashCode = (int) (c * (Math.pow(31, power--)));
			result += hashCode;
		}
		
		hashCode += result;
		return hashCode;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(last);
		return str.toString();
	}
}
