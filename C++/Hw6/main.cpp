//Derick Hansraj
//12/02/2018
//Project 1
//Cs211 Lab

#include <iostream>
#include <string>
#include <sstream>
#include "Student.h"
#include "Roster.h"
#include "Date.h"
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;
void remove(std::vector<Roster>& vec, size_t pos);

int main()
{

    std::string name,course,instructor,type,line,line2;
    double credits;
    std::string lastName, firstName,standing, id,type2;
    Date dateOfBirth, dateOfMat;
    double gpa, credits2;
    Date dob(01,01,2018);
    Date dom(07,12,1993);
    int current = 0;
    int stuCount = 0;

    std::vector<int> vectorStu;

    //Creating a vector of Rosters
    std::vector<Roster> vectorRoster;
    //Creating a vector of Rosters

    std::vector<Student> vectorAdd;



    //Reading the course descriptions in from file rosters.txt and adding to the roster
    std::ifstream inFile3("rosters.txt");
    while(getline(inFile3, line))
    {
        if(line.substr(0,7) == "course1")
        {
            std::vector<std::string> result;
            std::istringstream iss(line);
            for(std::string line; iss >> line; )
            {
                result.push_back(line);
            }
            Roster r(result[1],result[2],stoi(result[3]),result[4]);
            vectorRoster.push_back(r);
        }

        if(line.substr(0,6) == "course" && line.substr(0,7) != "course1")
        {
            std::vector<std::string> result;
            std::istringstream iss(line);
            for(std::string line; iss >> line; )
            {
                result.push_back(line);
            }
            Roster r(result[1],result[2],stoi(result[3]),result[4]);
            vectorRoster.push_back(r);
            vectorStu.push_back(stuCount);
            stuCount = 0;
        }
        if(line.substr(0,7) == "student")
        {
            stuCount++;
        }
    }
    vectorStu.push_back(stuCount);
    //Reading the course descriptions in from a file and adding to the roster

    std::ifstream inFile2("rosters.txt");
    while(getline(inFile2, line2))
    {
        if(line2.substr(0,7) == "student")
        {
            std::vector<std::string> result2;
            std::istringstream iss2(line2);
            for(std::string line2; iss2 >> line2; )
            {
                result2.push_back(line2);
            }
            Student s(result2[2],result2[1],dob,dom,stod(result2[6]),stod(result2[5]),result2[3]);
            vectorAdd.push_back(s);
        }
    }
    for(int i = 0; i < vectorStu.size(); i++)
    {
        Roster r;
        for(int j = 0; j < vectorStu[i]; j++)
        {
            r.addStudent(vectorAdd.at(j));


        }
        vectorRoster[i] = r;
    }
    //*******************************************************************************************************************************************************************************************************

    std::string username = "";
    std::string password = "";
    std::string u = "";
    std::string p = "";
    bool loginSuccess = false;
    bool cont = false;
    int choice = 0;
    bool firstCont = false;
    int choiceR = 0;
    int choiceR2 = 0;
    int choiceU = 0;
    int choiceU2 = 0;
    bool menuCont = true;
    bool userLoop = true;
    bool adminLoop = true;
    Student tempAdd;
    Roster tempAddR;




    while(menuCont)
    {
        firstCont = false;
        userLoop = true;
        adminLoop = true;
        choice = 0;
        while(!firstCont)
        {
            choice = 0;
            std::cout << "*****Welcome to Roster Management Program*****\nChoose either mode by entering 1 or 2. Enter 3 to exit:\n1. User Mode\n2. Supervisor Mode\n3. Exit\n";
            std::cin >> choice;

            system("CLS");
            if(choice == 1)
            {
                std::cout << "*****Welcome to User Mode*****\n";
                firstCont = true;
            }
            else if (choice == 2)
            {
                system("CLS");

                std::cout << "*****Welcome to Supervisor Mode*****\n";
                firstCont = true;
                userLoop = false;
            }
            else if (choice == 3)
            {
                std::ofstream file;
                file.open("rosters.txt");
                for(int i = 0; i < vectorRoster.size(); i++)
                {
                    file << "course" << i+1 << " " << vectorRoster[i].getCourseName() << " " << vectorRoster[i].getCourseCode() << " "<< vectorRoster[i].getNumCredits() << " "
                         << vectorRoster[i].getInstructorName() << endl;
                    for(int j = 0; j < vectorRoster[i].getNum(); j++)
                    {
                        file << "student" << j+1 << " " << vectorRoster[i][j];

                    }
                }
                file.close();
                exit(0);
            }
            else
            {
                std::cout << "*****Invalid Input, Try Again!*****\n";
            }
        }
        while(userLoop)
        {
            if(choice == 1)
            {
                std::cout <<"1. Insert new Student to Roster\n2. Remove a Student from a Roster\n3. Update a Student in a Roster\n4. Main Menu\n";
                std::cin >> choiceU2;
                if(choiceU2 < 5)
                {
                    if(choiceU2 == 4)
                    {
                        std::cout << "Directing to main menu.\n";
                        system("pause");
                        system("CLS");
                    }
                    else if(vectorRoster.size() == 0)
                    {
                        std::cout << "There are no Rosters at this time ask admin to add Roster\n";
                        choiceU2 = 4;
                        system("pause");
                        system("CLS");
                    }
                    else
                    {
                        if(choiceU2 != 4)
                        {
                            system("CLS");

                            std::cout << "Select a Roster from the following:\n";
                            for(int i = 0; i < vectorRoster.size(); i++)
                            {
                                std::cout << i+1 << ". \n" << "**********ROSTER*********\nCurrent Roster Size: " << vectorRoster[i].getNum() << "\nCourse Name : " << vectorRoster[i].getCourseName()
                                          << "\nCourse Code: " << vectorRoster[i].getCourseCode() << "\nCourse Instructor: " << vectorRoster[i].getInstructorName()
                                          << "\nCourse Credits: " << vectorRoster[i].getNumCredits() << "\n\n";
                            }
                            std::cin >> choiceU;
                        }
                    }
                }

                if(choiceU2 == 1)
                {
                    system("CLS");

                    std::cout << "Enter a student to be added: " << endl;
                    std::cin >> tempAdd;
                    Roster tempAddR2;
                    tempAddR2 = vectorRoster[choiceU-1];
                    tempAddR2.addStudent(tempAdd);
                    vectorRoster[choiceU-1] = tempAddR2;
                    std::cout << "Student added\n" << vectorRoster[choiceU-1] << endl;

                }
                else if(choiceU2 == 2)
                {
                    if(vectorRoster[choiceU-1].getNum() == 0)
                    {
                        std::cout << "\nRoster Empty";
                        system("pause");

                        system("CLS");

                    }
                    else
                    {
                        system("CLS");

                        std::cout << "Select a student to be removed: " << endl;
                        std::cout << vectorRoster[choiceU-1] << endl;
                        int rem;
                        std::cin >> rem;
                        Student temp = vectorRoster[choiceU-1][rem-1];
                        vectorRoster[choiceU-1].deleteStudent(temp);
                        std::cout << "Student deleted\n" << vectorRoster[choiceU-1] << endl;
                    }
                }
                else if(choiceU2 == 3)
                {
                    system("CLS");

                    std::cout << "Select a student to be updated: " << endl;
                    std::cout << vectorRoster[choiceU-1] << endl;
                    int rem;
                    std::cin >> rem;
                    std::cin >> vectorRoster[choiceU-1][rem-1];

                    std::cout << "Student updated\n" << vectorRoster[choiceU-1] << endl;
                }
                else if(choiceU2 == 4)
                {
                    userLoop = false;
                }
                else
                {
                    std::cout << "*****Invalid Input, Try Again!*****\n";
                }

            }
        }

        if(choice == 2)
        {


            std::cout << "Please login below to access the rosters\n\n";
            do
            {
                std::cout << "\nUsername: ";
                std::cin >> username;
                std::cout << "Password: ";
                std::cin >> password;
                std::ifstream infile("database.txt");

                while (infile >> u >> p)
                {
                    if(username == u && password == p)
                    {
                        cont = true;
                    }
                }

                if(cont)
                {
                    std::cout << "\nSuccessfull Login\n\n";
                    loginSuccess = true;
                    system("CLS");
                }
                else
                {

                    system("CLS");
                    std::cout << "Incorrect username and password combo\n";
                    std::cout << "Please try to login again";
                }
            }
            while(!loginSuccess);

            while(adminLoop)
            {
                choiceR2 = 0;
                vectorRoster.resize(vectorRoster.size());
                system("pause");
                system("CLS");

                std::cout << "*****Welcome to Supervisor Mode*****\n";
                std::cout << "1. Create a new Roster" << endl;
                std::cout << "2. Drop a Roster" << endl;
                std::cout << "3. Display Roster Information" << endl;
                std::cout << "4. Display all Rosters" << endl;
                std::cout << "5. Select a Roster to perform operations" << endl;
                std::cout << "6. Main Menu\n" << endl;


                std::cin >> choiceR2;

                if(choiceR2 == 1)
                {
                    system("CLS");

                    std::cout << "Enter Roster details to be created: " << endl;
                    std::cin >> tempAddR;
                    vectorRoster.push_back(tempAddR);
                    std::cout << "Roster created" << endl;
                }
                else if(choiceR2 == 2)
                {
                    if(vectorRoster.size() == 0)
                    {
                        std::cout << "The roster is empty there is nothing to display" << endl;
                        system("pause");

                        system("CLS");

                    }
                    else
                    {
                        system("CLS");
                        std::cout << "Enter a course number to be removed: " << endl;
                        std::string courseRem;
                        std::cin >> courseRem;
                        std::vector<Roster> vectorRoster2;
                        int pos = 0;
                        for(int i = 0; i < vectorRoster.size(); i++)
                        {
                            if(courseRem == vectorRoster[i].getCourseCode())
                            {
                                pos = i;
                            }
                        }
                        for(int i = 0 ; i < vectorRoster.size(); i++)
                        {
                            if(i != pos)
                                vectorRoster2.push_back(vectorRoster.at(i));
                        }
                        vectorRoster.swap(vectorRoster2);
                        system("pause");
                    }
                }
                else if(choiceR2 == 3)
                {
                    if(vectorRoster.size() == 0)
                    {
                        std::cout << "The roster is empty there is nothing to display" << endl;
                        system("pause");

                        system("CLS");

                    }
                    else
                    {
                        system("CLS");

                        std::cout << "Enter a course number to display information: " << endl;
                        std::string courseRem;
                        std::cin >> courseRem;
                        int pos = 0;

                        for(int i = 0; i < vectorRoster.size(); i++)
                        {
                            if(courseRem == vectorRoster[i].getCourseCode())
                            {
                                pos = i;
                            }
                        }
                        std::cout << "Displaying chosen Rosters information: " << endl;
                        std::cout << "**********ROSTER*********\nCurrent Roster Size: " << vectorRoster[pos].getNum() << "\nCourse Name : " << vectorRoster[pos].getCourseName()
                                  << "\nCourse Code: " << vectorRoster[pos].getCourseCode() << "\nCourse Instructor: " << vectorRoster[pos].getInstructorName()
                                  << "\nCourse Credits: " << vectorRoster[pos].getNumCredits() << "\n\n";
                    }

                }
                else if(choiceR2 == 4)
                {
                    if(vectorRoster.size() == 0)
                    {
                        std::cout << "The roster is empty there is nothing to display" << endl;
                        system("pause");

                        system("CLS");

                    }
                    else
                    {
                        system("CLS");

                        std::cout << "Displaying all Rosters:\n" << endl;
                        for(int i = 0; i < vectorRoster.size(); i++)
                        {
                            std::cout << i + 1 << ". " << "**********ROSTER*********\nCurrent Roster Size: " << vectorRoster[i].getNum() << "\nCourse Name : " << vectorRoster[i].getCourseName()
                                      << "\nCourse Code: " << vectorRoster[i].getCourseCode() << "\nCourse Instructor: " << vectorRoster[i].getInstructorName()
                                      << "\nCourse Credits: " << vectorRoster[i].getNumCredits() << "\n\n";
                        }
                    }

                }
                else if(choiceR2 == 5)
                {
                    if(vectorRoster.size() == 0)
                    {
                        std::cout << "The roster is empty there is nothing to display" << endl;
                        system("pause");

                        system("CLS");

                    }
                    else
                    {
                        system("CLS");

                        std::cout << "Select a Roster from the following:\n";
                        for(int i = 0; i < vectorRoster.size(); i++)
                        {
                            std::cout << i + 1 << ". " << "**********ROSTER*********\nCurrent Roster Size: " << vectorRoster[i].getNum() << "\nCourse Name : " << vectorRoster[i].getCourseName()
                                      << "\nCourse Code: " << vectorRoster[i].getCourseCode() << "\nCourse Instructor: " << vectorRoster[i].getInstructorName()
                                      << "\nCourse Credits: " << vectorRoster[i].getNumCredits() << "\n\n";
                        }
                        std::cin >> choiceU;

                        std::cout << "1. Insert a new Student to the Roster\n2. Remove a Student from a Roster\n3. Update a Student in a Roster\n4. List all Students in one Roster in sorted order" << endl;

                        //****************************************************************************************************************************
                        std::cin >> choiceU2;

                        if(choiceU2 == 1)
                        {
                            system("CLS");

                            Student temp2;
                            std::cout << "Enter a student to be added: " << endl;
                            std::cin >> temp2;
                            Roster tempAddR3;
                            tempAddR3 = vectorRoster[choiceU-1];
                            tempAddR3.addStudent(temp2);
                            vectorRoster[choiceU-1] = tempAddR3;
                            std::cout << "Student added\n" << vectorRoster[choiceU-1] << endl;

                        }
                        else if(choiceU2 == 2)
                        {
                            if(vectorRoster[choiceU-1].getNum() == 0)
                            {
                                std::cout << "\nRoster Empty";
                                system("pause");

                                system("CLS");

                            }
                            else
                            {
                                system("CLS");

                                std::cout << "Select a student to be removed: " << endl;
                                std::cout << vectorRoster[choiceU-1] << endl;
                                int rem;
                                std::cin >> rem;
                                Student temp = vectorRoster[choiceU-1][rem-1];
                                vectorRoster[choiceU-1].deleteStudent(temp);
                                std::cout << "Student deleted\n" << vectorRoster[choiceU-1] << endl;
                            }
                        }
                        else if(choiceU2 == 3)
                        {
                            if(vectorRoster[choiceU-1].getNum() == 0)
                            {
                                std::cout << "\nRoster Empty";
                                system("pause");

                                system("CLS");

                            }
                            else
                            {
                                system("CLS");
                                std::cout << vectorRoster[choiceU-1] << endl;

                                std::cout << "Select a student to be updated: " << endl;
                                int rem;
                                std::cin >> rem;
                                std::cin >> vectorRoster[choiceU-1][rem-1];

                                std::cout << "Student updated\n" << vectorRoster[choiceU-1] << endl;
                            }
                        }

                        else if(choiceU2 == 4)
                        {
                            if(vectorRoster[choiceU-1].getNum() == 0)
                            {
                                std::cout << "\nRoster Empty";
                                system("pause");

                                system("CLS");

                            }
                            else
                            {
                                system("CLS");

                                std::cout << "Sorted list of students in Roster: " << endl;
                                std::cout << vectorRoster[choiceU-1] << endl;
                            }
                        }
                        else
                        {
                            std::cout << "Invalid Option";
                            userLoop = false;
                        }
                    }

                    //****************************************************************************************************************************
                }
                else if(choiceR2 == 6)
                {
                    adminLoop = false;
                    std::cout << "Directing to main menu.\n";
                    system("pause");
                    system("CLS");

                }
                else
                {
                    std::cout << "*****Invalid Input, Try Again!*****\n";
                }
            }
        }
    }
    system("pause");
    return 0;
}

