import java.util.HashMap;


public class Interpreter extends Type_Evaluation{

	public static HashMap<String, Val> interpret = new HashMap<String, Val>();
	
	public static void main(String[] args) {
		setLex(args[0], args[1]);
		
		getToken();
		Program program = program();
		if(!t.isEmpty()){
			displayln(t+" -- unexpected symbol");
		}
		else if(! errorFound){
			program.printParseTree("");
			program.M(state);
			if(ParseTree.errorFound == false){
				program.m(state, interpret);
				System.out.println(interpret.toString());
			}
		}
		closeIO();
	}

}
