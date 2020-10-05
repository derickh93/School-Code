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

    Student s3("Hansraj","Derick",d2,d1,3.75,120,"0002");
    r.addStudent(s3);

    Student s4("Hansraj","Derick",d2,d1,3.75,120,"0003");
    r.addStudent(s4);

    Student s5("Hansraj","Derick",d2,d1,3.75,120,"0004");
    r.addStudent(s5);

    Student s6("Hansraj","Derick",d2,d1,3.75,120,"0005");
    r.addStudent(s6);

    Student s7("Hansraj","Derick",d2,d1,3.75,120,"0006");
    r.addStudent(s7);

    Student s8("Hansraj","Derick",d2,d1,3.75,120,"0007");
    r.addStudent(s8);

    Student s9("Hansraj","Derick",d2,d1,3.75,120,"0008");
    r.addStudent(s9);

    Student s10("Hansraj","Derick",d2,d1,3.75,120,"0009");
    r.addStudent(s10);

    Student s11("Hansraj","Derick",d2,d1,3.75,120,"0010");
    r.addStudent(s11);
    std::cout << endl;

    //printing out the populated roster
    std::cout << r;

    //deleting 4 of the student objects from the roster and then printing it out after each deletion
    r.deleteStudent(s);
    std::cout << r;

    r.deleteStudent(s2);
    std::cout << r;

    r.deleteStudent(s3);
    std::cout << r;

    r.deleteStudent(s4);
    std::cout << r;

    //creating a 5th student object and adding it to the roster.
    Student s12("Manning","Eli",d2,d1,4.00,10,"0123");
    r.addStudent(s12);

    //printing the roster with the new student
    std::cout << r;

    //testing the searchStudent function
    std::cout << r.searchStudent(s5) << endl;;


    //Testing out the == operator
    if(s == s)
        std::cout << "Equal" << endl;
    else
        std::cout << "Not Equal" << endl;

    //Testing out the != operator
    if(s2 != s6)
        std::cout << "Not Equal" << endl;
    else
        std::cout << "Equal" << endl;

    //Testing out the < operator
    if(s2 < s6)
        std::cout << "Student s2 is less than Student s6" << endl;
    else
        std::cout << "Student s2 is not less than Student s6" << endl;

    //Testing out the > operator
    if(s2 > s6)
        std::cout << "Student s2 is greater than Student s6" << endl;
    else
        std::cout << "Student s2 is not greater than Student s6" << endl;

    //Testing out the >= operator
    if(s3 >= s)
        std::cout << "Student s3 is greater than or equal to Student s" << endl;
    else
        std::cout << "Student s3 is not greater than or equal to Student s" << endl;

    //Testing out the <= operator
    if(s3 <= s)
        std::cout << "Student s3 is less than or equal to Student s" << endl;
    else
        std::cout << "Student s3 is not less than or equal to Student s" << endl;

    //Testing out the >= operator
    if(s >= s)
        std::cout << "Student s is greater than or equal to Student s" << endl;
    else
        std::cout << "Student s is not greater than or equal to Student s" << endl;

    //Testing out the <= operator
    if(s3 <= s3)
        std::cout << "Student s3 is less than or equal to Student s3" << endl;
    else
        std::cout << "Student s3 is not less than or equal to Student s3" << endl;



    //adding 3 students that were deleted before to test the sort function
    r.addStudent(s3);
    r.addStudent(s2);
    r.addStudent(s);
    std::cout << r;

	return 0;
}
