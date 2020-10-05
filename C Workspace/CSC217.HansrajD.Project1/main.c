/*
 * main.c
 *
 *  Created on: Feb 13, 2018
 *      Author: Derick Hansraj (N00827531)
 *      OS: Windows
 */

#include <stdio.h>
#include <stdbool.h>


int main() {

	//variables
	bool over = false;
	bool done;
	char firstNameInitial = 'D';
	char lastNameInitial = 'H';
	char in[4];
	char arrInitial[] = {firstNameInitial,lastNameInitial};
	int binaryFirst[8];
	int binaryLast[8];
	int firstOnes = 0;
	int lastOnes = 0;
	int binaryStrOne[8];
	int binaryStrTwo[8];
	int binaryStrThree[8];
	int strFirstOne = 0;
	int strSecondOne = 0;
	int strThirdOne = 0;


	//loop until user has a match
	while(!over) {
		done = false;
		//loop until user enters correct input
		while(!done) {
			puts("\n**********Enter 3 characters A-Z or a-z only**********\n");
			fflush(stdout);
			krGetLine(in,4);

			if(isAlpha(in))
				done = true;
		}

		//convert the first initial to binary and count the amount of ones
		convertCharToBinary(firstNameInitial, binaryFirst);
		firstOnes = countOnes(binaryFirst);

		//conver the last initial to binary and count the amount of ones
		convertCharToBinary(lastNameInitial, binaryLast);
		lastOnes = countOnes(binaryLast);

		//display the amount of ones in the first initial and display the binary numbers
		printf("Char: %c \nOnes: %i",firstNameInitial,firstOnes);
		puts("\nBinary");
		for(int i = 0; i < 8; i++) {
			printf("%i",binaryFirst[i]);
		}

		//display the amount of ones in the last initial and display the binary numbers
		printf("\n\nChar: %c \nOnes: %i",lastNameInitial,lastOnes);
		puts("\nBinary");

		for(int i = 0; i < 8; i++) {
			printf("%i",binaryLast[i]);
		}

		//convert the user input to binary and count the amount of ones
		convertCharToBinary(in[0], binaryStrOne);
		strFirstOne = countOnes(binaryStrOne);

		//display amount of ones in the first user input and display the binary numbers
		printf("\n\nChar: %c \nOnes: %i",in[0],strFirstOne);
		puts("\nBinary");
		for(int i = 0; i < 8; i++) {
			printf("%i",binaryStrOne[i]);
		}

		//display amount of ones in the second user input and display the binary numbers
		convertCharToBinary(in[1], binaryStrTwo);
		strSecondOne = countOnes(binaryStrTwo);

		printf("\n\nChar: %c \nOnes: %i",in[1],strSecondOne);
		puts("\nBinary");
		for(int i = 0; i < 8; i++) {
			printf("%i",binaryStrTwo[i]);
		}

		//display amount of ones in the third user input and display the binary numbers
		convertCharToBinary(in[2], binaryStrThree);
		strThirdOne = countOnes(binaryStrThree);

		printf("\n\nChar: %c \nOnes: %i",in[2],strThirdOne);
		puts("\nBinary");
		for(int i = 0; i < 8; i++) {
			printf("%i",binaryStrThree[i]);
		}

		//loops through arrays to check if there is a match in the amount of ones
		int arrInitialsOnes [2] = {firstOnes,lastOnes};
		int arrInOnes [3] = {strFirstOne,strSecondOne,strThirdOne};
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 3; j++){
				if(arrInOnes[j] == arrInitialsOnes[i]) {
					printf("\nChar %c and Char %c both have %i ones. You are done!!!"
							,in[j],arrInitial[i],arrInOnes[j]);
					i = j = 3;
					over = true;
				}
			}
		}
	}
	return 0;
}

