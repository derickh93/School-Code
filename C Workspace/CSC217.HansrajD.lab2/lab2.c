/*
 * lab2.c
 *
 *  Created on: Feb 1, 2018
 *      Author: cooli
 */

#include <stdio.h>
#include <stdbool.h>
#include <string.h>


int mygetline(char s[], int lim) {
	char c;
	int i;

	for(i = 0; i < lim-1 && (c = getchar()) != EOF && c != '\n'; i++)
		s[i] = c;
	if(c == '\n') {
		s[i] = c;
		i++;
	}

	s[i] = '\0';
	return i;
}

bool mystrcmp(char s[], char t[]) {
	for(int i = 0;s[i] == t[i]; i++) {
		if(!s[i])
			return true;
	}
	return false;
}

int mystrlen(char s[]) {
	int counter = 0;
	for(;s[counter] != '\0';counter++);
	return counter;
}



int main() {
	char teststring[] = "abc";
	char s[4];
	printf("Enter the string: %s:\n",teststring);

	do{
		fflush(stdout);
		mygetline(s,4);
	}
	while(!mystrcmp(s,teststring));

	printf("Correct Answer!!!");
	fflush(stdout);
	return 0;
}


