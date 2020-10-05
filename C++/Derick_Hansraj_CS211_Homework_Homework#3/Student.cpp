//Derick Hansraj
//9/25/2018
//Homework #2
//Cs211 Lab

#include "Student.h"


//accessor function for lastName
std::string Student::getLast()
{
    return lastName;
}

//mutator function for lastName
void Student::setLast(std::string l)
{
    lastName = l;
}

//accessor function for firstName
std::string Student::getFirst()
{
    return firstName;
}

//mutator function for firtName
void Student::setFirst(std::string f)
{
    firstName = f;
}

//accessor function for dateOfBirth
std::string Student::getDateOfBirth()
{
    return dateOfBirth;
}

//mutator function for dateOfBirth
void Student::setDateOfBirth(std::string dob)
{
    dateOfBirth = dob;
}

//accessor function for dateOfMat
std::string Student::getDom()
{
    return dateOfMat;
}

//mutator function for dateOfMat
void Student::setDom(std::string dom)
{
    dateOfMat = dom;
}

//accessor function for gpa
double Student::getGpa()
{
    return gpa;
}

//mutator function for gpa
void Student::setGpa(double gp)
{
    gpa = gp;
}

//accessor function for credits
double Student::getCredits()
{
    return credits;
}

//mutator function for credits, this will also determine the standing of the Student
void Student::setCredits(double credit)
{
    credits = credit;
    if(credits < 15)
        standing = "Lower Freshman";
	else if(credits < 30)
        standing = "Upper Freshman";
    else if(credits < 45)
        standing = "Lower Sophmore";
    else if(credits < 60)
        standing = "Upper Sophmore";
    else if(credits < 75)
        standing = "Lower Junior";
    else if(credits < 90)
        standing = "Upper Junior";
    else if(credits < 105)
        standing = "Senior";
    else
        standing = "Upper Senior";
}

//accessor function for standing
std::string Student::getStandings()
{
    return standing;
}

//input function that will ask the user for values that will be used with the mutator functions
//for each private member .
void Student::input()
{
    std::cout << "Enter the students last name:";
    std::string ln;
    std::cin >> ln;
    setLast(ln);

    std::cout << "Enter the students first name:";
    std::string fn;
    std::cin >> fn;
    setFirst(fn);

    std::cout << "Enter the students date of birth:";
    std::string dob;
    std::cin >> dob;
    setDateOfBirth(dob);

    std::cout << "Enter the students date of matriculation:";
    std::string dom;
    std::cin >> dom;
    setDom(dom);

    std::cout << "Enter the students GPA:";
    double gp;
    std::cin >> gp;
    setGpa(gp);

    std::cout << "Enter the students number of credits:";
    double cr;
    std::cin >> cr;
    setCredits(cr);
}

//output function that will output a Student by displaying all of its private members
void Student::output()
{
    std::cout << "Last Name: " << lastName << std::endl
    << "First Name: " << firstName << std::endl
    << "Date of Birth: " << dateOfBirth << std::endl
    << "Date of Matriculation: " << dateOfMat << std::endl
    << "GPA: " << std::setprecision(2) << std::fixed << gpa << std::endl
    << "Credits: " << credits << std::endl
    << "Standing: " << standing << "\n" << std::endl;
}

bool Student::operator==(const Student& rhs) const {
    return ((firstName == rhs.firstName)
    && (lastName == rhs.lastName)
    && (dateOfBirth == rhs.dateOfBirth)
    && (dateOfMat == rhs.dateOfMat)
    && (gpa == rhs.gpa)
    && (credits == rhs.credits));
	}

