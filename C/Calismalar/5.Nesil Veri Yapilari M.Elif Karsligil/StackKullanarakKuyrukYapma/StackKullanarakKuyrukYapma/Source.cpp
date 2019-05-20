#include <stdio.h>
#include <cstdlib>

typedef struct link {
	int value;
	link *left, *right;
	}NODE;

NODE* elemanolustur();

NODE *  sirayaekle(NODE *root, int value);

NODE* kuyrukyazdir(NODE*);

int main ()
{
	NODE *p;
	p = elemanolustur();
	static NODE *root;
	root=sirayaekle(root,1);
	root = kuyrukyazdir(root);
	root = sirayaekle(root, 2);
	root = kuyrukyazdir(root);
	root = sirayaekle(root, 3);
	root = kuyrukyazdir(root);
	root = sirayaekle(root, 4);		
	root = kuyrukyazdir(root);
	root = sirayaekle(root, 5);
	root=kuyrukyazdir(root);
	system("PAUSE");
}
NODE* elemanolustur()
{
	NODE * p;
	p = (NODE *)malloc(sizeof(NODE));
	return p;
}
NODE * sirayaekle(NODE *root, int value)
{
	if (root == NULL) {
		root = elemanolustur();
		root->left = root->right = root;
		root->value = value;
	}
	else
	{
		NODE * p = root;
		while( p->right != root)
		{
			p = p->right;
		}
		NODE * newnode;
		newnode = elemanolustur();
		newnode->value = value;
		p->right = newnode;
		newnode->left = p;
		newnode->right = root;
	}
	return root;
}

NODE* kuyrukyazdir(NODE* root)
{
	NODE *p = root;
	printf("Kuyruk \n");
	do
	{
		printf("|%d", p->value);
		p = p->right;
	} while (p != root);
	return root;
}
