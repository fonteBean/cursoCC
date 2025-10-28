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

int soma();

int somaRec(No *no);

int contaPar();

int contaParRec(No *no);

bool div11();

bool div11Rec(No *no);

int tamanho();

int tamanhoRec(int tam, No*pai);
