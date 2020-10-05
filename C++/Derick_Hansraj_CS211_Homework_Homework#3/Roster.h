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
	int numCredits;
    int num = 0;
    Student student[MAX_CAPACITY];


public:

	//accessor functions
	std::string getCourseName()const;
	std::string getCourseCode()const;
	std::string getInstructorName()const;
	int getNumCredits()const;
	int getNum()const;


	//mutator functions
	void setCourseName(std::string n);
	void setCourseCode(std::string c);
	void setInstructorName(std::string i);
	void setNumCredits(int c);

	//Students functions
	void addStudent(Student s);
	void deleteStudent(Student s);
	bool searchStudent(Student s);

	//output function
	void output();

	//Constructors
	Roster();
	Roster(std::string cn, std::string cc, int nc, std::string in);
};

#endif

