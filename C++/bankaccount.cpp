#include <iostream>

struct BankAccount
{
   int accno;
   std::string name;
   double balance;
};

void input(BankAccount& a);
BankAccount input();
void output(BankAccount a);

int main()
{
   BankAccount acc;
   acc = input();
   output(acc);

   return 0;
}

void input(BankAccount& a)
{
   std::cout << "Acc# : ";
   std::cin >> a.accno;
   std::cout << "Name: ";
   std::cin >> a.name;
   std::cout << "Balance: ";
   std::cin >> a.balance;
}

BankAccount input()
{
   BankAccount a;
   std::cout << "Acc# : ";
   std::cin >> a.accno;
   std::cout << "Name: ";
   std::cin >> a.name;
   std::cout << "Balance: ";
   std::cin >> a.balance;
   
   return a;
}

void output(BankAccount a)
{

   std::cout << "Acc# :   " << a.accno << std::endl
             << "Name:    " << a.name << std::endl
             << "Balance: " << a.balance << std::endl;
}
