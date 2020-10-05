//Derick Hansraj
//12/12/2018
//Quiz 4
//Cs211 Lab

#ifndef EMPLOYEE_H
#define EMPLOYEE_H

#include <iostream>
#include "Human.h"

class Employee : public Human
{
public:

//Constructors
Employee();
Employee(std::string first_name,std::string last_name,std::string employer,std::string id, std::string title, double hourlyWage);

//Accessor
std::string getID() const;
std::string getEmployer() const;
std::string getTitle() const;
double getHourlyWage() const;

//Mutator
void setID(std::string id);
void setEmployer(std::string employer);
void setTitle(std::string title);
void setHourlyWage(double hourlyWage);

//Function
void output();

private:
//Member Variable
std::string id,employer,title;
double hourlyWage;
};

#endif
