/*
* Top-down Parser
* Based on: http://picasso.cs.qc.cuny.edu/cs316/parseArith.java
* With modifications by: Brian Ruslim (bruslim100@qc.cuny.edu)
*/

public class Parser extends LexicalAnalyzer
{

	private static final String TAB = "    ";
	/**
	* <fun defs> -> {<fun def>}+
	*/
	private static void fun_defs(String indent)
	{
		String oIndent = indent; // ye Olde Indent
		displayln("<" + (oIndent.length()/TAB.length()) + ":FunctionDefinitions>");
		
		indent = TAB;
		fun_def(indent);
		
		while (a != -1 && (state == state.Id || state == State.Id_i ||
			state == State.Id_e || state == State.Id_el ||
			state == State.Id_els)) // eos
		{
			indent = TAB;		
			fun_def(indent);
		}
		
		displayln("</" + (oIndent.length()/TAB.length()) + ":FunctionDefinitions>");
	}

	/**
	* <fun def> -> <header> <body>
	*/
	private static void fun_def(String indent)
	{
		String oIndent = indent;
		displayln(oIndent + "<" + (oIndent.length()/TAB.length()) + ":FunctionDefinition>");
		indent = indent + TAB;

		header(indent);

		body(indent);    	
		
		displayln(oIndent + "</" + (oIndent.length()/TAB.length()) + ":FunctionDefinition>");
		// Platoon Policiy:
		/*
		if (a != -1)
		{
			switch (state)
			{
				case Id:
				case Id_i:
				case Id_e:
				case Id_el:
				case Id_els:
					fun_def(oIndent);
					break;
				case If:
				case Else:
					errorMsg2(t);
					displayln("");
					break;		
			}
		}
		*/
	}

	/**
	* <header> -> <fun name> "(" <parameter list> ")"
	*/
	private static void header(String indent)
	{
		String oIndent = indent;
		displayln(oIndent + "<" + (oIndent.length()/TAB.length()) + ":Header>");
		indent = indent + TAB;

		fun_name(indent);
		
		parameter_list(indent);
	
		displayln(oIndent + "</" + (oIndent.length()/TAB.length()) + ":Header>");
	}

	/**
	* <fun name> -> <id>
	*/
	private static void fun_name(String indent)
	{
		display(indent + "<" + (indent.length()/TAB.length()) + ":FunctionName>");
		while (state == State.Id || state == State.Id_e || state == State.Id_el
				|| state == State.Id_els || state == State.Id_i)
		{
			display(t);
			getToken();
		}
		switch(state)
		{
			case If:
			case Else:		
				errorMsg2(t);
		}
		displayln("</" + (indent.length()/TAB.length()) + ":FunctionName>");
	}

	/**
	* <parameter list> -> ? | <id> {"," <id>}
	*/
	private static void parameter_list(String indent)
	{
		display(indent + "<" + (indent.length()/TAB.length()) + ":ParamterList>");
		if (state == State.LParen)
		{
			getToken();     // Discard (
			while (state == State.Id || state == State.Id_e
					|| state == State.Id_el || state == State.Id_els
					|| state == State.Id_i  || state == State.Comma)
			{
				display(t);
				getToken();
			}
			switch(state)
			{
				case RParen:
					getToken(); // Discard )
				case If:
				case Else:
					errorMsg2("else");
				default:
					errorMsg(")");
			}
		}
		else
			errorMsg("(");
		displayln("</" + (indent.length()/TAB.length()) + ":ParameterList>");
	}

	/**
	* <body> -> "{" <Exp> "}"
	*/
	private static void body(String indent)
	{
		String oIndent = indent;
		displayln(oIndent + "<" + (oIndent.length()/TAB.length()) + ":Body>");
		indent = indent + TAB;
		if (state == State.LBrace)
		{
			getToken();     // discard {
			exp(indent);
			if (state == State.RBrace)
				getToken(); // discard }
			else
			{
				display(indent);
				errorMsg("}");
				displayln("");
			}
		}
		else
		{
			display(indent);
			errorMsg("{");
			displayln("");
		}
		displayln(oIndent + "</" + (oIndent.length()/TAB.length()) + ":Body>");
	}

	/**
	* <Exp> -> "if" "(" <expr> ")" <Exp> "else" <Exp> | <expr>
	*/
	private static void exp(String indent)
	{
		String oIndent = indent;
		displayln(oIndent + "<" + (oIndent.length()/TAB.length()) + ":Exp>");
		indent = indent + TAB;
		if (state == State.If)
		{
			displayln(indent+"<"+ (indent.length()/TAB.length()) + ":IfStatement>");
			getToken(); //discard if
			if (state == State.LParen)
			{
				displayln(indent+TAB+"<"+ ((indent.length()/TAB.length())+1) +
					":Conditional>");
				getToken(); //discard (
				expr(indent+TAB+TAB);
				if (state == State.RParen)
					getToken(); // discard )
				else
				{
					display(indent+TAB+TAB);
					errorMsg(")");
					displayln("");
				}
				displayln(indent+TAB+"</"+ ((indent.length()/TAB.length())+1) +
					":Conditional>");
				displayln(indent+TAB+"<"+ ((indent.length()/TAB.length())+1) +
					":TRUE_Block>");
				exp(indent+TAB+TAB);
				displayln(indent+TAB+"</"+ ((indent.length()/TAB.length())+1) +
					":TRUE_Block>");
				displayln(indent+TAB+"<"+ ((indent.length()/TAB.length())+1) +
					":ELSE_Block>");
				if(state == State.Else)
				{
					getToken(); // discard else
					exp(indent+TAB+TAB);
				}
				else
				{
					display(indent+TAB);
					errorMsg("else");
					displayln("");
				}
				displayln(indent+TAB+"</"+ ((indent.length()/TAB.length())+1) +
					":ELSE_Block>");
			}
			else
				errorMsg("(");
			displayln(indent+"</"+(indent.length()/TAB.length()) + ":IfStatement>");
		}
		else		
			expr(indent);		
		displayln(oIndent + "</" + (oIndent.length()/TAB.length()) + ":Exp>");
	}

	/**
	* <expr> -> <boolTerm> { "||" <boolTerm> }
	*/
	private static void expr(String indent)
	{
		String oIndent = indent;
		displayln(oIndent + "<" + (oIndent.length()/TAB.length()) + ":expr>");
		indent = indent + TAB;
		boolTerm(indent);
		
		while (state == State.Or)
		{
			getToken();
			boolTerm(indent);
		}
		displayln(oIndent + "</" + (oIndent.length()/TAB.length()) + ":expr>");
	}

	/**
	* <boolTerm> -> <boolPrimary> { "&&" <boolPrimary> }
	*/
	private static void boolTerm(String indent)
	{
		String oIndent = indent;
		displayln(oIndent + "<" + (oIndent.length()/TAB.length()) + ":BooleanTerm>");
		indent = indent + TAB;
		boolPrimary(indent);
		while (state == State.And)
		{
			getToken();
			boolPrimary(indent);
		}
		displayln(oIndent + "</" + (oIndent.length()/TAB.length()) + ":BooleanTerm>");
	}

	/**
	* <boolPrimary> -> <E> [ <rel op> <E> ]
	*/
	private static void boolPrimary(String indent)
	{
		String oIndent = indent;
		displayln(oIndent + "<" + (oIndent.length()/TAB.length()) + ":BooleanPrimary>");
		indent = indent + TAB;
		E(indent);
		switch (state)
		{
			case Lesser:
			case LesserEqual:
			case Greater:
			case GreaterEqual:
			case Equal:
			case NotEqual:
				rel_op(indent);
		}
		displayln(oIndent + "</" + (oIndent.length()/TAB.length()) + ":BooleanPrimary>");
	}

	/**
	* <rel op> -> "<" | "<=" | ">" | ">=" | "==" | "!="
	*/
	private static void rel_op(String indent)
	{
		display(indent + "<" + (indent.length()/TAB.length()) + ":RelationOperator>");
		switch (state)
		{
			case Lesser:
			case LesserEqual:
			case Greater:
			case GreaterEqual:
			case Equal:
			case NotEqual:
				display(" " + t +" ");		
				displayln("</" + (indent.length()/TAB.length()) + ":RelationOperator>");
				getToken();
				E(indent);
				break;
			default:
				errorMsg("< OR <= OR > OR >= OR == OR !=");
				displayln("</" + (indent.length()/TAB.length()) + ":RelationOperator>");
		}
	}

	/**
	* <E> --> <term> { (+|-) <term> }
	*
	* @param indent
	*/
	public static void E(String indent)
	{
		String oIndent = indent;
		displayln(oIndent + "<" + (oIndent.length()/TAB.length()) + ":E>");
		indent = indent + TAB;
		term(indent);
		while (state == State.Plus || state == State.Minus)
		{
			displayln(indent +"<" + (indent.length()/TAB.length()) + ":AirthmetricOperator> "
				+ t + " </" + (indent.length()/TAB.length()) + ":AirthmetricOperator>");
			getToken();
			term(indent);
		}
		displayln(oIndent + "</" + (oIndent.length()/TAB.length()) + ":E>");
	} // end E

	/**
	* <term> --> <primary> { (*|/) <primary> }
	*
	* @param indent
	*/
	public static void term(String indent)
	{
		String oIndent = indent;
		displayln(oIndent + "<" + (oIndent.length()/TAB.length()) + ":Term>");
		indent = indent + TAB;

		primary(indent);
		while (state == State.Times || state == State.Div)
		{
			displayln(indent +"<" + (indent.length()/TAB.length()) + ":AirthmetricOperator> "
				+ t + " </" + (indent.length()/TAB.length()) + ":AirthmetricOperator>");
			getToken();
			primary(indent);
		}
		
		displayln(oIndent + "</" + (oIndent.length()/TAB.length()) + ":Term>");
	} // end term

	/**
	* <primary> --> <id> | <int> | <float> | <fun E> "(" <expr> ")"
	*                    | − <primary> | ! <primary>
	*
	* @param indent
	*/
	public static void primary(String indent)
	{
		String oIndent = indent;
		display(oIndent + "<" + (oIndent.length()/TAB.length()) + ":Primary>");
		indent = indent + TAB;

		// state == Id, Int, Float, or FloatE
		switch (state)
		{
			case Id:
			case Id_i:
			case Id_e:
			case Id_el:
			case Id_els:
				String temp = "";
				while (state == State.Id || state == State.Id_i ||
						state == State.Id_e || state == State.Id_el ||
						state == State.Id_els)
				{
					temp += t;
					getToken();
				}
				if (state == State.LParen)
				{
					displayln("");
					displayln(indent + "<" + (indent.length()/TAB.length()) + ":FunctionCall>");
					displayln(indent + TAB + "<" + ((indent.length()/TAB.length()) + 1) +
						":FunctionName>" + temp + "</" + ((indent.length()/TAB.length()) + 1) +
						":FunctionName>");

					expr_list(indent+TAB);

					displayln(indent + "</" + (indent.length()/TAB.length()) + ":FunctionCall>");
					displayln(oIndent + "</" + (oIndent.length()/TAB.length()) + ":Primary>");
				}
				else
				{
					displayln(temp + "</" + (oIndent.length()/TAB.length()) + ":Primary>");
				}
				break;

			case Int:
				while (state == State.Int)
				{
					display(t);
					getToken();
				}    		
			case Period:
				if (state == State.Period)
				{
					display(t);
					getToken(); // Discard .
				}
			case Float:
			case FloatE:
				while (state == State.Float || state == State.FloatE)
				{
					display(t);
					getToken();
				}
				displayln("</" + (oIndent.length()/TAB.length()) + ":Primary>");
				break;

			case LParen:
				displayln("");
				getToken(); // discard (
				expr(indent);
				if (state != State.RParen)
				{
					display(indent);
					errorMsg(")");
				}
				else
				{
					getToken(); // discard )
				}
				displayln(oIndent + "</" + (oIndent.length()/TAB.length()) + ":Primary>");
				break;
			
			case Not:
			case Minus:
				String x = t;
				displayln("");
				displayln(indent +"<" + (indent.length()/TAB.length()) + ":" + x + "Operator>");
				getToken();
				primary(indent+TAB);
				displayln(indent + "</" + (indent.length()/TAB.length()) + ":" + x + "Operator>");
				displayln(oIndent + "</" + (oIndent.length()/TAB.length()) + ":Primary>");
				break;
			case If:
			case Else:
				errorMsg2(t);
				//getToken();
				displayln("</" + (oIndent.length()/TAB.length()) + ":Primary>");
				break;
			default:
				errorMsg("id OR number OR E OR - OR ! OR (");
				//getToken();
				displayln("</" + (oIndent.length()/TAB.length()) + ":Primary>");
		}
			
		
	} // end primary

	public static void expr_list(String indent)
	{
		String oIndent = indent;
		displayln(oIndent + "<" + (oIndent.length()/TAB.length()) + ":FunctionParameters>");
		indent = indent + TAB;
		if (state == State.LParen)
		{
			getToken();

			if (state != State.RParen)
			{
				expr(indent);
				while (state == State.Comma)
				{
					getToken(); // Discard ,
					expr(indent);
				}
			}

			if (state == State.RParen)
			{
				getToken(); // Discard )
			}
			else
			{
				display(indent);
				errorMsg(")");
				displayln("");
			}
		}

		displayln(oIndent + "</" + (oIndent.length()/TAB.length()) + ":FunctionParameters>");
	}

	public static void errorMsg(String expected)
	{
		display("!!== ERROR: ");
		switch (state)
		{
			case If:
			case Else:
				display(t + " is a reserved symbol, where " + expected + " expected ==!!");
				break;
			default:
				display(t + " unexpected symbol where " + expected
					+ " expected ==!!");
		}
	} // end errorMsg
	public static void errorMsg2(String reserved)
	{
		display("!!== ERROR: " + reserved + " is a reserved symbol ==!!");
	} // end errorMsg

	/**
	* The input/output file names must be passed as argv[0] and argv[1].
	*
	* @param argv
	*/
	public static void main(String argv[])
	{
		setLex(argv[0], argv[1]);
		getToken();
		fun_defs("");
		closeIO();
	} // end main
}
