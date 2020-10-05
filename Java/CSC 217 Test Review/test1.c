/*
 * lab2.c
 *
 *  Created on: Feb 1, 2018
 *      Author: cooli
 */

#include <stdio.h>
#include <stdbool.h>


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

void mystrcat(char d[],char s[]) {
	int len = strlen(d);
	for(int i = 0;s[i] != '\0';i++)
		d[len++] = s[i];
	d[len] = '\0';
}

void mystrcopy(char d[], char s[]) {
	int i = 0;
	for(i = 0; s[i] != '\0'; i++)
		d[i] = s[i];
	d[i] = '\0';
}



int main() {
	char teststring[] = "derick";
	char teststring2[81];
	char teststring3[] = "derick";
	char teststring4[] = "avinash";
	char teststring5[81];


	int temp = mystrlen(teststring);
	int temp2 = mygetline(teststring2, 81);
	bool temp3 = mystrcmp(teststring, teststring3);
	bool temp4 = mystrcmp(teststring, teststring4);
	mystrcopy(teststring5, teststring4);
	printf("%i\n%i\n%i\n%i\n%s",temp,temp2,temp3,temp4,teststring5);
	return 0;
}

