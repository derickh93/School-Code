/**

This program was written and tested with JDK 1.6.0.

This class contains a parser for VM instruction streams defined by:

<instructions> --> { <instruction> }+
<instruction> --> <push> | <op> | <if_f> | <goto> | <label> | <call> | <return>
<push> --> push "#"<int> | push <int> | push <float> | push <floatE>
<op> --> add | sub | mul | div | or | and | inv | neg | lt | le | gt | ge | eq | neq
<if_f> --> if_f goto <int>
<goto> --> goto <int>
<label> --> <int> ":"
<call> --> call <int> "," <int>
<return> --> return

The following functions from LexInst.java are used:

static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token
static void display(String s)
static void displayln(String s)
static void setLex(String inFile, String outFile)
static void closeIO()

Internally, the instructions are represented by objects of the descendant classes of the "Instruction" class.
As the parser processes each instruction in the input file, it creates a corresponding instruction object
and stores it in the "instStore[]" array.
The updateLabels() function then updates the target labels of "goto", "if_f", "return" instructions to 
addresses (indexes) of "instStore[]".
The interpret() function then executes the instructions.

**/

import java.io.*;
import java.util.*;

public abstract class VM extends LexInst
{

	private static int instStoreSize = 2000;	
	private static Instruction instStore[] = new Instruction[instStoreSize]; // the instruction store

	private static int i = 0; // index used to load instructions in "instStore[]"

	public static int pc = 0; // the program counter

	private static int opStackSize = 2000;
	public static Elem opStack[] = new Elem[opStackSize]; // the operand stack
	public static int top = -1; // the top index of the operand stack

	private static int runtimeStackSize = 2000;
	public static AR runtimeStack[] = new AR[runtimeStackSize];
	public static int topR = -1; // the top index of the runtime stack 

	public static HashMap<Integer,Integer> labelMap = new HashMap<Integer,Integer>();
		// used to map labels to addresses (indexes) of "instStore[]"

	private static boolean lexSyntaxError = false;

	public static void instruction()

	// Extracts a single instruction and stores it in "instStore" array.

	{
	switch( state )
	{
		case Push:
		{
			getToken();

			switch( state )
			{
			case Sharp:
			{
				getToken();
				if ( state == State.Int )
				{
					PushVar p = new PushVar();
					p.index = Integer.parseInt(t);
					instStore[i] = p; 
					getToken();
				}
				else
					errorMsg(2);
				return;
			}
			case Int:
			{
				Num n = new Num();
				n.val = myParseInt(t);
				PushNum p = new PushNum();
				p.arg = n;
				instStore[i] = p; 
				getToken();
				return;
			}
			case Float: case FloatE:
			{
				Num n = new Num();
				n.val = Double.parseDouble(t);
				PushNum p = new PushNum();
				p.arg = n;
				instStore[i] = p; 
				getToken();
				return;
			}
			default:	
				errorMsg(1);
				return;
			}
		}
		case Add: case Sub: case Mul: case Div: case Or: case And:
		{
			Operator1 op = new Operator1();
			op.opcode = state;
			op.numOfArgs = 2;
			instStore[i] = op;
			getToken();
			return;
		}
		case Inv: case Neg: case Lt: case Le: case Gt: case Ge: case Eq: case Neq:
		{
			Operator2 op = new Operator2();
			op.opcode = state;
			instStore[i] = op;
			getToken();
			return;
		}
		case Iff:
		{
			getToken();
			if ( state == State.Goto )
			{
				getToken();
				if ( state == State.Int )
				{
					Iff iff = new Iff();
					iff.target = Integer.parseInt(t);
					instStore[i] = iff;
					getToken();
				}
				else
					errorMsg(2);
			}
			else
				errorMsg(3);
			return;
		}
		case Goto:
		{
			getToken();
			if ( state == State.Int )
			{
				Goto g = new Goto();
				g.target = Integer.parseInt(t);
				instStore[i] = g;
				getToken();
			}
			else
				errorMsg(2);
			return;
		}
		case Int:
		{
			String label = t;
			getToken();

			if ( state == State.Colon )
			{
				labelMap.put(Integer.parseInt(label), i);
				i--;
				getToken();
			}
			else
				errorMsg(4);
			return;
		}
		case Call:
		{
			getToken();
			if ( state == State.Int )
			{
				Call c = new Call();
				c.funStart = Integer.parseInt(t);
				getToken();
				if ( state == State.Comma )
				{
					getToken();
					if ( state == State.Int)
					{
						c.numOfArgs = Integer.parseInt(t);
						instStore[i] = c;
						getToken();
					}
					else
						errorMsg(2);
				}
				else
					errorMsg(6);
			}
			else
				errorMsg(2);
			return;
		}
		case Return:
		{
			instStore[i] = new Return();;
			getToken();
			return;
		}
		default:
			errorMsg(5);
			return;
	}
	}

	private static int myParseInt(String t)
	{
		if ( t.charAt(0) == '+' )
			return Integer.parseInt(t.substring(1));
		else
			return Integer.parseInt(t);
	}

	public static void errorMsg(int i)
	{
		display(t + ": unexpected symbol where");

		switch( i )
		{
		case 1:
			displayln(" #, int, or float expected"); break;
		case 2:
			displayln(" int expected"); break;
		case 3:
			displayln(" goto expected"); break;
		case 4:
			displayln(" : expected"); break;
		case 5:
			displayln(" label or instruction expected"); break;
		case 6:
			displayln(" , expected"); break;
		}

		lexSyntaxError = true;

	}

	public static void instructions()
	{
		instruction();
		i++;
		while ( state.ordinal() >= State.Add.ordinal() || state == State.Int )
		{
			instruction();
			i++;
		}
	}

	public static void updateLabels()

	// update target labels of "goto", "if_f", "return" instructions to addresses (indexes) of "instStore[]"

	{
		int j = 0;

		while ( j <= instStoreSize-1 && instStore[j] != null )
		{
			instStore[j].updateLabel();
			j++;
		}
	}

	public static void interpret()
	{
		while ( instStore[pc] != null ) 

		// The program terminates as soon as the "null" instruction is fetched.

		{
			instStore[pc].execute();
		}
	}
		
	public static void main(String argv[])
	{
		setLex( argv[0], argv[1] );

		getToken();
		instructions();
		if ( ! t.isEmpty() )
		{
			displayln(t + "  -- unexpected symbol");
			lexSyntaxError = true;
		}

		if ( lexSyntaxError )
			System.out.println("Lex/syntax errors found -- see the file "+argv[1]);
		else
		{
			updateLabels();
			//System.out.println(labelMap.toString());

			interpret();

			System.out.println(opStack[0].toString());
		}

		closeIO();
	}
}
