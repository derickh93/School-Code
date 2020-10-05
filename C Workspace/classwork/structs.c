#include<string.h>
#include<stdio.h>
#include<stdlib.h>
/*
 * structs.c
 *
 *  Created on: Apr 26, 2018
 *      Author: Jamie
 */

int mainn(){
	struct person{
		int id;
		char name[50];
	};

	struct person p1;
	p1.id = 100;
	strcpy(p1.name, "bob");

	struct person *p2;

	p2=malloc(sizeof(struct person));
	p2->id=5;
	strcpy(p2->name,"Marc");

	printf("id %i\n",p1.id);
	printf("id %i\n",p2->id);

	struct person *p3 = malloc(sizeof(struct person));

	free(p2); //Unallocate memory
	p2=p3;

	//p2=NULL; // empty pointer

	struct person *p4;
	p4 = p3;

	return 0;
}

