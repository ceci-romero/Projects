#include <stdio.h>
//#include <stdlib.h>
#include <string.h>

#include "spell.h"

#define MAX_CAPACITY  101

/*
Repeatedly read 2 strings from the user.
The strings must be separated by a space.
The program will compute the edit distance between them,
print the cost table, the cost and the alignment.

After that, read 2 file names (one for a dictionary file and
one for a file of misspelled words); find best matches for all the words in the test file
and get user's choice for fixing the word.
*/

int main()
{
    // any word or file name can be at most 100 characters.
    char first[MAX_CAPACITY], second[MAX_CAPACITY];
    char dictname[MAX_CAPACITY], testname[MAX_CAPACITY];
    char c;    
    //int  printOn = 0;  // print-off

    // Part1: Compute and show edit distance for pairs of words  
    printf("\nPart 1 - edit distance table\n");  
    printf(" Repeatedly enter two words separated by a space (e.g.: cat someone).\n Stop with: -1 -1\n");
    while ( 1==1 ) {
        scanf("%s%s%c", first, second, &c);
        printf("\n first: %s",   first);
        printf("\nsecond: %s\n", second);
        if (strcmp(first,"-1") == 0 && strcmp(second,"-1") == 0) {
            break;
        }
        int dist = edit_distance(first, second, 1);  // last argument, 1, indicates that the distance table should be printed
        printf("edit distance: %d\n", dist);
        printf("\n=========================================================================================\n");
    }

    // Part2: read filenames and call spell-check
    printf("\nPart 2 - spell check\n");
    printf("Enter the dictionary file name: ");
    scanf("%s%c", dictname, &c);  // remove \n from read buffer
    printf("Enter the test file name: ");
    scanf("%s%c", testname, &c);  // remove \n from read buffer
    spell_check(testname, dictname);

    printf("\nGood bye!\n");
    return 0;
}