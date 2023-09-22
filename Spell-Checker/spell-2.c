#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "spell.h"

/*  Suggestions
- When you work with 2D arrays, be careful. Either manage the memory yourself, or
work with local 2D arrays. Note C99 allows parameters as array sizes as long as 
they are declared before the array in the parameter list. See: 
https://www.geeksforgeeks.org/pass-2d-array-parameter-c/

Worst case time complexity to compute the edit distance from T test words
 to D dictionary words where all words have length MAX_LEN:
Student answer:  Theta(............)


Worst case to do an unsuccessful binary search in a dictionary with D words, when 
all dictionary words and the searched word have length MAX_LEN 
Student answer:  Theta(............)
*/


/* Write helper functions here */

//The following function prints out the edit distance table

void print_2D(int num_rows, int num_cols, int arr2D[num_rows][num_cols], char * first_string, char * second_string)
{	
	int r,c;
//formatting table
char blank = ' ' ;
printf("%3c|", blank);
printf("%3c|", blank);
for (r = 0; r<num_rows; r++){
if(r == 0){ //print second word (column letters)
for(c=0; c<num_cols-1; c++){
printf("%3c|",second_string[c]);
}
printf("\n");
for(int d=0 ;d< num_cols+1; d++){ // formatting
printf("----");
}
printf("\n");
printf("%3c|", blank);
for(c=0; c<num_cols; c++){ //following loops print out the values
//from the array
printf("%3d|", arr2D[r][c]);
}
printf("\n");
for(c=0 ;c< num_cols+1; c++){
printf("----");
}
printf("\n");
if(r<num_rows-1){
printf("%3c|", first_string[r]); //print first word (row letters)
}
}
printf("\n\n");
}
}


/*
Parameters:
  - first_string - pointer to the first string (displayed vertical in the table)
  - second_string - pointer to the second string (displayed horizontal in the table)
  - print_table - If 1, the table with the calculations for the edit distance will be printed.
                  If 0 (or any value other than 1)it will not print the table
Return:  the value of the edit distance
*/
int edit_distance(char * first_string, char * second_string, int print_table){

  
  
  int edit_dist = -1;

  int r = strlen(first_string)+1;
  int c = strlen(second_string) +1; 

  int arr_2D[r][c];
  int diagnol =0;
  int top =0;
  int previous =0;
  char temp_str_1[3] ="";
  char temp_str_2[3] = "";

  arr_2D[0][0] = 0;   // initialize array

  //set row 0
  for ( int SL_1 = 0; SL_1 < r; SL_1++ )
  {
    arr_2D[SL_1][0] = SL_1;
  }

  //set col 0
  for (int SL_2 = 0; SL_2 < c; SL_2++ )
  {
    arr_2D[0][SL_2] = SL_2;
  }

  //initiallize 2D array by filling with zeros
  for (int rt = 1; rt < r; rt++){
	
		for(int ct=1; ct<c; ct++){
      arr_2D[rt][ct] = 0;
	
		}
  }
    //edit distance calculations
    for (int r1 = 1; r1 < r; r1++){

		for(int c1=1; c1<c; c1++){

      previous= arr_2D[r1][c1-1] +1;
      top= arr_2D[r1-1][c1] +1;

 
      temp_str_1[0]=first_string[r1-1];         //get individual char's from strings
      temp_str_2[0] = second_string[c1-1];

      if(strcmp(temp_str_1, temp_str_2))    //compare chars
      {
       diagnol = arr_2D[r1-1][c1-1]+1;      //if not equal add 1
      }
      else 
      {
        diagnol = arr_2D[r1-1][c1-1];       //if equal dont add anything
      }


      if (( diagnol <= previous) && (diagnol <= top))        //the following if-else statements compute the minimum
      {                                                      //between the previous, diagnol, and top
        arr_2D[r1][c1] = diagnol;
      }
      else if (( previous <= diagnol) && (previous <= top))
      {
        arr_2D[r1][c1] = previous;
      }
      else if (( top <= diagnol) && (top <= previous))
      {
        arr_2D[r1][c1] = top;
      }
      
      //arr_2D[r1][c1] = 0;
	
		}
  }
  edit_dist = arr_2D[r-1][c-1];  //edit distance is last value in array

  if(print_table == 1)
  {
    print_2D(r, c, arr_2D,first_string,second_string );
  }


    return edit_dist;  
}





/*
Parameters:
  - testname - string containing the name of the file with the paragraph to spell check, includes file extenssion e.g. "text1.txt" 
  - dictname - name of file with dictionary words. Includes file extenssion, e.g. "dsmall.txt"
Behavior: If any of the files cannot be open displays a message and returns. (Does not exit the program.)
*/
void spell_check(char * testname, char * dictname){
  
    // Write your code here
    int choice = 0;
    int num_words = 0;
    int ED =0;

    //int word_ED[num_words];

	  FILE *fp;
	  fp =fopen(dictname, "r");

    fscanf(fp, " %d", &num_words);	

    printf("%d\n", num_words);

    //we are given the max size of a word of 100 chars
    // char words_list [num_words][101];



   int word_ED[num_words];

  //READ IN DICTIONARY
  char **w_list = calloc(num_words, (sizeof (char*)) );
	
	for(int r = 0; r<num_words; r++){
		w_list[r] = calloc( 101, sizeof(char) );
	}

    for( int i =0; i < num_words;i++)
    {
       fscanf(fp, "%s", w_list[i]);

       //printf("%s\n", w_list[i]);
    }

    fclose(fp);

    //---------------------------------------------------------------------------------------------------------------
   //testname file handling
    int test_size =0;
    fp =fopen(testname, "r");

    fscanf(fp, " %d", &test_size);
    char current_word[101] = "";	


    for(int f =0; f < test_size; f++)
    {
      fscanf(fp, "%s", current_word);

     

      printf("-->|%s|\n", current_word);
      printf("-1 - type correction\n");
      printf(" 0 - leave word as is (do not fix spelling)\n");

      for(int h = 0; h <  num_words ; h++)
      {
        //printf("%d\n", num_words);

        ED = edit_distance(current_word, w_list[h], 0);

       // printf("%d\n", ED);

        word_ED[h] = ED;

      }



      //Here we're finding the minimum ED

	    int min = word_ED[0];
      int increment = 1;

      
      for(int c=0; c<num_words; c++){
		  if(word_ED[c] <  min){
			  min = word_ED[c];
		  } 
	    }
      
      printf("Minimum Edit Distance:%d\n", min);


      //display choices 

      
      for(int y =0; y < num_words; y++)
      {
        if(word_ED[y] == min)
        {
         printf("%d) %s\n", increment, w_list[y]);

          increment++;

       }

      }
      
      scanf("%d", &choice);


      char correct_word[101] = "";

      int count = 1;

      if(choice == -1){

        printf("Type correct word:");
        scanf(" %s", correct_word);

        printf("Correct word: %s\n", correct_word);
        
      }
      else if(choice == 0){
        printf("Correct word: %s\n", current_word);
      }
      else if(choice > 0)
      {
        for(int t =0; t < num_words; t++)
      {
        if(word_ED[t] == min)
        {
        // printf("%d) %s\n", increment, w_list[y]);

         if(count == choice)
         {
          printf("Correct word: %s\n",w_list[t] );
         }

          count++;

       }
      }


      }

      printf("\n\n");
    }


    

  //free allocated memory
    for( int w =0; w < num_words;w++)
    {
      free(w_list[w]);

    }
    free(w_list);

    fclose(fp);

    
}


