#include<stdio.h>
#include <stdlib.h>
void printArrays(int* key, int* lock, int i);
int isExit(int number, int* array, int n);
void quickSort(int* , int , int);
void printArray(int * array, int n);
int*  createKeysandLocks(int * , int );
void xchg(int *,int,int );
void quickSort(int*, int* ,int , int , int);
int searchIndex(int* key, int i, int pivot);
int main() {
	int  *Lock=NULL, *Key=NULL, randomKey,n;
	printf("Please enter the number of keys:");
	scanf("%d", &n); // N is the size of Arrays
	printf("!Please enter the Key Array\n");
	Key=createKeysandLocks(Key,n); // Creating Key Array with user console input 
	printf("\n!Please enter the Lock Array\n");
	Lock= createKeysandLocks(Lock, n);// Creating Lock Array with user console input
	printf("Initial state of arrays\n");
	printArrays(Key, Lock, n);
	printf("*************************************\n");
	quickSort(Key,Lock,0,n-1,n);
	printf("*************************************\nLast state of arrays\n ");
	printArrays(Key, Lock, n);
	system("PAUSE");
}
int * createKeysandLocks(int *array, int n) {
	array= (int *)calloc(n,  sizeof(int));
	int i;
	for(i=0;i<n; i++ ) {
		printf("Please enter the %d.Elementh:\n", i + 1);
		scanf("%d",&array[i]);
	}
	return array;
}
void xchg(int *Array, int i1, int i2) {
	int tmp = Array[i1];
	Array[i1] = Array[i2];
	Array[i2] = tmp;
}
int partition(int* Key,int * Lock,int l, int r, int n) {
	int pivot = rand() % (r - l+1)+l;
	printf("Chosen index in Key Array  i = %d. Value of key[i]= %d \n", pivot, Key[pivot]);
	xchg(Key, r, pivot);
	xchg(Lock, r, searchIndex(Lock, n, Key[r]));
	pivot = Lock[r];
	int i, k = l;
	for (i = l; i < r; i++) {
		if (pivot >= Lock[i]) {
			xchg(Lock, i, k);
			k++;
		}
	}
	xchg(Lock, k, r);
	k = l;
	pivot = Key[r];
	for(i=l;i<r;i++) {
		if(pivot>=Key[i]) {
			xchg(Key, i, k);
			k++;
		}
	}
	xchg(Key, k, r);
	printf("New Arrays \n");
	printArrays(Key, Lock, n);
	return k;
}
void printArray(int * array , int n) {
	int i;
	for (i = 0; i < n; i++) printf("%d\t", array[i]);
}

void quickSort(int*Key,int *Lock, int l, int r,int n) {
	printf("Left : %d Right: %d \n", l, r);
	if (r > l) {
		int s = partition(Key,Lock, l, r,n);
		printf("\nPartition return value S = %d\n", s);
		quickSort(Key,Lock, l, s - 1,n);
		quickSort(Key, Lock, s + 1, r,n);
	}
}
int searchIndex(int* Lock, int n, int value) {
	int i = 0, flag = 1;
	while (flag && i < n) { 
		if (Lock[i] == value) flag = 0; 
		i++;
	}
	return i-1;
}
void printArrays(int* Key, int* Lock, int n) {
	printf("\n\tKey Array\n");
	printArray(Key, n);
	printf("\n\tLock Array\n");
	printArray(Lock, n);
	printf("\n");
}