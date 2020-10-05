//Derick Hansraj
//11/19/2018
//Homework #5
//Cs211 Lab

#include <iostream>
#include <string>
#include <sstream>
#include "Student.h"
#include "Roster.h"
#include "Date.h"

int main() {

    //creating a roster using the parameterized constructor
    Roster r("CSC","111",4,"Sands");
    //creating two date objects
    Date d1(01,01,2018);
    Date d2(07,12,1993);

    //printing the roster with default values
    std::cout << r;

    //attempting to add 11 students to the roster. The first 10 will be added
    //the last one will not be added due to size restriction

    Student s("Hansraj","Derick",d2,d1,3.75,120,"0000");
    r.addStudent(s);

    Student s2("Hansraj","Derick",d2,d1,3.75,120,"0001");
    r.addStudent(s2);
    std::cout << r;


	return 0;
}

