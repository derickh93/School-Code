/*
 * lab3.c
 *
 *  Created on: Mar 6, 2018
 *      Author: Derick Hansraj
 *      OS: Windows
 *      Source: https://www.le.ac.uk/users/rjm1/cotter/page_78.htm

 */

#include <stdio.h>
#include <stdbool.h>

bool isnumeric(char s[]) {
	for(int i = 0; s[i] != '\0'; i++) {
		if(s[i] < '0' || s[i] > '9')
			return false;
	}
	return true;
}

int parseint(char s[]) {
	int total = 0;
	int value = 0;
	char c;
	if(isnumeric(s)) {
		for(int i = 0; s[i] != '\0'; i++) {
			 c = s[i];
			value = c - 48;
			total = 10 * total + value;
		}
	}
	return total;
}

