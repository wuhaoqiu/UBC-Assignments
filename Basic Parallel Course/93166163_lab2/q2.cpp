#include <stdio.h>
#include <stdlib.h>
#include<time.h>
#define SIZE 10000

int* addVec2(int* A, int* B, int size){
	int* c;
	c=(int*)malloc(size*sizeof(int));
	int*d=c;
	//
	for(int i=0;i<size;i++){
		*(d+i)=*(A+i)+*(B+i);
	}
	if(c==NULL){
		return NULL;
	}else return c;
}

int main(){
	double start_t,end_t;
	int size=100000;
	int* a=(int*)calloc(SIZE,sizeof(int));
	int* b=(int*)calloc(SIZE,sizeof(int));
	int* c;
	if(a!=NULL||b!=NULL){
	 c=addVec2(a,b,size);
	 if(c==NULL){
	 	printf("c has not allocated successfully");
	 }	
	 else{
	 	start_t=clock();
	 	for(int i=0;i<10;i++){
	 		printf("%d ",*(c+i));
		 }//end of inner for
		 end_t=clock();
		 printf("\nspent time is %f",end_t-start_t);
	 }//end of else
	}else printf("not enough memory");	
}

