I want this code in my final progam

#define DEBUG
#ifdef DEBUG

I do not want this code in my final program

#endif

//#include "RAT.h" -- To include class declaration file. This is done in Rat.cpp

//#include "Rat.h" -- To include declaration of functions in the main file.

//g++ rat.o main.o -- to produce a.out
					//g++ rat.o main.o -o main.exe
					//g++ -c rat.o -- to compile and make object file
					
					
//Date.h -- Class Date, function headers
	//private int year, month, day;
	//public functions accessors mutators

//Date.ccp -- #include "date.h" and define functions

//person.h -- #include date.h, define function headers and private field Date dob, string name;

//person.cpp -- #include person.h

//main.cpp -- #include date.h
				//#include person.h
				//include student.h
				
			//Person p;
			//Student s;
			//Date d;
				
//student.h -- #include date.h

//student.cpp -- #include student.h
				//#include date.h
				
//To solve the problem of repeatedly declaring header files use macros ifndef / ifdef

//EX   #ifndef DATE_H
		//#define DATE_H
		
//EX   #ifndef PERSON_H
		//#define PERSON_H
	
//EX   #ifndef STUDENT_H
		//#define STUDENT_H
		
//g++ *.cpp -- produces a.out by including all cpp files
