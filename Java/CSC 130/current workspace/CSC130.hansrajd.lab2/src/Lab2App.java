import java.util.Date;

public class Lab2App {
	public static void main(String [] args) {
		@SuppressWarnings("deprecation")
		SalariedEmployee se = new SalariedEmployee("Bert Simpson", "123456789", "001234", "Male", new Date(90,01,22), 1340);
		@SuppressWarnings("deprecation")
		HourlyEmployee he = new HourlyEmployee("Bert Simpson", "123456789", "001234", "Male", new Date(90,01,22), 1340,50);
		
		System.out.println(se.toString());
		System.out.println(" ");
		
		System.out.println(he.toString());
		System.out.println(" ");

		Employee emp = se;
		emp.display();
		System.out.println(se.compareTo(he));
	}
}
