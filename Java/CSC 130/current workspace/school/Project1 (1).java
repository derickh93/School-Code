package proj1;
import javax.swing.JOptionPane;

public class Project1 {
	public static void main(String[] args)
	{
		String fullName;
		int posOfSpace;
		String firstName;
		String lastName;
		int firstNameLength;
		int lastNameLength;
		int fullNameLength;
		char firstInitial;
		char lastInitial;
		double midterm;
		double finalExam;
		double average;
		
		lastName = fullName.substring(5);
		System.out.println("There are " + lastNameLength + " letters in my last name");
		posOfSpace = fullName.indexOf(" ");
		
		fullNameLength = fullName.length();
		firstName = fullName.substring(1, posOfSpace - 1);
		System.out.println("Midterm: " + midterm);
		
		System.out.println("Name: " + firstName + ", " + lastName + "\n");
		
		firstNameLength = lastName.length();
		
		fullName = JOptionPane.showInputDialog("Please enter your first name, followed by a space, followed by your last name");
		average = midterm + finalExam / 2;
		System.out.println("There are 11 letters in my full name\n");
		
		lastNameLength = lastName.length();
		firstInitial = fullName.charAt(0);
		lastInitial = fullName.charAt(firstNameLength - 1);
		
		System.out.println("Last initial: " + lastInitial + "\n");
		
		midterm = Double.parseDouble(JOptionPane.showInputDialog("Please enter your midterm score."));
		System.out.println("There are 4 letters in my first name " + firstNameLength);
		finalExam = Double.parseDouble(JOptionPane.showInputDialog("Please enter your final exam score."));		
		
		System.out.println("Final Exam: " + midterm);
		System.out.println("\nAverage: " + average);
	}

}
