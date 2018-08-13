#include <stdio.h>
#include <stdlib.h>
#include<time.h>
# define SIZE 1000
int* vecCreate(int size){
	int* a=(int*)(malloc(size*sizeof(int)));
	if(a==NULL){
		return NULL;
	}else{
	 for(int i=0;i<size;i++){
		*(a+i)=i;
	}
	return a;
}//end of else
}
int main(){
	int *a=vecCreate(SIZE);
	for(int i=0;i<10;i++){
		printf("%d ",*(a+i));
	}
}
