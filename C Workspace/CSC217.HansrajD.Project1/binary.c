/*
 * binary.c
 *
 *  Created on: Feb 13, 2018
 *      Author: Derick Hansraj (N00827531)
 *     	OS: Windows
 *
 */
#include <stdbool.h>;
#include <stdio.h>


bool isAlpha(char s[]) {
	for(int i = 0; s[i] != '\0'; i++) {
		if((s[i] < 65 || s[i] > 122) || (s[i] > 90 && s[i] < 97))
			return false;
	}
	return true;
}

void convertCharToBinary(char c, int binary[]) {
	int pos = 7;
	int num = c;
	while(pos >= 0) {
		if(num % 2 == 0)
			binary[pos] = 0;
		else
			binary[pos] = 1;
		num = num / 2;
		pos--;
	}

}

int krGetLine(char s[], int lim) {
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

int countOnes(int binary[]) {
	int num = 0;
	for(int i = 0; i < 8;i++) {
		if(binary[i] == 1){
			num++;
		}
	}
	return num;
}

int myStrLen(char s[]) {
	int counter = 0;
	for(;s[counter] != '\0';counter++);
	return counter;
}

