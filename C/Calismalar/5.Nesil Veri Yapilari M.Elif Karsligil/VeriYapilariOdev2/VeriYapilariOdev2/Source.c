#include <stdio.h>
#include <stdlib.h>
#include  <string.h>
typedef struct node{
	int IDNo; // Kullanýcýnýn kimli numarasýný tutan yapý
	char *name, *surname; // Kullanýcýsýn ismi ve soyismini tutan yapýlar
	int *friends; // Kullanýcýnýn arkadaslarýnýn ID numaralarýný tutan dinamik yapý
	int friendSize; // Kullanýcýsýn arkadas sayýsýný tutan yapý
	struct node *left, *right; // Kullanýcýnýn left ve right nodelarýný tutan yapý 
	}Kullanici;
Kullanici* createNewUser();
Kullanici* insertNewUser(Kullanici * binary_tree, Kullanici* );
void treeYazdir(Kullanici *root,int i);
Kullanici* contains(Kullanici* node, int TCNO);
void friends(Kullanici * root, int TcNo);
Kullanici* verileriOku(Kullanici *binaryTree);
//Kullanici* createKullanici();
void printNext( Kullanici *eleman, int i);
void printGreator(Kullanici *eleman);
void printInOrder(Kullanici* binaryTree);
Kullanici* deleteUser(Kullanici *binaryTree, int TCNO);
int size(Kullanici* node);

int main()
{
	Kullanici static *binaryTree = NULL;
	binaryTree=verileriOku(binaryTree);
	int secim = 1;
	while(secim)
	{
		system("CLS"); // Consol ekranýný temizleyen kod
		printf("1->Insert New User\n2->Delete User \n3->Contains\n4->Friends\n5->Size\n6->Print Next\n7->Print Greater\n8->Print Inorder\n0->Exit\nLutfen secim yapiniz");
		scanf("%d", &secim);
		treeYazdir(binaryTree, 0);
		switch (secim)
		{
		case 0: { break; }
		case 1:
			{
			binaryTree=insertNewUser(binaryTree, createKullanici());
			break;
			}
		case 2:
			{
			int TCNO;
			printf("Lutfen silmek istediginiz kullanicinin ID numarasini giriniz:");
			scanf("%d", &TCNO);
			binaryTree=deleteUser(binaryTree, TCNO);
			break;
			}
		case 3:
			{
			int TCNO;
			printf("Lutfen Aramak istediginiz kullanicinin ID numarasini giriniz:");
			scanf("%d", &TCNO);
			contains(binaryTree,TCNO);
			break;
			}
		case 4:
			{
			int TCNO;
			printf("Lutfen Arkadaslarini bulmak istediginiz kullanicinin ID numarasini giriniz:");
			scanf("%d", &TCNO);
			friends(binaryTree, TCNO);
			break;
			}
		case 5:
			{
			printf("Agactaki eleman sayisi= %d\n",size(binaryTree));
			break;
			}
		case 6:
			{
			int TCNO;
			printf("Lutfen agacta kendinden alltaki elemanlarn bulmak istediginiz kullanicinin ID numarasini giriniz:");
			scanf("%d", &TCNO);
			printNext(contains(binaryTree,TCNO),0);
			break;
			}
		case 7:
			{
			int TCNO;
			printf("Lutfen agacta kendinden buyuk elemanlarn bulmak istediginiz kullanicinin ID numarasini giriniz:");
			scanf("%d", &TCNO);
			printGreator(contains(binaryTree, TCNO));
			break;
			}
		case 8:
			{
			printInOrder(binaryTree);
			break;
			}
		}
	}

}

/*Kullanici* createKullanici()
{
	// Bu fonksiyon bir kullanýcýnýn tum ozelliklerini  alarak Kullanici * tipinde bir pointer olarak dondurur.
	Kullanici *p;
	int i;
	p = (Kullanici*)malloc(sizeof(Kullanici));
	if (p == NULL) {
		printf("Eleman olusturulamadi");
		exit(0);
	}
	printf("Kullanici ismi giriniz:");
	scanf("%s", p->name);
	printf("Kullanici soyismi giriniz:");
	scanf("%s", p->surname);
	printf("Kullanici TCNO veriniz:");
	scanf("%d", &p->IDNo);
	printf("Arkadas sayisini giriniz");
	scanf("%d", &p->friendSize);
	p->friends = (int *)malloc((p->friendSize) * sizeof(int));
	for(i=0;i<p->friendSize;i++)
	{
		printf("%d. arkadasin TCNO giriniz:",i+1);
		scanf("%d", &p->friends[i]);
	}
	p->left = NULL;
	p->right = NULL;
	return p;
}*/

Kullanici* insertNewUser(Kullanici* binaryTree, Kullanici* newNode)
{
	if (binaryTree == NULL) binaryTree = newNode;
	else {
		if (binaryTree->IDNo > newNode->IDNo)
		{
			if (binaryTree->left == NULL) binaryTree->left = newNode;
			else insertNewUser(binaryTree->left, newNode);
		}
		else
		{
			if (binaryTree->right == NULL) binaryTree->right = newNode;
			else insertNewUser(binaryTree->right, newNode);
		}
	}
	return binaryTree;
}

Kullanici* contains(Kullanici* node, int TCNO)
{
	Kullanici *person = NULL;
	if (node == NULL) printf("%d ID numarasina sahip  bir kullanici bulunamadi",TCNO);
	else if(node->IDNo==(TCNO))
	{
		printf("Kullanici ismi ve soyismi : %s %s\n", node->name, node->surname);
		return node;
	}
	else {
		if (node->IDNo > TCNO) person=contains(node->left, TCNO);
		else person=contains(node->right, TCNO);
	}
	return person;
}
void treeYazdir(Kullanici *root,int i)
{
	if(root != NULL)
	{
		printf("Katman %d -- TC: %d\n",i,root->IDNo);
		treeYazdir(root->left,i+1);
		treeYazdir(root->right,i+1);
	}
}
void friends(Kullanici * root,int TcNo)
{
	printf("Arkadaslari aranacak olan ");
	Kullanici * eleman= contains(root, TcNo);
	if(eleman->friendSize !=0)
	{
		int i = 0;
		while(i<eleman->friendSize)
		{
			printf("--------->%d.Arkadasin ",i+1);
			contains(root, eleman->friends[i]);
			i++;
		}
	}
	else printf("Hic arkadasi bulunamadi");
}
Kullanici* verileriOku(Kullanici *binaryTree)
{
	FILE *p;
	char buffer[150];
	int i;
	p = fopen("Input.txt", "r");
	if (p == NULL)
	{
		printf("Kaynak Dosyasi bulunamadi");
		return binaryTree;
	}
	else
	{
			char *tmp;
			while(!feof(p))
			{
				Kullanici *kullanici = createNewUser();
				fgets(buffer, 150, p);
				tmp = strtok(buffer, ",");
				kullanici->IDNo = atoi(tmp);
				tmp = strtok(NULL, " ");
				kullanici->name = (char*)malloc(strlen(tmp)*sizeof(char));
				strncpy(kullanici->name, tmp, strlen(tmp));
				kullanici->name[strlen(tmp)] = '\0'; //BUGDAN DOLAYI BOYUT AYARLAMASI
				tmp = strtok(NULL, ",");
				kullanici->surname = (char*)malloc(strlen(tmp) * sizeof(char));
				kullanici->surname[strlen(tmp)] = '\0';
				strncpy(kullanici->surname, tmp, strlen(tmp));
				tmp = strtok(NULL, "-");
				if(tmp != NULL ) // Arkadaslari varsa ekleme
				{
					kullanici->friends = (int *)malloc(strlen(tmp) * sizeof(int));
					kullanici->friends[0] = atoi(tmp);
					i = 2;
					while (NULL != (tmp = strtok(NULL, "-"))) {
						kullanici->friends = (int*)realloc(kullanici->friends, sizeof(int)*i);
						kullanici->friends[i - 1] =atoi( tmp);
						i++;
					}
					kullanici->friendSize = i - 1;
				}
				else kullanici->friendSize = 0;
				binaryTree=insertNewUser(binaryTree, kullanici);//Binary Tree'ye ekle
				fclose(p);
			}
	}
	return binaryTree;
}
Kullanici* createNewUser()
{
	Kullanici * p;
	p = (Kullanici *)malloc(sizeof(Kullanici));
	if (p == NULL) printf("Kullainiciya yer acilamadi");
	p->left = NULL;
	p->right = NULL;
	return p;
}
void printNext(Kullanici *eleman,int i)
{
	if (eleman == NULL) return;
	printNext(eleman->left,i+1);
	printNext(eleman->right,i+1);
	if(i!= 0)	printf("<%d>\t", eleman->IDNo);
}
void printGreator(Kullanici *eleman )
{
	if (eleman == NULL) return;
	printf("Isim:%s Soyisim:%s Kimlik NO:%d\n", eleman->name, eleman->surname, eleman->IDNo);
	printGreator(eleman->right);
}
void printInOrder(Kullanici* binaryTree)
{
	if (binaryTree == NULL) return;
	printInOrder(binaryTree->left);
	printf("Ism:%s\tSoyisim:%s\tKimlik No:%d\n", binaryTree->name, binaryTree->surname, binaryTree->IDNo);
	printInOrder(binaryTree->right);
}

int size(Kullanici* node)
{
	int i = 1;
	if (node == NULL) return 0;
	i += size(node->left);
	i += size(node->right);
	return i;
}
Kullanici* deleteUser(Kullanici *binaryTree,int TCNO)
{
	if (binaryTree == NULL) return;
	if (TCNO > binaryTree->IDNo)
	{
		binaryTree->right = deleteUser(binaryTree->right, TCNO);
	}
	else if (TCNO < binaryTree->IDNo)
	{
		binaryTree->left = deleteUser(binaryTree->left, TCNO);
	}
	else
	{
		if(binaryTree->right && binaryTree->left)
		{
			Kullanici *tmp = binaryTree->right;
			while (tmp->left->left != NULL) tmp = tmp->left;
			binaryTree->IDNo = tmp->left->IDNo;
			free(tmp->left);
			tmp->left = NULL;
		}
		else
		{
			if (binaryTree->right != NULL)
			{
				binaryTree->IDNo = binaryTree->right->IDNo;
				free(binaryTree->right);
				binaryTree->right = NULL;
			}
			else if (binaryTree->left != NULL)
			{
				binaryTree->IDNo = binaryTree->left->IDNo;
				free(binaryTree->left);
				binaryTree->left = NULL;
			}
			else {
				free(binaryTree);
				binaryTree = NULL;
			}
		}
	}
	return binaryTree;
}