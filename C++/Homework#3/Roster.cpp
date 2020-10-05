//Derick Hansraj
//10/22/2018
//Homework 3
//CS211 Lab


//This is the implementation file Roster.cpp of the class Roster
//The interface for this class is in header file Roster.h
#include <iostream>
#include "Roster.h"

	Roster::Roster() {
		courseName = "CSC";
		courseCode = "001";
		numCredits = 0;
		instructorName = "Temp";
	}
	Roster::Roster(std::string cn, std::string cc, int nc, std::string in) {
		courseName = cn;
		courseCode = cc;
		numCredits = nc;
		instructorName = in;
	}

	//accessor functions
	std::string Roster::getCourseName(){return courseName;}
	std::string Roster::getCourseCode(){return courseCode;}
	std::string Roster::getInstructorName(){return instructorName;}
	int Roster::getNumCredits(){return numCredits;}

	//mutator functions
	void Roster::setCourseName(std::string n) {
		courseName = n;
	}
	void Roster::setCourseCode(std::string c) {
		courseCode = c;
	}
	void Roster::setInstructorName(std::string i) {
		instructorName = i;
	}
	void Roster::setNumCredits(int c) {
		numCredits = c;
	}

	//student functions
	void Roster::addStudent(Student s) {
	    if(num > 9)
            std::cout << "Roster Full!!! Cannot add more students\n\n";
            else
	    Roster::student[num++] = s;
	}
	void Roster::deleteStudent(Student s) {
	    int pos = 0;
        if(searchStudent(s) == false) {
            std::cout << "The student is not in the roster";
            return;
        }
        else {
            for(int i = 0; i < MAX_CAPACITY; i++) {
	        if(s == Roster::student[i]) {
	            pos = i;
	            break;
	        }
        }
        for(int i = pos; i < MAX_CAPACITY; i++) {
                Roster::student[i] = Roster::student[i+1];
        }
        }
	}
	bool Roster::searchStudent(Student s) {
	    for(int i = 0; i < MAX_CAPACITY; i++) {
	        if(s == Roster::student[i]) {
                std::cout << "Student found in roster\n\n";
                return true;
	        }
	    }
        std::cout << "Student not found in roster\n\n";
        return false;
	}

	void Roster::output() {
	    std::cout << "**********ROSTER*********\nCourse Name : " << getCourseName() << "\nCourse Code: " << getCourseCode() << "\nCourse Instructor: " << getInstructorName() << "\nCourse Credits: " << getNumCredits() << "\n\n";

	    for(int i = 0; i < MAX_CAPACITY; i++) {
            std::cout << "Student # " << i+1 << ": \n";
            Roster::student[i].output();
	    }
	}
