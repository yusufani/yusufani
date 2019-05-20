#include <stdio.h>
#include <stdlib.h>

typedef struct link
{
	int value;
	link *left, *right;
} NODE;
NODE *root;
NODE* newNode(int data);
void agaciyazdir(NODE * node, int derece);
void findingBadArray(NODE *root, int * min, int *flag);
void inorder(NODE *x);
void postorder(NODE *x);
void preorder(NODE *x);
int main()
{
	int i;
	/*root = newNode(20);
	root->left = newNode(15);
	root->right = newNode(30);
	root->left->left = newNode(10);
	root->left->right = newNode(17);
	root->right->left = newNode(25);
	root->right->right = newNode(35);
	root->left->left->left = newNode(5);
	root->left->left->right = newNode(11);
	root->left->right->left = newNode(12);
	root->left->right->right = newNode(18);
	root->right->left->left = newNode(23);
	root->right->left->right = newNode(26);
	root->right->right->left = newNode(34);
	root->right->right->right = newNode(4);*/
	root = newNode(12);
	root->left = newNode(6);
	root->right = newNode(15);
	root->left->left = newNode(3);
	root->left->right = newNode(8);
	root->right->left = newNode(13);
	root->right->right = newNode(16);
	root->right->left->left = newNode(11);
	root->right->left->right = newNode(14);
	printf("PreOrder Gezince\n");
	preorder(root);
	printf("\nInorder Gezimi\n");
	inorder(root);
	printf("\nPostOrder Gezimi\n");
	postorder(root);
	agaciyazdir(root,0);
	printf("Eleman eklemek icin 1 i Eger  agacta bozuk bir eleman varsa onu aramay istiyorsanz 2 yi tuslaynz");
	int secim;
	scanf("%d",&secim);
	if (secim==1)
	{
		int value2;
		printf("Eklemek istediginiz elemanin degeri ?");
		scanf("%d", &value2);
		NODE *p;
		p = newNode(value2);
		if (root == NULL) root = p;
		else
		{
			NODE *current;
			current = root;
			int stop = 0;
			while(!stop)
			{
				if ( current->value > value2)
				{
					if(current->left == NULL)
					{
						current->left = p;
						stop = 1;
					}
					current = current->left;
				}
				else
				{
					if(current->right == NULL)
					{
						current->right = p;
						stop = 1;
					}
					current = current->right;
				}
			}




		}
		printf("Eklenmis agac");
		agaciyazdir(root, 0);
	}
	else
	{
		int flag = 0;
		int min = -2;
		findingBadArray(root, &min, &flag);
		printf("%d", flag);

	}
	system("PAUSE");
	
}

NODE* newNode(int data)
{
	// Allocate memory for new node  
	NODE * node = (NODE *)malloc(sizeof(NODE));

	// Assign data to this node 
	node->value = data;

	// Initialize left and right children as NULL 
	node->left = NULL;
	node->right = NULL;
	return(node);
}
void agaciyazdir( NODE * node, int derece)
{
	if (node == NULL) return;
	printf("\n%d.derece = %d ", derece,node->value);
	agaciyazdir(node->left,derece+1);
	agaciyazdir(node->right,derece+1);
	
}
void compare(int * min, int value, int* flag)
{
	printf("Gelen deger : %d \n", value);
	if (*min <= value) *min =value;
	else
	{
		*flag = value;
	}
}

void findingBadArray(NODE *root,int * min,int *flag)
{
	if(*flag== 0 )
	{
		if (root == NULL) return;
		findingBadArray(root->left, min, flag);
		compare(min, root->value, flag);
		findingBadArray(root->right, min, flag);
	}

}
void inorder(NODE *x) {
	if (x != NULL) {
		inorder(x->left);
		printf("%d\n", x->value);
		inorder(x->right);
	}
}
void postorder(NODE *x) {
	if (x != NULL) {
		postorder(x->left);
		postorder(x->right);
		printf("%d\n", x->value);
	}
}
void preorder(NODE *x) {
	if (x != NULL) {
		printf("%d\n", x->value);
		preorder(x->left);
		preorder(x->right);
	}
}