//Derick Hansraj
//10/25/2018
//Homework 4
//CS211 Lab

#include <iostream>
#include "Date.h"

int main () {
	//variable to hold size of dynamic arrays
	int dateSize;
	
	//variables to calculate sum of Dates
	int sum;
	int number;
	
	//Asking the user for input that will represent the size of both arrays
	std::cout << "Enter the size of your dynamic array of Dates: ";
	std::cin >> dateSize;
	
	//creating a dynamic array of Dates with user inputted size
	Date *dateArr = new Date[dateSize];
	std::cout << "\nDynamic Date array created\n";
	
	//creating a dynamic array of ints with user inputted size
	int *sumArr = new int[dateSize];
	std::cout << "\nDynamic Int array created\n";
	
	//for loop that will loop through Date array 
	for(int i = 0; i < dateSize; i++) {
		sum = 0, number = 0;
		//getting user input for each Date object
		dateArr[i].input();
		
		//summing up the numbers that reperesent the Date object
		number += dateArr[i].getMonthNum();
		number += dateArr[i].getDay();
		number += dateArr[i].getYear();

		//Repeatedly adding up the integers until we get a number between 1 and 9
		while(number != 0) {
    		while (number > 0) {
        		int digit = number%10;
        		number /= 10;
        		sum += digit;
    		}
    		
    		//keep looping until answer is between 1 and 9, if between 1 and 9 loop ends
    		if(sum > 9) {
    			number = sum;
    			sum = 0;
    		}
    	}

		//assigns sum of corresponding Date to the int array
    	sumArr[i] = sum;
	}
	
	//loop that will print all of the Date objects in the array along with all of the corresponding ints from the int array
	for(int i = 0; i < dateSize; i++) {
		std::cout << "\nThe sum of ";
		dateArr[i].output();
		std::cout << "Sum: " << sumArr[i] << "\n";
	}

	//delete both dynamics arrays to prevent memory leak issues
	std::cout << "\nBoth Dynamic arrays deleted";
	delete [] dateArr;
	delete [] sumArr;
	
	//end of program
	return 0;
}
