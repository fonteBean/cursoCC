#include "no.h"
#include <stdio.h>
#include <stdlib.h>

typedef struct Arvore {
    No* raiz;
} Arvore;

bool pesquisa(Arvore* arvore, int elemento);
void inserir(Arvore* arvore, int elemento);

