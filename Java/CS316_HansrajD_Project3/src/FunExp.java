import java.util.HashMap;

abstract class FunExp extends Exp
{
	abstract Val Eval(HashMap<String,Val> state);

}