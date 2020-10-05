//Derick Hansraj
//12/18/2018
//Project 2
//CS211 Lab

//Header file that defines the File class

#ifndef FILE_H
#define FILE_H

#include <string>

class File
{
public:
	//Constructors prototype
	File();
	File(std::string name, std::string type);

	//Destructor prototype
	virtual ~File();

	//Accessors prototype
	std::string getName() const;
	std::string getType() const;
    virtual int getSize()  = 0;

    //Display prototype
    virtual void display();



private:
    //private member functions
    std::string name,type;
};

#endif
