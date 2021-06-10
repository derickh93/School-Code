import java.util.Comparator;


/**
 * <p>Title: ObjectNodeComp Class</p>
 *
 * <p>Description: Defines a ObjectNodeComp class capable of definding a comparator
 * for the ObjectNode class. This allows for sorting of ObjectNodes by their count.</p>
 *
 * @author Derick Hansraj
 */
class ObjectNodeComp implements Comparator<ObjectNode<String>>{

	  /**
	   * compare method- a method that will compare two ObjectNodes by their count.
	   * @param arg0 the value to be compared against
	   * @param arg1 the count to be compared to
	   */
	@Override
	public int compare(ObjectNode<String> arg0, ObjectNode<String> arg1) {
		if(arg0.getCount() < arg1.getCount())
			return 1;
		else
			return -1;
	}
}