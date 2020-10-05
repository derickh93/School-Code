//Derick Hansraj
//12/10/2018
//Homework 7
//Cs211 Lab

#ifndef SAFE_MATRIX_H
#define SAFE_MATRIX_H

#include <iostream>
#include <cstdlib>

using namespace std;

template <class T>
class SafeMatrix
{
   private:
    T* array;
    SafeArray<int> rows;
    SafeArray<int> columns;

   public:
      SafeMatrix(int s);
      SafeMatrix();
      T& operator [] (int index);
      int length() const;
      ~SafeMatrix();
      SafeMatrix(const SafeMatrix<T>& other);
      SafeMatrix<T>& operator = (const SafeMatrix<T>& other);
};

template <class T>
SafeMatrix<T>::SafeMatrix() : array(NULL), size(0)
{
   // empty
}

template <class T>
SafeMatrix<T>::SafeMatrix(int s) : size(s)
{
   if (size < 0)
   {
      cout << "Array size cannnot be negative." << endl;
      exit(1);
   }
   array = new T[size];
}

template <class T>
T& SafeMatrix<T>::operator [] (int index)
{
   if (index < 0 || index >= size)
   {
      cout << "Array index: " << index << " is out of bounds."
           << endl;
      exit(1);
   }
   return array[index];
}

template <class T>
int SafeMatrix<T>::length() const
{
   return size;
}

template <class T>
SafeMatrix<T>::~SafeMatrix()
{
   if (array != NULL)
   {
      delete [] array;
      array = NULL;
   }
}

template <class T>
SafeMatrix<T>::SafeMatrix(const SafeMatrix<T>& other) : size(other.size)
{
   array = new T[size];

   for (int i = 0; i < size; i++)
   {
      array[i] = other.array[i];
   }
}

template <class T>
SafeMatrix<T>& SafeMatrix<T>::operator = (const SafeMatrix<T>& other)
{
   if (this != &other)
   {
      if (array != NULL)
      {
         delete [] array;
         array = NULL;
      }
      size = other.size;
      array = new T[size];
      for (int i = 0; i < size; i++)
      {
         array[i] = other.array[i];
      }
   }
   return *this;
}

#endif
