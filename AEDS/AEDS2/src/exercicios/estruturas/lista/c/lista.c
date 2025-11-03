#include <stdio.h>
#include <stdlib.h>

typedef struct Celula
{
	int e;
	struct Celula *prox;
} Celula;

Celula *novaCelula(int x)
{
	Celula *nova = malloc(sizeof(Celula));
	nova->prox = NULL;
	nova->e = x;
	return nova;
};

int tam(Celula *cabeca)
{
	int tam = 1;
	Celula *atual = cabeca->prox;
	for (; atual != NULL; atual = atual->prox, tam++)
		;
	return tam;
}

void inserirNoInicio(Celula *cabeca, int x)
{
	Celula *nova = novaCelula(x);
	nova->prox = cabeca->prox;
	cabeca->prox = nova;
}

void inserir(Celula *cabeca, int x, int pos)
{
	if (pos > tam(cabeca))
	{
		printf("err");
		return;
	}

	Celula *atual = cabeca;
	Celula *nova = novaCelula(x);
	for (int i = 0; i < pos; atual = atual->prox, i++)
		;
	nova->prox = atual->prox;
	atual->prox = nova;
}

void inserirNoFim(Celula *cabeca, int x)
{
	Celula *nova = novaCelula(x);

	Celula *atual = cabeca;
	while (atual->prox != NULL)
	{
		atual = atual->prox;
	}
	atual->prox = nova;
};

int removerNoFim(Celula *cabeca)
{
	Celula *atual = cabeca;
	while (atual->prox->prox != NULL)
	{
		atual = atual->prox;
	}

	int e = atual->prox->e;
	Celula *ultimo = atual->prox;
	atual->prox = NULL;
	free(ultimo);
	return e;
};

int removerNoInicio(Celula *cabeca)
{
	Celula *primeiro = cabeca->prox;
	cabeca->prox = primeiro->prox;
	primeiro->prox = NULL;
	int e = primeiro->e;
	free(primeiro);
	return e;
}

int remover(Celula *cabeca, int pos)
{
	if (pos > tam(cabeca))
	{
		printf("err");
		return -1;
	}

	Celula *atual = cabeca;
	for (int i = 0; i < pos; atual = atual->prox, i++)
		;
	Celula *tmp = atual->prox;
	atual->prox = atual->prox->prox;
	tmp->prox = NULL;
	int e = tmp->e;
	free(tmp);
	return e;
}

void imprime(Celula *cabeca)
{
	Celula *atual = cabeca->prox;
	printf("[");
	for (; atual != NULL; atual = atual->prox)
	{
		printf("%i ", atual->e);
	}
	printf("]\n");
};

void limpar(Celula *cabeca)
{
	Celula *atual = cabeca->prox;
	Celula *libera;
	while (atual != NULL)
	{
		libera = atual;
		atual = atual->prox;
		free(libera);
	}
};

void inverteRec(Celula *i, Celula *j)
{
	if (i == j)
	{
		return;
	}
	int e = i->e;
	i->e = j->e;
	j->e = e;

	Celula *tmp = i;
	for (; tmp->prox != j; tmp = tmp->prox)
		;
	inverteRec(i->prox, tmp);
}

void inverte(Celula *cabeca)
{
	Celula *i = cabeca->prox;
	Celula *j = cabeca;
	for (; j->prox != NULL; j = j->prox)
		;
	inverteRec(i, j);
}

int main()
{
	Celula *cabeca = novaCelula(0);

	inserirNoFim(cabeca, 1);
	inserirNoFim(cabeca, 2);
	inserir(cabeca, 3, 2);

	imprime(cabeca);

	inverte(cabeca);
	imprime(cabeca);

	limpar(cabeca);
	return 0;
}
