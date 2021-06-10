
public class TypeInt extends Type {
	String id;

	TypeInt(String ident){
		id = ident;
	}

	void printParseTree(String indent)
	{

		lexArithArray.displayln(indent + indent.length() + " " + id);
	}

}
