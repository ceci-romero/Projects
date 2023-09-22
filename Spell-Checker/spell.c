//Cecilia Romero
//Student ID: 1001885226


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
Student answer:  Theta(  T*D*(MAX_LEN)^2 )


/* Write helper functions here */

//The following function prints out the edit distance table

void print_2D(int num_rows, int num_cols, int arr2D[num_rows][num_cols], char * first_string, char * second_string)
{	
	int r=0;
  int c=0;


  //formatting table
  char blank = ' ' ;
	printf("%3c|", blank);
  printf("%3c|", blank);
	for (r = 0; r<num_rows; r++)  //(Max L)^2
  {
    
    if(r == 0)
    {           //print second word (column letters)
      for(c=0; c<num_cols-1; c++)      //Max L
      {
			  printf("%3c|",second_string[c]);
		  }
    

      printf("\n");

      for(int d=0 ;d< num_cols+1; d++)   //Max L
      {   // formatting
        printf("----");
      }
      printf("\n");
      printf("%3c|", blank);
    }  
  
		for(c=0; c<num_cols; c++)   //Max L
    {                              //following loops print out the values from the array
			printf("%3d|", arr2D[r][c]);
		}
		printf("\n");
    for(c=0 ;c< num_cols+1; c++)   //Max L
    {
      printf("----");
    }
    printf("\n");
    if(r<num_rows-1)
    {
      printf("%3c|", first_string[r]);   //print first word (row letters)
    }


  }
  printf("\n\n");
}


/*
Parameters:
  - first_string - pointer to the first string (displayed vertical in the table)
  - second_string - pointer to the second string (displayed horizontal in the table)
  - print_table - If 1, the table with the calculations for the edit distance will be printed.
                  If 0 (or any value other than 1)it will not print the table
Return:  the value of the edit distance
*/
int edit_distance(char * first_string, char * second_string, int print_table)  //(Max L)^2
{
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
  for ( int SL_1 = 0; SL_1 < r; SL_1++ )      //Max L
  {
    arr_2D[SL_1][0] = SL_1;
  }

  //set col 0
  for (int SL_2 = 0; SL_2 < c; SL_2++ )  //Max L
  {
    arr_2D[0][SL_2] = SL_2;
  }

  //initiallize 2D array by filling with zeros
  for (int rt = 1; rt < r; rt++)   //(Max L)^2
  {
		for(int ct=1; ct<c; ct++){    //Max L
      arr_2D[rt][ct] = 0;
	
		}
  }
  //edit distance calculations
  for (int r1 = 1; r1 < r; r1++)  //(Max L)^2
  {

		for(int c1=1; c1<c; c1++) //Max L
    {

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
void spell_check(char * testname, char * dictname)
{
  
  // Write your code here
  int choice = 0;
  int num_words = 0;
  int ED =0;

  //int word_ED[num_words];

	FILE *fp , *ft;
	fp =fopen(dictname, "r");
  ft =fopen(testname, "r");


  if ((fp == NULL) || (ft ==NULL))
  {
		printf("File could not be opened.\n");
    
	}
  else
  {

    fscanf(fp, " %d", &num_words);	
    printf("\nLoaded dictionary %s has size: %d\n\n", dictname, num_words);



    int word_ED[num_words];

    //READ IN DICTIONARY
    char **w_list = calloc(num_words, (sizeof (char*)) );
	
	  for(int r = 0; r<num_words; r++)  //D
    {
      //we are given the max size of a word of 100 chars + \0
		  w_list[r] = calloc( 101, sizeof(char) );
	  }

    for( int i =0; i < num_words;i++)  //D
    {
      fscanf(fp, "%s", w_list[i]);
    }

    fclose(fp);

    //---------------------------------------------------------------------------------------------------------------
    //testname file handling
    int test_size =0;

    fscanf(ft, " %d", &test_size);
    char current_word[101] = "";	


    for(int f =0; f < test_size; f++) // T*D*(Max L)^2
    {
      fscanf(ft, "%s", current_word);

     

      printf("---> |%s|\n", current_word);
      printf("-1 - type correction\n");
      printf(" 0 - leave word as is (do not fix spelling)\n");

      for(int h = 0; h <  num_words ; h++) //D 
      {
        //printf("%d\n", num_words);

        ED = edit_distance(current_word, w_list[h], 0);  //(Max L)^2

        // printf("%d\n", ED);

        word_ED[h] = ED;

      }



      //Here we're finding the minimum ED

	    int min = word_ED[0];
      int increment = 1;

      
      for(int c=0; c<num_words; c++)  //D
      {
		    if(word_ED[c] <  min)   
        {
			    min = word_ED[c];
		    } 
	    }
      
      printf("     Minimum distance: %d\n     Words that give minimum distance:\n", min);


      //display choices per testname word
      for(int y =0; y < num_words; y++)  //D
      {

        //if the edit distance of a word matches the minimum edit distance print the option
        if(word_ED[y] == min)
        {
          printf("%d - %s\n", increment, w_list[y]);

          increment++;

        }

      }
      

      //use chooses the correction
      printf("Enter your choice:");
      scanf("%d", &choice);


      char correct_word[101] = "";

      int count = 1;

      //decide what to do based on user choice
      if(choice == -1)
      {

        printf("Enter correct word:");
        scanf(" %s", correct_word);

        printf("The corrected word is: %s\n", correct_word);
        
      }
      else if(choice == 0) 
      {
        printf("The corrected word is: %s\n", current_word);
      }
      else if((choice > 0) && (choice < increment))
      {

        for(int t =0; t < num_words; t++) //D
        {
          if(word_ED[t] == min)
          {
            // printf("%d) %s\n", increment, w_list[y]);

            if(count == choice)
            {
              printf("The corrected word is: %s\n",w_list[t] );
            }

            count++;

          }
        }
      }
      else
      {
        printf("Invalid choice. Original word will be kept. \nThe corrected word is: %s\n", current_word);

      }

      printf("\n\n");
    }
    

    //free allocated memory
    for( int w =0; w < num_words;w++) //D
    {
      free(w_list[w]);
    }
    free(w_list);

    fclose(ft);

  }
}
