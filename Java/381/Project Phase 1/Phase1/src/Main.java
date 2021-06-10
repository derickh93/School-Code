import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;


/**
 * Title: Main Class
 *
 * Description: Defines a Main class that will read both the input and output file name from the user. It
 * will then use scanner to read the file and obtain the knapsack problem. The values will be stored in local
 * variables and then passed to a Knapsack object. The solve method will then be called to solve the problem
 * using a brute force solution and display the answer.
 *
 * @author Derick Hansraj
 */
public class Main {
	public static void main(String argv[]) throws IOException {

		//an if statement to handle invalid input
		if(argv[0].equals(null) || argv[1].equals(null)) {
			System.out.println("Invalid arguments: Enter the input file and then the output file");
			return;
		}

		//instance variables
		String inputFile = argv[0];
		String outputFile = argv[1];
		int arraySize = 0;
		int capacity = 0;
		int[] weightArr = null;
		int[] valueArr = null;

		//scanner will take in integer input
		try (Scanner scanner = new Scanner(new FileInputStream(inputFile));) {
			while(scanner.hasNextLine()) {
				//saves the values from the file input into the appropriate variables.
				arraySize = scanner.nextInt();
				capacity = scanner.nextInt();
				weightArr = new int[arraySize];
				valueArr = new int[arraySize];
				//for loop that will store values into the weightArr
				for(int i = 0; i < arraySize;i++) {
					weightArr[i] = scanner.nextInt();
				}

				//for loop that will store values into the valueArr
				for(int i = 0; i < arraySize;i++) {
					valueArr[i] = scanner.nextInt();
				}
			}
		}
		//create a KnapSack object and pass the parameters as read in from the file.
		KnapSack ks = new KnapSack(arraySize,capacity,weightArr,valueArr);
		
		//create a file
		File fout = new File(outputFile);
		//set the output stream
		FileOutputStream fos = new FileOutputStream(fout);
	 
		//use buffered writer to write the answer of the KnapSack problem to the output file defined.
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	 	bw.write(ks.solve());
	 
	 	//close the bufferedwriter
		bw.close();
	}
}
