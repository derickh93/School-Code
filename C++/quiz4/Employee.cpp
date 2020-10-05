//Derick Hansraj
//12/12/2018
//Quiz 4
//Cs211 Lab



#include <iostream>
#include "Employee.h"


//Constructors
Employee::Employee() : Human()
{
    id = "0000";
    employer = "Queens College";
    title = "Employee";
    hourlyWage = 0;
}
Employee::Employee(std::string first_name,std::string last_name,std::string employer,std::string id, std::string title, double hourlyWage) : Human(first_name,last_name)
{
    this->id = id;
    this->employer = employer;
    this->title = title;
    this->hourlyWage = hourlyWage;
}

//Accessor
std::string Employee::getID() const{return this->id;}
std::string Employee::getEmployer() const{return this->employer;}
std::string Employee::getTitle() const{return this->title;}
double Employee::getHourlyWage() const{return this->hourlyWage;}

//Mutator
void Employee::setID(std::string id){this->id = id;}
void Employee::setEmployer(std::string employer){this->employer = employer;}
void Employee::setTitle(std::string title){this->title = title;}
void Employee::setHourlyWage(double hourlyWage){this->hourlyWage = hourlyWage;}

//Function
void Employee::output() {
    Human().output();
    std::cout << "ID: " << this->id << std::endl << "Employer: " << this->employer << std::endl << "Title: " << this->title << std::endl << "Hourly Wage: " << this->hourlyWage<< std::endl;
}
