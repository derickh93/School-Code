
/**
 * <p>Title: ObjectNode Class</p>
 *
 * <p>Description: Defines a ObjectNode class capable of storing a 
 * reference to an object, its count, and a reference to the next node in
 * a linked list. Accessors and mutators are defined for both.</p>
 *
 * @author Derick Hansraj
 */
public class ObjectNode<T>
{
  private T data; //a reference to an object of type T
  private int dataCount; //a reference to an object count of type int
  private ObjectNode<T> next; //the address of the next node in the list

  /**
   * default constructor - initializes data and next to null and dataCount to 0
   */
  public ObjectNode()
  {
	  data = null;
	  dataCount = 0;
	  next = null;
  }
  
  /**
   * parameterized constructor - initializes data to the user 
   * specified value; next is set to null
   * @param newItem the value to be stored in the node
   * @param itemCount the count to be stored in the node
   */
  public ObjectNode(T newItem,int itemCount)
  {
    data = newItem;
    dataCount = itemCount;
    next = null;
  }

  /**
   * parameterized constructor - initializes data and next to user 
   * specified values
   * @param newItem the object reference to be stored in the node
   * @param nextItem the reference to the next node in the list
   * @param itemCount the count of the data item
   */
  public ObjectNode(T newItem, ObjectNode<T> nextItem,int itemCount)
  {
    data = newItem;
    dataCount = itemCount;
    next = nextItem;
  }

  /**
   * setItem - stores a new value in data
   * @param newItem the object reference to be stored in the node
   */
  public void setItem(T newItem,int itemCount)
  {
    data = newItem;
    dataCount = itemCount;
  }

  /**
   * setNext - stores a new value in next
   * @param nextItem the reference to be stored in next
   */
  public void setNext(ObjectNode<T> nextItem)
  {
    next = nextItem;
  }

  /**
   * getItem - returns the reference stored in data
   * @return a reference to the data stored in the node
   */
  public T getItem()
  {
    return data;
  }
  
  /**
   * getCount - returns the count stored in dataCount
   * @return a reference to the data Count stored in the node
   */
  public int getCount()
  {
    return dataCount;
  }

  /**
   * getNext - returns the reference stored in next
   * @return the reference stored in next
   */
  public ObjectNode<T> getNext()
  {
    return next;
  }
  
  public String toString() {
	  return dataCount + " " + data;
  }
}
