/*
 * main.c
 *
 *  Created on: Mar 6, 2018
 *      Author: Derick Hansraj
 *      OS: Windows
 *      Source: https://www.le.ac.uk/users/rjm1/cotter/page_78.htm
 */

#include <stdio.h>
#include <stdbool.h>

int main(int argc, char *argv[]) {
	bool run = true;
	int numOne;
	int numTwo;

	if(argc == 3 && isnumeric(argv[1]) == true && isnumeric(argv[2]) == true) {
		numOne = parseint(argv[1]);
		numTwo = parseint(argv[2]);
		printf("%s %i %i\n%i",argv[0],numOne,numTwo,numOne+numTwo);
		run = false;
	}
	else
		puts("Incorrect usage. Use this program as such:\nmyprogram 7 9");

	return 0;
}
