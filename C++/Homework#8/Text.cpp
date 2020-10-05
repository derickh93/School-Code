//Derick Hansraj
//12/18/2018
//Project 2
//CS211 Lab

//CPP file that implements the function prototypes from Text.h

#include <iostream>
#include "Text.h"
#include <fstream>


//Define static variable
const std::string Text::type = "txt";

//Constructors
Text::Text() : File()
{
    charCount = 0;
}

Text::Text(std::string name) : File(name,type)
{
    charCount = 0;
}

Text::Text(std::string name,int charCount) : File(name,type)
{
    this-> charCount = charCount;
}

//Accessors functions
int Text::getCharCount() const {return this->charCount;}
int Text::getSize() {return this->getCharCount();}

//Display function
void Text::display()  {
    File::display();
    std::cout << "Character Count: " << this->charCount <<  std::endl;
}




