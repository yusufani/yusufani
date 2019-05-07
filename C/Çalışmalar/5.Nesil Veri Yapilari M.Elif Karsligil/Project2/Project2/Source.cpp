#include<stdio.h>
#include <stdlib.h>
void fillarray(int **arr);
int main() {
	static int * arr, i;
	fillarray(&arr);
	for ( i = 0; i < 5; i++) {
		printf("%d-", arr[i]);
	}
}
void fillarray(int **arr) {
	int i;
	arr = (int **)calloc(5, sizeof(int*));
	for ( i = 0; i < 5; i++) {
		*arr[i] = i;
	}
}