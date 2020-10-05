#include<stdlib.h>
#include<stdio.h>

/*
 * process.c
 *
 *  Created on: Apr 26, 2018
 *      Author: Jamie
 */


int test(){

	FILE *fp;

	char s[81];

	fp = popen("ipconfig", "r");

	if(fp == NULL){
		printf("Error\n");
		return 1;
	}
	while(fgets(s,81,fp)){
		printf("%s", s);



	}

	fclose(fp);

	return 0;
}
