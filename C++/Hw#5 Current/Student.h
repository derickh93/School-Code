//Derick Hansraj
//11/19/2018
//Homework #5
//Cs211 Lab

#ifndef STUDENT_H
#define STUDENT_H


#include <iostream>
#include <iomanip>
#include "Date.h"

//Define Student class
class Student
{
	//Define private members/variables
	private:
		std::string lastName, firstName,standing, id;
		Date dateOfBirth, dateOfMat;
		double gpa, credits;

	//Define public members/variables
	public:
		Student() {
			lastName = "Doe";
		    firstName = "John";
		    gpa = 0;
		    setCredits(0);
		    id = "00000000";
		}

		Student(std::string last, std::string first,Date dob, Date dom, double gp, double credit,std::string stuID) {
		    lastName = last;
		    firstName = first;
		    dateOfBirth = dob;
		    dateOfMat = dom;
		    gpa = gp;
		    setCredits(credit);
			id = stuID;

		}

        //Mutator functions
        void setLast(std::string l);
        void setFirst(std::string f);
		void setDateOfBirth(Date dob);
		void setDom(Date dom);
		void setGpa(double gp);
		void setCredits(double credit);
		void setID(std::string stuID);


        //Accessor functions
		std::string getLast();
		std::string getFirst();
        Date getDateOfBirth();
        Date getDom();
		double getGpa();
		double getCredits();
		std::string getStandings();
		std::string getID();


		//input and output functions
		void input();
		void output();

		//Overloaded Operators as member functions
        bool operator==(const Student& rhs) const;
        bool operator!=(const Student& rhs) const;
        bool operator<(const Student& rhs) const;
        bool operator>(const Student& rhs) const;
        bool operator<=(const Student& rhs) const;
        bool operator>=(const Student& rhs) const;

		//Overloaded Operators as friends
		friend std::ostream& operator <<(std::ostream& out, const Student& s);
		friend std::istream& operator >>(std::istream& in, Student& s);


};

#endif

