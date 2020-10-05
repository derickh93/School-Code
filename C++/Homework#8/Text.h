//Derick Hansraj
//12/18/2018
//Project 2
//CS211 Lab
//Header file that defines the Text class

#ifndef TEXT_H
#define TEXT_H

#include <string>
#include "File.h"


class Text : public File
{
public:
	//Constructors prototype
	Text();
	Text(std::string name);
    Text(std::string name,int charCount);

	//Accessors prototype
	virtual int getSize() override;
	int getCharCount() const;

	//Display prototype
	void display() ;

private:
    //member variables
    int charCount = 0;
    static const std::string type;
};

#endif
