package exercicios.estruturas.hibrida.arvoreLista.java;

public class Arvore2 {
  No2 raiz;

  public Arvore2() {
    this.raiz = null;
  }

  public void inserir(String nome) {
    raiz = inserir(raiz, nome);
  }

  public No2 inserir(No2 no, String nome) {
    if (no == null) {
      no = new No2(nome);
    } else if (no.nome.compareTo(nome) > 0) {
      no.esq = inserir(no.esq, nome);
    } else if (no.nome.compareTo(nome) < 0) {
      no.dir = inserir(no.dir, nome);
    }
    return no;
  }

  public void imprime() {
    imprime(raiz);
  }

  public void imprime(No2 no) {
    if (no != null) {
      imprime(no.esq);
      System.out.printf("%s ", no.nome);
      imprime(no.dir);
    }
  }

  public int contaPalavra(int tam) {
    return contaPalavra(raiz, tam);
  }

  public int contaPalavra(No2 no, int tam) {
    if (no == null) {
      return 0;
    }
    int resp = no.nome.length() == tam ? 1 : 0;
    return resp + contaPalavra(no.esq, tam) + contaPalavra(no.dir, tam);
  }
}