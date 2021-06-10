import java.util.*;

class FunCall extends FunExp
{
	FunName funName;
        ExpList expList; // value can be null

	FunCall(FunName fn, ExpList el)
	{
		funName = fn;
		expList = el;
	}

	void semanticCheck()
	{
		if ( expList != null )
			expList.semanticCheck();
	}

	Val Eval(HashMap<String,Val> state)
	{
		String funNameId = funName.id;

		ClassDefEntry classDefEntry = SemanticChecker.symbolTable.get(funNameId);
		if ( classDefEntry != null ) // funNameId is a class-object constructor
		{
			ExpList eList = expList;
			HashMap<String,Val> newFields = new HashMap<String,Val>();

			for ( String field: classDefEntry.fields ) // assign field values
			{
				if ( eList == null )
					newFields.put(field, NullObj.nullObj);
				else
				{
					Val eVal = eList.firstExp().Eval(state);
					if ( eVal == null )
						return null;
					newFields.put(field, eVal); // "field" is bound to eVal
					eList = eList.tailExpList();
				}
			}

			return new ClassObj(funNameId, newFields);
		}

		System.out.println( "Error: nonexistent constructor: " + funNameId );
		return null;
	}
}