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

public class Student {
	
	// instance variables
	String studentId, firstName, lastName;
	double[] grades = new double[4];
	
	
	
	/** 
	 * default constructor
	 * the id, first and last names are initialized to "none"
	 * the array is created with 4 elements - each element is
	 * initialized to 0
	 */
	public Student(){
		studentId = "none";
		firstName = "none";
		lastName = "none";
		grades = new double[]{0.0, 0.0, 0.0, 0.0};
	}
	
	/** 
	 * parameterized constructor
	 * stores the parameters into the appropriate instance variables
	 * @param theId the value to be stored in studentId
	 * @param theFirstName the value to be stored in firstName
	 * @param theLastName the value to be stored in lastName
	 * @param theExams the value to be stored in grades
	 */
	public Student(String theId, String theFirstName, String theLastName, double[] theExams){
		studentId = theId;
		firstName = theFirstName;
		lastName = theLastName;
		grades = theExams;
	}
		
	
	/**
	 * setStudentId - mutator method for studentId
	 * stores the parameter into the instance variable
	 * @param theId the value to be stored in the instance variable studentId
	 */
	public String setStudentId(String theId) {
		studentId = theId;
		return studentId;
	}

	/**
	 * setGrades - mutator method for grades
	 * stores the parameter into the instance variable
	 * @param theGrades a reference to an array containing the new grades
	 */
	public void setGrades(double[] theGrades) {
		grades = theGrades;
	}

	/**
	 * getFirstName - accessor method for firstName
	 * @return a reference to the instance variable firstName
	 */
	public String getFirstName(){
		return firstName;
		
	}

	/**
	 * getLastName - accessor method for lastName
	 * @return a reference to the instance variable lastName
	 */
	public String getLastName(){
		return lastName;
	}
	
	/**
	 * getStudentId - accessor method for id
	 * @return a reference to the instance variable id
	 */
	public String getStudentId(){
		return studentId;
	}
	
	/**
	 * getGrades - accessor method for exams
	 * @return 
	 * @return a reference to the instance variable exams
	 */
	public double [] getGrades() {
		return grades;
	}
	
	/**
	 * findLowestExam - find the lowest exam score in the array and
	 * return its location in the array
	 * @return the position of the lowest exam grade in the array
	 */
	public double [] findLowestExam() {
		  double smallest = grades[0];
          double biggest = grades[0];
         
          for(double i=1; i< grades.length; i++)
          {
                  if(grades[i] > biggest)
                          biggest = grades[i];
                  else if (grades[i] < smallest)
                          smallest = grades[i];
	 }

	
	/**
	 * calcExamAverage - calculates the average of the exams in one of two ways 
	 * if the parameter is true, the lowest exam score is dropped in 
	 * calculating the average
	 * if the parameter is false, no exams are dropped in the calculating
	 * the average
	 * @param drop should the lowest grade be dropped from the average?
	 * @return the average of the exams
	 */
	
	/**
	 * toString - create and return a String with the instance variable values
	 * @return a reference to a String containing the id, first and last names
	 * and the exam grades
	 */
	public String toString(){
		return ("Id: " + studentId + "\nName: " + lastName + ", " + firstName + "\nGrades:" + grades[0] + " " +grades[1]+ " "+grades[2]+ " "+grades[3]);

		
	}

}
