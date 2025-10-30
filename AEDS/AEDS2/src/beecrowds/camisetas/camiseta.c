#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct
{
  char nome[50];
  char cor[20];
  char tamanho;
} Pessoa;

void swap(Pessoa *a, Pessoa *b)
{
  Pessoa tmp = *a;
  *a = *b;
  *b = tmp;
}

int particiona(Pessoa *pessoas, int inicio, int fim)
{
  Pessoa pivo = pessoas[fim];
  int j = inicio;

  for (int i = inicio; i < fim; i++)
  {
    if (strcmp(pessoas[i].cor, pivo.cor) < 0 ||
        (strcmp(pessoas[i].cor, pivo.cor) == 0 && pessoas[i].tamanho > pivo.tamanho) ||
        (strcmp(pessoas[i].cor, pivo.cor) == 0 && pessoas[i].tamanho == pivo.tamanho && strcmp(pessoas[i].nome, pivo.nome) < 0))
    {
      swap(&pessoas[i], &pessoas[j]);
      j++;
    }
  }

  swap(&pessoas[j], &pessoas[fim]);
  return j;
}

void quicksort(Pessoa *pessoas, int inicio, int fim)
{
  if (inicio < fim)
  {
    int pivo = particiona(pessoas, inicio, fim);
    quicksort(pessoas, inicio, pivo - 1);
    quicksort(pessoas, pivo + 1, fim);
  }
}

void imprimeLista(Pessoa *pessoas, int n)
{
  for (int i = 0; i < n; i++)
  {
    printf("%s %c %s\n", pessoas[i].cor, pessoas[i].tamanho, pessoas[i].nome);
  }
}

int main()
{
  int nTestes;
  while (scanf("%d", &nTestes) && nTestes > 0)
  {
    Pessoa *pessoas = malloc(nTestes * sizeof(Pessoa));

    for (int i = 0; i < nTestes; i++)
    {
      scanf(" %[^\n]", pessoas[i].nome);
      scanf(" %s %c", pessoas[i].cor, &pessoas[i].tamanho);
    }

    quicksort(pessoas, 0, nTestes - 1);
    imprimeLista(pessoas, nTestes);

    free(pessoas);
  }
  return 0;
}
