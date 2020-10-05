/**
 * File: Person.java
 * Description: Defines a person class.
 * @author Derick Hansraj
 * N#: N00827531
 */
public class Person {


	// will need instance vars for first name, last name, email
	// gets and sets for all, param constructor methods as well
	private String idNum;
	private String first;
	private String last;
	private String email;

	public Person(String idNum, String first, String last, String email) {
		this.idNum = idNum;
		this.first = first;
		this.last = last;
		this.email = email;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
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
		str.append("[" +idNum + ", " + first + " " + last + ", " + email + "]");
		return str.toString();
	}
}