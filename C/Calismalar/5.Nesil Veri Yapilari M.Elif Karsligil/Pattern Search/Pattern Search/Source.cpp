#include <stdio.h>
#include <stdlib.h>
#include <cstring>
int * harspool(char [] );

int main ()
{
	char sentence[] = { "FINDINGAHAYSTACKNEEDLEINA" };
	char pattern[] = { "NEEDLE" };
	int *alfabe=harspool(pattern);
	int i, n, m, skip, flag = 1, j;
	n = strlen(sentence);
	m = strlen(pattern);
	i = 0;
	while (i<(n-m)  && flag )
	{
		j = m - 1;
			
		while(pattern[j] == sentence[i+j])
		{
			j--;
		}
		if ( j>= 0  )
		{
			skip = j - alfabe[sentence[i + j] - 'A'];
			if (skip< 0 )
			{
				i++;
			}
			else i += skip;
		}
		else flag = 0;
	}
	printf(" FOUND=%d" ,flag);
	system("PAUSE");

}
int * harspool(char pattern [])
{
	int i = 0;
	int n = strlen(pattern);
	int alfabe[29];
		for (i = 0; i < 28; i++) alfabe[i] = -1;
	for ( i=0; i<n;i++)
	{
		alfabe[(pattern[i] - 'A')] = i;
	}
	return alfabe;
}
