#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct Celula
{
	int nota;
	struct Celula *prox;
} Celula;

Celula *novaCelula(int x)
{
	Celula *nova = malloc(sizeof(Celula));
	nova->prox = NULL;
	nova->nota = x;
	return nova;
}

void inserir(Celula *cabeca, int x)
{
	Celula *nova = novaCelula(x);
	if(cabeca->prox == NULL){
		cabeca->prox = 
	}


	Celula *tmp = cabeca;
	while (tmp->prox != NULL){
		tmp = tmp->prox;
	}
	tmp->prox = nova;
}

void inserirOrdenado(Celula *cabeca, int x)
{
	Celula *nova = novaCelula(x);
	Celula *tmp = cabeca;

	while (tmp->prox != NULL && tmp->prox->nota < x)
		tmp = tmp->prox;

	nova->prox = tmp->prox;
	tmp->prox = nova;
}

int mudancas(Celula *cabeca1, Celula *cabeca2)
{
	int resp = 0;
	Celula *t1 = cabeca1->prox, *t2 = cabeca2->prox;
	for (; t1 != NULL && t2 != NULL; t1 = t1->prox, t2 = t2->prox)
	{
		if (t1->nota != t2->nota)
			resp++;
	}
	return resp;
}

void limpar(Celula *cabeca)
{
	Celula *tmp = cabeca;
	while (tmp != NULL)
	{
		Celula *atual = tmp;
		tmp = tmp->prox;
		free(atual);
	}
}

int main()
{
	int nTestes;
	while (scanf("%d", &nTestes) != EOF)
	{
		int nCriancas;
		scanf("%d", &nCriancas);
		getchar();

		Celula *ordenada = novaCelula(0);
		Celula *insercao = novaCelula(0);
		char linha[1000];
		fgets(linha, sizeof(linha), stdin);

		char *token = strtok(linha, " ");
		while (token != NULL)
		{
			int nota = atoi(token);
			inserir(insercao, nota);
			inserirOrdenado(ordenada, nota);
			token = strtok(NULL, " ");
		}

		printf("%d\n", mudancas(insercao, ordenada));

		limpar(insercao);
		limpar(ordenada);
	}

	return 0;
}
