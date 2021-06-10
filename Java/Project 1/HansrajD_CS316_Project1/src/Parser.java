import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


/**

This class is a top-down, recursive-descent parser for the following syntactic categories:

<assignment list> --> <assignment> | <assignment> <assignment list>
<assignment> --> <id> = <E> ";"
<E> --> <term> | <term> + <E> | <term> - <E>
<term> --> <primary> | <primary> * <term> | <primary> / <term>
<primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")" 

The definitions of the tokens are given in the lexical analyzer class file "LexArithArray.java". 

The following variables/functions of "IO"/"LexArithArray" classes are used:

static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

static void setLex()
static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token

An explicit parse tree is constructed in the form of nested class objects.

The main function will display the parse tree in linearly indented form.
Each syntactic category name labeling a node is displayed on a separate line, 
prefixed with the integer i representing the node's depth and indented by i blanks. 

 **/


public abstract class Parser extends LexArithArray
{
	static boolean errorFound = false;
	public static AssignmentList assignmentList()

	// <assignment list> --> <assignment> | <assignment> <assignment list>

	{
		Assignment assignment = assignment();
		if ( state == State.Id )
		{
			AssignmentList assignmentList = assignmentList();
			return new MultipleAssignment(assignment, assignmentList);
		}
		else
			return assignment;
	}

	public static Assignment assignment()

	// <assignment> --> <id> = <E> ";"

	{
		if ( state == State.Id )
		{
			String id = t;
			getToken();
			if ( state == State.Assign )
			{
				getToken();
				E e = E();
				if ( state == State.Semicolon )
				{
					getToken();
					return new Assignment(id, e);
				}
				else
					errorMsg(4);
			}
			else
				errorMsg(3);
		}
		else
			errorMsg(5);
		return null;
	}

	public static E E()

	// <E> --> <term> | <term> + <E> | <term> - <E>

	{
		Term term = term();
		if ( state == State.Plus )
		{			
			getToken();
			E e = E();
			return new AddE(term, e);
		}
		else if ( state == State.Minus )
		{
			getToken();
			E e = E();
			return new SubE(term, e);
		}
		else {
			return new SingleTerm(term);
		}
	}

	public static Term term()

	// <term> --> <primary> | <primary> * <term> | <primary> / <term>

	{
		Primary primary = primary();
		if ( state == State.Times )
		{			
			getToken();
			Term term = term();
			return new MulTerm(primary, term);
		}
		else if ( state == State.Div )
		{
			getToken();
			Term term = term();
			return new DivTerm(primary, term);
		}
		else
			return new SinglePrimary(primary);
	}

	public static Primary primary()

	// <primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")"

	{
		switch ( state )
		{
		case Id:

			Id id = new Id(t);
			getToken();
			return id;

		case Int:

			Int intElem = new Int(Integer.parseInt(t));
			getToken();
			return intElem;

		case Float: case FloatE:

			Floatp floatElem = new Floatp(Float.parseFloat(t));
			getToken();
			return floatElem;

		case LParen:

			getToken();
			E e = E();
			if ( state == State.RParen )
			{
				getToken();
				Parenthesized paren = new Parenthesized(e);
				return paren;
			}
			else
			{
				errorMsg(1);
				return null;
			}

		default:

			errorMsg(2);
			return null;
		}
	}

	public static void errorMsg(int i)
	{
		errorFound = true;

		display(t + " : Syntax Error, unexpected symbol where");

		switch( i )
		{
		case 1:	displayln(" arith op or ) expected"); return;
		case 2: displayln(" id, int, float, or ( expected"); return;
		case 3:	displayln(" = expected"); return;
		case 4:	displayln(" ; expected"); return;
		case 5:	displayln(" id expected"); return;		
		}
	}

	public static void main(String argv[])
	{
		// argv[0]: input file containing an assignment list
		// argv[1]: output file displaying the parse tree
		// argv[2]: output file displaying the numbers of constructed objects
		PrintWriter outStream = null;


		setIO( argv[0], argv[1] );
		setLex();

		getToken();

		AssignmentList assignmentList = assignmentList(); // build a parse tree
		if ( ! t.isEmpty() )
			errorMsg(5);
		else if ( ! errorFound )
			assignmentList.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file

		closeIO();

		//define a linkedlist of type ObjectNode that holds strings
		LinkedList<ObjectNode<String>> finalList = new LinkedList<ObjectNode<String>>();
		int strCount = 0;
		//a for loop that will go through the linkedlist and get the count of each class type
		//and store it in the ConObjects defined in Obj.
		for(int i = 0; i < Obj.collection.size()-1;i++) {
			ObjectNode<String> temp;
			String content = Obj.collection.data(i);
			if(content.equals(Obj.collection.data(i+1))) {
				strCount++;
			}
			else {
				if(i == Obj.collection.size()-2 && Obj.collection.data(Obj.collection.size()-1).equals(content)) {
					++strCount;
				}
				if(i == Obj.collection.size()-2 && !Obj.collection.data(Obj.collection.size()-1).equals(content)) {
					temp = new ObjectNode<String>(Obj.collection.data(Obj.collection.size()-1),1);
					finalList.add(temp);
					System.out.println(1 + " " + Obj.collection.data(Obj.collection.size()-1) + "\n\n");
				}
				++strCount;
				temp = new ObjectNode<String>(content,strCount);
				finalList.add(temp);
				System.out.println(strCount + " " + content + "\n\n");
				strCount = 0;
			}
		}

		//This will sort the collection of ObjectNodes by count
		Collections.sort(finalList,new ObjectNodeComp());
		System.out.println(finalList.toString());

		//This will write the data such as the object class name and the count of the class occurences.
		try {
			//Name and path of the file
			File file = new File(argv[2]);

			if(!file.exists()){
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);

			BufferedWriter bw = new BufferedWriter(fw);
			for(int i = 0; i < finalList.size();i++) {
				bw.write(finalList.get(i).toString() + "\n\n");
			}
			bw.close();

		} catch(IOException ex) {
			System.out.println("Exception occurred:");
			ex.printStackTrace();
		}
	}
}
