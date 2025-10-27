package exercicios.estruturas.fila;

import exercicios.estruturas.Celula;

public class Fila {
  Celula inicio, fim;

  public Fila() {
    inicio = new Celula();
    fim = inicio;
  }

  public void inserir(int x) {
    fim.prox = new Celula(x);
    fim = fim.prox;
  }

  public int remover() throws Exception {
    if (inicio == fim) {
      throw new Exception();
    }
    int elemento = inicio.prox.elemento;
    Celula tmp = inicio;
    inicio = inicio.prox;
    tmp.prox = null;
    tmp = null;
    return elemento;
  }

  public void mostrar() {
    mostrar(inicio.prox);
  }

  private void mostrar(Celula i) {
    if (i != null) {
      System.out.println(i.elemento);
      mostrar(i.prox);
    }
  }

  public int terceiro() {
    return inicio.prox.prox.prox.elemento;
  }

  public int maior() {
    int maior = 0;
    Celula tmp = inicio;
    while (tmp != null) {
      if (maior < tmp.elemento) {
        maior = tmp.elemento;
      }
      tmp = tmp.prox;
    }
    return maior;
  }

  public int soma() {
    int soma = 0;
    for (Celula i = inicio.prox; i != null; i = i.prox) {
      soma += i.elemento;
      System.out.println(soma);

    }
    return soma;
  }

  // public void inverte() {
  // Celula primeiro = inicio.prox;

  // while (primeiro != null) {
  // Celula tmp = primeiro;
  // while (tmp.prox != null) {
  // tmp = tmp.prox;
  // }
  // int tmpE = primeiro.elemento;
  // primeiro.elemento = tmp.elemento;
  // tmp.elemento = tmpE;
  // tmp = null;
  // }

  // }

}
