#include <stdio.h>
#include <stdlib.h>
#include<time.h>
#define SIZE 10000
void addVec(int* C, int* A, int* B, int size){
	for(int i=0;i<size;i++){
		*(C+i)=*(A+i)+*(B+i);
	}
}

int main(){
	double start_t,end_t;
	int* a=(int*)calloc(SIZE,sizeof(int));
	int* b=(int*)calloc(SIZE,sizeof(int));
	int* c=(int*)calloc(SIZE,sizeof(int));
	if(a!=NULL||b!=NULL||c!=NULL){
		addVec(c,a,b,SIZE);
	}else printf("not enough memory");
	start_t=clock();
	for(int i=0;i<10;i++){
		printf("%d ",*(c+i));
	}
	end_t=clock();
	printf("spent time is %f",end_t-start_t);
	return 0;
}



