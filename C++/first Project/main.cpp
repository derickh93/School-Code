#include <iostream>

using namespace std;
/* This program shows the difference between
* signed and unsigned integers.
*/

int main() {
    short int variable_2; //a signed short integer
    short unsigned variable_1;// an unsigned short integer

    variable_1 = 50000;

    variable_2 = variable_1;
    cout << variable_2 << " <-- Variable 1 and Variable 2 --> " << variable_1;
    return 0;
}
