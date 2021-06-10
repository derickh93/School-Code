import java.util.Stack;

import com.sun.jdi.Field;

/**
 * <p>Title: Obj Class</p>
 *
 * <p>Description: Defines a abstract class that is the parent of all object classes defined. 
 * There is a ConObjects variable declared that will store each type of parent and child class 
 * that is called through inheritance. As each type of parent/child classes constructor is called
 * it will be inserted into the ConObjects instance as defined by collection.
 *
 * @author Derick Hansraj
 */

abstract class Obj
{
	//variable that defines a ConObjects object
    public static ConObjects collection = new ConObjects();
    public static Stack<String> st = new Stack<String>();

    /**
     * default constructor - as each parent and child constructor is called the class/object name 
     * is inserted into the ConObjects variable.
     */
	Obj()
	// This constructor will be invoked every time the constructor of any descendant class is invoked.
	{
		super();
        collection.insert("Object");        
        collection.insert(getClass().getName());	
        collection.insert(getClass().getSuperclass().getName());
        st.push(getClass().getName());
        ///////////////////////////////////////////////////////
        try {            
          
            // returns the array of Field objects        	
            java.lang.reflect.Field[] fields = getClass().getDeclaredFields();
            for(int i = 0; i < fields.length; i++) {
            	fields[i].setAccessible(true);
               System.out.println("Field = " + fields[i].toString());
            }
         } catch(Exception e) {
            System.out.println(e.toString());
         }
        ///////////////////////////////////////////////////////
	}
	
	public String traversal() {
		StringBuilder str = new StringBuilder();
		str.append("--------------------------------------------------------\n"
				+ "Object-graph traversal has started ...");
        str.append(st.toString());
        str.append("Total of " + st.size() +" Obj objects have been visited.");
        return str.toString();
	}
}