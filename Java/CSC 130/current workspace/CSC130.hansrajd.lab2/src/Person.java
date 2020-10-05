import java.util.Date;

public class Person {
	private String name;
	private String ssno;
	private String gender;
	private Date dob;

	@SuppressWarnings("deprecation")
	public Person() {
		name = "John Doe";
		ssno = "012345678";
		gender = "Male";
		dob = new Date(90,01,01);
	}
	
	public Person(String name, String ssno, String gender, Date dob) {
		this.name = name;
		this.ssno = ssno;
		this.gender = gender;
		this.dob = dob;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSSno() {
		return ssno;
	}
	
	public String getGender() {
		return gender;
	}
	
	public Date getDateOfBirth() {
		return dob;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setSSno(String ssno) {
		this.ssno = ssno;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setDateOfBirth(Date dob) {
		this.dob = dob;
	}

	public String toString() {
		return "name: " + name + ", ssno: " + ssno + ", gender: " + gender + ", dob: " + dob;
	}
	
	
}
