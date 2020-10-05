#include <stdlib.h>
/*
 * linkedlist.c
 *
 *  Created on: Apr 26, 2018
 *      Author: Jamie
 */

int main(){
	struct node{
		int data;
		struct node *next;
	};

	struct node *head;
	head=NULL;

	head=malloc(sizeof(struct node));
	head->data=10;
	//head->next = NULL;
	struct node *node2 = malloc(sizeof(struct node));
	node2->data = 20;
	node2->next = head;
	head= node2;

	struct node *temp = head;

	head=head->next;
	temp->next = NULL;
	free(temp);
	return 0;
}

