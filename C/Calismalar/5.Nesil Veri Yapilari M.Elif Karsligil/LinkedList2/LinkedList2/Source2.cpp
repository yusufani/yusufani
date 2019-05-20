# include <stdio.h>
void ElemanEkle(NODE *root);
typedef struct {
	int value;
	struct NODE *next;
} NODE;
NODE *root = NULL;
int main()
{
	int secim = 1;
	while (secim)
	{
		printf("Lutfen Secim Yap?n?z\n1-) Eleman ekle(S?raya Gore)\n 2-)Eleman Sil");
		scanf("%d", &secim);
		case 1: {
			ElemanEkle(root);
			break;
		}

	}
}
void ElemanEkle(NODE *root)
{
	int value;
	printf("Deger giriniz: ");
	scanf("%d", &value);
	NODE *p;
	p = (NODE *)calloc(1, sizeof(NODE));
	p->next = NULL;
}