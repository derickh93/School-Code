/**
 * Assignment #3
 * CSCI 370
 * @author Derick Hansraj
 * 9/23/2019
 */
package edu.qc.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyCustomStringTest {

	private MyCustomStringInterface mycustomstring;

	@Before
	public void setUp() {
		mycustomstring = new MyCustomString();
	}

	@After
	public void tearDown() {
		mycustomstring = null;
	}

	/**
	This was a given test method and will test countNumbers method with a mixture of strings and digits
	with the expected result of 7
	 */
	@Test
	public void testCountNumbers1() {
		mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
		assertEquals(7, mycustomstring.countNumbers());
	}

	/**
	 * This will test countNumbers method on an empty string with the expected result of 0
	 */
	@Test
	public void testCountNumbers2() {
		mycustomstring.setString("");
		assertEquals(0, mycustomstring.countNumbers());
	}

	/**
	 * This will test the countNumbers method on a null string with an expected
	 * exception to be thrown.
	 */
	@Test(expected=NullPointerException.class)
	public void testCountNumbers3() {
		mycustomstring.setString(null);
		assertEquals(new NullPointerException(), mycustomstring.countNumbers());
	}

	/**This will test the countNumbers method on a string that consist of only digits with
	the expected result of 1
	**/
	@Test
	public void testCountNumbers4() {
		mycustomstring.setString("12345");
		assertEquals(1, mycustomstring.countNumbers());
	}

	/**This will test the countNumbers method with a string that has no digits with the expected
	result of 0.
	**/
	@Test
	public void testCountNumbers5() {
		mycustomstring.setString("this has no digits");
		assertEquals(0, mycustomstring.countNumbers());
	}
	
	/**
	 * This will test the countNumbers method with a string consisting of multiple digits
	 together and separated.
	 */
	@Test
	public void testCountNumbers6() {
		mycustomstring.setString("1234 5678 9 10 11 12 13 14 15 1617181920");
		assertEquals(10, mycustomstring.countNumbers());    }

	/**This will test the getEveryNthCharacterFromBeginingorEnd method with a mixture of strings
	and digits that will get every 3rd character starting from the beginning.
	*/
	@Test
	public void testGetEveryNthCharacterFromBeginningOrEnd1() {
		mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
		assertEquals("d33p md1  i51,it", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
	}

	/**This will test the getEveryNthCharacterFromBeginingorEnd method with a mixture of strings
	and digits that will get every 3rd character starting from the end.
	*/
	@Test
	public void testGetEveryNthCharacterFromBeginningOrEnd2() {
		mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
		assertEquals("'bt t0 6snh r6rh", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));
	}

	/**This will test the getEveryNthCharacterFromBeginingorEnd method with a null value
	that will get every 3rd character starting from the end. This should throw a NullPointerException
	because the value of the string is null so our method should throw the exception.
	*/
	@Test(expected=NullPointerException.class)
	public void testGetEveryNthCharacterFromBeginningOrEnd3() {
		mycustomstring.setString(null);
		assertEquals(new NullPointerException(), mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));    
	}

	/**This will test the getEveryNthCharacterFromBeginingorEnd method with a null value
	that will get every 3rd character starting from the beginning. This should throw a NullPointerException
	because the value of the string is null so our method should throw the exception.
	*/
	@Test(expected=NullPointerException.class)
	public void testGetEveryNthCharacterFromBeginningOrEnd4() {
		mycustomstring.setString(null);
		assertEquals(new NullPointerException(), mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));    
	}

	/**This will test the getEveryNthCharacterFromBeginingorEnd method with a series of strings
	and digits that will get every 6th character starting from the end. This will test the method
	for its ability to get only the nth digit and stop.
	*/
	@Test
	public void testGetEveryNthCharacterFromBeginningOrEnd5() {
		mycustomstring.setString("abc123");
		assertEquals("a", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(6, true));    
	}

	/**This will test the getEveryNthCharacterFromBeginingorEnd method with a series of strings
	and digits that will get every 6th character starting from the beginning. This will test the method
	for its ability to get only the nth digit and stop.
	*/
	@Test
	public void testGetEveryNthCharacterFromBeginningOrEnd6() {
		mycustomstring.setString("abc123");
		assertEquals("3", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(6, false));    
	}

	/**This will test the getEveryNthCharacterFromBeginingorEnd method with a string value
	that will get every 0 character starting from the end. This should throw a IllegalArgumentException
	because our position starts at 0 so our method should throw the exception.
	**/
	@Test(expected=IllegalArgumentException.class)
	public void testGetEveryNthCharacterFromBeginningOrEnd7() {
		mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
		assertEquals(new IllegalArgumentException(), mycustomstring.getEveryNthCharacterFromBeginningOrEnd(0, true));    
	}

	/**This will test the getEveryNthCharacterFromBeginingorEnd method with a string value
	that will get every 0 character starting from the beginning. This should throw a IllegalArgumentException
	because our position starts at 0 so our method should throw the exception.
	*/
	@Test(expected=IllegalArgumentException.class)
	public void testGetEveryNthCharacterFromBeginningOrEnd8() {
		mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
		assertEquals(new IllegalArgumentException(), mycustomstring.getEveryNthCharacterFromBeginningOrEnd(0, false));    
	}

	/**This will test the getEveryNthCharacterFromBeginingorEnd method with a string value
	that will get every -5th character starting from the beginning. This should throw a IllegalArgumentException
	because our position starts at 1 so our method should throw the exception.
	*/
	@Test(expected=IllegalArgumentException.class)
	public void testGetEveryNthCharacterFromBeginningOrEnd9() {
		mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
		assertEquals(new IllegalArgumentException(), mycustomstring.getEveryNthCharacterFromBeginningOrEnd(-5, false));    
	}

	/**This will test the getEveryNthCharacterFromBeginingorEnd method with a string value
	that will get every -5th character starting from the end. This should throw a IllegalArgumentException
	because our position starts at 1 so our method should throw the exception.
	*/
	@Test(expected=IllegalArgumentException.class)
	public void testGetEveryNthCharacterFromBeginningOrEnd10() {
		mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
		assertEquals(new IllegalArgumentException(), mycustomstring.getEveryNthCharacterFromBeginningOrEnd(-5, true));    
	}

	/**This will test the getEveryNthCharacterFromBeginingorEnd method with a series of strings
	and digits that will get every 2nd character starting from the end. This will test the method
	for its ability to recognize the nth character is greater than the length of the string.
	This results in returning an empty string.
	*/
	@Test
	public void testGetEveryNthCharacterFromBeginningOrEnd11() {
		mycustomstring.setString("a");
		assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, true));      
	}

	/**This will test the getEveryNthCharacterFromBeginingorEnd method with a series of strings
	and digits that will get every 2nd character starting from the beginning. This will test the method
	for its ability to recognize the nth character is greater than the length of the string.
	This results in returning an empty string.
	*/
	@Test
	public void testGetEveryNthCharacterFromBeginningOrEnd12() {
		mycustomstring.setString("a");
		assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, false));      
	}

	/**This will test the getEveryNthCharacterFromBeginingorEnd method with a series of strings
	and digits that will get every 2nd character starting from the end. This will test the method
	for its ability to recognize an empty string. This results in returning an empty string.
	*/
	@Test
	public void testGetEveryNthCharacterFromBeginningOrEnd13() {
		mycustomstring.setString("");
		assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, true));      
	}

	/**This will test the getEveryNthCharacterFromBeginingorEnd method with a series of strings
	and digits that will get every 2nd character starting from the beginning. This will test the method
	for its ability to recognize an empty string. This results in returning an empty string.
	*/
	@Test
	public void testGetEveryNthCharacterFromBeginningOrEnd14() {
		mycustomstring.setString("");
		assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, false));      
	}

	/**This will test the convertDigitsToNamesInSubstring method with a series of strings
	and digits that will replace the digits in range of 17 to 23 with the actual spelling of the word
	. This will test the method for its ability to recognize digits in the substring and replacing
	them with the correct words.
	*/
	@Test
	public void testConvertDigitsToNamesInSubstring1() {
		mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
		mycustomstring.convertDigitsToNamesInSubstring(17, 23);
		assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", mycustomstring.getString());
	}

	/**This will test the convertDigitsToNamesInSubstring method with a series of strings
	and digits that will replace the digits in range of 15 to 12 with the actual spelling of the word
	. This will test the method for its ability to recognize that the starting postion is greater than
	the ending postino which results in an IllegalArgumentException being thrown.
	*/
	@Test(expected=IllegalArgumentException.class)
	public void testConvertDigitsToNamesInSubstring2() {
		mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
		mycustomstring.convertDigitsToNamesInSubstring(15, 12);
		assertEquals(new IllegalArgumentException(), mycustomstring.getString());    
	}

	/**This will test the convertDigitsToNamesInSubstring method with a series of strings
	and digits that will replace the digits in range of 0 to 12 with the actual spelling of the word
	. This will test the method for its ability to recognize that the starting position is out of the range
	 because we start at position 1;
	 */
	@Test(expected=MyIndexOutOfBoundsException.class)
	public void testConvertDigitsToNamesInSubstring3() {
		mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
		mycustomstring.convertDigitsToNamesInSubstring(0, 12);
		assertEquals(new MyIndexOutOfBoundsException(), mycustomstring.getString());  
	}

	/**This will test the convertDigitsToNamesInSubstring method with a series of strings
	and digits that will replace the digits in range of 1 to 12 with the actual spelling of the word
	. This will test the method for its ability to recognize that the string contains a null value
	and it should throw a NullPointerException.
	*/
	@Test(expected=NullPointerException.class)
	public void testConvertDigitsToNamesInSubstring4() {
		mycustomstring.setString(null);
		mycustomstring.convertDigitsToNamesInSubstring(1, 12);
		assertEquals(new NullPointerException(), mycustomstring.getString());  
	}

	/**This will test the convertDigitsToNamesInSubstring method with a series of strings
	and digits that will replace the digits in range of 17 to 23 with the actual spelling of the word
	. This will test the method for its ability to recognize that the string is empty so the bounds
	are not possible which results in a MyOutOfBoundsException being thrown.
	*/
	@Test(expected=MyIndexOutOfBoundsException.class)
	public void testConvertDigitsToNamesInSubstring5() {
		mycustomstring.setString("");
		mycustomstring.convertDigitsToNamesInSubstring(17, 23);
		assertEquals(new MyIndexOutOfBoundsException(), mycustomstring.getString());   
	}

	/**This will test the convertDigitsToNamesInSubstring method with a series of strings
	and digits that will replace the digits in range of 1 to 18 with the actual spelling of the word
	. This will test the method for its ability to recognize a string with multiple digits of 
	varying lengths.
	*/
	@Test
	public void testConvertDigitsToNamesInSubstring6() {
		mycustomstring.setString("1 2 3 4 1234 45353");
		mycustomstring.convertDigitsToNamesInSubstring(1, 18);
		assertEquals("One Two Three Four OneTwoThreeFour FourFiveThreeFiveThree", mycustomstring.getString());    
	}

	/**This will test the convertDigitsToNamesInSubstring method with a series of strings
	and digits that will replace the digits in range of 1 to 34 with the actual spelling of the word
	. This will test the method for its ability to recognize a string that contains no digits.
	*/
	@Test
	public void testConvertDigitsToNamesInSubstring7() {
		mycustomstring.setString("there are no digits in this string");
		mycustomstring.convertDigitsToNamesInSubstring(1, 34);
		assertEquals("there are no digits in this string", mycustomstring.getString());    
	} 

	/**This will test the convertDigitsToNamesInSubstring method with a series of strings
	and digits that will replace the digits in range of 1 to 2 with the actual spelling of the word
	. This will test the method for its ability to recognize a empty string and throw an 
	OutOfBoundsException because the bounds are not possible.
	*/
	@Test(expected=MyIndexOutOfBoundsException.class)
	public void testConvertDigitsToNamesInSubstring8() {
		mycustomstring.setString("");
		mycustomstring.convertDigitsToNamesInSubstring(1, 2);
		assertEquals(new MyIndexOutOfBoundsException(), mycustomstring.getString());   
	}
}
