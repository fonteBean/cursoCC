package exercicios.estruturas.fila;

import javax.swing.plaf.ColorChooserUI;

import exercicios.estruturas.Celula;

public class Fila {
  Celula inicio = new Celula();
  Celula fim = inicio;

  public Fila() {
    inicio = fim = null;
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
    mostrar(inicio);
  }

  private void mostrar(Celula i) {
    if (i != fim) {
      System.out.println(i.elemento);
      mostrar(i.prox);
    }
  }
}
