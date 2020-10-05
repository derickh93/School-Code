//Derick Hansraj
//9/05/2018
//Homework #1 Question 2
//CS211

#include <iostream>
using namespace std;

void questionTwo(int num);

int main() {
    //int variable that will hold user input to be tested
    std::cout << "Enter a positive integer";
    int input2;
    std::cin >> input2;

    //if statement to check for valid user input
    if(!cin.good() || input2 < 1)
        cout << "Invalid Input";

    //for loop that will print all perfect numbers from 1 to
    //the user input
    for(int i = 1; i <= input2; i++) {

        //call to the function that will determine whether a
        //number is perfect
        questionTwo(i);
    }
    return 0;
}

//function that will determine whether a int is a perfect number.
//if so it will be printed to the console.
void questionTwo(int num) {
    //int variables that will respectively represent the sum of
    //the factors and the divisor to be used.
    int result = 0;
    int divisor;

    //for loop that will check for factors of an int
    for (int i = 1; i < num; i++) {

        //starting at 1 a divisor will be determined up to the user defined int
        divisor = num % i;

        //if the divisor is 0 the current iteration of i is added to the result along
        //with the current value in result
        if (divisor == 0)
            result = result + i;
    }

    //if the result is equal to the int entered that means it is a perfect
    //number which will be printed out to the console.
    if (result == num)
        cout << num << endl;
}
