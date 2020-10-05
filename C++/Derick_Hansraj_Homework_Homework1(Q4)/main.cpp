//Derick Hansraj
//9/05/2018
//Homework #1 Question 4
//CS211

#include <iostream>
using namespace std;

int questionFour(int num);

int main() {
	
	//bool variable that will control the loop.
    bool run = true;
    
    //loop that will run until something other than a positive integer is entered.
    while(run) {
    	
    	//the user enters input that is stored in an int
    	std::cout << "Enter a positive integer";
    	int input4;
    	std::cin >> input4;
    	
    	//if the int entered is a positive number the function is called.
    	if(input4 > 0)
        	questionFour(input4);
    	else {
    		//if the int entered is not a positive number the loop breaks and the program ends.
    		std::cout << "Invalid input";
       		break;
    }
	return 0;
	}
}	

//The function will accept an integer and then return it in reverse.
int questionFour(int num) {
	
	//int variables that will be used to reverse the int
    int r = 0;
    int result = 0;
    
    //while loop that will be used to compute the integer in reverse.
    while(num != 0) {
        r = num % 10;
        result = result*10 + r;
        num /= 10;
    }
    
    //the output will be the int in reverse.
    std::cout << "Reversed Number = " << result <<"\n" << endl;
}
