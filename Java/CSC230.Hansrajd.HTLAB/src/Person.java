/**
 * File: Person.java
 * Description: Defines a person class.
 * @author Derick Hansraj
 * N#: N00827531
 */
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
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("First: " + first + "\tLast: " + last + "\tEmail: " + email);
		return str.toString();
	}
}
