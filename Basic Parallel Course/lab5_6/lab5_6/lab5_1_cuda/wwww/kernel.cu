
#include "cuda_runtime.h"
#include "device_launch_parameters.h"

#include <stdio.h>
#include <stdlib.h>

#define N 10000000

__global__ void addKernel(float *a, int n)
{
	int i = blockIdx.x*blockDim.x + threadIdx.x;
	a[i] = (float)i / n;
}

int main()
{
	float* a;

	float* d_a;

	a = (float*)malloc(N * sizeof(float));

	cudaMalloc(&d_a, N * sizeof(float));

	cudaMemcpy(d_a, a, N * sizeof(float), cudaMemcpyHostToDevice);

	int numBlock = N / 1000;

	addKernel <<<numBlock, 1000 >>> (d_a, N);

	printf("a[%d]:%.7f\n", 0, a[0]);
	printf("a[%d]:%.7f\n", 1, a[1]);

	cudaMemcpy(a, d_a, N * sizeof(float), cudaMemcpyDeviceToHost);

	int n = N;

	printf("a[%d]:%.7f\n", 0, a[0]);
	printf("a[%d]:%.7f\n", 1, a[1]);
	printf("a[%d]:%.7f\n", 2, a[2]);
	printf("a[%d]:%.7f\n", 3, a[3]);
	printf("a[%d]:%.7f\n", 4, a[4]);
	printf("a[%d]:%.7f\n", n - 5, a[n - 5]);
	printf("a[%d]:%.7f\n", n - 4, a[n - 4]);
	printf("a[%d]:%.7f\n", n - 3, a[n - 3]);
	printf("a[%d]:%.7f\n", n - 2, a[n - 2]);
	printf("a[%d]:%.7f\n", n - 1, a[n - 1]);

	free(a);

	cudaFree(d_a);

	return 0;
}