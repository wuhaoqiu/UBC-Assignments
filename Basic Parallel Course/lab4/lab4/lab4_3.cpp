// lab4_3.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<omp.h>
#include <stdio.h>
#include <stdlib.h>
# define N 30
#define num_thread 4

int main()
{	
	
	int* a = (int*)calloc(N, sizeof(int));
	*(a+0) = 0;
#pragma omp parallel for num_threads(num_thread)
	for (int i = 1; i < N; i++) {
		for (int j = 1;j < i+1;j++) {
			a[i] += j;
		}
		printf("T:%d,num %d :%d\n", omp_get_thread_num(), i, a[i]);
	}
	// verify parallel algorithm
	for(int i=1;i<N;i++)
		{
			a[i] = a[i - 1] + i;
			printf("\nT:%d,num %d :%d\n", omp_get_thread_num(), i, a[i]);
		}
	return 0;
}

