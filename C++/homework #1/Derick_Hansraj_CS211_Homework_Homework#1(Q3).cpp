//Derick Hansraj
//9/05/2018
//Homework #1 Question 3
//CS211

#include <iostream>
using namespace std;

bool questionThree(int num);

int main() {

	//ask the user to enter a minimum year and stores it in the int mimYear
    std::cout << "Enter a minimim year to begin";
    int minYear;
    std::cin >> minYear;
    
    //checks whether the input is valid
    if(!cin.good() || minYear < 1) {
        cout << "Invalid Input";
        return 0;
    }
    
    //ask the user to enter a maximum year and stores it in the int maxYear
    std::cout << "Enter a maximum year to end";
    int maxYear;
    std::cin >> maxYear;
    
    //checks whether the input is valid
    if(!cin.good() || maxYear < 1) {
        cout << "Invalid Input";
        return 0;
    }
    
    //int to keep track of the count so that there are 5 entries per line
    int count = 0;
    
    //for loop that will start at the mimumum year entered and work its way up
    //to the maximum year. If a year is a leap year it will be displayed 5 to a line.
    for(int i = minYear; i <= maxYear; i++) {
    	
    	//if the year is a leap year it will increment the count
        if(questionThree(i) == true)
        	count++;
        	
        //once a line is full it will start the count over on a new line
        if(count == 5) {
        	count = 0;
        	std::cout << "\n";
        }
    }
    return 0;
}

//function that will accept an int . This will then be determined to be
//a leap year or not.
bool questionThree(int num) {
	
	//bool variables to store whether or not an int is a century year or a non  century Year;
    bool centuryYear = false;
    bool non_centuryYear = false;

	//if the statement is true the int is a century year.
    if(num % 100 == 0)
        centuryYear = true;

	//if the statement is true the int is a non century year.
    if(centuryYear == false)
        non_centuryYear = true;

	//if either one of the statements are true the int is a leap year. This prints the year out
	//to the console and returns true.
    if((centuryYear == true && num % 400 == 0) || (non_centuryYear == true && num % 4 == 0)) {
        std::cout << num << " ";
        return true;
    }
    
    //returns false if the year is not a leap year.
    return false;
}
