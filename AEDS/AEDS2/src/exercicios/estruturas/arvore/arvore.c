#include "arvore.h"
#include <stdio.h>
#include <stdlib.h>


No *raiz;



void liberaArvore(No *no) {
    if (no != NULL) {
        liberaArvore(no->esq);
        liberaArvore(no->dir);
        free(no);
    }
}

void limpar() {
    liberaArvore(raiz);
    raiz = NULL;
}


void start(){
	raiz = NULL;
}	
void inserir(int x){
	raiz = inserirRec(x,raiz);
}

No *inserirRec(int x, No* pai){
	if(pai == NULL){
	 pai = novoNo(x);
	}else if(x > pai->elemento){
	  pai->dir= inserirRec(x,pai->dir);
	}else if(x < pai->elemento){
	  pai->esq =  inserirRec(x,pai->esq);
	}else{
		printf("ERROR");
	}
	return pai;
}

void caminha(){
	caminhaRec(raiz);
}

void caminhaRec(No* no){
	if(no != NULL){
		caminhaRec(no->esq);
		printf("%d ", no->elemento);
		caminhaRec(no->dir);
	}
}



int tamanhoRec(int tam, No *pai) {
    if (pai == NULL) return tam;
    int tamDir = tamanhoRec(tam + 1, pai->dir);
    int tamEsq = tamanhoRec(tam + 1, pai->esq);
    return tamDir > tamEsq ? tamDir : tamEsq;
}


int tamanho() {
    if (raiz != NULL) {
        int tamDir = tamanhoRec(0, raiz->dir);
        int tamEsq = tamanhoRec(0, raiz->esq);
        return tamDir > tamEsq ? tamDir : tamEsq;
    }
    return 0;
}


int main(){
	start();
	int x;
	for(int i =0 ; i < 5; i++){
		scanf("%d", &x);
		inserir(x);
	}
 	caminha();
	printf("%d", tamanho());
	limpar();
}

