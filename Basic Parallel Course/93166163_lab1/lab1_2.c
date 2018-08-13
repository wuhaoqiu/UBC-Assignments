#include <stdio.h>
#include <stdlib.h>
#include<time.h>
# define SIZE 1000000

int main(void) {
	double start_t,end_t;

	int *a=(int*)malloc(SIZE*sizeof(int));
	int *b=(int*)malloc(SIZE*sizeof(int));
	int *c=(int*)malloc(SIZE*sizeof(int));

	start_t=clock();
	
	int i;
	
	if(a!=NULL||b!=NULL||c!=NULL){

	for(i=0 ;i<SIZE;i++){
		*(a+i)=i*3;
		*(b+i)=-i*3;
		*(c+i)=*(a+i)+*(b+i);
	}
}else printf("no enough memory\n");

	end_t=clock();

	printf("total time is %f",end_t-start_t);
	
	return 0;

}
