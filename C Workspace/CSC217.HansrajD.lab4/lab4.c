/*
 * lab4.c
 *
 *  Created on: Apr 24, 2018
 *      Author: Derick Hansraj (N00827531)
 *      OS: Windows
 *      Source: https://www.cs.bu.edu/teaching/c/file-io/intro/
 *
 */


#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>


int main(int argc, char* argsv[]) {

	int inputMatrix[2][2] = {{0}};
	int outputMatrix[2][2] = {{0}};
	char str[81];
	bool in = false;
	bool out = false;
	int index;
	int multiply;
	char buff[81];
	FILE *fp;


	if(argc > 1) {
		if((argsv[1][0] == '-') && (argsv[1][1] == 'h')) {
			printf("***********************HELP***********************\nFor example if your program is called mathio:\nmathio -i input.txt -o output.txt "
					"\nYour program will have to read the numbers from the file called input.txt and output the result to file called output.txt. "
					"Those filenames are just examples.\nmathio -i input.txt \nYour program will have to read the numbers from the file called input.txt"
					" and output the result to standard output. \nmathio -o output.txt \nYour program will have to read the numbers from standard input and "
					"output result to file called output.txt.\nmathio \nYour program will have to read the numbers from standard input and output to standard "
					"output. \nmathio -h \nYour program should give examples [help] on how to use the program.\n***********************HELP***********************");

			return 0;

		}
		else {
			if (argc > 0) {
				if(strcmp(argsv[1], "-o" ) == 0) {
					index = 1;
					out = true;
				}
				else if(strcmp(argsv[1], "-i") == 0) {
					in = true;
				}
				if (argc > 3 ) {
					if(strcmp(argsv[3], "-o") == 0) {
						out = true;
						index = 3;
					}

				}
			}
		}
	}


	if( in == true) {
		fp = fopen( argsv[2] ,"r");
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++){
				fgets(buff, 81, fp);
				inputMatrix[i][j] = atoi(buff);
			}
		}
		fgets(buff, 81, fp);
		multiply = atoi(buff);
	}
	else {
		printf("Enter 5 numbers\n");
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 2; j++){
				fgets(str, 81, stdin);
				inputMatrix[i][j] = atoi(str);
			}
		}
		fgets(str,81,stdin);
		multiply = atoi(str);
	}

	for(int i = 0; i < 2; i++) {
		for(int j = 0; j < 2; j++) {
			outputMatrix[i][j] = inputMatrix[i][j] * multiply;
		}
	}


	if(out ==  true) {
		fp = fopen(argsv[index+1], "w");
		fprintf(fp, "[ %i ][ %i ] \r\n",outputMatrix[0][0],outputMatrix[0][1]);
		fprintf(fp, "[ %i ][ %i]",outputMatrix[1][0],outputMatrix[1][1]);
		fclose(fp);
	}
	else {
		printf("[ %i ][ %i ] \n",outputMatrix[0][0],outputMatrix[0][1]);
		printf( "[ %i ][ %i ]",outputMatrix[1][0],outputMatrix[1][1]);
	}
	return 0;
}

//1). I would use the instructions thought in class to read input from a file. That is the '<' instruction. Example: lab4 < output.txt

//2). I would use the instructions thought in class to redirect output to a file. That is the '>' instruction. Example: lab4 > output.txt

//3). I would use the instructions thought in class to both read input and redirect output. That is the '>' and '<' instructions. Example:
//lab4 < input.txt > output.txt



