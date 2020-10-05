/*
 * stack.c
 *
 *  Created on: May 3, 2018
 *      Author: Neil Sarran - N00833746
 */
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
#include "finalproj1.h"

struct Node
{
	char data[15];
	struct Node* next;
};

struct Node* head = NULL;
int count = 0;

bool sIsEmpty()
{
	return(head == NULL);
}

int sTotal()
{
	return count;
}

struct Node* push(char *s)
{
	struct Node* temp = (struct Node*)malloc(sizeof(struct Node));

	strCpy(temp -> data, s);
	temp -> next = head;
	head = temp;

	count++;
	return head;
}

struct Node* pop()
{
	if(head == NULL)
	{
		printf("The stack is empty.\n");
		exit(0);
	}
	struct Node* temp = (struct Node*)malloc(sizeof(struct Node));
	temp = head;

	head = head -> next;
	count--;

	free(temp);
	return head;
}

void sToString()
{
	struct Node* temp = head;

	while(temp != NULL)
	{
		printf("%s, ",temp -> data);
		temp = temp -> next;
	}

	printf("\n");
}
