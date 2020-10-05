//Derick Hansraj
//12/18/2018
//Project 2
//CS211 Lab


//Header file that defines the Image class

#ifndef IMAGE_H
#define IMAGE_H

#include <string>
#include "File.h"

class Image : public File
{
public:

	//Constructors prototype
	Image();
	Image(std::string name, int pixelColum, int pixelRow, int colorDepth);

	//Accessors prototype
	int getPixelColumn() const;
	int getPixelRow() const;
	int getColorDepth() const;
	virtual int getSize() override;

	//Display prototype
    void display() ;


private:
    //member variables
	int colorDepth = 0,pixelColumn = 0,pixelRow = 0;
	static const std::string type;
};

#endif
