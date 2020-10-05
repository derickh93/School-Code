#include <iostream>
#include <cmath>
#include <cstdlib>

using namespace std;

int gcd(int a, int b);
int lcm(int a, int b);
void input(int r[]);
void output(int r[]);
void add(int r1[], int r2[], int r[]);
void sub(int r1[], int r2[], int r[]);
void mult(int r1[], int r2[], int r[]);
void div(int r1[], int r2[], int r[]);

int main()
{
   int r1[2];
   int r2[2];

   input(r1);
   input(r2);

   output(r1);
   output(r2);
   
   int sum[2];
   int diff[2];
   int prod[2];
   int quot[2];
   add(r1, r2, sum);
   sub(r1, r2, diff);
   mult(r1, r2, prod);
   div(r1, r2, quot);

   cout << "sum is: ";
   output(sum);

   cout << "diff is: ";
   output(diff);
 
   cout << "prod is: ";
   output(prod);

   cout << "quot is: ";
   output(quot);

   return 0;
}

int gcd(int a, int b)
{
   while (b != 0)
   {
      int c = a;
      a = b;
      b = c % b;
   }

   return a;
}

int lcm(int a, int b)
{
   return (a * b) / gcd(a, b);
}

void input(int r[])
{
   cout << "num: ";
   cin >> r[0];
   cout << "denom: ";
   cin >> r[1];

   if (r[1] == 0)
   {
      cout << "Error: denominator cannot be zero." << endl;
      exit(1);
   }
   else if (r[1] < 0)
   {
      r[1] = -r[1];
      r[0] = -r[0];
   }
}

void output(int r[])
{
   cout << r[0] << "/" << r[1] << endl;
}

void add(int r1[], int r2[], int r[])
{
   int ell = lcm(r1[1], r2[1]);
   r[1] = ell;
   r[0] = r1[0] * ell / r1[1] + r2[0] *  ell / r2[1];
}

void sub(int r1[], int r2[], int r[])
{
   int ell = lcm(r1[1], r2[1]);
   r[1] = ell;
   r[0] = r1[0] * ell / r1[1] - r2[0] *  ell / r2[1];
}

void mult(int r1[], int r2[], int r[])
{
   r[0] = r1[0] * r2[0];
   r[1] = r1[1] * r2[1];
}

void div(int r1[], int r2[], int r[])
{
   if (r2[0] == 0)
   {
       cout << "Error. Cannot divide by zero." << endl;
       exit(1);
   }
   r[0] = r1[0] * r2[1];
   r[1] = r1[1] * r2[0];
}
