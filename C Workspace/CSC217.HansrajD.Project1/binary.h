/*
 * binary.h
 *
 *  Created on: Feb 13, 2018
 *      Author: Derick Hansraj (N00827531)
 *     	OS: Windows
 *     	Sources:
 *     	1). https://www.programiz.com/c-programming/examples/alphabet
 *     	2). https://stackoverflow.com/questions/19896645/how-to-check
 *     		-if-a-string-is-a-lettera-z-or-a-z-in-c
 *     	3). https://www.tutorialspoint.com/cprogramming/c_loops.htm
 *
 */
#include <stdbool.h>;
#ifndef BINARY_H_
#define BINARY_H_

char firstNameInitial;
extern char lastNameInitial;
extern char arrinitial[];
extern bool done;
extern int binaryFirst[8];
extern int binaryLast[8];
extern int firstOnes;
extern int lastOnes;
extern int binarystrone[8];
extern int binarystrtwo[8];
extern int binarystrthree[8];
extern int strfirstone;
extern int strsecondone;
extern int strthirdone;
extern char in[4];
extern bool over;
extern int arrInitialsOnes[];
extern int arrInOnes[];

bool isAlpha(char s[]);

void convertCharToBinary(char c, int binar[]);

int krgetline(char s[], int lim);

int countOnes(int binary[]);

int mystrlen(char s[]);


#endif /* BINARY_H_ */
