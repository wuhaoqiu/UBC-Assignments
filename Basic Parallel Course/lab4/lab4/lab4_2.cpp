// lab4_2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <omp.h>


#define RA 20 /* number of rows in A */
#define CA 30 /* number of columns in A = number of rows in B */
#define CB 10 /* number of columns in matrix B */

int main()
{
	double a[RA][CA], b[CA][CB], c[RA][CB];
	double entry = 0;
	double start = omp_get_wtime();
#pragma omp parallel num_threads(8) firstprivate(entry)
	{
# pragma omp for
		for (int i = 0;i < RA;i++)
			for (int j = 0;j < CA;j++) {
				a[i][j] = 0;
			}//assign values to matrix A
# pragma omp for
		for (int i = 0;i < CA;i++)
			for (int j = 0;j < CB;j++) {
				b[i][j] = 1;
			}//assign values to matrix A
# pragma omp for
		for (int i = 0;i < RA;i++) {
			for (int j = 0;j < CB;j++) {
				for (int k = 0;k < CA;k++) {
					entry = entry + a[i][k] * b[k][j];
				}
				c[i][j] = entry;
				printf("c[%d][%d] is %f\n", i, j, entry);
				entry = 0;
			}
		}
	}//end of parallel
	double end = omp_get_wtime();
	printf("time is %f", end - start);
    return 0;
}

