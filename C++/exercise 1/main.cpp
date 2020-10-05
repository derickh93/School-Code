//Derick
//8/28/2018
//Exercise 1
//CS211 Lab

#include <iostream>
using namespace std;


int main () {
    int num = 0;
    bool prime;
    for (int i=2; i<2000; i++) {
        prime=true;
        for (int j=2; j*j<=i; j++) {
            if (i % j == 0) {
                prime=false;
                break;
            }
        }
        if(prime) {
            cout << i << "\n";
            num++;
        }
    }
    cout << "Total number of primes from 1 to 2000: " << num;
    return 0;
}
