import java.util.Date;

/**
 * Title: CSC130hansrajd.lab2
 * Filename: Lab2App.java
 * Date Written: September 26, 2017
 * Due Date: September 30, 2017
 * Description: Lab2App Class - This application class  creates a SalariedEmployee object
 * , a Employee object and a HourlyEmployee object. It then uses various methods from each class
 * to process and display the desired parameters.
 * @author <Derick Hansraj>
 */
public class Lab2App {
	public static void main(String [] args) {
		SalariedEmployee se = new SalariedEmployee("Bert Simpson", "123456789", "001234", "Male", new Date(90,01,22), 1340);
		HourlyEmployee he = new HourlyEmployee("Bert Simpson", "123456789", "001234", "Male", new Date(90,01,22), 1340,50);

		System.out.println(se.toString() + "\n");

		System.out.println(he.toString() + "\n");

		Employee emp = se;
		emp.display();

		System.out.println(se.compareTo(he));

		SalariedEmployee se2 = new SalariedEmployee("Derick Hansraj", "987654321", "432100", "Male", new Date(93,07,12), 1500);
		HourlyEmployee he2 = new HourlyEmployee("Bert Simpson", "123456789", "001234", "Male", new Date(90,01,22), 1340,50);

		System.out.println(se2.toString() + "\n");

		System.out.println(he2.toString() + "\n");

		Employee emp2 = se2;
		emp2.display();

		System.out.println(se2.compareTo(he2));
	}
}

/**
1. Call (invoke) the Employee display method. What happens and why?
- The method displays the current status of the Employee object. The method is not 
defined in the Employee class but it is defined in the SalariedEmployee class.

2. What happens if you remove the statement super from the Employee constructors?
- The removal of super results in the default constructor for the Person class getting used.

3. What happens if you implement the Comparable interface and the display method in 
the Employee class instead of the subclass? Which way is better?
- The ability to compare when using the Employee class results in a more general comparison. 
The choice to implement in the subclass is better because it gives you a more desired result
because it is geared towards the information for that type of Employee.
 */
