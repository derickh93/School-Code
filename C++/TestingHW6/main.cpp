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


int main() {
    int populateCount = 0;
    int populateCount2 = 0;
    std::string name,course,instructor,type,line;
    double credits;
    std::string lastName, firstName,standing, id,type2, dateOfBirth, dateOfMat;
    double gpa, credits2;


    //Creating a vector of Rosters
    vector<Roster> vectorRoster;
    //Creating a vector of Rosters



    //Reading the course descriptions in from file rosters.txt and adding to the roster
    ifstream inFile3("rosters.txt");
        while(getline(inFile3, line)) {
            if(line.substr(0,6) == "course"){
                std::vector<std::string> result;
                std::istringstream iss(line);
                for(std::string line; iss >> line; ) {
                    result.push_back(line);
                }
                Roster r(result[1],result[2],stoi(result[3]),result[4]);
                vectorRoster.push_back(r);
            }
    }
    inFile3.close();
    //Reading the course descriptions in from a file and adding to the roster



    std::ifstream inFile2("rosters.txt");
    while(inFile2 >> type2 >> firstName >> lastName >> id >> standing >> credits2 >> gpa >> dateOfBirth >> dateOfMat) {
        if(type2.substr(0,7) == "student") {
            Student temp;
            int month,day,year;
            month = stoi(dateOfBirth.substr(0,2));
            day = stoi(dateOfBirth.substr(2,4));
            year = stoi(dateOfBirth.substr(4,8));

            int month2,day2,year2;
            month2 = stoi(dateOfMat.substr(0,2));
            day2 = stoi(dateOfMat.substr(2,4));
            year2 = stoi(dateOfMat.substr(4,8));

            Date dob(month,day,year);
            Date dom(month2,day2,year2);
            temp.setCredits(credits2);
            temp.setDateOfBirth(dob);
            temp.setID(id);
            temp.setLast(lastName);
            temp.setFirst(firstName);
            temp.setDom(dom);
            temp.setGpa(gpa);
            vectorRoster[populateCount].addStudent(temp);
        }
        if(type2.substr(0,7) != "student") {
            populateCount++;
        }
        inFile2.close();
    }

    std::cout << vectorRoster << endl;

    std::cout << vectorRoster[1] << endl;

    std::cout << vectorRoster[2] << endl;

    std::cout << vectorRoster[3] << endl;

    /**
    std::string username = "";
    std::string password = "";
    std::string u = "";
    std::string p = "";
    bool loginSuccess = false;
    bool cont = false;
    std::string choice;
    bool firstCont = false;
    std::string choiceR;
    std::string choiceU;


    while(!firstCont) {
        std::cout << "*****Welcome to Roster Management Program*****\nChoose either mode by entering 1 or 2:\n1. User Mode\n2. Supervisor Mode\n3. Exit\n";
        std::cin >> choice;

        system("CLS");

        if(choice == "1"){
            std::cout << "*****Welcome to User Mode*****\n";
            firstCont = true;
        }
        else if (choice == "2") {
            std::cout << "*****Welcome to Supervisor Mode*****\n";
            firstCont = true;
        }
        else if (choice == "3") {
            exit(0);
        }
        else {
            std::cout << "*****Invalid Input, Try Again!*****\n";
        }
    }

    if(choice == "1") {
        std::cout << "Select a Roster from the following:\n";
        std::cin >> choiceU;
    }

    if(choice == "2") {


        std::cout << "Please login below to access the rosters\n\n";
        do {
            std::cout << "\nUsername: ";
            std::cin >> username;
            std::cout << "Password: ";
            std::cin >> password;
            std::ifstream infile("database.txt");

            while (infile >> u >> p) {
                if(username == u && password == p) {
                    cont = true;
                }
            }

            if(cont) {
                std::cout << "\nSuccessfull Login\n\n";
                loginSuccess = true;
                system("CLS");
            }
            else{
                system("CLS");
                std::cout << "Incorrect username and password combo\n";
                std::cout << "Please try to login again";
            }
        }while(!loginSuccess);

        std::cout << "*****Welcome to Supervisor Mode*****\n";
        std::cout << "1. Create a new Roster" << endl;
        std::cout << "2. Drop a Roster" << endl;
        std::cout << "3. Display Roster Information" << endl;
        std::cout << "4. Display all Rosters" << endl;
        std::cout << "5. Select a Roster to perform operations" << endl;

        std::cin >> choiceR;

        system("pause");
    }
    **/

	return 0;
}

