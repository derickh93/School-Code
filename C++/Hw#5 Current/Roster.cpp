//Derick Hansraj
//11/19/2018
//Homework #5
//Cs211 Lab


//This is the implementation file Roster.cpp of the class Roster
//The interface for this class is in header file Roster.h
#include <iostream>
#include "Roster.h"

    //Default constructor for the Roster class
	Roster::Roster() {
		courseName = "CSC";
		courseCode = "001";
		numCredits = 0;
		instructorName = "Temp";
	}

	//Parameterized constructor for the Roster class
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
    int Roster::getNum(){return num;}


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

	//adds a student to the roster if there is space
	void Roster::addStudent(Student s) {
	    if(num > 9)
            std::cout << "Roster Full!!! Cannot add more students\n\n";
        else if(searchStudent(s) > 0)
            std::cout << "Student Already Exist";
        else
            Roster::student[num++] = s;
        sort(student,num);
	}

	//deletes a student from the roster if they are in it
	void Roster::deleteStudent(Student s) {
        int pos = 0;
        Student s2;

        if(searchStudent(s) < 0)
            std::cout << "The student is not in the roster";
        else{
            pos = searchStudent(s);
            for(int i = pos; i < num-1; i++) {
                Roster::student[i] = Roster::student[i+1];
            }
            Roster::student[num-1] = s2;
            num--;
        }
        sort(student,num);
    }

	//searches for a student in the Roster
	int Roster::searchStudent(Student s) {
	    int status = -1;
	    for(int i = 0; i < num; i++) {
	        if(s == Roster::student[i]) {
                status = i;
	        }
	    }
        return status;
	}

	//outputs the Roster in a proper manner
	void Roster::output() {
	    sort(student,num);
	    std::cout << "**********ROSTER*********\nCurrent Roster Size: " << num << "\nCourse Name : " << getCourseName()
	    << "\nCourse Code: " << getCourseCode() << "\nCourse Instructor: " << getInstructorName()
            << "\nCourse Credits: " << getNumCredits() << "\n\n";

	    for(int i = 0; i < num; i++) {
            std::cout << "Student # " << i+1 << ": \n";
            Roster::student[i].output();
	    }
	}

	//Overload of the << operator in a nice manner
    std::ostream& operator <<(std::ostream& out, const Roster& s) {
        out << "**********ROSTER*********\nCurrent Roster Size: " << s.num << "\nCourse Name : " << s.courseName
            << "\nCourse Code: " << s.courseCode << "\nCourse Instructor: " << s.instructorName
                << "\nCourse Credits: " << s.numCredits << "\n\n";

        for(int i = 0; i < s.num; i++) {
            out << "Student # " << i+1 << ": \n";
            std::cout << s.student[i];
        }
        return out;
    }

    //Overload of the >> operator in a nice manner
    std::istream& operator >>(std::istream& in, Roster& r) {

        std::cout << "Enter the Course Name:";
        std::string cn;
        in >> cn;
        r.setCourseName(cn);

        std::cout << "Enter the Course Code:";
        std::string cc;
        in >> cc;
        r.setCourseCode(cc);

        std::cout << "Enter the Number of Credits:";
        int nc;
        in >> nc;
        r.setNumCredits(nc);

        std::cout << "Enter the Instructors Name:";
        std::string insN;
        in >> insN;
        r.setInstructorName(insN);

        return in;
    }

template<class T>
void Roster::swap(T& v1, T& v2) {
	T temp;
	temp = v1;
	v1 = v2;
	v2 = temp;
}

template<class T>
void Roster::sort(T a[], int numberUsed) {
	int minIndex;
	for(int i = 0; i < numberUsed - 1; i++) {
		minIndex = i;
		for(int j = i + 1; j < numberUsed; j++) {
			if(a[j] < a[minIndex])
                minIndex = j;
		}
		swap(a[minIndex], a[i]);
	}
}
