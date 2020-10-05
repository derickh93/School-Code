//Derick Hansraj
//12/18/2018
//Project 2
//CS211 Lab

//CPP file that creates a menu application to test the File, Image, and Text classes. Recursive functions are used to achieve our goals.

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include "File.h"
#include "Image.h"
#include "Text.h"

//Helper Recursive Functions
void displayFile (const std::vector<File*>& files);
std::vector<File*> fileSeperate (const std::vector<File*>& files, std::string type);

//Recursive Functions
void fileSeperateVoid (std::vector<File*>& vecIn, std::vector<File*>& vecOut, std::string type);
void displayFileVoid (std::vector<File*>& files);

int main( )
{
    //variables to control the flow of the program
    int choice = 0, row, column;
    std::string line, name, charsCount, colorDepth, dimensions, type;
    std::vector<File*> files,images,texts;
    bool cont = true;

//loop that will keep the program running until user closes it
    while(cont)
    {
        system("CLS");
        //Presenting the user with 8 choices to choose in the menu
        std::cout << "1. Read from a File\n2. Create an Image File (Creates a single Image File asking the user for its properties)\n3. Create a Text File (Creates a single Text File asking the user for its properties)" << std::endl;
        std::cout << "4. Print All Files\n5. Print Image Files\n6. Print Text Files\n7. Delete a file by file name and type (Removes a file from a vector)\n8. Quit (Terminate the program) " << std::endl;

        //getting user inputted choice
        std:: cin >> choice;

        //if user inputted 1 then a file is read in
        if(choice == 1)
        {
            //Opening the file file.txt
            std::ifstream inFile("file.txt");
            //reading each line from file.txt until the end
            while(getline(inFile, line))
            {
                //if the line is a text file we will read the lines to follow and use that information to create a new Text object and add it to the vector of file pointers.
                if(line.substr(0,3) == "txt")
                {
                    getline(inFile,name);
                    getline(inFile,charsCount);
                    files.push_back(new Text(name,stoi(charsCount)));
                }

                //if the line is a image file we will read the lines to follow and use that information to create a new Image object and add it to the vector of file pointers.
                if(line.substr(0,3) == "gif")
                {
                    getline(inFile,name);
                    getline(inFile,dimensions);
                    getline(inFile,colorDepth);
                    row = stoi(dimensions.substr(0,3));
                    column = stoi(dimensions.substr(6,9));
                    files.push_back(new Image(name,row,column,stoi(colorDepth)));
                }
            }
            std::cout << "File has been read" << std::endl;
        }

        //if the user chose 2 then they are prompted to enter details for a image file to be created and added to the vector of files pointers
        else if(choice == 2)
        {
            std::cout << "Enter the image file name:" << std::endl;
            std::cin >> name;
            std::cout << "Enter the image file width:" << std::endl;
            std::cin >> row;
            std::cout << "Enter the image file height:" << std::endl;
            std::cin >> column;
            std::cout << "Enter the image file color depth:" << std::endl;
            std::cin >> colorDepth;
            files.push_back(new Image(name,row,column,stoi(colorDepth)));
            std::cout << "Image file created and added to the vector of file pointers" << std:: endl;


        }

        //if the user chose 3 then they are prompted to enter details for a text file to be created and added to the vector of files pointers
        else if(choice == 3)
        {
            std::cout << "Enter the text file name:" << std::endl;
            std::cin >> name;
            std::cout << "Enter the text file character count:" << std::endl;
            std::cin >> charsCount;
            files.push_back(new Text(name,stoi(charsCount)));
            std::cout << "Text file created and added to the vector of file pointers" << std:: endl;
        }

        //if the user chose 4 all files are displayed unless the vector is empty
        else if(choice == 4)
        {
            if(files.size() == 0)
            {
                std::cout << "There are no files!!!!!" << std::endl;
            }
            else
            {
                std::cout << "*****Displaying all files:*****     Total files: " << files.size() << std::endl;
                displayFile(files);
            }


        }

        //if the user chose 5 all image files are displayed unless the vector is empty
        else if(choice == 5)
        {
            if(files.size() == 0)
            {
                std::cout << "There are no files!!!!!" << std::endl;
            }
            else
            {
                std::cout << "*****Displaying all Image files*****" << std::endl;
                images = fileSeperate(files,"gif");
                if(images.size() == 0)
                    std::cout << "There were no image files" << std::endl;
                else
                    displayFile(images);
            }

        }

        //if the user chose 6 all text files are displayed unless the vector is empty
        else if(choice == 6)
        {
            if(files.size() == 0)
            {
                std::cout << "There are no files!!!!!" << std::endl;
            }
            else
            {
                std::cout << "*****Displaying all Text files*****" << std::endl;
                texts = fileSeperate(files,"txt");
                if(texts.size() == 0)
                    std::cout << "There were no text files" << std::endl;
                else
                    displayFile(texts);
            }


        }

        //if the user chose 7 they are able to enter the credentials for a file to be deleted. There is a check to see if the file exist. If exist there will be a
        //confirmation message upon deletion, else there will be a message stating the file was not found.
        else if(choice == 7)
        {
            if(files.size() == 0)
            {
                std::cout << "There are no files!!!!!" << std::endl;
            }
            else
            {
                bool found = false;
                std::cout << "Enter a file type to be removed" << std::endl;
                std::cin >> type;
                std::cout << "Enter a file name to removed" << std::endl;
                std::cin >> name;
                for(int i = 0; i < files.size(); i++)
                {
                    if(files[i]->getName() == name && files[i]->getType() == type)
                    {
                        files.erase(files.begin() + i);
                        std::cout << "File found and removed!!!!!" << std::endl;
                        found = true;
                    }
                }
                if(!found)
                {
                    std::cout << "The file does not exist!!!!!" << std::endl;
                }
            }
        }

        //if chose 8 the program will exit
        else if(choice == 8)
        {
            exit(0);
        }

        //if invalid input the user will be notified and looped back to the menu
        else
        {
            std::cout << "Invalid input" << std::endl;
        }

        //pause the program so that user can read information prior to the screen being cleared and taken to main menu
        system("pause");
    }

    return 0;
}

//recursive function to help display all files in vector of files
void displayFile(const std::vector<File*>& files)
{
    //creates a temporary vector of file pointers
    std::vector<File*> tempVec(files);
    //calls displayFileVoid on tempVec
    displayFileVoid(tempVec);
}

//recursive function that helps display user entered type of file
std::vector<File*> fileSeperate(const std::vector<File*>& files, std::string type)
{
    //creates a temporary vector of file pointers
    std::vector<File*> tempVec(files), retVec;
    //calls fileSeperateVoid on tempVec
    fileSeperateVoid(tempVec, retVec, type);

    return retVec;
}

//recursive function that helps display user entered type of file
void fileSeperateVoid(std::vector<File*>& vecIn, std::vector<File*>& vecOut, std::string type)
{
    //if the vector input is not empty return
    if (vecIn.empty())
    {
        return;
    }
    //create a file pointer to the back of the file vector
    File* backFile = vecIn.back();
    //pop the object at the back of the file vector
    vecIn.pop_back();
    //call fileSeperateVoid function
    fileSeperateVoid(vecIn, vecOut, type);

    //if object at back of file is the same as user entered type push it to the destination vector
    if (backFile->getType() == type)
    {
        vecOut.push_back(backFile);
    }
}

//recursive function to help display all files in vector of files
void displayFileVoid(std::vector<File*>& files)
{
    //if vector parameter is not empty traverse from the back
    if (!files.empty())
    {
        //create a file pointer to the back of the file vector
        File* backFile = files.back();
        //pop the object at the back of the file vector
        files.pop_back();
        //call display file function
        displayFile(files);

        //if backfile is a gif file display its details
        if (backFile->getType() == "gif")
        {
            Image* imageFile = dynamic_cast<Image*>(backFile);
            std::cout << "File Name: " + imageFile->getName() + "\n" << "     Type: " + imageFile->getType() << "\n     Dimension: " << imageFile->getPixelColumn();
            std::cout << " X " << imageFile->getPixelRow() <<  "\n     Color Depth: " << imageFile->getColorDepth() << "\n     Size: " << imageFile->getSize() << " bytes\n" << std::endl;
        }
        //if backfile is a txt file display its details
        else
        {
            Text* textFile = dynamic_cast<Text*>(backFile);
            std::cout << "File Name: " + textFile->getName() + "\n" << "     Type: " + textFile->getType() << "\n     Size: " << textFile->getSize() << " bytes" << std::endl;
            std::cout << "     Character Count: " << textFile->getCharCount() <<  "\n" << std::endl;
        }
    }
}

