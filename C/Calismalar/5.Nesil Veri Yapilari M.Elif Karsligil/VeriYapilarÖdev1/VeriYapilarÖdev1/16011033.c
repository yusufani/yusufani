#include<stdio.h>
#include <stdlib.h>
#include <string.h>
void PrintStacks(float * stackNumber, int numberIndex, char * stackExpression, int expressionIndex);
void AddExpressionStack(char * stackExpression, int * expressionIndex, char element);
char GetExpressionStack(char * stackExpression, int *expressionIndex);
void AddNumberStack(int * stackNumber, int * numberIndex, float element);
int GetNumberStack(int * stackNumber, int *numberIndex);
char ReadExpressionStack(char * stackExpression, int expressionIndex);
float ReadNumberStack(int * stackNumber, int numberIndex);
int MAX_NUM = 50; MAX_EXPR = 50;
int main() {
	char *stackExpression, *mathematicalExpression, tmp;
	float *stackNumber;
	int i = 0, stackNumberIndex = 0, stackExpressionIndex = 0;
	stackNumber = (float *)calloc(MAX_NUM, sizeof(float));
	stackExpression = (char *)calloc(MAX_EXPR, sizeof(char));
	mathematicalExpression = (char *)calloc(500, sizeof(char));
	printf("Matematiksel ifade cozucuye hosgeldiniz!\nLutfen matematiksel ifade giriniz:");
	gets(mathematicalExpression);
	printf("Yiginlarin Ilk Durumu:\n");
	while (i< strlen(mathematicalExpression)) {
		if (mathematicalExpression[i-1] != ' '  || i ==0 ) PrintStacks (stackNumber, stackNumberIndex, stackExpression, stackExpressionIndex);// Ekrana yazdirma islemleri
		switch (mathematicalExpression[i]) {
		case '(': {
			AddExpressionStack(stackExpression, &stackExpressionIndex, mathematicalExpression[i]);
			break;
		}
		case ')': {
			while ((tmp=ReadExpressionStack(stackExpression,stackExpressionIndex)) != '(') {
				stackNumberIndex -= 2;
				if (tmp == '+') AddNumberStack(stackNumber, &stackNumberIndex, (ReadNumberStack(stackNumber, stackNumberIndex + 1) + ReadNumberStack(stackNumber, stackNumberIndex + 2)));
				else if (tmp == '-')  AddNumberStack(stackNumber, &stackNumberIndex, (ReadNumberStack(stackNumber, stackNumberIndex + 1) - ReadNumberStack(stackNumber, stackNumberIndex + 2)));
				else if (tmp == '*')  AddNumberStack(stackNumber, &stackNumberIndex, (ReadNumberStack(stackNumber, stackNumberIndex + 1) * ReadNumberStack(stackNumber, stackNumberIndex + 2)));
				else   AddNumberStack(stackNumber, &stackNumberIndex, (ReadNumberStack(stackNumber, stackNumberIndex + 1) / ReadNumberStack(stackNumber, stackNumberIndex + 2)));
				stackExpressionIndex--;
				PrintStacks(stackNumber, stackNumberIndex, stackExpression, stackExpressionIndex);// Ekrana yazdirma islemleri
			}
			stackExpressionIndex--;
			break;
		}
		case ' ': {	break;}
		case '+':
		case '-': {
			if (ReadExpressionStack(stackExpression, stackExpressionIndex) == '(' || stackExpressionIndex == 0) { // Eğer Yiginin en üstündeli işaret ( ise 
				AddExpressionStack(stackExpression, &stackExpressionIndex, mathematicalExpression[i]);
			}
			else{
				while (((tmp = ReadExpressionStack(stackExpression, stackExpressionIndex)) != '(') && stackExpressionIndex != 0) {
					stackNumberIndex -= 2;
					if (tmp == '+') AddNumberStack(stackNumber, &stackNumberIndex, (ReadNumberStack(stackNumber, stackNumberIndex+1) + ReadNumberStack(stackNumber, stackNumberIndex + 2)));
					else if (tmp == '-')  AddNumberStack(stackNumber, &stackNumberIndex, (ReadNumberStack(stackNumber, stackNumberIndex+1) - ReadNumberStack(stackNumber, stackNumberIndex+2)));
					else if (tmp == '*')  AddNumberStack(stackNumber, &stackNumberIndex, (ReadNumberStack(stackNumber, stackNumberIndex+1) * ReadNumberStack(stackNumber, stackNumberIndex+2)));
					else   AddNumberStack(stackNumber, &stackNumberIndex, (ReadNumberStack(stackNumber, stackNumberIndex+1) / ReadNumberStack(stackNumber, stackNumberIndex+2)));
					stackExpressionIndex--;
					PrintStacks(stackNumber, stackNumberIndex, stackExpression, stackExpressionIndex);// Ekrana yazdirma islemleri
				}
				AddExpressionStack(stackExpression, &stackExpressionIndex, mathematicalExpression[i]);
			}
			break;
		}
		case '*':
		case '/': {
			if ((tmp = ReadExpressionStack(stackExpression, stackExpressionIndex)) == '/' || tmp == '*') { // Eğer Yiginin en üstündeli işaret / ise
				while (tmp  != '+' && tmp != '-' && tmp != '(' && stackExpressionIndex != 0) {
					stackNumberIndex -= 2;
					if (tmp == '/')  AddNumberStack(stackNumber, &stackNumberIndex, (ReadNumberStack(stackNumber, stackNumberIndex +1) / ReadNumberStack(stackNumber, stackNumberIndex+2)));
					else AddNumberStack(stackNumber, &stackNumberIndex, (ReadNumberStack(stackNumber, stackNumberIndex + 1) * ReadNumberStack(stackNumber, stackNumberIndex+2)));
					stackExpressionIndex--;
					PrintStacks(stackNumber, stackNumberIndex, stackExpression, stackExpressionIndex);// Ekrana yazdirma islemleri
					tmp = ReadExpressionStack(stackExpression, stackExpressionIndex);
				}
			}
			AddExpressionStack(stackExpression, &stackExpressionIndex, mathematicalExpression[i]);
			break;
		}
		default: {
			if (mathematicalExpression[i+1] >= 48 && mathematicalExpression[i+1] <= 57) { // Asci tablosunda 0 -> 48 degerini gösteriyor
				if (mathematicalExpression[i + 2] >= 48 && mathematicalExpression[i + 2] <= 57) {
					AddNumberStack(stackNumber, &stackNumberIndex, (100 * (mathematicalExpression[i] - 48) + 10 * (mathematicalExpression[i + 1] - 48) + (mathematicalExpression[i + 2] - 48)));
					i+=2;
				}
				else {
					AddNumberStack(stackNumber, &stackNumberIndex, (10 * (mathematicalExpression[i] - 48) + (mathematicalExpression[i + 1] - 48)));
					i++;
				}
			}
			else AddNumberStack(stackNumber, &stackNumberIndex, (stackNumber[stackNumberIndex] = mathematicalExpression[i] - 48));
		}
		}
		i++;
	}
	PrintStacks(stackNumber, stackNumberIndex, stackExpression, stackExpressionIndex);
	while (stackNumberIndex != 1) {
		stackNumberIndex -= 2;
		if ((tmp = ReadExpressionStack(stackExpression, stackExpressionIndex)) == '+') AddNumberStack(stackNumber, &stackNumberIndex, (ReadNumberStack(stackNumber, stackNumberIndex + 1) + ReadNumberStack(stackNumber, stackNumberIndex + 2)));
		else if (tmp == '-')  AddNumberStack(stackNumber, &stackNumberIndex, (ReadNumberStack(stackNumber, stackNumberIndex + 1) - ReadNumberStack(stackNumber, stackNumberIndex + 2)));
		else if (tmp == '*')  AddNumberStack(stackNumber, &stackNumberIndex, (ReadNumberStack(stackNumber, stackNumberIndex + 1) * ReadNumberStack(stackNumber, stackNumberIndex + 2)));
		else   AddNumberStack(stackNumber, &stackNumberIndex, (ReadNumberStack(stackNumber, stackNumberIndex + 1) / ReadNumberStack(stackNumber, stackNumberIndex + 2)));
		stackExpressionIndex--;
		PrintStacks(stackNumber, stackNumberIndex, stackExpression, stackExpressionIndex);
	}
	printf("Islem Sonucumuz :%c%f\n",stackExpression[0], stackNumber[0]);
	system("pause");
}
void PrintStacks(float * stackNumber, int numberIndex, char * stackExpression, int expressionIndex) {
	printf("Sayi Yigini:\t\t##\t\tIsaret Yigini:\n");
	while (numberIndex > 0 || expressionIndex > 0) {
		if (numberIndex > 0) {
			printf("| %f |\t\t##\t\t", stackNumber[numberIndex - 1]);
			numberIndex--;
		}
		else printf("\t\t\t##\t\t");
		if (expressionIndex > 0) {
			printf("| %c |\n", stackExpression[expressionIndex - 1]);
			expressionIndex--;
		}
		else printf("\t\n");
	}
	printf("\n*******Yeni Ifade Aliniyor********\n\tYiginlarin anlik durumu\n");
}
void AddExpressionStack(char * stackExpression, int * expressionIndex,char element) {
	if (MAX_EXPR - 1 == *expressionIndex) {
		MAX_EXPR *= 2;
		stackExpression = (char *)realloc(stackExpression, MAX_EXPR);
	}
	stackExpression[*expressionIndex] = element;
	(*expressionIndex)++;
}
char GetExpressionStack(char * stackExpression, int *expressionIndex) {
	(*expressionIndex)--;
	return stackExpression[*expressionIndex];
}
void AddNumberStack(float * stackNumber,int  *numberIndex, float element) {
	if (MAX_NUM - 1 == *numberIndex) {
		MAX_NUM *= 2;
		stackNumber = (float *)realloc(stackNumber, MAX_NUM);
	}
	stackNumber[*numberIndex] = element;
	(*numberIndex)++;
}
int GetNumberStack(float * stackNumber, int *numberIndex) {
	(*numberIndex)--;
	return stackNumber[*numberIndex];
}
char ReadExpressionStack(char * stackExpression, int expressionIndex) {
	return stackExpression[expressionIndex-1];
}
float ReadNumberStack(float * stackNumber, int numberIndex) {
	return stackNumber[numberIndex-1];
}