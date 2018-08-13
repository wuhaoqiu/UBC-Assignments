#include<stdio.h>
#include <stdlib.h>

int main (void) { // runs on the host
const int n=10000000;
float *a;
a=(float*)malloc(n*sizeof(float));
for(int i=0;i<n;i++){
	a[i]=float(i)/n;
}

printf("a[%d]:%.7f\n",0,a[0]);
printf("a[%d]:%.7f\n",1,a[1]);
printf("a[%d]:%.7f\n",2,a[2]);
printf("a[%d]:%.7f\n",3,a[3]);
printf("a[%d]:%.7f\n",4,a[4]);
printf("a[%d]:%.7f\n",n-5,a[n-5]);
printf("a[%d]:%.7f\n",n-4,a[n-4]);
printf("a[%d]:%.7f\n",n-3,a[n-3]);
printf("a[%d]:%.7f\n",n-2,a[n-2]);
printf("a[%d]:%.7f\n",n-1,a[n-1]);
return 0;
}


