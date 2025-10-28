#include <stdio.h>
#include <stdlib.h>
#include "arvore.c"


int main(){
	Arvore* arvore = novaArvore();

	free(arvore);	
}
