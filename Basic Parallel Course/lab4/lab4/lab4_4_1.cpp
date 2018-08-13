// lab4_4_1.cpp : Defines the entry point for the console application.
//

// lab4_4.cpp : Defines the entry point for the console application.
//

/* File:     my_rand.c
*
* Purpose:  implement a linear congruential random number generator
*
* my_rand:  generates a random unsigned int in the range 0 - MR_MODULUS
* my_drand: generates a random double in the range 0 - 1
*
* Notes:
* 1.  The generator is taken from the Wikipedia article "Linear congruential
*     generator"
* 2.  This is *not* a very good random number generator.  However, unlike
*     the C library function random(), it *is* threadsafe:  the "state" of
*     the generator is returned in the seed_p argument.
* 3.  The value referred to by seed_p must be nonzero
* 4.  The main function is just a simple driver.
*/
#include "stdafx.h"
#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include <time.h>


#define MR_MULTIPLIER 279470273 
#define MR_INCREMENT 0
#define MR_MODULUS 4294967291U
#define MR_DIVISOR ((double) 4294967291U)


double my_drand(unsigned* seed_p);
unsigned my_rand(unsigned* seed_p);
int main(void) {
	long long number_in_circle = 0;
	//long long number_of_hit = 0;
	double estimate;
	long long number_of_tosses = 10000;


#pragma omp parallel num_threads(8)  reduction(+:number_in_circle)
	{
		unsigned num = (unsigned)(omp_get_wtime()+1);
#pragma omp for
		for (long long toss = 0; toss < number_of_tosses; toss++) {
			//unsigned thread_num = (unsigned)(omp_get_thread_num() + 1);
			//unsigned seed = my_rand(&thread_num);
			//unsigned seed2 = my_rand(&seed);
			double x = 2 * my_drand(&num) - 1;
			//printf("x is %f\n", x);
			double y = 2 * my_drand(&num) - 1;
			//printf("y is %f\n", y);
			double distance = x * x + y * y;
			if (distance <= 1) number_in_circle++;
			printf("T:%d, number of hit :%d\n", omp_get_thread_num(), number_in_circle);
		}
		//number_of_hit += number_in_circle;
	}// end of parallel
	printf("number of hit:%d\n", number_in_circle);
	estimate = 4 * number_in_circle / ((double)number_of_tosses);
	printf("estimate is : %f", estimate);
	return 0;
}


/* Function:      my_rand
* In/out arg:    seed_p
* Return value:  A new pseudo-random unsigned int in the range
*                0 - MR_MODULUS
*
* Notes:
* 1.  This is a slightly modified version of the generator in an
*     old version of the Wikipedia article "Linear congruential
*     generator"
* 2.  The seed_p argument stores the "state" for the next call to
*     the function.
* 3.  *seed_p must be nonzero
* 4.  The value of *seed_p should be set before the first call
*     to my_rand, and the value returned from one call should
*     simply be passed to the next call.
*/
unsigned my_rand(unsigned* seed_p) {
	long long z = *seed_p;
	z *= MR_MULTIPLIER;
	z %= MR_MODULUS;
	*seed_p = z;
	return *seed_p;
}

/* Function:      my_drand
* In/out arg:    seed_p
* Return value:  A new pseudo-random double in the range 0 - 1
*
* Note:          See my_rand for info about seed_p
*/
double my_drand(unsigned* seed_p) {
	unsigned x = my_rand(seed_p);
	double y = x / MR_DIVISOR;
	return y;
}