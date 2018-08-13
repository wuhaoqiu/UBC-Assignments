#include "stdafx.h"
#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include<time.h>
#define SIZE 800000

int* vecCreateOpenMp(int size, int num_thread) {
	if (size%num_thread == 0) {
		int *c = (int*)malloc(size * sizeof(int));
		if (c != NULL)
		{
			double s, e;
			s = clock();
#pragma omp parallel num_threads( num_thread )
			{
				double start, end;
				int interval = size / num_thread;
				int my_num = omp_get_thread_num();
				start = clock();
				for (int i = my_num*interval;i < my_num*interval + interval;i++)
					*(c + i) = i;
				end = clock();
				printf("%d thread spend %f\n", my_num, end - start);
			}//end of parallel
			e = clock();
			printf("overall time is %f\n", e - s);
			return c;
		}
		else printf("c has not been allocated successfully\n");
	}
	else
		return NULL;
}


	int main() {
		int *a = vecCreateOpenMp(SIZE,8);
		for (int i = 0;i < SIZE;i+=1000) {
			printf("%d ", *(a + i));
		}
		return 0;
	}
	
