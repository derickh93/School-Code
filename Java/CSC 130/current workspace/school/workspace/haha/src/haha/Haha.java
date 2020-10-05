package haha;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Haha {
	
	public static void main(String[] args){
		if (Desktop.isDesktopSupported()) {
		    try {
		        
		        File myFile3 = new File("C:/Users/DERICK/Downloads/haha1.txt");
		        File myFile4 = new File("C:/Users/DERICK/Downloads/haha2.txt");
		        File myFile5 = new File("C:/Users/DERICK/Downloads/haha3.txt");
		        File myFile6 = new File("C:/Users/DERICK/Downloads/haha4.txt");
		        File myFile7 = new File("C:/Users/DERICK/Downloads/haha5.txt");
		        File myFile8 = new File("C:/Users/DERICK/Downloads/haha6.txt");

		        
		        Desktop.getDesktop().open(myFile3);
		        Desktop.getDesktop().open(myFile4);
		        Desktop.getDesktop().open(myFile5);
		        Desktop.getDesktop().open(myFile6);
		        Desktop.getDesktop().open(myFile7);
		        Desktop.getDesktop().open(myFile8);

		    } catch (IOException ex) {
		        // no application registered for PDFs
		    }
		}
}
}
