/**
 * File: PersonInformation.java
 * Description: Stores the last name and id of a person along with a two hashcode methods.
 * @author Derick Hansraj
 * N#: N00827531
 * Sources:
 * https://stackoverflow.com/questions/15735079/convert-from-one-base-to-another-in-java
 * http://research.cs.vt.edu/AVresearch/hashing/
 * https://coderanch.com/t/689995/java/Mid-Square-Hashing-Bit-Manipulation
 */
public class PersonInformation {

	private String last;
	private String id;

	public PersonInformation(String idNum,String lastName) {
		id = idNum;
		last = lastName;

	}

	/**
	 * Midsquare method- In the mid-square method, the index is calculated by multiplying the key by itself 
	 * and then using the extraction method (from the middle). This means taking the same digits from the
	 * middle of the integer.
	 * **/
	public int hashCode() {
		int result = 0;
		int c;
		String str = id + last;
		for(int i = 0 ; i < str.length(); i++) {
			c = str.charAt(i);
			result += c;
		}
		result = (int) Math.pow(result, 2);
		String hash = Integer.toString(result);
		result = Integer.parseInt(hash.substring((hash.length()/2) - 3, hash.length()/2 + 3));
		return result;
	}
	
	
	/**
	 * Radix Transformation method- Transform the key into another numeric base
	If our key is in base 10, we convert it to base 7.

	 
	 	public int hashCode() {
		int result = 0;
		int c;
		String str = id + last;

		for(int i = 0 ; i < str.length(); i++) {
			c = str.charAt(i);
			result += c;
		}
		return Integer.parseInt(Integer.toString(Integer.parseInt(Integer.toString(result), 10), 7));
	}
	*/


	public boolean equals(Object obj) {
		if (this == obj) 
			return true;

		if(obj == null)
			return false;

		if(obj instanceof PersonInformation) {
			PersonInformation p1 = (PersonInformation) obj;

			if(p1.id.equals(id) && p1.last.equals(last)) {
				return true;
			}
		}

		return false;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Last Name:" + last);
		str.append(" ID Number:" + id);
		return str.toString();
	}
}