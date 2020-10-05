#ifndef HUMAN_H
#define HUMAN_H
class Human
{
public:
//Constructors
Human() {fn = "John"; ln = "Doe";}
Human(std::string f, std::string l)
{
fn = f;
ln = l;
}
//Accessors
std::string getFirst(){return fn;}
std::string getLast(){return ln;}
//Mutators
void setFirst(std::string f){fn = f;}
void setLast(std::string l){ln = l;}
void output() {
    std::cout << "First Name: " << fn << std::endl << "Last Name: " << ln << std::endl;
}
private:
//Member Variables
std::string fn;
std::string ln;
};
#endif
