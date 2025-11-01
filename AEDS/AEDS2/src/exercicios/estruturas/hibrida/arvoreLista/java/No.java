
package exercicios.estruturas.hibrida.arvoreLista.java;

public class No {
  char letra;
  No dir, esq;
  Arvore2 interno;

  public No(char c) {
    this.letra = c;
    this.dir = null;
    this.esq = null;
    this.interno = new Arvore2();
  }

}