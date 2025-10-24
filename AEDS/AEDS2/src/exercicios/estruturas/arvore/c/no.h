#include <stdio.h>
#include <stdlib.h>

typedef struct No {
    int elemento;
    struct No* esquerda;
    struct No* direita;
} No;

No* novoNo(int elemento) {}