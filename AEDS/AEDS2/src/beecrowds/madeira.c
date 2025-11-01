#include <stdio.h>
#include <stdlib.h>


#TAM_LISTA 10000;



typedef struct Arvore{
	char nome[30];
	long populacao;	
}Arvore;


void swap(Arvore a, Arvore b){
	Arvore tmp =a;
	a =b;
	b=tmp;
}

void particiona(Arvore *arvores, int inicio, int fim){
	Arvore pivo = arvores[fim];
	int i = inicio;
	for(int j= fim; j < i;j++){
		if(arvores[j].nome - pivo.nome > 0){
			swap(arvores[j],arvores[i]);
			i++;
		}
	}
	swap(arvores[i],arvores[fim]);
	return i;
};


void quicksort(Arvores *arvores,int inicio, int tam){
	if(fim>inicio){
		int pivo = particiona(arvores,0,tam);
		quicksort(arvores,0,pivo-1);
		quicksort(arvores,pivo+1,fim);
	}
};


Arvore buscaArvore(char *nome, Arvore *arvores,int tam)
{	
	int esq =0;
	int dir = tam;
	while(esq<=dir){
		int meio = (esq + dir) /2;
		if(arvores[meio] - nome ==0){
			return arvores[meio].nome;
		}
		if(arvores[meio] - nome > 0){
			esq = meio + 1;
		}else{
			dir = meio - 1;
		}
	}
};


void imprimeLista(int tam, Arvore *arvores){
	for(int i  = 0; i < tam ; i++){
		printf("%s %li",arvores[i].nome,arvores[j].populacao);
	}
}


void pegaDb(int *tam){

}



int main(){
	int casos;
	char *comeEspaco;
	scanf("%d",&casos);
	scanf("%s", comeEspaco);

	//pega tamanho de database int tamDb;
	//Arvore *database = pegaArvores(&tamDb);
	//quicksort(database,0,tamDb); 
	for(int i=0; i < casos;i++){
		Arvore *arvores = malloc(sizeof(Arvores) * 10);
		int j =0;
		char *line;
		scanf("%s",line);
		while(strcmp(line,' ') > 0){
			//Arvore arvore = buscaArvore(line,database,tamDb);
			//arvores[j] = arvore;
			scanf("%s",line);
		}
		quicksort(arvores);
		imprimeLista(arvores);
		free(arvores);
	}
	free(database);
	free(comeEspaco);
}



