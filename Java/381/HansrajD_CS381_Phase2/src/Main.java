import java.io.IOException;

public class Main {
	public static void main(String argv[]) throws IOException {
		//array for small data set
		int smallArr[] = new int[5];
		int smallArr2[] = new int[5];
		
		//array for medium data set
		int medArr[] = new int[20];
		int medArr2[] = new int[20];
		
		//array for large data set
		int largeArr[] = new int[30];
		int largeArr2[] = new int[30];
		
		//solution arrays for datasets
		int smMaxSolnArr[] = {1,1,0,0,1};
		int medMaxSolnArr[] = {1,1,1,1,0,0,0,1,0,1,0,0,1,1,1,0,0,0,0,0};
		int lgMaxSolnArr[] = {0,1,0,1,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0};
		
		//calculating the fitness of each data set
		int smMaxFit = calculateFitness(smMaxSolnArr);
		int medMaxFit = calculateFitness(medMaxSolnArr);
		int LgMaxFit = calculateFitness(lgMaxSolnArr);
		
		//variables to store max fitness average fitness and minimum fitness
		int maxFitnessMath = 0, maxFitnessBBS = 0;
		int minFitnessMath = 0, avgFitnessMath = 0, minFitnessBBS = 0, avgFitnessBBS = 0;


		//loop that will go through 10 generations
		for(int k = 0; k < 10;k++) {
			System.out.println("***********Generation: " + (k+1) + "***********");
			//loop that will create a population of 1000
			for(int j = 0; j < 1000;j++) {
				//both randoms functions are used and stored in arrays
				getMathRandomSoln(smallArr);
				getBBSRandomSoln(1,5,50,smallArr2);
				System.out.println("Math Random Solution ["+(j+1)+"]");
				//for loop that will print the result of Math.Random Solution
				for (int i = 0; i < smallArr.length; i++) {
					System.out.print(smallArr[i]);
				}
				
				//for loop that will print the result of Random BBS Solution
				System.out.println("\nBBS Random Solution ["+(j+1)+"]");
				for (int i = 0; i < smallArr2.length; i++) {
					System.out.print(smallArr2[i]);
				}
				System.out.println("\n");
			}
		}
		System.out.println(smMaxFit);
		System.out.println(maxFitnessBBS);
		System.out.println(maxFitnessMath);
		System.out.println(minFitnessMath);
		System.out.println(avgFitnessMath);
		System.out.println(minFitnessBBS);
		System.out.println(avgFitnessBBS);
	}
	
	//functino that will get a random number using math.random
	private static int getMathRandom(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		return (int)(Math.random() * ((max - min) + 1)) + min;
	}

	//function that will get a random number using BBS method
	private static double getRandomBBS(int p, int q, double x0) {
		int n = p * q;
		double answer = Math.pow(x0, 2)%n;
		return answer;		
	}

	//function that will fill an array with random numbers using getMathRandom function
	private static void getMathRandomSoln(int[] arr) {
		for(int i = 0; i < arr.length;i++) {
			arr[i] = getMathRandom(0,1);
		}
	}

	//functino that will fill an array with random numbers using getRandomBBS function
	private static void getBBSRandomSoln(int p,int q, double x0, int[] arr) {
		for(int i = 0; i < arr.length;i++) {
			arr[i] = (int) getRandomBBS(p,q,x0);
		}
	}

	//function that will calculate the fitness
	private static int calculateFitness(int[] arr) {
		int fitness = 0;
		for(int i = 0; i < arr.length;i++) {
			if(arr[i] == 1)
				fitness++;
		}
		return fitness;
	}
}
