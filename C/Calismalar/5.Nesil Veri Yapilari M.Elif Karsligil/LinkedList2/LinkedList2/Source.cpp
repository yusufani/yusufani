# include <stdio.h>
#include <cstdlib>

typedef struct link {
	int value;
	struct link *next;
} NODE;
void ElemanEkle();
NODE *root = NULL;
void ListeyiYazdir( );
void ElemanSil(int);
int main()
{
	int secim = 1;
	while (secim)
	{
		printf("Lutfen Secim Yapnz\n1-) Eleman ekle(Sraya Gore)\n 2-)Eleman Sil\n3->Listeyi yazdir");
		scanf("%d", &secim);
		switch (secim)
		{
		case 1: {
			ElemanEkle();
			ListeyiYazdir();
			break;
		}
		case 2:
			{
			printf("Silinecek eleman :");
			scanf("%d", &secim);
			ElemanSil(secim);
			ListeyiYazdir();
			break;
			}
		case 3:
			{
			ListeyiYazdir();
			break;
			}
		}


	}
}
void ElemanEkle()
{
	int value2;
	printf("Deger giriniz: ");
	scanf("%d", &value2);
	NODE *p;
	p = (NODE *)calloc(1, sizeof(NODE));
	p->value = value2;
	p->next = NULL;
	if (root == NULL) root = p;
	else
	{
		NODE *e;
		e = root;
		if (root->value > value2)
		{
			p->next = root;
			root = p;
		}
		else
		{
			while (e->next != NULL  && e->next->value < value2 )
			{
				e = e->next;
			}
			if (e->next != NULL) p->next = e->next;
			e->next = p;

		}
	
	}
	

}
void ListeyiYazdir()
{
	NODE  *e;
	e = root;
	printf("\n Listemiz \n");
	while(e!=NULL)
	{
		printf("-%d",e->value);
		e = e->next;
	}
	printf("\n");
}
void ElemanSil(int value2 )
{
	NODE *p;
	p = root;
	if (root->value == value2) root = root->next;
	else {
		while (p->next != NULL && p->next->value != value2) p = p->next;
		if (p->next == NULL) printf("Boyle bir eleman yok ");
		else
		{
			p ->next = p->next->next;
		}
	}
}