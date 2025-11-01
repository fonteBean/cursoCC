package exercicios.estruturas.hibrida.arvoreLista.java;

public class Main {

  public static void main(String[] args) {
    Arvore arvore = new Arvore();
    arvore.inserir("PROVA");
    arvore.inserir("PORTA");
    arvore.inserir("PORCA");

    int i = arvore.contaPalavra("PORRA");
    System.out.println(i);

  }
}
