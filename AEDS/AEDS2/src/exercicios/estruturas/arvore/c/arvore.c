#include "arvore.h"
#include <stdio.h>
#include <stdlib.h>

No *raiz;

void liberaArvore(No *no)
{
	if (no != NULL)
	{
		liberaArvore(no->esq);
		liberaArvore(no->dir);
		free(no);
	}
}

void limpar()
{
	liberaArvore(raiz);
	raiz = NULL;
}

void start()
{
	raiz = NULL;
}
void inserir(int x)
{
	raiz = inserirRec(x, raiz);
}

No *inserirRec(int x, No *pai)
{
	if (pai == NULL)
	{
		pai = novoNo(x);
	}
	else if (x > pai->elemento)
	{
		pai->dir = inserirRec(x, pai->dir);
	}
	else if (x < pai->elemento)
	{
		pai->esq = inserirRec(x, pai->esq);
	}
	else
	{
		printf("ERROR");
	}
	return pai;
}

void caminha()
{
	caminhaRec(raiz);
}

void caminhaRec(No *no)
{
	if (no != NULL)
	{
		caminhaRec(no->esq);
		printf("%d ", no->elemento);
		caminhaRec(no->dir);
	}
}

int soma()
{
	return somaRec(raiz);
}

int somaRec(No *no)
{
	if (no == NULL)
		return 0;
	int resp = no->elemento + somaRec(no->esq) + somaRec(no->dir);
	return resp;
}

int contaPar()
{
	return contaParRec(raiz);
}

int contaParRec(No *no)
{
	if (no == NULL)
	{
		return 0;
	}

	int resp = no->elemento % 2 == 0 ? 1 : 0;
	return resp + contaParRec(no->dir) + contaParRec(no->esq);
}

bool div11()
{
	return div11Rec(raiz);
}

bool div11Rec(No *no)
{
	bool resp = false;
	if (no != NULL)
	{
		resp = (no->elemento % 11 == 0 || div11Rec(no->esq) || div11Rec(no->dir)) ? true : false;
	}
	return resp;
}

int getMaior()
{
	if (raiz != NULL)
	{
		return getMaiorRec(raiz);
	}
}

int getMaiorRec(No *no)
{
	if (no->dir == NULL)
	{
		return no->elemento;
	}
	return getMaiorRec(no->dir);
}

int tamanhoRec(int tam, No *pai)
{
	if (pai == NULL)
		return tam;
	int tamDir = tamanhoRec(tam + 1, pai->dir);
	int tamEsq = tamanhoRec(tam + 1, pai->esq);
	return tamDir > tamEsq ? tamDir : tamEsq;
}

int tamanho()
{
	if (raiz != NULL)
	{
		int tamDir = tamanhoRec(0, raiz->dir);
		int tamEsq = tamanhoRec(0, raiz->esq);
		return tamDir > tamEsq ? tamDir : tamEsq;
	}
	return 0;
}

int main()
{
	start();
	int x;
	for (int i = 0; i < 5; i++)
	{
		scanf("%d", &x);
		inserir(x);
	}
	caminha();
	printf("%d", contaPar());
	limpar();
}
