//Derick
//8/28/2018
//Exercise 1
//CS211 Lab

#include <iostream>
using namespace std;

int main() {
	int count = 0;
	int num = 1;
	int cons = 2000;
	while(num <= 2000) {
		if((num/2) == 0) {
		cout << num;
		count++;
	}
		num++;
	}
	cout << count;
	return 0;
}
