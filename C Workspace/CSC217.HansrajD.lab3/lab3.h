/*
 * lab3.h
 *
 *  Created on: Mar 6, 2018
 *      Author: Derick Hansraj
 *      OS: Windows
 *      Source: https://www.le.ac.uk/users/rjm1/cotter/page_78.htm
 *
 */

#ifndef LAB3_H_
#define LAB3_H_

#include <stdio.h>
#include <stdbool.h>


// converts null terminated string to an int, don’t worry
//about negative numbers, this function can assume all chars except null are
//number characters.
int parseint(char s[]);

// returns true if the string contains number characters
//only (aside from the null terminator), false otherwise
bool isnumeric(char s[]);



#endif /* LAB3_H_ */
