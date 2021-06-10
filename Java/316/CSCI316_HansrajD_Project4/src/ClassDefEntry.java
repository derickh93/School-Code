import java.util.*;

public class ClassDefEntry
{
	String superClassName; // value is "" if superclass is absent
	LinkedList<String> fields = new LinkedList<String>();
	HashMap<String, LinkedList<String>> funMap = new HashMap<String, LinkedList<String>>();
		// function names mapped to their parameters
	
	HashMap<String, String> funBodyMap = new HashMap<String, String>();
	// function names mapped to their body expression

	boolean fieldsExpanded = false; // indicates if the fields of this class are expanded by inherited fields
	boolean inheritedFunctionsExpanded = false; // indicates if the functions of this class are expanded by inherited functions


	public String toString()
	{
		return "\nsuperclass=" + superClassName + "\nfields=" + fields + "\nfunctions=" + funMap + "\nfunction body=" + funBodyMap;
	}
}
