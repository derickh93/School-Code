#include <iostream>

class Student {
	private:
		int age, idNo;
		std::string name;
		
		public:
			int getAge();
			int getidNo();
			std::string getName();
			void setAge(int a);
			void setidNo(int i);
			void setName(std::string n);
			void output();
			void input();
};

main() {
	Student derick;
	derick.input();
	derick.output();
	return 0;
}

int Student::getAge() {
	return age;
}

int Student::getidNo() {
	return idNo;
}

std::string Student::getName() {
	return name;
}

void Student::setAge(int a) {
	age = a;
}

void Student::setidNo(int i) {
	idNo = i;
}

void Student::setName(std::string n) {
	name = n;
}

void Student::input() {
	std::cout << "Enter an age: ";
	std::cin >> age;
	
	std::cout << "Enter an idno: ";
	std::cin >> idNo;
	
	std::cout << "Enter an name: ";
	std::cin >> name;
}

void Student::output() {
	std::cout << "AGE: " << age << " IDNO: " << idNo << " NAME: " << name;
}
