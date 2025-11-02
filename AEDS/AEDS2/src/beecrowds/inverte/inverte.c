#include <stdio.h>
#include <stdlib.h>

typedef struct Celula
{
	int elemento;
	struct Celula *prox;
} Celula;

typedef struct Lista
{
	Celula primeiro = NULL;
	Celula ultimo = primeiro;
} Lista;

int remover(Lista lista)
{
	for (Celula *tmp = primeiro; tmp->prox != tmp)
}

Celula *novaCelula(int x)
{
	Celula *celula = (*Celula) malloc(sizeof(Celula);
	celula->elemento = x;
	celula->prox = NULL;i
	return celula;
}

void inverte(Lista lista)
{
	if (lista->primeiro != lista->ultimo)
	{
		Celula *i = primeiro;
		Celula *j = ultimo;
		while (i->prox != j)
		{
			Celula *tmp = i;
			for (; tmp->prox != j; tmp = tmp->prox)
				;
			int auxInt = i->elemento;
			i->elemento = j->elemento;
			j->elemento = auxInt;

			i = i->prox;
			j = tmp;
		}
	}
}

void imprime(Lista lista)
{
	for (Celula *tmp = lista->primeiro; tmp != NULL; tmp = tmp->prox)
	{
		printf("%s\n", tmp->elemento)
	};
}

int main()
{
}
