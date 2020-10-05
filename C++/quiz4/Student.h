
#include <iostream>
class Student : public Human
{
public:
//Constructors
Student() : Human() {id = "1234 ";}
Student(std::string i) : Human()
{
id = i;
}
//Accessor
std::string getID(){return id;}
//Mutator
void setID(std::string i){id = i;}
//Function
void output()
{
Human().output();
std::cout << "ID: "
<< id << std::endl;
}
private:
//Member Variable
std::string id;
};
