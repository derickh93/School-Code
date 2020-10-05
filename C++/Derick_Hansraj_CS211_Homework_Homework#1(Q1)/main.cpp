//Derick Hansraj
//9/05/2018
//Homework #1 Question 1
//CS211

#include <iostream>
#include <cstring>
#include <cctype>
using namespace std;

void listPrime(int num);

int main() {
    //int variable to store user input that will be passed as
    //a parameter to listPrime
    int input;
    std::cout << "Enter a positive integer" << endl;
    std::cin >> input;

    //if statement to check for valid user input
    if(!cin.good() || input < 1)
        cout << "Invalid Input";

    //call the function listPrime with input as the parameter
    else
        listPrime(input);

    return 0;
}

//function that will list all the primes from 1 to a user defined int.
void listPrime(int num) {
	//boolean variable used to track whether or not a number is prime
	bool isPrime;
	//int variable to track the number of primes
	int count = 0;
	//nested for loop that iterates through all numbers 2 - 2000
	for(int i = 2; i <= num; i++) {
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
			std::cout << i << endl;
			count++;
		}
	}

	//output the amount of primes up to the entered range.
	std::cout << "There is: " << count << " prime numbers from 1 to 2000." << endl;
}
