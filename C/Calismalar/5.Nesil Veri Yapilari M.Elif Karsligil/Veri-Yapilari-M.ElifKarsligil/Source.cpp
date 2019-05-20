#include <stdio.h>
int pop();
void push(int x);
int isEmpty();
int recursive(int * flag);
int a[10], sinir = 0;
int main() {
	int flag = 1;
	a[0] = 1;
	a[1] = 2;
	a[2] = 3;
	a[3] = 4;
	a[4] = 5;
	a[5] = 6;
	sinir = 5;
	recursive(&flag);
}
int recursive(int * flag) {
	int x;
	if (isEmpty()) return -123456;
		 x = pop();
		*flag = recursive(flag);
	if (*flag == -123456) {
		*flag = 1;
		return 1;
	}
	else {
		push(x);
		return 1;
	}
}
int pop() {
	if (sinir > 0) {
		sinir--;
		return a[sinir];
	}
	return -1;
}
void push(int x) {
	a[sinir] = x;
	sinir++;
}
int isEmpty() {
	if (sinir == 0) return 1;
	return 0;
}