package proj3;

import java.util.Scanner;

/***
 * Title: Basic Math
 * Description: Produce a program that will provide students with
 * addition and subtraction problems within the guidelines of their
 * level of study. The answers will also be provided.
 * 
 * @author Derick Hansraj
 *
 */

public class Project4App {
	 /**
     * <p> Name: main method </p>
     * 
     * @param args values to be sent to the method
     */

	public static void main(String[] args) {
	    Scanner s = new Scanner(System.in);
	    int correctAdd = 0;
        int correctSub = 0;
        int wrongAdd = 0;
        int wrongSub = 0;
        double total = (correctAdd + correctSub + wrongAdd + wrongSub);
        double correct = (correctAdd + correctSub);
    	double percentage = ((correct / total) * 100);
        
    	for(int j = 0; j < 20; j++ ) {
    		if ((j > 9) && (percentage >= 85.0))
    			break;
    		Question problem = new Question();
    		System.out.println("What is the result?\n" + problem.toString());
    		int answer = s.nextInt();
    		int answer2 = (problem.determineAnswer());
    		char sign = problem.getOperator();

    		if ((answer2 == answer) && (sign == '+'))
    			correctAdd++;
    		else if ((answer2 == answer) && (sign == '-'))
    			correctSub++;
    		else if ((answer2 != answer) && (sign == '+'))
    			wrongAdd++;
    		else if ((answer2 != answer) && (sign == '-'))
    			wrongSub++;
    		else System.out.println("error");


    		if (answer2 == answer)
    			System.out.println("Congratulations, you got it correct!\n" + "");
    		else if (answer2 != answer)
    			System.out.println("The correct answer for " + problem.toString() + "is " + answer2 + "\n");
    		else
    			System.out.println("error");

    		total = (correctAdd + correctSub + wrongAdd + wrongSub);
    		correct = (correctAdd + correctSub);
    		percentage = ((correct / total) * 100);
    	}
	

    	total = (correctAdd + correctSub + wrongAdd + wrongSub);
    	correct = (correctAdd + correctSub);
    	percentage = ((correct / total) * 100);
    	float percentageRound = (float)Math.round(percentage);    
    	System.out.println("Progress Report:");
    	System.out.println("Addition:\n" + "You got " + correctAdd + " correct" + " and " + wrongAdd + 
    			" incorrect.");
    	System.out.println("Subtraction:\n" + "You got " + correctSub + " correct" + " and " + wrongSub + 
    			" incorrect.");
    	System.out.println("The percent correct is " + percentageRound + "%");
	}
}



