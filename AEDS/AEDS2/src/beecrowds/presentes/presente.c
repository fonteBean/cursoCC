#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

typedef struct Presente
{
	char *nome;
	float valor;
	int preferencia;
} Presente;

typedef struct Crianca
{
	char *nome;
	int numeroPresentes;
	Presente presentes[100000];

} Crianca;

Crianca *novaCrianca(char *nome, int numeroPresentes)
{
	Crianca *crianca = malloc(sizeof(Crianca));
	strcpy(crianca->nome, nome);
	crianca->numeroPresentes = numeroPresentes;
	return crianca;
};

Presente *novoPresente(char *nome, float valor, int preferencia)
{
	Presente *novo = (*Presente)malloc(sizeof(Presente));
	novo->prox = NULL;
	strcpy(novo->nome, nome);
	novo->valor = valor;
	novo->preferencia = preferencia;
	return novo;
}

void swap(Presente *a, Presente *b)
{
	Presente temp = *a;
	*a = *b;
	*b = temp;
}

int particiona(Presente *presentes, int inicio, int fim)
{
	Presente pivo = presentes[fim];
	int i = inicio;

	for (int j = inicio; j < fim; j++)
	{
		if (presentes[j].preferencia < pivo.preferencia ||
				(presentes[j].preferencia == pivo.preferencia && presentes[j].valor <= pivo.valor))
		{
			swap(&presentes[i], &presentes[j]);
			i++;
		}
	}

	swap(&presentes[i], &presentes[fim]);
	return i;
}

void quicksort(Presente *presentes, int inicio, int fim)
{
	if (inicio < fim)
	{
		int pivo = particiona(presentes, inicio, fim);
		quicksort(presentes, inicio, pivo - 1);
		quicksort(presentes, pivo + 1, fim);
	}
}

int main()
{
	FILE fr = fopen("/tmp/presente.txt/");
	if (fr == NULL)
	{
		printf("Deu ruim");
		return 1;
	}
	Crianca crianca;
	while (fscanf("%s %d", crianca.nome, crianca.numeroPresentes))
	{
		Presente **presentes = (**Presente)malloc(sizeof(Presente) * crianca.numeroPresentes);
		for (int i = 0; i < crianca.numeroPresentes; i++)
		{
			fscanf("%s", presentes[i]->nome);
			fscanf("%f %d", presentes[i]->valor, presentes[i]->preferencia);
		}
		quicksort(presentes, 0, crianca.numeroPresentes);
		printf("Lista de %s\n", crianca.nome);
		for (int i = 0; i < crianca.numeroPresentes; i++)
		{
			printf("%s - R$%.2f", presentes[i]->nome, presentes[i]->valor);
			free(presentes[i]);
		}
	}
	free(presentes);
	free(crianca);
}