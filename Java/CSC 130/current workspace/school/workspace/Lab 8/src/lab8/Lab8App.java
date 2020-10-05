package lab8;
/**
 * <p>Title: Student Average Program
 *
 * <p>Description: To create a program that will accept user input
 * such as first name, last name, student id and four test grades.
 * Then it will use this information to get the students average before
 * and after dropping the lowest grade.
 *
 * @author Derick Hansraj
 */


public class Lab8App {
	public static void main(String[] args){
	Student defaul = new Student();
	System.out.println("Testing default constructor: \n" + defaul.toString());
	System.out.println("");
	
	double[] s1A = new double[] {98.5, 76.5, 79.0, 84.0};
	Student s1 = new Student("987654321", "Jane", "Doe", s1A);
	System.out.println("Testing default constructor: \n" + s1);
	System.out.println("");
	
	s1.setStudentId("987654321");
	System.out.println("Setting Jane Doe’s id: \n" + s1.toString());
	System.out.println("");
	
	double[] s1A2 = {98.5, 95.0, 79.0, 84.0};
	s1.setGrades(s1A2);
	System.out.println("Setting Jane Doe’s second exam:\n" + s1.toString());
	System.out.println("");
	
	double [] newGrades = s1.getGrades();
	System.out.println("Getting the student id for Jane Doe:\n" + "Id is " + s1.getStudentId());
	System.out.println("Getting the first name for Jane Doe:\n" + "First name is " + s1.getFirstName());
	System.out.println("Getting the last name for Jane Doe:\n" + "Last name is " + s1.getLastName());
	System.out.println("Getting Jane Doe's grades:\n" + newGrades[0] + " " + newGrades[1] + " " + newGrades[2] + " " + newGrades[3]);
	System.out.println("Getting the array position of the lowest exam:\n" + "Lowest exam is at position" + );
	
	
	
	}

}
