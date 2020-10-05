/*
 * queue.c
 *
 *  Created on: May 5, 2018
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

struct Node* front;
struct Node* rear;
int count = 0;;

bool qIsEmpty()
{
	return(front == NULL);
}

int qTotal()
{
	return count;
}

void Enqueue(char *s)
{
	struct Node* temp = (struct Node*)malloc(sizeof(struct Node));

	strCpy(temp -> data, s);
	temp -> next = NULL;

	if(front == NULL && rear == NULL)
	{
		front = rear = temp;
	}
	else
	{
		rear -> next = temp;
		rear = temp;
	}

	count++;
}

void Dequeue()
{
	if(front == NULL)
	{
		printf("The queue is empty.\n");
		return;
	}

	struct Node* temp = front;

	if(front == rear)
	{
		front = rear = NULL;
	}
	else
	{
		front = front -> next;
	}

	count--;
	free(temp);
}

void qToString()
{
	struct Node* temp = front;

	while(temp != NULL)
	{
		printf("%s, ",temp -> data);
		temp = temp -> next;
	}
	printf("\n");
}
