#include <stdio.h> 
#include <stdlib.h>
void BubbleSort(int * arr, int n);
void SelectionSort(int * arr, int n);
void InsertionSort(int * arr, int n);
void ShellSort(int* arr, int n);
void MergeSort(int* arr, int p, int q);
void merge(int* arr, int i, int q, int r);
int partition(int* arr, int i, int r);
void quickSort(int * arr, int l, int r);
int main() {
	int *arr, n, i;
	printf("Lutfen dizi boyutunu giriniz (Please enter the size of array):");
	scanf("%d", &n);
	arr = (int *)calloc(n, sizeof(int));
	for (i = 0; i < n; i++) {
		printf("Dizinin %d. elemanini giriniz(Enter the %d. elementh):", i + 1,i+1);
		scanf("%d", &arr[i]);
	}
	printf("Lutfen Siralama Metodunu Seciniz(Please Select a Sort Type) :\n1-Bubble Sort\n2-Selection Sort\n3-Insertion Sort\n4-Shell Sort\n5-Merge Sort\n6-Quick Sort");
	scanf("%d", &i);
	switch (i) {
		case 1: {
			BubbleSort(arr, n);
			break;
		}
		case 2: {
			SelectionSort(arr, n);
			break;
		}
		case 3: {
			InsertionSort(arr, n);
			break;
		}
		case 4: {
			ShellSort(arr, n);
			break;
		}
		case 5: {
			MergeSort(arr, 0,n-1);
			break;
		}
		case 6: {
			quickSort(arr, 0, n - 1);
			break;
		}
	}
	printf("Siralanmis Dizi\n");
	for (i = 0; i < n; i++) {
		printf("%d\t", arr[i]);
	}
	scanf("%d");
}
void BubbleSort(int * arr, int n) {
	int i = 0, j, xchg = 1, tmp;
	while (i < n - 1 && xchg) {
		xchg = 0;
		for (j = 0; j < n - 1 - i; j++) {
			if (arr[j] > arr[j + 1]) {
				tmp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = tmp;
				xchg = 1;
			}
		}
		i++;
	}

}
void SelectionSort(int * arr, int n) {
	int i, j, min, yer;
	for (i = 0; i < n - 1; i++) {
		min = arr[i];
		yer = i;
			for (j = i + 1; j < n; j++) {
				if (arr[j] < min) {
					min = arr[j];
					yer = j;
				}
			}
		arr[yer] = arr[i];
		arr[i] = min;
	}
}
void InsertionSort(int * arr, int n) {
	int i, j, min;
	for (i = 1; i < n; i++) {
		min = arr[i];
		j = i - 1;
		while (j >= 0 && min < arr[j]) {
			arr[j + 1] = arr[j];
			j--;
		}
		arr[j + 1] = min;
	}
}

void ShellSort(int* arr, int n)
{
	for (int k = n / 2; k > 0; k /= 2)
	{
		for (int i = k; i < n; i++)
		{
			int temp = arr[i];
			int j = i;
			while (j >= k && arr[j - k] > temp)
			{
				arr[j] = arr[j - k];
				j -= k;
			}
			arr[j] = temp;
		}
	}
}



void MergeSort(int* arr, int p,int r) {
	if (p < r) {
		int q = (p + r) / 2;
		MergeSort(arr, p, q);
		MergeSort(arr, q+1, r);
		merge(arr, p, q, r);
	}

}
void merge(int * arr , int p , int q ,int r) {
	int *tmp, i = p, j = q+1, k = 0;
	tmp = (int *)malloc(sizeof(int) * (r - p + 1));
	if( tmp == NULL) {
		printf("Can not create an array ");
		exit(0);
	}
	while(i<= q && j <= r) {
		if(arr[i] < arr[j]) tmp[k++] = arr[i++];
		else tmp[k++] = arr[j++];
	}
	while(i<=q) 	tmp[k++] = arr[i++];
	while( j<= r)   tmp[k++] = arr[j++];
	for (i = p,k=0; i <= r; i++,k++) arr[i] = tmp[k];
	printf("Merge islemi su sayilarla yapildi\n");
	for (i = 0; i < k; i++)printf("%d-", tmp[i]);
	printf("\n");
	free(tmp);
}

int partition(int* arr, int low, int high)
{
	int pivot = arr[high];    // pivot 
	int i = low, tmp; // Index of smaller element 
	for (int j = low; j <= high - 1; j++)
	{
		printf(" Dizi\n");
		for (int k = low; k <= high; k++) {
			printf("%d\t", arr[k]);
		}
		printf("\n");
		// If current element is smaller than or 
		// equal to pivot 
		if (arr[j] <= pivot)
		{
			printf("Degisim var \n");
			tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
			i++;    // increment index of smaller element 
		}
	}
	tmp = arr[i];
	arr[i] = arr[high];
	arr[high] = tmp;
	printf(" Sirali Dizi\n");
	for (int k = low; k <= high; k++) {
		printf("%d\t", arr[k]);
	}
	printf("\n");
	return (i );
}
void quickSort(int * arr , int l , int r) {
	if (l < r) {
		int s = partition(arr, l, r);
		quickSort(arr, l, s - 1);
		quickSort(arr, s + 1, r);
	}
}
