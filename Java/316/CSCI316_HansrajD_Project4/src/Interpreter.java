import java.util.*;

public abstract class Interpreter extends SemanticChecker
{
	static void expandSymbolTable() // expand the field lists of classes by inherited fields
	{
		Set<String> classNameSet = symbolTable.keySet();
		for ( String className: classNameSet )
		{
			expand(className);
			expandFunctions(className);
		}
	}

	static LinkedList<String> expand(String className)

	// Expands the field list of className by the inherited fields and returns it.

	{
		ClassDefEntry classDefEntry = symbolTable.get(className);
		if ( classDefEntry == null )
		{
			displayln("class "+className+" not defined");
			semanticErrorFound = true;
			return new LinkedList<String>();
		}
		else if ( classDefEntry.fieldsExpanded )
		{
			return classDefEntry.fields;
		}
		else if ( classDefEntry.superClassName.isEmpty() )
		{
			classDefEntry.fieldsExpanded = true; // classDefEntry is a root class
			return classDefEntry.fields;
		}
		else
		{
			LinkedList<String> superClassFields = expand(classDefEntry.superClassName);
			classDefEntry.fields.addAll(0, superClassFields);
			classDefEntry.fieldsExpanded = true;
			return classDefEntry.fields;
		}
	}
	
	static HashMap<String, LinkedList<String>> expandFunctions(String className){
		// Expands the field list of className by the inherited fields and returns it.

		{
			ClassDefEntry classDefEntry = symbolTable.get(className);
			if ( classDefEntry == null )
			{
				displayln("class "+className+" not defined");
				semanticErrorFound = true;
				return new HashMap<String, LinkedList<String>>();
			}
			else if ( classDefEntry.inheritedFunctionsExpanded )
			{
				return classDefEntry.funMap;
			}
			else if ( classDefEntry.superClassName.isEmpty() )
			{
				classDefEntry.inheritedFunctionsExpanded = true; // classDefEntry is a root class
				return classDefEntry.funMap;
			}
			else
			{
				HashMap<String, LinkedList<String>> superClassFunctions = expandFunctions(classDefEntry.superClassName);
				classDefEntry.funMap.putAll(superClassFunctions);
				classDefEntry.inheritedFunctionsExpanded = true;
				return classDefEntry.funMap;
			}
		}
	}

	public static void main(String argv[])

	/* 
	   argv[0]: source program file containing class definitions
	   argv[1]: lexical/syntactic/semantic error messages for the source program in argv[0]
	   argv[2]: single expression to be evaluated
	   argv[3]: lexical/syntactic error messages for the expression in argv[2]
	 
	   The evaluation result and runtime errors will be displayed on the terminal.
	*/

	{
		setIO( argv[0], argv[1] );
		setLex();

		getToken();
		ClassDefList classDefList = classDefList(); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + " : Syntax Error, unexpected symbol");
		else if ( ! syntaxErrorFound )
		{
			classDefList.semanticCheck(); // build the symbol table and do semantic checking
			expandSymbolTable(); // expand the field and function lists of classes by inherited fields and functions

			if ( ! semanticErrorFound )
			{
				closeIO();
				setIO( argv[2], argv[3] );

				getToken();
				Exp exp = exp(); // build a parse tree of the expression to be evaluated
				if ( ! t.isEmpty() )
					displayln(t + " : Syntax Error, unexpected symbol");
				else if ( ! syntaxErrorFound )
				{
					Val v = exp.Eval(new HashMap<String,Val>());  // evaluate the given expression
					if ( v != null )
						System.out.println( v.toString() );   // print the value on the terminal
				}				
			}
		}
		
		closeIO();
		//System.out.println(symbolTable.toString());

	}
}
