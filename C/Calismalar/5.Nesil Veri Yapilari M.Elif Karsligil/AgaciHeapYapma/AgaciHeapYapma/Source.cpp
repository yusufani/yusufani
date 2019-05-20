#include <stdio.h>
#include <cstdlib>
void xchg(int [], int , int );

void printheap(int [], int );

int main ()
{
	int a[10];
	a[0] = 5;
	a[1] = 15;
	a[2] = 23;
	a[3] = 4;
	a[4] = 7;
	a[5] = 9;
	a[6] = 13;
	a[7] = 75;
	a[8] = 80;
	a[9] = 2;
	a[10] = 99;
	a[11] = 131;
	a[12] = 250;
	a[13] = 7;
	a[14] = 17;
	a[15] = 21;
	a[16] = 28;
	a[17] = 35;
	a[18] = -5;
	a[19] = -25;
	int n = 20;
	int i = 0 , j;
	printheap(a, n);
	while( 2*i+2<n)
	{
		j = i;
		while( 2*j+2<n)
		{
			if ( a[2*j+2] > a[2*j+1] )
			{
				if( a[i] < a[2*j+2]) xchg(a, i, 2 * j + 2);
			}
			else
			{
				if (a[i] < a[2 * j +1]) xchg(a, i, 2 * j + 1);
			}
			j++;
		}
		if ( 2*j +1< n)
		{
			if (a[j] < a[2 * j + 1]) xchg(a, i, 2 * j + 1);
		}
		printheap(a, n);
		i++;
	}
	system("PAUSE");
}
void xchg(int a[], int i , int j)
{
	int tmp;
	tmp = a[i];
	a[i] = a[j];
	a[j] = tmp;
}
void printheap(int a[], int n)
{
	int i = 0;
	printf("\nHeapimiz: \n");
	for (i= 0; i<n ; i++)
	{
		printf("-%d", a[i]);
	}
}
