//Derick Hansraj
//10/22/2018
//Homework 3
//CS211 Lab



#include <iostream>
#include "Roster.h"

int main() {

	Student a("Hansraj","Derick","07/12/1993","01/01/2018", 4.0,98);
    Student b("Hansraj","Derick","07/12/1993","01/01/2018", 4.0,98);
	Student c("Hansraj","Derick","07/12/1993","01/01/2018", 4.0,98);
	Student d("Hansraj","Derick","07/12/1993","01/01/2018", 4.0,98);
	Student e("Hansraj","Derick","07/12/1993","01/01/2018", 4.0,98);
    Student f("Pitambar","Avinash","04/22/2004","01/01/2018", 4.0,98);
    Student g("Hansraj","Derick","07/12/1993","01/01/2018", 4.0,98);
    Student h("Pitambar","Avinash","04/22/2004","01/01/2018", 4.0,98);
    Student i("Hansraj","Derick","07/12/1993","01/01/2018", 4.0,98);
    Student j("Beckham JR","Odell","12/13/1978","02/12/2011", 3.0,60);
    Student k("Pitambar","Avinash","04/22/2004","01/01/2018", 4.0,98);



	Roster r;
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

    r.output();

    r.searchStudent(k);
    r.deleteStudent(e);

    r.output();

	return 0;
}
