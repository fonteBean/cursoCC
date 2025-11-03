#include <stdio.h>
#include <stdlib.h>

typedef struct Crianca
{
  char *nome;
  int e;
} Crianca;

typedef struct Celula
{
  Crianca c;
  struct Celula *prox, *ant;
} Celula;

Celula *novaCelula(int x)
{
  Celula *nova = malloc(sizeof(Celula));
  nova->prox = NULL;
  nova->ant = NULL;
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

void inserir(Celula *cabeca, int x)
{
  Celula *nova = novaCelula(x);
  Celula *atual = cabeca;
  while (atual->prox != cabeca)
  {
    atual = atual->prox;
  }
  atual->prox = nova;
  nova->ant = atual;
  nova->prox = cabeca;
};

int remover(Celula **cabeca, int passos)
{
  if (*cabeca == NULL)
    return -1;

  Celula *atual = (passos % 2 == 0) ? caminhaDir(*cabeca, passos)
                                    : caminhaEsq(*cabeca, passos);

  if (atual == *cabeca)
  {
    if (atual->prox == atual)
    {
      int e = atual->e;
      free(atual);
      *cabeca = NULL;
      return e;
    }
    atual->ant->prox = atual->prox;
    atual->prox->ant = atual->ant;
    *cabeca = atual->prox;
    int e = atual->e;
    free(atual);
    return e;
  }

  atual->ant->prox = atual->prox;
  atual->prox->ant = atual->ant;
  int e = atual->e;
  free(atual);
  return e;
}

Celula *caminhaDir(Celula *cabeca, int passos)
{
  Celula *tmp = cabeca;
  for (int i = 0; i < passos; i++)
    tmp = tmp->prox;
  return tmp;
}

Celula *caminhaEsq(Celula *cabeca, int passos)
{
  Celula *tmp = cabeca;
  for (int i = 0; i < passos; i++)
    tmp = tmp->ant;
  return tmp;
}

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

int main()
{
  Celula *cabeca = novaCelula(0);

  inserir(cabeca, 1);
  inserir(cabeca, 2);

  imprime(cabeca);

  limpar(cabeca);
  return 0;
}