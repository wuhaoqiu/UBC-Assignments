// lab4_5.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<omp.h>
#include<stdlib.h>
#define N 10000

void count_sort_serial(int a[], int n);
void count_sort_parallel(int a[], int n);
int main()
{
	int a[N];
	srand(1);
	for (int i = 0;i < N;i++) {
		a[i] = (int)(rand());
	}
	double start = omp_get_wtime();
	count_sort_serial(a, N);
	double end = omp_get_wtime();
	printf("serial spent time is:%f\n", end - start);
	for (int i = 0;i < N;i++) {
		//printf("a[%d]:%d  ", i, a[i]);
	}
	srand(1);
	for (int i = 0;i < N;i++) {
		a[i] = (int)(rand());
	}
	start = omp_get_wtime();
	count_sort_parallel(a, N);
	end = omp_get_wtime();
	printf("\nparallel spent time is:%f\n", end - start);
	for (int i = 0;i < N;i++) {
		//printf("a[%d]:%d  ", i, a[i]);
	}
	return 0;
}

void count_sort_serial(int a[], int n) {
	int i, j, count;
	int* temp = (int *)malloc(n * sizeof(int));
	for (i = 0; i < n; i++) {
		count = 0;
		for (j = 0; j < n; j++)
			if (a[j] < a[i])
				count++;
			else if (a[j] == a[i] && j < i)
				count++;
		temp[count] = a[i];
	}
	memcpy(a, temp, n * sizeof(int));
	free(temp);
}

void count_sort_parallel(int a[], int n) {
	int i, j, count;
	int* temp = (int *)malloc(n * sizeof(int));//Qa:private:count,j   shared:a[],*temp
#pragma omp parallel for num_threads(8) private(count,j)
	for (i = 0; i < n; i++) {                  //Qb:no loop carried dependency because for each i, j has to be runned for N times, and although there may exist same values in a, its count can be calculated independently
		count = 0;
		for (j = 0; j < n; j++)
			if (a[j] < a[i])
				count++;
			else if (a[j] == a[i] && j < i)
				count++;
		temp[count] = a[i];
	}
	memcpy(a, temp, n * sizeof(int));
	free(temp);
}

