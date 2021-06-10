import java.util.HashMap;

// value objects in the domain of the language, returned by Eval function

abstract class Val
{
	abstract Val cloneVal();
	abstract float floatVal(); // conversion to floating-point
	abstract boolean isNumber();
	abstract boolean isZero();
	abstract Val Eval(HashMap<String,Val> state);

}
