/*
 * main.c
 *
 *  Created on: May 6, 2018
 *      Author: Neil  Sarran - N00833746
 */
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
#include "finalproj1.h"

int main ()
{
	FILE* fp;
	char color1[10];
	char color2[10];
	char color3[10];

	fp = fopen("colors.txt.rtf", "r");

	while(fscanf(fp,"%s %s %s", color1, &color2, &color3)>0) {
		Enqueue(color1);
		push(color1);
		Enqueue(color2);
		push(color2);
		Enqueue(color3);
		push(color3);

	}
	printf("Queue: \n");
	qToString();

	printf("Stack: \n");
	sToString();

	int i = qTotal();

	while(i > 0)
	{
		pop();
	}

	int j = sTotal();

	while(j > 0)
	{
		dequeue();
	}

	fclose(fp);

	return 0;
}

