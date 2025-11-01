package exercicios.estruturas;

import exercicios.estruturas.fila.Fila;
import exercicios.estruturas.lista.Lista;

public class Main {

  public static void main(String args[]) throws Exception {
    Fila fila = new Fila();

    Lista lista = new Lista();
    lista.inserirNoInicio(1);
    lista.inserirNoInicio(2);
    lista.inserirNoInicio(3);
    lista.inserirNoInicio(4);
    lista.inserirNoFim(8);
    lista.inverter();
    lista.mostrar();

  }

}
