package wenjieproj2;

public class LinkedStack<T> implements LinkedStackADT<T> {
	class Node<E>
	{ private E data;
	  private Node<E> next;
	  
	  public Node()
	  { data = null;
	    next = null;
	  }
	  
	  public Node(E d)
	  { data = d;
	    next = null;
	  }
	  
	  public Node(E d, Node<E> n)
	  { data = d;
	    next = n;  
	  }
	}
	  
	  private int size;
	  private Node<T> first;
	  
	  public void push(T d)
	  { if(isEmpty())
		  first = new Node<T>(d);
	    else
	      first = new Node<T>(d, first);
	    size ++;	  
	  }
	  
	  public int getSize()
	  {return size;
	  }
	  
	  public boolean isEmpty()
	  {return first == null;
	  }
	  
	  public T pop() throws StackEmptyException
	  {  if(isEmpty())
		  throw new StackEmptyException();
	     T data = first.data;
	     first = first.next;
	     size--;
	     return data;	  
	  }
	  
	  public T peek() throws StackEmptyException
	  {  if(isEmpty())
		  throw new StackEmptyException();
	      return (T) first.next;  
	  }
	  
	  public String toString()
	  {  String str = null;
	     if(!isEmpty())
	        str = "Stack: " + size + "\n";
	       Node<T> trav = first;
	       while(!(trav == null))
	       { str = str + trav.data + "\n";
	         trav = trav.next;   
	       }
	    return str;
	   }
}
