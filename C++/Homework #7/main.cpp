//Derick Hansraj
//12/10/2018
//Homework 7
//Cs211 Lab

#include <iostream>
#include "SafeArray.h"
#include "SafeMatrix.h"


using namespace std;

SafeMatrix<int> m(10,9);
SafeMatrix<std::string> m2(3,3);
SafeMatrix<int> m3();




int main()
{
    std::cout << "//****************/TESTING MATRIX CLASS OF INT/***********************************//" << endl;

    //populating SafeMatrix m with values 0-8 for each row
    //then displaying each values
    for(int i = 0;i < m.length();i++) {
        for(int j = 0;j < m[i].length();j++) {

            m[i][j] = j;
            std::cout << m[i][j] << " ";
        }
        std::cout << endl;
    }

    //Testing  setting values using bracket operators.
    m[0][3] = 123;
    m[6][6] = 54;
    m[8][3] = 93;

    //Testing boundary checking
    		try
		{
        m[0][11] = 5;
		}
		catch(int e)
		{
			std::cout	<< endl;
		}

			try
		{
        m[10][1] = 5;
		}
		catch(int e)
		{
			std::cout	<< endl;
		}

			try
		{
        m[-1][91] = 5;
		}
		catch(int e)
		{
			std::cout	<< endl;
		}

    //Testing getting values using operator.
    std::cout << "Element at m[0][3] is : " << m[0][3] << endl;
    std::cout << "Element at m[6][6] is : " << m[6][6] << endl;
    std::cout << "Element at m[8][3] is : " << m[8][3] << endl;
    std::cout << "Amount of rows in int matrix is: " << m.length() << endl;
    std::cout << "Amount of columns in row 0 of int matrix is: " << m[0].length() << endl;

    //Displaying changed matrix
        for(int i = 0;i < m.length();i++) {
        for(int j = 0;j < m[i].length();j++) {
            std::cout << m[i][j] << " ";
        }
        std::cout << endl;
    }

    std::cout << "//****************/TESTING MATRIX CLASS OF STRING/***********************************//" << endl;

    //populating SafeMatrix m with values 0-8 for each row
    //then displaying each values
    for(int i = 0;i < m2.length();i++) {
        for(int j = 0;j < m2[i].length();j++) {
            std::string line;
        std::cout << "Enter a string for m2[" << i << "][" << j << "]" << endl;
            std::cin >> line;
            m2[i][j] = line;
            std::cout << m2[i][j] << " ";
        }
        std::cout << endl;
    }

    //Testing  setting values using bracket operators.
    m2[0][2] = "A";
    m2[1][1] = "B";
    m2[2][0] = "C";

    //Testing boundary checking
    		try
		{
        m2[0][11] = "ABC";
		}
		catch(int e)
		{
			std::cout	<< endl;
		}

			try
		{
        m2[10][1] = "NYC";
		}
		catch(int e)
		{
			std::cout	<< endl;
		}

			try
		{
        m2[-1][91] = "QUEENS COLLEGE";
		}
		catch(int e)
		{
			std::cout	<< endl;
		}

    //Testing getting values using operator.
    std::cout << "Element at m[0][3] is : " << m2[0][2] << endl;
    std::cout << "Element at m[6][6] is : " << m2[1][1] << endl;
    std::cout << "Element at m[8][3] is : " << m2[2][0] << endl;
    std::cout << "Amount of rows in string matrix is: " << m2.length() << endl;
    std::cout << "Amount of columns in row 0 of string matrix is: " << m2[0].length() << endl;

    //Displaying changed matrix
        for(int i = 0;i < m2.length();i++) {
        for(int j = 0;j < m2[i].length();j++) {
            std::cout << m2[i][j] << " ";
        }
        std::cout << endl;
    }

    return 0;
}
