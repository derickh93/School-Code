/*
 * stack.c
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

struct Node* head = NULL;
int countStack = 0;

bool stackEmpty() {
	return(head == NULL);
}

int stackTotal() {
	return countStack;
}

struct Node* push(char *s) {
	struct Node* temp = (struct Node*)malloc(sizeof(struct Node));

	strcpy(temp -> data, s);
	temp -> next = head;
	head = temp;

	countStack++;
	return head;
}

struct Node* pop() {
	if(head == NULL) {
		printf("The stack is empty.\n");
		exit(0);
	}
	struct Node* temp = (struct Node*)malloc(sizeof(struct Node));
	temp = head;

	head = head -> next;
	countStack--;

	free(temp);
	return head;
}

void stackString() {
	struct Node* temp = head;

	while(temp != NULL) {
		printf("%s, ",temp -> data);
		temp = temp -> next;
	}

	printf("\n");
}
