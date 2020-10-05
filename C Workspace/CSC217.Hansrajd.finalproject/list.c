/*
 * list.c
 *
 *  Created on: May 3, 2018
 *      Author: THE BOSS
 */
#include <stdio.h>
#include <stdbool.h>

struct node
{
	char *data;
	struct node* next;
};

/*
    init the stack
 */
void init(struct node* head)
{
	head = NULL;
}

/*
    push an element into stack
 */

void printlist(struct node* head) {
	struct node *current;
	current = head;
	if(current!= NULL)
	{
		do
		{
			printf("%s ",current->data);
			current = current->next;
		}
		while (current!= NULL);
		printf("=>");
	}
	else
	{
		printf("The Stack is empty\n");
	}

}

void push(struct node *head, char *s) {
	struct node* tmp = (struct node*)malloc(sizeof(struct node));
	if(tmp == NULL)
	{
		exit(0);
	}
	tmp->data = s;
	tmp->next = head;
	head = tmp;
	printlist(head);
}



void deletelist(struct node *head) {

}


