
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

    /**
     * default constructor - as each parent and child constructor is called the class/object name 
     * is inserted into the ConObjects variable.
     */
	Obj()
	// This constructor will be invoked every time the constructor of any descendant class is invoked.
	{
		super();
        collection.insert("Object");	
        
        collection.insert(getClass().toString());	
        
        collection.insert(getClass().getSuperclass().toString());

	}
}