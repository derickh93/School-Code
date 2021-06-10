package cs316project;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class ClassDefEntry // symbol table entry for a single ⟨class def⟩
{
	String superClassName; // value is "" if superclass is absent
	LinkedList<String> fields = new LinkedList<String>();
	HashMap<String, LinkedList<String>> funMap = new HashMap<String, LinkedList<String>>();
		// function names mapped to their parameters

	public String toString()
	{
		return "\nsuperclass=" + superClassName + "\nfields=" + fields.toString() + "\nfunctions=" + funMap.toString();
	}
	
	ClassDefEntry(Class passedClass) {
		this.superClassName = passedClass.getSuperclass().toString();
		this.fields = new LinkedList(Arrays.asList(fields));
		LinkedList<String> parameters = new LinkedList(Arrays.asList(passedClass.getMethods()));
		this.funMap = new HashMap<String, LinkedList<String>>();
	}
	

static HashMap<String, ClassDefEntry> symbolTable = new HashMap<String, ClassDefEntry>();
	// class names mapped to their ClassDefEntry objects
}