#include <stdio.h>
#include <stdlib.h>
void insertHeap(int  [] , int *, int);
void showHeap(int HT[], int *n);
int findSmallerChild(int HT[], int i, int n);
int removeMin(int HT[], int *n);
void HeapSort(int HT[], int * n);
int main() {
	int *HT;
	HT = (int *)calloc(20 , sizeof(int));
	int n = 0;
	insertHeap(HT, &n,11);
 	showHeap(HT,&n);
	insertHeap(HT, &n, 9);
	printf("\n");
	showHeap(HT, &n);
	insertHeap(HT, &n, 12);
	printf("\n");
	showHeap(HT, &n);
	insertHeap(HT, &n, 13);
	printf("\n");
	showHeap(HT, &n);
	insertHeap(HT, &n, 4);
	printf("\n");
	showHeap(HT, &n);
	removeMin(HT,&n);
	printf("\n");
	showHeap(HT, &n);
	removeMin(HT, &n);
	printf("\n");
	showHeap(HT, &n);
	removeMin(HT, &n);
	printf("\n");
	showHeap(HT, &n);
	printf("\nMin hesap \n");
	insertHeap(HT, &n, 6);
	insertHeap(HT, &n, 11);
	insertHeap(HT, &n, 16);
	insertHeap(HT, &n, 18);
	showHeap(HT, &n);
	printf("\n \n");
	HeapSort(HT, &n);
	system("PAUSE");

}
void insertHeap(int HT [], int *n, int value) {
	HT[*n] = value;
	int i, parent;
	i = (*n);
	parent = (i - 1) / 2;
	while (i >= 1 && (HT[parent] > HT[i])) {
		int tmp = HT[parent];
		HT[parent] = HT[i];
		HT[i] = tmp;
		i = parent;
		parent = (i - 1) / 2;
	}
	(*n)++;
}
void showHeap(int HT[],int *n ) {
	int i;
	for (i = 0; i < *n; i++) {
		printf("-%d", HT[i]);
	}
}
int removeMin(int HT[], int *n) {
	int min = HT[0];
	int i=0, adr, tmp;
	(*n)--;
	HT[0] = HT[*n];
	HT[*n] = min;
	adr = findSmallerChild(HT, i, *n);
	while (adr && HT[i] > HT[adr]) {
		tmp = HT[i];
		HT[i] = HT[adr];
		HT[adr] = tmp;
		i = adr;
		adr = findSmallerChild(HT, i, *n);
	}
	return min;
}
int findSmallerChild(int HT[], int i, int n) {
	if (2 * i + 2 < n) {
		if (HT[2 * i + 2] < HT[2 * i + 1]) return 2 * i + 2;
		else return 2 * i + 1;
	}
	else if (2 * i + 1 < n) return 2 * i + 1;
	else return 0;
}
void HeapSort(int HT[], int  * n) {
	int size = *n;
	int i;
	for (i = 0; i < size; i++) {
		printf("- %d ",removeMin(HT, n));
	}
}