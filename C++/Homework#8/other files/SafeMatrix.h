//Derick Hansraj
//12/10/2018
//Homework 7
//Cs211 Lab


#ifndef SAFE_MATRIX_H
#define SAFE_MATRIX_H

#include "SafeArray.h"
#include <iosfwd>
#include <iostream>


using std::ostream;

template <class T>
class SafeMatrix
{

public:
    SafeMatrix (int r, int c);
    int width ( ) const;
	int length ( ) const;

    friend std::ostream& operator<<(std::ostream& out, const SafeMatrix& tempMat)
    {
        for (int i = 0; i < tempMat.size; ++i)
        {
            out << tempMat.matrix[i] << "\n";
        }
        return out;
    }
    SafeArray<T>& operator[](int index);


private:
    SafeArray<SafeArray<T>> matrix;
    int size = 0;
    int rsize = matrix[0].length();
};

template <class T>
SafeMatrix<T>::SafeMatrix(int r, int c) : size(r), matrix(SafeArray<SafeArray<T>>(size))
{
    for (int i = 0; i < size; ++i)
    {
        matrix[i] = SafeArray<T>(c);
    }
}

template <class T>
SafeArray<T>& SafeMatrix<T>::operator[](int index)
{
    return matrix[index];
}

template <class T>
int SafeMatrix<T>::width ( ) const {
	if(size == 0) {
		return 0;
	}
	return rsize;
}

template <class T>
int SafeMatrix<T>::length( ) const {
	return size;
}


#endif
