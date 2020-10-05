package checkCalc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Calc {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calc window = new Calc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnPushToBegin = new JButton("Push to Begin");
		btnPushToBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog("Thank You! Lets Begin.");
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
				
				double hoursConverted = Integer.parseInt(hours);
				double  rateConverted =  Integer.parseInt(rate);
				
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
				
				System.out.println("" );
				System.out.println("Name: "+lastName+','+firstName );
				System.out.println("Your initials are: "+ initials);
				System.out.println("There are "+firstCount+ " letters in your first name." );
				System.out.println("There are "+lastCount+ " letters in your last name." );
				System.out.println("There are " +nameCount+" letters in your full name." );
				System.out.println("D.O.B: "+dob);
				System.out.println("Hours Worked: "+hours);
				System.out.println("Pay Rate:$"+rate);
				System.out.println("Net Pay: $"+net );
				if (hoursConverted >40)
					System.out.println("You have worked " + (hoursConverted-40)+" hours overtime.");
					else
						System.out.println("You have not worked overtime.");
			}
		});
		btnPushToBegin.setBounds(151, 11, 129, 23);
		frame.getContentPane().add(btnPushToBegin);
	}
}
