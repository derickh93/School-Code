import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Proj1 {
	public static void main (String [] args) {
		String[] markerString;
		String latitude = null;
		String longitude = null;
		String label = null;
		String color = null;
		String category = null;

		try {
			File file = new File("markers.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String line = scanner.next();
				int start = line.indexOf(",");
				String lineStart = line.substring(start+1);
				markerString = lineStart.split(",");
				latitude = markerString[0];
				longitude = markerString[1];
				label = markerString[2];
				color = markerString[3];
				category = markerString[4];
				Marker mark = new Marker(latitude, longitude,label,color,category);
				System.out.println(mark.toString());
				//list.addToRear(mark);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 

	}

}
