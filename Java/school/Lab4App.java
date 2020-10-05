package lab4;

/**
 * <p>Title: The Lab4 Application class</p>
 *
 * <p>Description: This class test the various methods in the Card class to see what is returned and
 * determine if the methods work properly. </p>
 *
 * @author <your names here>
 */

public class Lab4App 
{

    /**
     * <p> Name: main method </p>
     * 
     * @param args values to be sent to the method
     */
	public static void main( String args[] )
	{
		Card card1 = new Card(24);
		System.out.println( "Card 1: " + card1.toString() );
		System.out.println( "Card 1's value is: " + card1.getValue() );
		System.out.println( "Card 1's suit is: " + card1.getSuit() );
			
	}
}
