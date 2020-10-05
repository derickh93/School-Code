/*
 * finalproj1.h
 *
 *  Created on: May 6, 2018
 *      Author: Neil Sarran - N00833746
 */
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>

#ifndef FINLPROJ1_H_
#define FINLPROJ1_H_

int main ();

bool qIsEmpty();

int qTotal();

void Enqueue(char *s);

void Dequeue();

void qToString();

bool sIsEmpty();

int sTotal();

struct Node* push(char *s);

struct Node* pop();

void sToString();

#endif /* FINLPROJ1_H_ */
