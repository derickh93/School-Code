//Derick Hansraj
//11/19/2018
//Homework #5
//Cs211 Lab

#ifndef ROSTER_H
#define ROSTER_H

#include <iostream>
#include "Student.h"
#include "Date.h"
#define MAX_CAPACITY 10


class Roster {

private:
	//member variables
	std::string courseName, courseCode, instructorName;
	int numCredits,num = 0;
    Student student[MAX_CAPACITY];
    template<class T>
    void swap(T& v1, T& v2);

    template<class T>
    void sort(T a[], int numberUsed);


public:

	//accessor functions
	std::string getCourseName();
	std::string getCourseCode();
	std::string getInstructorName();
	int getNumCredits();
	int getNum();

	//mutator functions
	void setCourseName(std::string n);
	void setCourseCode(std::string c);
	void setInstructorName(std::string i);
	void setNumCredits(int c);

	//Students functions
	void addStudent(Student s);
	void deleteStudent(Student s);
	int searchStudent(Student s);
	void output();

	//Constructors
	Roster();
	Roster(std::string cn, std::string cc, int nc, std::string in);

    //Overloaded Operators as friends
    friend std::ostream& operator <<(std::ostream& out, const Roster& s);
    friend std::istream& operator >>(std::istream& in, Roster& s);
};

#endif


