//Derick Hansraj
//12/18/2018
//Project 2
//CS211 Lab


//CPP file that implements the function prototypes from File.h

#include <string>
#include <iostream>
#include "File.h"

//Default constructor
File::File()
{
	this->name = "";
	this->type = "";
}

//Parameterized constructor
File::File(std::string name, std::string type)
{
	this->name = name;
	this->type = type;
}

//Destructor
File::~File( ) {}

//Accessors
std::string File::getName() const {return this->name;}
std::string File::getType() const {return this->type;}

//Display function
void File::display() {
        std::cout << "\nFile Name: " << this->name << "\nFile Type: " << this->type << "\nFile Size: " << this->getSize()  << " Bytes" << std::endl;
    }








