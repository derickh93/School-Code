//Derick Hansraj
//10/22/2018
//Homework 3
//CS211 Lab

#include <iostream>
#include "Roster.h"

//Testing out the Roster class
int main() {

    //creating 11 students
	Student a("Hansraj","Derick","07/12/1993","01/01/2018", 4.0,98);
    Student b("Smith","John","03/10/1989","01/01/2018", 3.0,12);
	Student c("Stark","Tony","02/05/1976","01/01/2018", 2.0,79);
	Student d("Griffin","Stewie","01/23/1998","01/01/2018", 1.0,45);
	Student e("Manning","Eli","12/11/1987","01/01/2018", 3.5,60);
    Student f("Barkley","Saquan","11/02/2004","01/01/2018", 2.1,111);
    Student g("Collins","Landon","10/30/1993","01/01/2018", 4.0,29);
    Student h("Jenkins","Janoris","09/24/2001","01/01/2018", 3.0,88);
    Student i("Apple","Eli","06/12/1985","01/01/2018", 4.0,44);
    Student j("Beckham JR","Odell","04/13/1978","02/12/2011", 1.0,31);
    Student k("Pitambar","Avinash","02/22/1992","01/01/2018", 2.0,120);

    //creating two rosters
	Roster r;
    Roster s;


    //testing mutators functions
    r.setCourseCode("MATH");
    r.setCourseName("231");
    r.setInstructorName("Prof Sands");
    r.setNumCredits(4);

    //testing accessor functions
    std::cout << "Course Code: " << r.getCourseCode() << std::endl;
    std::cout << "Course Name: " << r.getCourseName() << std::endl;
    std::cout << "Instructor Name: " << r.getInstructorName() << std::endl;
    std::cout << "Number of Credits: " << r.getNumCredits() << std::endl;

    //attempting to add 11 students to roster r
	r.addStudent(a);
	r.addStudent(b);
	r.addStudent(c);
	r.addStudent(d);
	r.addStudent(e);
    r.addStudent(f);
    r.addStudent(g);
    r.addStudent(h);
    r.addStudent(i);
    r.addStudent(j);
    r.addStudent(k);

    //outputting roster r after attempting to add 11 students
    r.output();

    //search for three students in roster r
    r.searchStudent(k);
    r.searchStudent(g);
    r.searchStudent(i);

    //delete 3 students from roster r
    r.deleteStudent(a);
    r.deleteStudent(b);
    r.deleteStudent(c);

    //outputting roster r after deleting 3 students
    r.output();

    //add a student to roster r
    r.addStudent(k);

    //outputting roster r after adding a student
    r.output();

    //outputting default roster s
    s.output();

	return 0;
}
