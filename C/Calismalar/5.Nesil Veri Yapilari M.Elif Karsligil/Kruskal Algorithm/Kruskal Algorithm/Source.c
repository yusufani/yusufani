#include <stdio.h>
#include <stdlib.h>

typedef struct node{
	int u,v,w; // w is the edge of 2 two nodes
	}NODE;
int partition(NODE* node, int l, int r);
void sortGraphs(NODE* node,int );
void qSort(NODE* node, int i, int elements);
int main () {
	/*printf("Please enter the number of vertexs");
	int n;
	scanf("%d", &n);
	printf("Please enter the number of edges");
	int m;
	scanf("%d", &m);
	int i;
	NODE* graph,*mst;
	graph = (NODE*)malloc(m * sizeof(NODE));
	mst = (NODE*)malloc(m * sizeof(NODE));
	for(i=0; i<m ; i ++ ) {
		printf("Please enter the first node number ");
		scanf("%d", &graph[i].v);
		printf("Please enter the second node number");
		scanf("%d", &graph[i].u);
		printf("Please enter the edge between two nodes");
		scanf("%d", &graph[i].w);
	}*/
	printf("Please enter the number of vertexs");
	int n = 7;
	printf("Please enter the number of edges");
	int m = 11;
	int i;
	NODE* graph, * mst;
	graph = (NODE*)malloc(m * sizeof(NODE));
	mst = (NODE*)malloc(m * sizeof(NODE));

	graph[0].u = 3;
		graph[0].v = 4;
	graph[0].w = 15;
		graph[1].u = 5;
		graph[1].v = 6;
		graph[1].w = 11;
		graph[2].u = 4;
		graph[2].v = 6;
		graph[2].w = 9;;
		graph[3].u = 1;
		graph[3].v = 3;
		graph[3].w = 9;
		graph[4].u = 4;
		graph[4].v = 5;
		graph[4].w = 8;
		graph[5].u = 1;
		graph[5].v = 2;
		graph[5].w = 8;
	graph[6].u = 1;
		graph[6].v = 4;
		graph[6].w = 7;
		graph[7].u = 0;
		graph[7].v = 1;
		graph[7].w = 7;
	graph[8].u = 3;
		graph[8].v = 5;
		graph[8].w = 6;
		graph[9].u = 2;
	graph[9].v = 4;
	graph[9].w = 5;
	graph[10].u = 0;
	graph[10].v = 3;
	graph[10].w = 5;
	sortGraphs(graph,m);
	int* label;
	label=(int*)calloc(n, sizeof(int));
	i = 0;
	int u, w, v, j = 0, labelNo = 0, totalWeight = 0;
	while(i<n-1) {
		printf("\n\n");
		u = graph[j].u;
		v = graph[j].v;
		w = graph[j].w;
		if(label[u] + label[v] == 0) { // Two nodes are new node
			label[u] = label[v] = ++labelNo;
			mst[i].u = u;
			mst[i].v = v;
			mst[i++].w = w;
			totalWeight += w;
			j++;
			printf("Linked %d  and %d nodes. Edge is : %d . Total Weight = %d \n", u, v, w, totalWeight);
		}
		else { // At least one node is old node 
			if (label[u] == label[v]) {
				j++; // Two nodes are old 
				printf("Cycle Danger between %d , %d \n", u, v);
			}
			else {// Different nodes from each other 
				mst[i].u = u;
				mst[v].v = v;
				mst[w].w = w;
				totalWeight += w;
				i++;
				if (label[u] == 0) { // If u never visited  meaning u is new 
					printf("%d is the new node.\n",u);
					label[u] = label[v];
				}
				else {
					if (label[v] == 0) { // If v never visited  meaning u is new 
						printf("%d is the new node.\n", v);
						label[v] = label[u];
					}
					else { // two nodes are visited , i will give a same label in graph which has label u or v 
						printf("Two graph making connected\n");
						int k;
						int tmp = label[v];
						for ( k=0; k<n; k++) {
							if ( label [k] == tmp) { 
								label[k] = label[u];
							}
						}
					}
				}
				j++;
				printf("Linked %d  and %d nodes. Edge is : %d . Total Weight = %d \n", u, v, w, totalWeight);
			}
			
		}
		printf("Printing Label\n");
		int k;
		for ( k=0;k<n;k++) {
			printf("%d\t", label[k]);
		}
		printf("\n");
	}
}

int partition(NODE* node, int l, int r) {
	int tmp;
	int pivot = node[r].w;
	int i;
	int k = l;
	for ( i=l;i<r;i++) {
		if(pivot > node[i].w) {
			tmp = node[i].w;
			node[i].w = node[k].w;
			node[k].w = tmp;

			tmp = node[i].v;
			node[i].v = node[k].v;
			node[k].v = tmp;

			tmp = node[i].u;
			node[i].u = node[k].u;
			node[k].u= tmp;
			k++;
		}
	}
	tmp = node[k].w;
	node[k].w = node[r].w;
	node[r].w = tmp;

	tmp = node[k].v;
	node[k].v = node[r].v;
	node[r].v = tmp;

	tmp = node[k].u;
	node[k].u = node[r].u;
	node[r].u = tmp;
	return k;
}

void qSort(NODE* graph, int l, int r) {
	if(r>l) {
		int s = partition(graph, l, r);
		qSort(graph, l, s - 1);
		qSort(graph, s + 1, r);
	}
}

void sortGraphs(NODE* graph,int n) {
	qSort(graph, 0, n - 1);
	printf("Printing Edges ");
	int i;
	for (i = 0; i < n; i++) {
		printf("%d\n", graph[i].w);
	}
}
