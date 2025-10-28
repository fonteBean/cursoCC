package exercicios.estruturas;

import exercicios.estruturas.fila.Fila;
import exercicios.estruturas.lista.Lista;

public class Main {

  public static void main(String args[]) throws Exception {
    Fila fila = new Fila();

    Lista lista = new Lista();
    lista.inserirNoInicio(5);
    lista.inserirNoInicio(4);
    lista.inserirNoInicio(8);
    lista.inserirNoInicio(10);
    lista.remover(3);
    lista.removerNoFim();
    lista.mostrar();

  }

}
