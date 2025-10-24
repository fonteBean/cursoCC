#include "No.h"
#include <stdio.h>
#include <stdlib.h>

No* novoNo(int elemento) {
    No* no = (No*)malloc(sizeof(No));
    no->elemento = elemento;
    no->esquerda = NULL;
    no->direita = NULL;
    return no;
}



int main(){

}