/*
 * queue.c
 *
 *  Created on: May 7, 2018
 *      Author: Derick Hansraj (N00827531)
 *      OS: Windows
 *
 */
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
#include "list.h"


struct Node {
	char data[15];
	struct Node* next;
};

struct Node* front;
struct Node* rear;
int count = 0;;

bool queueEmpty() {

	return(front == NULL);
}

int queueTotal() {
	return count;
}

void enqueue(char *s) {
	struct Node* temp = (struct Node*)malloc(sizeof(struct Node));

	strcpy(temp -> data, s);
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

void dequeue() {
	if(front == NULL) {
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

void queueString() {
	struct Node* temp = front;

	while(temp != NULL) {
		printf("%s, ",temp -> data);
		temp = temp -> next;
	}
	printf("\n");
}
