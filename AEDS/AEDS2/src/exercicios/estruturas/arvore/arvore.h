#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "no.c"

typedef struct Arvore{
	No* raiz;
}Arvore;


void limpar();

void liberaArvore(No *no);

void start();

void inserir(int x);

No* inserirRec(int x, No* pai);

void caminhar();

void caminhaRec();

int tamanho();

int tamanhoRec(int tam, No*pai);
