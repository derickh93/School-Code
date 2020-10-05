package csc130.grahamf.stuff;

public class AutoApp {

	public static void main(String[] args)  {
		/**Automobile[] auto = new Automobile[12];

		for(int i=0; i < auto.length; i++)
			auto[i] = new Automobile();


		auto[1] = new Automobile("BMW","M5", "Gold", 2018, 100000);

		for(Automobile i : auto)
			System.out.println(i);

		double cost = 45000;
		for(int i=0; i < auto.length; i++){
			if(auto[i].getPrice() < cost)
				System.out.println("Cost less than " + cost + ": " + auto[i].getMake() + " " + auto[i].getModel());
		}

		List list1 = new List(12);
		double carPrice = 150000;
		int year = 2000;
		while(!list1.isFull()){
		list1.insert(new Automobile("Mercedes","S550", "Black", year, carPrice));
		carPrice++;
		year ++;
		}
		System.out.println(list1.toString());
		 */

		int [][] multiArray = new int[5][5];
		int count = 1;
		for(int i = 0; i < multiArray.length; i++) {
			for(int j = 0; j < multiArray[i].length; j++) {
				multiArray[i][j] = count;
				count++;
			}
		}
		for(int i = 0; i < multiArray.length; i++) {
			System.out.println("\n");
			for(int j = 0; j < multiArray[i].length; j++) {
				System.out.print(multiArray[i][j] + " ");
			}
		}
		System.out.println("\n\nAmount of numbers in the MultiDimensional Array: " + (count-1));
	}

}
