package sd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Card c1 = new Card(10, "Spade");
		//Card c2 = new Card(5, "Diamonds");
		
		//System.out.println(c1);
		//System.out.println(c2);
		
		/*
		Card[] cArray = new Card[2];
		
		cArray[0] = new Card(2, "Hearts");
		cArray[1] = new Card(9, "Diamonds");
		
		
		try {
			FileOutputStream fos = new FileOutputStream("outfile.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			
			//oos.writeObject(c1);
			//oos.writeObject(c2);
			oos.writeObject(cArray);
			
			oos.close();
			fos.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
		
		
		//Card c3;
		//Card c4;
		Card[] cArray;
		
		try {
			FileInputStream fis = new FileInputStream("outfile.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			
			//c4 = (Card)ois.readObject();
			//c3 = (Card)ois.readObject();
			
			cArray = (Card[])ois.readObject();
			
			
			
			ois.close();
			fis.close();
			
			System.out.println(cArray[0]);
			System.out.println(cArray[1]);
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		

	}

}
