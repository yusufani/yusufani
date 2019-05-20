#include <stdio.h>
#include <stdlib.h>
#include  <string.h>
#define WORD_COUNT 2415
#define WORD_SIZE 5
typedef struct node{
	int value,comesFrom;
}NODE;
typedef struct queue {
	int value;
	struct queue* next;
}QUEUE;
void printQueue(QUEUE** );
void fillAdjacency(char** , char** );
NODE* initializeNode();
void checkAdjacency(char** , char** ,  char* ,  char* );
int findIndex(char** , char* );
void enqueu(int , QUEUE** , QUEUE** );
int dequeu(QUEUE** , QUEUE** );
int isConversion(char** , char** , char* , char* );
void printPath(char** , NODE* , int );
int main () {
	int i, j, k = 0;
	FILE* file;
	file = fopen("kelime.txt", "r");
	if ( file == NULL ) {
		printf("File can not be found ");
		exit(0);
	}
	char** words;
	words = (char**)calloc(WORD_COUNT, sizeof(char*));
	for (i = 0; i < WORD_COUNT; i++) 	words[i] = (char*)calloc(WORD_COUNT, sizeof(char));
	i = 0;
	while (!feof(file))	fscanf(file, "%s", words[i++]);
	char** adjacency;
	adjacency = (char**)calloc(WORD_COUNT, sizeof(char*));
	for (i = 0; i < WORD_COUNT; i++) 	adjacency[i] = (char*)calloc(WORD_COUNT, sizeof(char));
	fillAdjacency(words, adjacency);
	char* word1, * word2;
	word1 = (char*)malloc(WORD_SIZE * sizeof(char));
	word2 = (char*)malloc(WORD_SIZE * sizeof(char));
	int menu = 1;
	while(menu) {
		system("CLS");
		printf("Please make a choose \n1-> Check Adjaceny\n2-> Is there any Conversion\n0->EXIT\n");
		scanf("%d", &menu);
		switch (menu) {
		case 1: {
			printf("Please enter the first word:");
			scanf("%s", word1);
			printf("Please enter the first word:");
			scanf("%s", word2);
			checkAdjacency(words, adjacency, word1, word2);
			printf("The operation is completed. Press a key to continue.\n");
			system("PAUSE");
			break;
		}
		case 2 : {
			printf("Please enter the first word:");
			scanf("%s", word1);
			printf("Please enter the first word:");
			scanf("%s", word2);
			isConversion(words, adjacency, word1, word2);
			printf("The operation is completed. Press a key to continue.\n");
			system("PAUSE");
			break;
		}
		case 0: {
			menu = 0;
		}
		}
	}
}

void fillAdjacency(char** words, char** adjacency) {
	int j, k, i;
	char flag;
	for (i = 0; i < WORD_COUNT; i++) 
		for (j = i + 1; j < WORD_COUNT; j++) {
			flag = 1;
			k = 0;
			while (flag >= 0 && k < strlen(words[i]))	
				if (words[i][k++] != words[j][k])
					flag--;
			if (flag >= 0)	
				adjacency[i][j] = adjacency[j][i] = 1;
		}

}

void checkAdjacency(char** words, char** adjacency, char* str1, char* str2) {
	int i = findIndex(words,str2), k = findIndex(words, str1);
	if (adjacency[i][k] == adjacency[k][i] && adjacency[i][k] == 1) {
		printf("link found \n");
		return;
	}
	printf("link didn't find\n");
}

int isConversion(char** words, char** adjacency,char *str,char *str2 ) {
	int m = 0;
	QUEUE* root = NULL;
	QUEUE* current = NULL;
	NODE* p =initializeNode();
	p->value=findIndex(words, str);
	p->comesFrom = -1;
	int i;
	char* visited;
	visited = (char*)calloc(WORD_COUNT , sizeof(char));
	visited[p->value] = 1;
	enqueu(p->value, &root, &current);
	int step=0,v, IndexOfPath = 0, str2Index = findIndex(words, str2);
	while((v=dequeu(&root,&current)) !=-1 && v!=str2Index) { 
		printf("%d.Step = %s\n  v==%d ",++step, words[v],v);
		for(i=0;i<WORD_COUNT; i++) {
			if(adjacency[v][i]== 1  && visited[i] ==  0) {
				p = (NODE*)realloc(p,sizeof(NODE)* (++IndexOfPath+1));
				p[IndexOfPath].value = i;
				p[IndexOfPath].comesFrom = v;
				enqueu(i, &root, &current);
				visited[i] = 1;
			}
		}
		QUEUE * freeQUEUE;
		freeQUEUE = root;
		root = root->next;
		free(freeQUEUE);
	}
	if (v == str2Index) {
		printf("Fond Path in %d steps . Printing Path\n",step);
		printPath(words, p, v);
	}
	else 	printf("Didn't find path ");
	free(visited);
}
NODE* initializeNode() {
	return (NODE*)malloc(sizeof(NODE));
}
void enqueu (int value,QUEUE **root,QUEUE  ** current) {
	QUEUE* p;
	p = (QUEUE * )malloc(sizeof(QUEUE));
	p->next = NULL;
	p->value = value;
	if ((*root) == NULL) {
		*root = p;
		*current = *root;
		return;
	}
	else {
		(*current)->next = p;
		(*current) = (*current)->next;
	}
}
int dequeu(QUEUE** root, QUEUE** current) {
	if (*root == NULL) return -1;// Index can not be -1 
	int tmp = (*root)->value;

	return tmp;
}
int findIndex(char **matrix, char *str1) {
	int k = 0;
	while (k < WORD_COUNT && strcmp(matrix[k], str1)) k++;
	if (k >= WORD_COUNT) {
		printf("Word '%s'  can not be found Program Canceled", str1);
		exit(0);
	}
	return k;
}
void printQueue(QUEUE** root) {
	QUEUE* p;
	p = *root;
	printf("\nKuyruk Yazdiirliyor\n");
	if (p == NULL) printf("Kuyruk boŸ \n");
	else {
		while (p != NULL) {
			printf("%d\t", p->value);
			p = p->next;
		}
	}
}
void printPath(char ** words , NODE* p, int i) {
	if(i!=-1) {
		int k = 0;
		while(p[k].value != i ) {
			k++;
		}
		printPath(words,p, p[k].comesFrom);
		printf("%s->", words[p[k].value]);
	}	
}