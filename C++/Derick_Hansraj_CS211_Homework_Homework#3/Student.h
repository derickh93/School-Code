//Derick Hansraj
//9/25/2018
//Homework #2
//Cs211 Lab

#ifndef NUM_H
#define NUM_H


#include <iostream>
#include <iomanip>

//Define Student class
class Student
{
	//Define private members/variables
	private:
		std::string lastName, firstName, dateOfBirth, dateOfMat,standing;
		double gpa, credits;

	//Define public members/variables
	public:
		Student() {
			lastName = "Doe";
		    firstName = "John";
		    dateOfBirth = "00/00/0000";
		    dateOfMat = "00/00/0000";
		    gpa = 0;
		    setCredits(0);
		}

		Student(std::string last, std::string first,std::string dob, std::string dom, double gp, double credit) {
		    lastName = last;
		    firstName = first;
		    dateOfBirth = dob;
		    dateOfMat = dom;
		    gpa = gp;
		    setCredits(credit);
		}

        //Mutator functions
        void setLast(std::string l);
        void setFirst(std::string f);
		void setDateOfBirth(std::string dob);
		void setDom(std::string dom);
		void setGpa(double gp);
		void setCredits(double credit);

        //Accessor functions
		std::string getLast();
		std::string getFirst();
        std::string getDateOfBirth();
        std::string getDom();
		double getGpa();
		double getCredits();
		std::string getStandings();

		//input and output functions
		void input();
		void output();
        bool operator==(const Student& rhs) const;

};

#endif

