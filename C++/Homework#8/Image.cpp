//Derick Hansraj
//12/18/2018
//Project 2
//CS211 Lab

//CPP file that implements the function prototypes from Image.h

#include <iostream>
#include "Image.h"

//Define static variable
const std::string Image::type = "gif";

//Constructors
Image::Image( ) : File()
{
    pixelColumn = 0;
    pixelRow = 0;
    colorDepth = 0;
}

Image::Image(std::string name, int pixelColumn, int pixelRow, int colorDepth) : File(name, type)
{
    this->pixelColumn = pixelColumn;
    this->pixelRow = pixelRow;
    this->colorDepth = colorDepth;
}


//Accessor functions
int Image::getColorDepth( ) const {return colorDepth;}

int Image::getPixelColumn( ) const {return pixelColumn;}

int Image::getPixelRow( ) const {return pixelRow;}

int Image::getSize( ) {return (pixelColumn * pixelRow* colorDepth) / 8;}

//Display function
void Image::display() {
        File::display();
        std::cout << "Pixel Row: " << this->pixelRow  << "\nPixel Column: " << this-> pixelColumn << "\nColor Depth: " <<  this->colorDepth << std::endl;
    }



