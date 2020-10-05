package test;
import java.util.Scanner;

public class First {
	public static void main ( String args[]){
		Scanner s = new Scanner(System.in);
		String firstName;
		String lastName;
		String dob;
		String hours;
		String rate;
		double net;
		int nameCount;
		int firstCount;
		int lastCount;
		String firstInitial;
		String lastInitial;
		String initials;
		String gender;
		String charName = null;
		String age;
		String fullGender = null;
		String Peter = "Peter";
		String Meg = "Meg";
		String Brian = "Brian";
		String Stewie = "Stewie";
		String Lois = "Lois";
		String Chris = "Chris";
		String Male = "Male";
		String Female = "Female";
		
		
		System.out.println("Enter your first name:");
		firstName = s.nextLine();

		System.out.println("Enter your last name:");
		lastName = s.nextLine();
		
		
		System.out.println("Enter your DOB (Month Date Year):");
		dob = s.nextLine();
		
		System.out.println("Enter your hours worked:");
		hours = s.nextLine();
		
		System.out.println("Enter your pay rate:");
		rate = s.nextLine();
		
		System.out.println("Enter your gender:  (M for Male,  F for Female)");
		gender = s.nextLine();
		

		System.out.println("Enter your age: ");
		age = s.nextLine();
		
		double hoursConverted = Integer.parseInt(hours);
		double  rateConverted =  Integer.parseInt(rate);
		int ageConverted = Integer.parseInt(age);	
		
		if (gender.equals("m"))
			fullGender=Male;
			else
				fullGender=Female;
		
			
			
				
		
			if (hoursConverted >40)
				net = (((hoursConverted-40)*(rateConverted*1.5) + (rateConverted*40)));
				else 
					net = (rateConverted*hoursConverted);
			
			firstCount = firstName.length();
			lastCount = lastName.length();
			nameCount = (firstCount+lastCount);
			firstInitial = firstName.substring(0, 1);
			lastInitial = lastName.substring(0, 1);
			initials = (firstInitial+','+lastInitial);
			
			if (gender.equals("m") && ageConverted >= 40)
				charName=Peter;
			else if
			(gender.equals("m") && ageConverted >= 20)
				charName=Brian;
			else if
			(gender.equals("m") && ageConverted >= 15)
				charName=Chris;
			else if
			(gender.equals("m") && ageConverted <= 14)
				charName=Stewie;
			else if
			(gender.equals("f") && ageConverted >= 40)
				charName=Lois;
			else 
				charName=Meg;
			
		
		
		System.out.println("" );
		System.out.println("Name: "+lastName+','+firstName );
		System.out.println("Your initials are: "+ initials);
		System.out.println("There are "+firstCount+ " letters in your first name." );
		System.out.println("There are "+lastCount+ " letters in your last name." );
		System.out.println("There are " +nameCount+" letters in your full name." );
		System.out.println("D.O.B: "+dob);
		System.out.println("Your age is " +age);
		System.out.println("Your gender is "+fullGender );
		System.out.println("Your character name is "+charName );
		System.out.println("Hours Worked: "+hours);
		System.out.println("Pay Rate:$"+rate);
		System.out.println("Net Pay: $"+net );
		if (hoursConverted >40)
			System.out.println("You have worked " + (hoursConverted-40)+" hours overtime.");
			else
				System.out.println("You have not worked overtime.");
		
}
}