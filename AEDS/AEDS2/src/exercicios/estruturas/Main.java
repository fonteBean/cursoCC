package exercicios.estruturas;

import exercicios.estruturas.fila.Fila;

public class Main {

  public static void main(String args[]) throws Exception {
    Fila fila = new Fila();
    fila.inserir(5);
    fila.inserir(4);

    fila.inserir(1);
    fila.inserir(10);
    // fila.inverte();
    fila.mostrar();
  }

}
