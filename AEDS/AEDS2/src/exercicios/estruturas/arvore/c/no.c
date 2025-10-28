#include "no.h"
#include <stdio.h>
#include <stdlib.h>

No *novoNo(int elemento){
	No* no = (No*) malloc(sizeof(No));
	no->elemento = elemento;
	no-> dir = NULL;
	no->esq = NULL;
	return no;
}


