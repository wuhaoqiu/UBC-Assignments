#include <stdio.h>
#include <stdlib.h>

int main(void) {
	int sum, count = 0;
	int a[4], i;
	double average;
	printf("pls enter four numbers\n");

	scanf("%d %d %d %d", &a[0], &a[1], &a[2], &a[3]);

	sum = a[0] + a[1] + a[2] + a[3];

	average = sum / 4;

	for (i = 0; i < 4; i++) {
		if (a[i] > average)
			count++;
	}

	printf("number of elements above average is %d", count);

	return 0;

}
