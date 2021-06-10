package cs316project;

public class ClassDef extends ClassDefList{
	ClassName className;
	ClassName superClassName;
	ClassBody classBody;
	
	ClassDef(ClassName cn, ClassName cn2, ClassBody cb){
		className = cn;
		superClassName = cn2;
		classBody = cb;
	}
	
	ClassDef(ClassName cn, ClassBody cb){
		className = cn;
		classBody = cb;
	}
}
