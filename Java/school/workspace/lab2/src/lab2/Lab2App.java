
package lab2;
import java.util.Scanner;

/**
 * 	Title: Lab 2: Parsing a URL 
 * 	Description: Ask the user to enter a URL and it will display
 * 	the protocol, domain name and file name specified.
 * @Derick Hansraj
 *
 */

public class Lab2App {
	public static void main ( String [] args ) {
		Scanner s = new Scanner ( System.in );
		String url;
		int positionofColon;
		String protocol;
		String restofURL;
		int positionofSlash;
		String domainName;
		String fileName;
		int fileNameLength;
		char firstLetter;
		char lastLetter;
		
		System.out.println( " Please enter a URL: ");
		url = s.next();
		
		System.out.println("The URL is "+url);
		
		positionofColon = url.indexOf( ':' );
		protocol = url.substring(0, positionofColon);
		restofURL = url.substring(positionofColon+3);
		positionofSlash = restofURL.indexOf('/');
		domainName = restofURL.substring(0, positionofSlash);
		
	
		System.out.println("The colon is at position " + positionofColon);
		System.out.println("The protocol is " + protocol);
		System.out.println("The rest of the URL is " + restofURL);
		System.out.println("The domain name is " + domainName);
		
		//Extract and print the file name
		fileName = restofURL.substring(positionofSlash +1);
		System.out.println("The file name is "+fileName);
		
		// Determine and print the amt. of characters in the filename
		fileNameLength = fileName.length();
		System.out.println("The file name is "+fileNameLength + " characters long");
		
		// Determine and print the first letter of the filename
		firstLetter = fileName.charAt(0);
		System.out.println("The first letter of the file name is "+firstLetter);
		
		// Determine and print the last letter of the filename
		lastLetter = fileName.charAt(fileNameLength-1);
		System.out.println("The last letter of the file name is "+lastLetter);
	}
	}



