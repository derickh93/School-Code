package ht;

public class Person {
	
	
	// will need instance vars for first name, last name, email
	// gets and sets for all, param constructor methods as well
	private String first;
	private String last;
	private String email;
	
	public Person(String f, String l, String e) {
		first = f;
		last = l;
		email = e;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	// will need a hashcode method which gets called from put and get in hashtable
	
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

		System.out.println(hashCode);
		return hashCode;
	}
}