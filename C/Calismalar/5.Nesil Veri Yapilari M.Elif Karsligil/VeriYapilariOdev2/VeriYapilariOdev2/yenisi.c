#include <stdio.h>
#include <stdlib.h>
#include  <string.h>

typedef struct node {
	int IDNo;
	char *name, *surname;
	int* friends;
	int friendSize;
	struct node *left, *right;
} Kullanici;

Kullanici* createNewUser();
Kullanici* insertNewUser(Kullanici* , Kullanici*);
Kullanici* contains(Kullanici* , int );
void friends(Kullanici* , int );
Kullanici* verileriOku(Kullanici* , FILE* );
void printNext(Kullanici* , int );
void printGreator(Kullanici* , int);
void printInOrder(Kullanici* );
Kullanici* deleteUser(Kullanici*, int );
int size(Kullanici* );
void deleteIDFromFriends(Kullanici* , int);
void bilgileriKopyala(Kullanici*, Kullanici* );

int main() {
	FILE* p = NULL; // Okunacak dosyanýn FIlE Pointerý
	Kullanici static* binaryTree = NULL; // Binary Tree icin root noktasý
	char secim = 1;
	while (secim) {  // Menu sistemi
		printf("\n1->Insert New User\n2->Delete User\n3->Contains\n4->Friends\n5->Size\n6->Print Next\n7->Print Greater\n8->Print Inorder\n0->Exit\nLutfen secim yapiniz");
		scanf("%d", &secim);
		system("CLS"); // Consol ekranini Temizler
		switch (secim) {
		case 0: { // Cikis
			fclose(p);
			break;
		}
		case 1: { // Insert New User
			if (p == NULL) p = fopen("Input.txt", "r"); // Eger dosya acilmamissa acmaya calis
			if (p == NULL) { // Halen acilmadiysa bir sorun vardir.Cik
				printf("Kaynak Dosyasi bulunamadi");
				exit(0);
			}
			binaryTree = verileriOku(binaryTree, p); // InsertNewUser fonksiyonun icinde cagrilmaktadir.
			break;
		}
		case 2: {// Delete User
			int TCNO;
			printf("Lutfen silmek istediginiz kullanicinin ID numarasini giriniz:");
			scanf("%d", &TCNO);
			binaryTree = deleteUser(binaryTree, TCNO); //Kullaniciyi binary treeden siler
			deleteIDFromFriends(binaryTree, TCNO); // Kullanicinin numarasini diger kullanicilarin friends dizisinden siler
			break;
		}
		case 3: { // Contains
			int TCNO;
			printf("Lutfen Aramak istediginiz kullanicinin ID numarasini giriniz:");
			scanf("%d", &TCNO);
			contains(binaryTree, TCNO); // Agacta verilmis id numarali kullanicinin olup olmadigini arar.
			break;
		}
		case 4: { // friends
			int TCNO;
			printf("Lutfen Arkadaslarini bulmak istediginiz kullanicinin ID numarasini giriniz:");
			scanf("%d", &TCNO);
			friends(binaryTree, TCNO); // idsi verilmis kullanicinin arkadaslarini ekrana yazdirir.
			break;
		}
		case 5: { // size
			printf("Agactaki eleman sayisi= %d\n", size(binaryTree)); // Agactaki kullanici sayisini bulur
			break;
		}
		case 6: { // Print Next
			int TCNO;
			printf("Lutfen agacta kendinden alltaki elemanlarini bulmak istediginiz kullanicinin ID numarasini giriniz:");
			scanf("%d", &TCNO);
			printNext(contains(binaryTree, TCNO), 0); // Kendinden asagidaki kullanicilarin idlerini yazdirir.
			break;
		}
		case 7: { // Print Greater 
			int TCNO;
			printf(	"Lutfen agacta kendinden buyuk elemanlarini bulmak istediginiz kullanicinin ID numarasini giriniz:");
			scanf("%d", &TCNO);
			printGreator(binaryTree, TCNO); // Verilmis id numarasindan buyuk id'li kullanicilari bulur.
			break;
		}
		case 8: {//Print InOrder
			printInOrder(binaryTree); // InOrder seklinde agaci dolasir.
		}
		}
	}

}

Kullanici* insertNewUser(Kullanici* binaryTree, Kullanici* newNode) { // Verilmis kullaniciyi agaca ekler.Root degerini geri dondurur.
	if (binaryTree == NULL) binaryTree = newNode;
	else {
		if (binaryTree->IDNo > newNode->IDNo) {
			if (binaryTree->left == NULL) binaryTree->left = newNode;
			else insertNewUser(binaryTree->left, newNode);
		}
		else {
			if (binaryTree->right == NULL) binaryTree->right = newNode;
			else insertNewUser(binaryTree->right, newNode);
		}
	}
	return binaryTree;
}

Kullanici* contains(Kullanici* node, int TCNO) { // ID'si verilmis bir kullaniciyi varsa Kullanici * tipinde dondurur.
	Kullanici* person = NULL;
	if (node == NULL) printf("%d ID numarasina sahip  bir kullanici bulunamadi", TCNO);
	else if (node->IDNo == (TCNO)) {
		printf(" Kullanici ismi ve soyismi : %s %s\n", node->name, node->surname);
		return node;
	}
	else {
		if (node->IDNo > TCNO) person = contains(node->left, TCNO);
		else person = contains(node->right, TCNO);
	}
	return person;
}

void friends(Kullanici* root, int TcNo) { // Verilmis id numarasina gore kullanicilarin arkadaslarini bulur ve yazdirir.
	printf("Arkadaslari aranacak olan ");
	Kullanici* eleman = contains(root, TcNo); // Kullaniciyi buluyor ve aliyor.
	if (eleman != NULL) {// Eger boyle bir kullanici yoksa contains fonksiyonu hatayi ekrana yazar.
		if (eleman->friendSize != 0) { // Arkadaslarini yazdirir.
			int i = 0;
			while (i < eleman->friendSize) {
				printf("--------->%d.Arkadasin ", i + 1);
				contains(root, eleman->friends[i]);
				i++;
			}
		}
		else printf("Hic arkadasi bulunamadi");
	}
}

Kullanici* verileriOku(Kullanici* binaryTree, FILE* p) { // Verilmis input dosyasindan 1 adet kullanici okur ve onu InserNewUser fonksiyonu ile agaca ekler.
	char buffer[150];
	int i;
	char* tmp;
	if (!feof(p)) { // Dosya sonuna geldiyse okuma
		Kullanici* kullanici = createNewUser(); // bir adet bos kullanici * tipinde eleman uretir.
		fgets(buffer, 150, p); // Bir satir eleman alir .
		if (strlen(buffer) > 8 && buffer[0] != -52) { // 8 elemandan dusuk anlamli veri olamaz veya -52 karakteri ile baslayan bir sayi olamaz.
			tmp = strtok(buffer, ",");// ',' gordugun yere kadar devam et ve geri kalan kismi dondur.
			kullanici->IDNo = atoi(tmp);// Stringi inte cevir
			tmp = strtok(NULL, " ");// Isimi almak icin bosluk gorene kadar devam et .
			kullanici->name = (char*)malloc(strlen(tmp) * sizeof(char));// Isým icin gerekli yeri ac
			strncpy(kullanici->name, tmp, strlen(tmp)); // ismi ekle
			kullanici->name[strlen(tmp)] = '\0'; //Son elemana string sonu ifadesi eklendi.
			tmp = strtok(NULL, ","); // Soyisim icin ',' gorene kadar al.
			kullanici->surname = (char*)malloc(strlen(tmp) * sizeof(char)); // Soy isim icin gerekli yeri ac
			kullanici->surname[strlen(tmp)] = '\0'; 
			strncpy(kullanici->surname, tmp, strlen(tmp));
			tmp = strtok(NULL, "-"); // Arkadaslari varsa 1 tane id dondurur.
			if (tmp != NULL) // Arkadaslari varsa ekleme yap
			{
				kullanici->friends = (int *)malloc(strlen(tmp) * sizeof(int));
				kullanici->friends[0] = atoi(tmp); // Ilk arkadasi ekle
				i = 2;
				while (NULL != (tmp = strtok(NULL, "-"))) { // Sirasiyla arkadaslari ekle
					kullanici->friends = (int*)realloc(kullanici->friends, sizeof(int) * i);
					kullanici->friends[i - 1] = atoi(tmp);
					i++;
				}
				kullanici->friendSize = i - 1; // Friends size'ý ayarla
			}
			else { // Eger tmp nullse arkadasi yoktur demektir.
				kullanici->friendSize = 0;
				if(kullanici->surname[strlen(kullanici->surname) - 1] == '\n')// Bu durumda soyisminin sonuna alt satira gecme karakteri olabilir.
				kullanici->surname[strlen(kullanici->surname) - 1] = '\0';// Onu kaldiriyoruz.
			}
			binaryTree = insertNewUser(binaryTree, kullanici); //Binary Tree'ye ekle
		}
	}
	return binaryTree;
}

Kullanici* createNewUser() { // 1 adet bos Kullanici * tipinde kullanici dondurur.
	Kullanici* p;
	p = (Kullanici *)malloc(sizeof(Kullanici));
	if (p == NULL) printf("Kullaniciya yer acilamadi");
	p->left = NULL;
	p->right = NULL;
	return p;
}

void printNext(Kullanici* eleman, char i) { //  Agacta Verilmis elemanin altindaki kullanicilari yazdirir.
	if (eleman == NULL) return;
	printNext(eleman->left, i + 1);
	if (i != 0) printf("<%d>\t", eleman->IDNo); // Eger eleman kendisi degilse yazdir.
	printNext(eleman->right, i + 1);
}

void printGreator(Kullanici* eleman, int TCNO) { // Verilmis id'den buyuk olan idleri yazdirir.
	if (eleman == NULL) return;
	printGreator(eleman->right, TCNO);
	if (eleman->IDNo <= TCNO) return; // Eger küçükse soluna bakmaya gerek yok 
	printf("Isim:%s Soyisim:%s Kimlik NO:%d\n", eleman->name, eleman->surname, eleman->IDNo);
	printGreator(eleman->left, TCNO);

}

void printInOrder(Kullanici* binaryTree) { // Agaci InOrder seklinde gezer 
	if (binaryTree == NULL) return;
	printInOrder(binaryTree->left);
	printf("Isim:%s\tSoyisim:%s\tKimlik No:%d\n", binaryTree->name, binaryTree->surname, binaryTree->IDNo);
	printInOrder(binaryTree->right);
}

int size(Kullanici* node) { // Agactaki her yeri gezerek her gezdigi yer icin indisi 1 arttirir.
	int i = 1;
	if (node == NULL) return 0;
	i += size(node->left);
	i += size(node->right);
	return i;
}

Kullanici* deleteUser(Kullanici* binaryTree, int TCNO) { // Agactan eleman siler.
	if (binaryTree == NULL) return;
	if (TCNO > binaryTree->IDNo) { // Eger aradigimiz eleman buyukse agacta saga dogru git.
		binaryTree->right = deleteUser(binaryTree->right, TCNO);
	}
	else if (TCNO < binaryTree->IDNo) {// Kucukse sola dogru git 
		binaryTree->left = deleteUser(binaryTree->left, TCNO);
	}
	else { // Eger esitse 
		if (binaryTree->right && binaryTree->left) { // Silinmek istenen kullanicinin 2 tane node'u da doluysa 
			Kullanici* tmp = binaryTree->right; 
			while (tmp->left->left != NULL) tmp = tmp->left; // Silinecek elemandan buyuk en kucuk elemani tmp->left'inde tut.
			bilgileriKopyala(binaryTree, tmp->left); // Tmp->leftindeki gerekli bilgileri silinecek yere kopyala
			free(tmp->left); // artik tmp->leftini silebiliriz.
			tmp->left = NULL;
		}
		else {// 2 Node'da dolu degilse 3 ihtimal olabilir.

			if (binaryTree->right != NULL) { // Saginda 1 eleman varsa 
				bilgileriKopyala(binaryTree, binaryTree->right); //Sagindaki elemani silinecek adrese kopyala.
				Kullanici* p = binaryTree->right; // Bellekten silmek uzere p degiskeninde sakla.
				binaryTree->left = p->left; // Artik silinecek eleman yerine gecicek elemanini right ve leftini ayarlamak lazim.
				binaryTree->right = p->right;
				free(p);
			}
			else if (binaryTree->left != NULL) {// Solunda 1 eleman varsa 
				bilgileriKopyala(binaryTree, binaryTree->left);//Solundaki elemani silinecek adrese kopyala.
				Kullanici* p = binaryTree->left;// Bellekten silmek uzere p degiskeninde sakla.
				binaryTree->left = p->left;// Artik silinecek eleman yerine gecicek elemanini right ve leftini ayarlamak lazim.
				binaryTree->right = p->right;
				free(p);
			}
			else { // Her iki node'u da bossa herhangi bir sikinti yoktur.
				free(binaryTree);
				binaryTree = NULL;
			}
		}

	}
	return binaryTree; // Herhangi bir root degisikligine gore geri dondur.
}

void deleteIDFromFriends(Kullanici* node, int tcno) {	//Bu fonksiyon Verilen ID numarasýný diger kullanýcýlarýn friends listesinden siler.
	if (node == NULL) return;
	int i = 0;
	while (i < node->friendSize && node->friends[i] != tcno) i++; // ID esit olana kadar aramaya devam et 
	if (i != node->friendSize) { // Eger son elemana gelinmemisse 
		while (i < node->friendSize - 1) { // Elemanlari kaydir.
			node->friends[i] = node->friends[i + 1];
			i++;
		}
		node->friendSize--; // Arkadas sayisini 1 azalt 
		node->friends = (int*)realloc(node->friends, sizeof(int) * (node->friendSize)); // Bellekte arkadas dizisini 1  kucult
	}
	deleteIDFromFriends(node->left, tcno);
	deleteIDFromFriends(node->right, tcno);
}

void bilgileriKopyala(Kullanici* destination, Kullanici* source) {// Source node'undaki bazý bilgileri Destinationa kopyalar.
	destination->IDNo = source->IDNo;
	strcpy(destination->name, source->name);
	strcpy(destination->surname, source->surname);
	destination->friends = source->friends;
	destination->friendSize = source->friendSize;
}
