package lab4;
 
 /**
  * <p>Title: The Lab4 Application class</p>
  *
  * <p>Description: This class test the various methods in the Card class to see what is returned and
  * determine if the methods work properly. </p>
  *
  * @author Derick Hansraj
  */
 
public class Lab4App 
{
 
     /**
      * <p> Name: main method </p>
      * 
      * @param args values to be sent to the method
      */
	public static void main( String [] args)
	{    
		Card card1 = new Card(24);
	    System.out.println( "Card 1: " + card1.toString() );
	    System.out.println( "Card 1's value is: " + card1.getValue() );
	    System.out.println( "Card 1's suit is: " + card1.getSuit() );
	    System.out.println( "Card 1 is a face card: " + card1.isAFaceCard() );
	    System.out.println("");
	         
	    Card card2 = new Card(7);
	    System.out.println( "Card 2: " + card2.toString() );    
	    System.out.println( "Card 2 is a face card: " + card2.isAFaceCard() );
	    System.out.println("");
	         
	    System.out.println( "Card 1's point value is: " + card1.getPointValue() );
	    System.out.println( "Card 2's point value is: " + card2.getPointValue() );
	    System.out.println("");
	         
	    System.out.println("Card 1 is " + card1.getSuit() + " - color is red: " + card1.isRed() );
	         
	    Card card3 = new Card(14);
	    Card card4 = new Card(26);
	    Card card5 = new Card(37);
	         
	    System.out.println("Card 3 is " + card3.getSuit() + " - color is red: " + card3.isRed() );
	    System.out.println("Card 4 is " + card4.getSuit() + " - color is red: " + card4.isRed() );
	    System.out.println("Card 5 is " + card5.getSuit() + " - color is red: " + card5.isRed() );
	    System.out.println("");
	         
	    System.out.println( card3.toString() );
	    System.out.println( card4.toString() );
	    System.out.println( card5.toString() );
	    System.out.println("");
	     
	    if ((card1.equalValue(card5)) == true)
	        System.out.println(( card1.toString() ) + " and " + 
	            ( card5.toString() + " have the same value."));
	    else 
	        System.out.println((( card1.toString() ) + " and " + 
	            ( card5.toString() + " does not have the same value.")));
	         
	    if ((card2.equalValue(card3)) == true)
	        System.out.println(( card2.toString() ) + " and " + 
	            ( card3.toString() + " have the same value."));
	    else 
	        System.out.println((( card2.toString() ) + " and " + 
	             ( card3.toString() + " does not have the same value.")));
	 
	    if ((card2.equalValue(card3)) == true)
	        System.out.println(( card2.toString() ) + " and " + 
	             ( card3.toString() + " have the same suit."));
	    else 
	        System.out.println((( card2.toString() ) + " and " + 
	            ( card3.toString() + " does not have the same suit.")));
	         
	         
        if ((card4.equalValue(card5)) == true)
            System.out.println(( card4.toString() ) + " and " + 
                ( card5.toString() + " have the same suit."));
        else 
	        System.out.println((( card4.toString() ) + " and " + 
	             ( card5.toString() + " does not have the same suit.")));
    }
}

