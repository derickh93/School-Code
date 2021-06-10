import java.util.*;

final class ClassObj extends Val
{
	String className; // class name of this object
	HashMap<String, Val> fields; // field name mapped to its value

	ClassObj(String cn, HashMap<String, Val> fs)
	{
		className = cn;
		fields = fs;
	}

	public String toString()
	{
		return className + " object: " + fields.toString();
	}

	Val cloneVal()

	// In this interpreter, returns this ClassObj object for efficiency without cloning.
	// Note that cloneVal() is only used in Id.Eval.

	{
		return this;
	}

	float floatVal()

	// This is not used by the interpreter. For other purposes, this might return some code value of this object.

	{
		return 0.0f;
	}

	boolean isNumber()
	{
		return false;
	}

	boolean isZero()
	{
		return false;
	}

	boolean equalVal(Val that)
	{
		if ( that.getClass() == ClassObj.class && this.className.equals(((ClassObj)that).className) )
		{
			Set<String> fieldSet = this.fields.keySet();
			for ( String fieldName: fieldSet )
			{
				Val thisFieldVal = this.fields.get(fieldName);
				Val thatFieldVal = ((ClassObj)that).fields.get(fieldName);
				if ( ! thisFieldVal.equalVal(thatFieldVal) )
						return false;
			}
			return true;
		}
		return false;
	}
}
