package proj2test;

import java.util.StringTokenizer;

public class LabProj2 {
	public static void main (String [] args) {
		LinkedStack <String>l1 = new LinkedStack<String>();
		String expression = "5+5";
		String delims = "*/-+";
		String op = "*/-+ ";
		String token;
		String result = new String();
		boolean b = true;
		StringTokenizer strToken = new StringTokenizer(expression, delims, true);
		while(strToken.hasMoreTokens()){
			token = strToken.nextToken();
			if (isInteger(token))
				result += token + " ";
			else if(token.equals("("))
				l1.push(token);
			else if(token.equals(")")) {
				while(!l1.peek().equals("(")) {
					result += l1.pop() + " ";
				}
				if(l1.peek().equals("("))
					l1.pop();
			}
			else if (op.contains(token)) {
				if(prec(token) == 0) {
					result += token + " ";
				}
				if(prec(token) == 1) {
					
				}		
				if(prec(token) == 2) {
					System.out.println("2");
				}
			}	
		}
		while(!l1.isEmpty()) {
			result += l1.pop() + " ";
		}
		System.out.println(result);


	}
	static boolean isInteger( String input ) {
		try
		{
			Integer.parseInt( input );
			return true;
		}
		catch( RuntimeException Exception )
		{
			return false;
		}
	}

	static int prec(String x) {
		int num = 0;
		if (x == "+" || x == "-")
			num = 1;
		if (x == "*" || x == "/")
			num = 2;
		return num;
	}

}
