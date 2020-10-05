//Derick Hansraj
//9/3/2018
//Exercise 1
//CS211 Lab

#include <iostream>
using namespace std;

int main() {
	//boolean variable used to track whether or not a number is prime
	bool isPrime;
	int num = 0;
	//nested for loop that iterates through all numbers 2 - 2000
	for(int i = 2; i <= 2000; i++) {
		//initially each prime number is set to true
		isPrime = true;
		//nested for loop that iterates through the integers that could possibly divide i evenly
		for(int j = 2; j <= i/2; j++) {
			//check to see if i can be divided by j
			if(i % j == 0) {
				//if the statement is true the number is not prime
				isPrime = false;
				//Break from the inner loop since we have a solution
				break;
			}
		}
		//if i cannot be divided therefore it is prime so we output to the console
		if(isPrime) {
			cout << i << endl;
			num++;
		}
	}
	cout << "There is: " << num << " prime numbers from 1 to 2000.";
	return 0;
}
