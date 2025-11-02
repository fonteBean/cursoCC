#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>


typedef struct Pessoa{
	char *nome;
	bool amigo;
}Pessoa;



bool checaLista(Pessoa *pessoas, Pessoa p,int n)
{
	for(int i =0 ; i < n; i++){
		if(strcmp(pessoas[i],p->nome) < 0){
			return false;
		}
	}
}




void swap(Pessoa a, Pessoa b)
{
	Pessoa tmp = a;
	a =b;
	b =tmp;
}


int particiona(Pessoa *pessoas,int inicio, int fim){
	Pessoa pivo = pessoas[fim];
	int i = inicio;
	for(int j = inicio; j < fim; j++){
		if(pessoas[j]->nome, pivo->nome) > 0){
			swap(pessoas[j],pessoas[i]);
			i++;
		}	
	}
	swap(pessoas[i],pessoas[fim]);
	return i;
}

void quicksort(Pessoa *pessoas,int inicio,int fim){
	if(fim> inicio){
		int pivo = particiona(pessoas,inicio,fim);
		quicksort(pessoas,inicio,pivo-1);
		quicksort(pessoas,pivo+1,fim);
	}
}



void ehAmigo(char *amigo){
	bool teste = strcmp(amigo,"YES") == 0 ? true : false;
	return teste;
}



int main(){
	char *nome;
	scanf("%s",nome)
	Pessoas *amigos = malloc(sizeof(Pessoas) * 40);
	Pessoas *cornos = malloc(sizeof(Pessoas) *40);
	int tamAmigos = 0; int tamCornos =0;
	while(strcmp("FIM",nome) !=0){
		Pessoa p;
		strcpy(p->nome,nome);
		char* amigo;
		scanf("%s",amigo);
		
		if(ehAmigo){
		 	add(p);
			tam++
		}else{

		}

		scanf("%s",nome);
	}

	free(amigos);
	free(cornos);
}


