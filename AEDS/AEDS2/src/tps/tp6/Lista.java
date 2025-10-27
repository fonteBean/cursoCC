package tps.tp6;

import exercicios.models.Game;

public class Lista {
  public Game primeiro, ultimo;

  public Lista() {
    primeiro = new Game();
    ultimo = primeiro;
  }

  public void mostrar() {
    Game i = primeiro.prox;
    while (i != null) {
      System.out.println(i.getNome());
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
    tmp = null;
  }

  public void inserirNoFim(Game g) {
    ultimo.prox = g;
    ultimo = g;
  }

  public String removerNoInicio() {
    String nome = primeiro.prox.getNome();
    Game tmp = primeiro;
    primeiro = primeiro.prox;
    tmp.prox = tmp = null;
    return nome;
  }

  public String remover(int pos) throws Exception {
    if (pos > this.tam()) {
      throw new Exception();
    }
    Game tmp = primeiro;
    for (int i = 0; i < pos; i++, tmp = tmp.prox)
      ;
    String nome = tmp.prox.getNome();
    tmp.prox = tmp.prox.prox;
    tmp.prox = tmp = null;
    return nome;
  }

  public String removerNoFim() {

    Game tmp = primeiro;
    for (; tmp.prox != ultimo; tmp = tmp.prox)
      ;
    String nome = tmp.prox.getNome();
    ultimo = tmp;
    tmp.prox = tmp = null;
    return nome;
  }

}
