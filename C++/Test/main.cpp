#include <iostream>

using namespace std;
void sneaky(int& temp);


int main(){
    //creates constant size of 4 for array numbers and gives each index a value
    const int size = 4;
    int numbers [size] = {5,10,15,20};

    //prints out the entire array
    for(int i = 0; i < size;i++)
        cout << numbers[i] << " ";

    //stores the address of the array a pointer named pnumbers
    int *pnumbers = &numbers[0];

    //prints out the value of pnumbers with pointer arithmetic
    cout << endl << *pnumbers << endl << *pnumbers+1 << endl << *(pnumbers+1) << endl;

    //prints out entire array using pointers
    for(int i = 0; i < size; i++)
        cout << *(pnumbers+i) << " ";
    cout << endl;

    //prints out the entire array
    for(int i = 0; i < size; i++)
        cout << *(numbers+i) << " ";
    cout << endl;

    //prints out the address of numbers
        cout << &numbers;
        cout << endl;

//*******************************************************************************************************************//

    //creats a int variable and an int pointer and prints out the value stored in ptr
    int var = 8;
    int* ptr = &var;
    cout << ptr;
    cout << endl;

    //creats a int variable and an int pointer and prints out the value stored in ptr2
    int var2 = 12;
    int* ptr2 = &var2;
    cout << ptr2;
    cout << endl;


    //ptr gets the address of var2
    ptr = &var2;

    // This prints a hexadecimal address
   cout << ptr << endl;
   cout << &var2 << endl;

   // This prints a value stored at the location to which
   // the pointer is pointing
   cout << *ptr << endl;
   sneaky(var);
   cout << var;
   cout << endl;

//*****************************************************************************************************************//
    //creates two pointers and an integer array of size 10 along with a int for the index
    int* p1, *p2;
    int a[10];
    int index;

    //fills the array a with values between 0 and 9
    for (index = 0; index < 10; index++)
        a[index] = index;

    //sets the array a equal to pointer p1
    p1 = a;

    //sets the pointer p2 to p1-1
    p2 = p1 - 1;

    //
    for (index = 0; index < 10; index++)
        cout << *(p1 + index) << " " << *(p2 + 1 + index) << endl;
    cout << endl;

    //sets p2 current value +1
    for (index = 1; index <= 10; index++)
        p2[index] = p2[index] + 1;

    //prints value of a
    for (index = 0; index < 10; index++)
        cout << *(a + index) << " ";
    cout << endl;
//**********************************************************************************************//
    //creats a int for the of array and creates a dynamic array based on size
    int sizeDynamic;
    cout << "Enter size of dynamic array: ";
    cin >> sizeDynamic;
    int *arr = new int[sizeDynamic];

    //loop that will put values into array
    int input;
    for(int i = 0; i < sizeDynamic;i++) {
            cout << "Enter a integer for arr[" << i << "]";
            cin >> input;
            arr[i] = input;
    }

    //loop that will print array
    for(int i = 0; i < sizeDynamic;i++) {
        cout << "arr[" << i << "]" << arr[i] << endl;
    }

    //clear space in dynamiclly allocated array
    delete [] arr;
    return 0;
}

/* sneaky()
 *
 * Demonstrates how referencess can be used to change a parameter's value.
 *
 * Requires: A reference to an initialized integer.
 * Modifies: The value of the parameter.
 */
void sneaky(int& temp)
{
    temp = 123321;
    cout << temp << endl;
}
