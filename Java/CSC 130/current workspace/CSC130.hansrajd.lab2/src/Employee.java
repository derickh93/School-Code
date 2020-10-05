import java.text.DateFormat;
import java.util.Date;

public abstract class Employee extends Person{
	protected Date dateHired = new Date();
	protected String employeeNo;
	
	public Employee() {
		super();
		employeeNo = "012345";
	}
	
	public Employee(String n, String ssno, String id, String gender, Date dob) {
		super(n, ssno, gender, dob);
		employeeNo = id;
	}
	
	public String getEmployeeNo() {
		return employeeNo;
	}
	
	public Date getDateHired() {
		return dateHired;
	}
	
	public void setEmployeeNo(String id) {
		employeeNo = id;
	}
	
	public void setDateHired(Date d) {
		dateHired = d;
	}
	
	public String createEmail() {
		String name = getName();
		char first = name.charAt(0);
		int pos = name.indexOf(' ');
		String last = getName().substring(pos+1);
		String temp = getEmployeeNo();
		String id = temp.substring(2);
		return first+last+id+"@acme.com";
		
	}

	public String toString() {
		return super.toString() + "\nEmployee no:" + employeeNo + "\nHired on: " + DateFormat.getDateInstance().format(dateHired);
	}
	
	public abstract double pay();
	
	public abstract void display();
	
}
