//Derick Hansraj
//9/25/2018
//Homework #2
//Cs211 Lab

#include <iostream>
#include <iomanip>

//Define Student class
class Student
{
	//Define private members/variables
	private:
		std::string lastName, firstName, dateOfBirth, dateOfMat,standing;
		double gpa, credits;

	//Define public members/variables
	public:
		Student() {
			std::cout << "A default Student object has been created" << std::endl;
		}

		Student(std::string last, std::string first,std::string dob, std::string dom, double gp, double credit) {
		    lastName = last;
		    firstName = first;
		    dateOfBirth = dob;
		    dateOfMat = dom;
		    gpa = gp;
		    setCredits(credit);
		}

        //Mutator functions
        void setLast(std::string l);
        void setFirst(std::string f);
		void setDateOfBirth(std::string dob);
		void setDom(std::string dom);
		void setGpa(double gp);
		void setCredits(double credit);

        //Accessor functions
		std::string getLast();
		std::string getFirst();
        std::string getDateOfBirth();
        std::string getDom();
		double getGpa();
		double getCredits();
		std::string getStandings();

		//input and output functions
		void input();
		void output();
};

//Main function that will test the class by use of the input method, then testing by
//the parameterized constructor and the default constructor.
int main() {
	//testing the input/output function
	Student derick;
	derick.input();
	derick.output();

	//tesing the parameterized constructor
	Student john = Student("Doe","John","01/01/1990","01/01/2015",3.0,29);
	john.output();
	
	//testing the default constructor
	Student jane;
	jane.output();
	
	//testing the mutators
	jane.setLast("Doe");
	jane.setFirst("Jane");
	jane.setDateOfBirth("04/12/1996");
	jane.setDom("02/01/2012");
	jane.setGpa(3.35);
	jane.setCredits(59.5);
	
	//testing the accessors
	std::cout << jane.getFirst() << std::endl;
	std::cout << jane.getLast() << std::endl;
	std::cout << jane.getDateOfBirth() << std::endl;
	std::cout << jane.getDom() << std::endl;
	std::cout << jane.getGpa() << std::endl;
	std::cout << jane.getCredits() << std::endl;
	std::cout << jane.getStandings() << std::endl;
	return 0;
}

//accessor function for lastName
std::string Student::getLast()
{
    return lastName;
}

//mutator function for lastName
void Student::setLast(std::string l)
{
    lastName = l;
}

//accessor function for firstName
std::string Student::getFirst()
{
    return firstName;
}

//mutator function for firtName
void Student::setFirst(std::string f)
{
    firstName = f;
}

//accessor function for dateOfBirth
std::string Student::getDateOfBirth()
{
    return dateOfBirth;
}

//mutator function for dateOfBirth
void Student::setDateOfBirth(std::string dob)
{
    dateOfBirth = dob;
}

//accessor function for dateOfMat
std::string Student::getDom()
{
    return dateOfMat;
}

//mutator function for dateOfMat
void Student::setDom(std::string dom)
{
    dateOfMat = dom;
}

//accessor function for gpa
double Student::getGpa()
{
    return gpa;
}

//mutator function for gpa
void Student::setGpa(double gp)
{
    gpa = gp;
}

//accessor function for credits
double Student::getCredits()
{
    return credits;
}

//mutator function for credits, this will also determine the standing of the Student
void Student::setCredits(double credit)
{
    credits = credit;
    if(credits < 30)
        standing = "Freshman";
    else if(credits < 60)
        standing = "Sophmore";
    else if(credits < 90)
        standing = "Junior";
    else if(credits < 120)
        standing = "Senior";
    else
        standing = "Graduate";
}

//accessor function for standing
std::string Student::getStandings()
{
    return standing;
}

//input function that will ask the user for values that will be used with the mutator functions
//for each private member .
void Student::input()
{
    std::cout << "Enter the students last name:";
    std::string ln;
    std::cin >> ln;
    setLast(ln);

    std::cout << "Enter the students first name:";
    std::string fn;
    std::cin >> fn;
    setFirst(fn);

    std::cout << "Enter the students date of birth:";
    std::string dob;
    std::cin >> dob;
    setDateOfBirth(dob);

    std::cout << "Enter the students date of matriculation:";
    std::string dom;
    std::cin >> dom;
    setDom(dom);

    std::cout << "Enter the students GPA:";
    double gp;
    std::cin >> gp;
    setGpa(gp);

    std::cout << "Enter the students number of credits:";
    double cr;
    std::cin >> cr;
    setCredits(cr);
}

//output function that will output a Student by displaying all of its private members
void Student::output()
{
    std::cout << "Last Name: " << lastName << std::endl
    << "First Name: " << firstName << std::endl
    << "Date of Birth: " << dateOfBirth << std::endl
    << "Date of Matriculation: " << dateOfMat << std::endl
    << "GPA: " << std::setprecision(2) << std::fixed << gpa << std::endl
    << "Credits: " << credits << std::endl
    << "Standing: " << standing << "\n" << std::endl;
}
