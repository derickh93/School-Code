import java.util.Date;

public class SalariedEmployee extends Employee {
	protected double salary;

	public SalariedEmployee(String n, String ssno, String id, String gender, Date dob, double salary) {
		super(n, ssno,id, gender, dob);
		this.salary = salary;
	}

	public String toString(){
		return super.toString()+ "\n"+  "Annual salary: " + salary;
	}

	public double getSalary() {
		return salary;
		
	}
	
	public void setSalary(double s) {
		salary = s;
	}

	public double pay() {
		return salary;
	}


	public void display() {
		System.out.println(this);
	}
	
	public int compareTo(Object o) {
		return 0;
		
	}
}