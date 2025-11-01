package exercicios.estruturas.lista;

import java.io.EOFException;

import exercicios.estruturas.Celula;

public class Lista {
  Celula inicio, fim;

  public Lista() {
    inicio = new Celula();
    fim = inicio;
  }

  public void mostrar() {
    Celula i = inicio.prox;
    while (i != null) {
      System.out.println(i.elemento);
      i = i.prox;
    }
  }

  public int tam() {
    int tam = 0;
    for (Celula i = inicio; i.prox != null; i = i.prox, tam++)
      ;
    return tam;
  }

  public void inserirNoInicio(int x) {
    Celula tmp = new Celula(x);
    tmp.prox = inicio.prox;
    inicio.prox = tmp;
    tmp = null;
  }

  public void inverter() {
    Celula i = inicio;
    Celula j = fim;
    System.out.println(j.elemento);
    Celula k = inicio.prox;
    for (; k.prox != j; k = k.prox) {
      System.out.println(k.elemento);
    }
    ;
    int e = i.elemento;
    i.elemento = j.elemento;
    j.elemento = e;
    i = i.prox;
    j = k;
  }

  public void inserir(int x, int pos) throws Exception {
    if (pos > this.tam() || pos < 0) {
      throw new Exception();
    }
    Celula tmp = inicio;
    for (int i = 0; i < pos; i++, tmp = tmp.prox) {

    }
    ;
    Celula nova = new Celula(x);
    nova.prox = tmp.prox;
    tmp.prox = nova;
    tmp = null;
  }

  public void inserirNoFim(int x) {
    Celula nova = new Celula(x);
    fim.prox = nova;
    fim = nova;
  }

  public int retirarDoInicio() {
    int e = inicio.prox.elemento;
    Celula tmp = inicio;
    inicio = inicio.prox;
    tmp.prox = null;
    tmp = null;
    return e;
  }

  public int remover(int pos) throws Exception {
    if (pos > this.tam()) {
      throw new Exception();
    }
    Celula tmp = inicio;
    for (int i = 0; i < pos; i++, tmp = tmp.prox)
      ;
    int e = tmp.prox.elemento;
    tmp.prox = tmp.prox.prox;
    tmp.prox = null;
    tmp = null;
    return e;
  }

  public int removerNoFim() {
    Celula i = inicio.prox;
    for (; i.prox != null; i = i.prox)
      ;
    int e = fim.elemento;
    fim = i;
    fim.prox = i = null;
    return e;
  }

  // Parte dos exercicios

  public int removeSegunda() throws Exception {
    if (inicio.prox != null || inicio.prox.prox != null) {
      throw new Exception();
    }
    Celula p = inicio.prox;
    Celula q = p.prox;
    int e = q.elemento;
    p.prox = q.prox;
    q.prox = null;
    q = null;
    return e;
  }

}
