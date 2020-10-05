//Derick Hansraj
//10/22/2018
//Homework 3
//CS211 Lab

#ifndef ROSTER_H
#define ROSTER_H

//This is the header file roster.h
//It is the interface for the class Roster
#include <iostream>
#include "Student.h"
#define MAX_CAPACITY 10


class Roster {

private:
	//member variables
	std::string courseName, courseCode, instructorName;
	int numCredits,num;
    Student student[MAX_CAPACITY];


public:

	//accessor functions
	std::string getCourseName();
	std::string getCourseCode();
	std::string getInstructorName();
	int getNumCredits();

	//mutator functions
	void setCourseName(std::string n);
	void setCourseCode(std::string c);
	void setInstructorName(std::string i);
	void setNumCredits(int c);

	//Students functions
	void addStudent(Student s);
	void deleteStudent(Student s);
	bool searchStudent(Student s);
	void output();
	//Constructors
	Roster();
	Roster(std::string cn, std::string cc, int nc, std::string in);
};

#endif

