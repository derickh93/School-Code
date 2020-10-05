package wenjieproj2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

/**
 * <b>Title:</b>	project2:<br>
 * <b>Filename:</b>	CalculatorFrame.java<br>
 * <b>Date Written:</b>		October 17.2017<br>
 * <b>Due Date:</b>		October 17,2017<br>
 * <p>
 * <b>Description:</b><br>
 * Design and implement a calculator application that permits the user to input an infix expression
 * by utilizing a stack. The program should then convert the infix expression to its postfix
 * equivalent, and then evaluate the postfix expression and display the result. The calculator should
 * operate on integer operands using the arithmetic operators +, �C, *, and / 
 * </p>
 * <p><b>Algorithm:</b></p>
 * <p>
 * </p>  
 * @author Wenjie Cao
 */



@SuppressWarnings("serial")
class CalculatorFrame extends JFrame implements ActionListener  {
	JTextField jtfInfix = new JTextField(21); // for infix 
	JTextField jtfPostfix = new JTextField();  // for postix
	JTextField result = new JTextField("0");   // for result

	JButton[][] calcButton = new JButton[4][5];

	JPanel calcPanel = new JPanel();	
	JPanel topPanel = new JPanel();   

	//private LinkedStack<Character> stack = new LinkedStack<Character>();


	public CalculatorFrame(){
		String[][] buttonText = 
				new String[][]{{"7","8","9","/","C"},{"4","5","6","*","B"},
			{"1","2","3","-","R"},{"0","(",")","+","="}};

			this.setTitle("CSC130 Calculator");
			this.setLayout(new BorderLayout(2,1));

			jtfInfix.setHorizontalAlignment(JTextField.RIGHT);
			jtfPostfix.setHorizontalAlignment(JTextField.RIGHT);
			result.setHorizontalAlignment(JTextField.RIGHT);
			jtfPostfix.setEnabled(false);
			result.setEnabled(false);
			//jtfInfix.setEditable(false); // hide text caret

			// set the font size to 34 for the text fields
			Font textFieldFont=new Font(jtfPostfix.getFont().getName(),jtfPostfix.getFont().getStyle(),24);
			jtfInfix.setFont(textFieldFont);
			jtfPostfix.setFont(textFieldFont);
			result.setFont(textFieldFont);

			topPanel.setLayout(new GridLayout(3,1));				
			topPanel.add(jtfInfix);		
			topPanel.add(jtfPostfix);
			topPanel.add(result);

			calcPanel.setLayout(new GridLayout(4,5,3,3));

			for (int i = 0; i < 4; i++) {
				for(int j = 0; j < 5; j++) {
					calcButton[i][j]= new JButton("" + buttonText[i][j]);
					calcButton[i][j].setForeground(Color.blue);
					calcButton[i][j].setFont(new Font("sansserif",Font.BOLD,42));
					calcButton[i][j].addActionListener(this);
					calcButton[i][j].setBorder(BorderFactory.createRaisedBevelBorder());
					calcPanel.add(calcButton[i][j]);
				}
			}
			this.add(topPanel,BorderLayout.NORTH);
			this.add(calcPanel,BorderLayout.CENTER);
	}
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 4; i++) {
			for(int j = 0; j < 5; j++) {				
				if(e.getSource() == calcButton[i][j]){
					// clear
					if(i==0 && j == 4){
						jtfInfix.setText(null);
						jtfPostfix.setText(null);
						result.setText("0");
					}
					// backspace
					else if(i==1 && j == 4){
						if(jtfInfix.getDocument().getLength()>0)
							try {
								jtfInfix.setText(jtfInfix.getText(0, jtfInfix.getDocument().getLength()-1));
							} catch (BadLocationException e1) {
								e1.printStackTrace();
							}

					}
					// number or operator
					else if(j<4){
						jtfInfix.setText(jtfInfix.getText()
								+ calcButton[i][j].getText());
					}
					// = button pressed
					else if(i==3&&j==4){
						// erase contents of the postfix textfield
						jtfPostfix.setText(null);  
						// update the postfix textfield with the String returned  
						jtfPostfix.setText(infixToPostfix());
						// update the result textfield with the result of the computation
						result.setText("" + calculate());
					}
				}
			}
		}
	}

	static boolean getInteger(String aChar ) {
		try
		{
			Integer.parseInt( aChar );
			return true;
		}
		catch( RuntimeException Exception )
		{
			return false;
		}
	}

	/** Task: Indicates the precedence of a given operator.
	 * @param operator a character that is (, ), +, -, *, /, or ^
	 * @return an integer that indicates the precedence of operator:
	 * 0 if ( or ), 1 if + or -, 2 if * or / -1 if anything else */
	private static int getPrecedence (char operator)
	{
		switch (operator)
		{
		case '(':
		case ')':
			return 0;
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		} 
		return -1;
	} 


	/** Task: Creates a postfix expression that represents a given infix
	 * expression.
	 * @param infix a string that is a valid infix expression
	 * @return a string that is the postfix expression equivalent to infix */
	public String infixToPostfix(){		
		StringBuffer postfix = new StringBuffer();
		LinkedStack<Character> stack = new LinkedStack<Character>();
		String infix = jtfInfix.getText();
		char topOperator;
		for(int index = 0; index < infix.length(); index ++)
		{  
			char aChar = infix.charAt(index);
			String s = "" + aChar;
			if(getInteger(s))
				postfix.append(aChar);
			else
			{
				switch(aChar)
				{ 
				case '(':
					stack.push(aChar);
					break;
				case ')':
					topOperator = (Character)stack.pop();
					while (topOperator != '(')
					{  postfix.append(topOperator);
					topOperator =(Character) stack.pop ();
					} 
					break;
				default:
					if(stack.isEmpty()||(getPrecedence(aChar)>getPrecedence((Character)stack.peek())))
						stack.push(aChar);
					else if(getPrecedence(aChar)<=(getPrecedence((Character)stack.peek())))
					{  while (!stack.isEmpty() && (getPrecedence(aChar)<=getPrecedence((Character)stack.peek()))) 
					{
						postfix.append((Character)stack.pop());
					}
					stack.push(aChar);
					}
					break;
				}
			}	

		}
		while (!stack.isEmpty ())
		{ postfix.append(stack.pop());
		} 
		return  postfix.toString();
	}


	public String calculate() {
		LinkedStack<Integer> stack = new LinkedStack<Integer>();
		String calc = infixToPostfix();
		String result = new String();
		int first, last;
		for(int index = 0; index < calc.length(); index ++) {  
			char aChar = calc.charAt(index);
			String s = "" + aChar;
			if(getInteger(s))
				stack.push(Integer.parseInt(s));
			else if(getPrecedence(aChar) > 0) {
				last = stack.pop();
				first = stack.pop();
				if(aChar == '+')
					stack.push(first+last);
				if(aChar == '-')
					stack.push(first-last);
				if(aChar == '/')
					stack.push(first/last);
				if(aChar == '*')
					stack.push(first*last);
			}
		}
		return result + stack.pop();
	}


	static final int MAX_WIDTH = 398, MAX_HEIGHT = 440;

	public static void main(String arg[]){
		JFrame frame = new CalculatorFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(MAX_WIDTH,MAX_HEIGHT);	
		frame.setBackground(Color.white);		
		frame.setResizable(false);				
		frame.setVisible(true);
	}
}