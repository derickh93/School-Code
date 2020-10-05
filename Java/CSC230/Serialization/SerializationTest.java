package dll;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationTest {
	public static void main (String [] args) {
		Card c1 = new Card(10, "Spade");
		Card c2 = new Card(5, "Diamonds");
		
		System.out.println(c1 + "\n" + c2);
		
		try {
			FileOutputStream fos = new FileOutputStream("outfile.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c1);
			oos.writeObject(c2);
			
			oos.close();
			fos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
