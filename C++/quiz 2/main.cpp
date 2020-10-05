#include <iostream>

using namespace std;

int main()
{
    int num, *p1, *p2;
    num = 10;
    p1 = &num;
    p2 = &num;
    p1 = 12;
    *p2 = 24;
    cout << *p1;
    return 0;
}
