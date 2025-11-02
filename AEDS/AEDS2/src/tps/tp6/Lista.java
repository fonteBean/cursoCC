package tps.tp6;

import exercicios.models.Game;

public class Lista {
  public Game primeiro, ultimo;

  public Lista() {
    primeiro = new Game();
    ultimo = primeiro;
  }

  public void mostrar() {
    int indice = 0;
    Game i = primeiro.prox;
    while (i != null) {
      System.out.printf("[%d] ", indice);
      i.imprimir();
      indice++;
      i = i.prox;
    }
  }

  public int tam() {
    int tam = 0;
    for (Game i = primeiro; i.prox != null; i = i.prox, tam++)
      ;
    return tam;
  }

  public void inserirNoInicio(Game g) {
    g.prox = primeiro.prox;
    primeiro.prox = g;
    if (ultimo == primeiro)
      ultimo = g;
  }

  public void inserir(Game g, int pos) throws Exception {
    if (pos > this.tam()) {
      throw new Exception();
    }
    Game tmp = primeiro.prox;
    for (int i = 0; i < pos; i++, tmp = tmp.prox)
      ;
    g.prox = tmp.prox;
    tmp.prox = g;
  }

  public void inserirNoFim(Game g) {
    ultimo.prox = g;
    ultimo = g;
  }

  public String removerNoInicio() {
    String nome = primeiro.prox.getNome();
    Game tmp = primeiro;
    primeiro = primeiro.prox;
    tmp.prox = null;
    return nome;
  }

  public String remover(int pos) throws Exception {
    if (pos < 0 || pos >= this.tam()) {
      throw new Exception("Posição inválida");
    }

    Game tmp = primeiro;
    for (int i = 0; i < pos; i++, tmp = tmp.prox)
      ;

    String nome = tmp.prox.getNome();
    tmp.prox = tmp.prox.prox;
    return nome;
  }

  public String removerNoFim() {
    Game i = primeiro;
    for (; i.prox != ultimo; i = i.prox)
      ;
    String e = ultimo.getNome();
    ultimo = i;
    ultimo.prox = null;
    return e;
  }

  public Game buscaGame(int id) {
    for (Game g = primeiro.prox; g != null; g = g.prox) {
      if (g.getId() == id) {
        return g;
      }
    }
    return null;
  }

}
