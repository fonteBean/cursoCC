package exercicios.estruturas.hibrida.arvoreLista.java;

public class Arvore {
  No raiz;

  public Arvore() {
    this.raiz = new No('M');
    String alfabeto = "ABCDEFGHIJKLNOPQRSTUVWXYZ";
    char[] letras = alfabeto.toCharArray();
    for (int i = 0; i < letras.length; i++) {
      inserirNo(letras[i]);
    }
  }

  private void inserirNo(char c) {
    raiz = inserirNoRec(c, raiz);
  }

  private No inserirNoRec(char c, No no) {
    if (no == null) {
      no = new No(c);
    } else if (c < no.letra) {
      no.esq = inserirNoRec(c, no.esq);
    } else if (c > no.letra) {
      no.dir = inserirNoRec(c, no.dir);
    }
    return no;
  }

  public void inserir(String nome) {
    nome = nome.toUpperCase();
    inserir(raiz, nome);
  }

  public No inserir(No no, String nome) {
    if (no.letra == nome.charAt(0)) {
      no.interno.inserir(nome);
    } else if (nome.charAt(0) > no.letra) {
      no.dir = inserir(no.dir, nome);
    } else if (nome.charAt(0) < no.letra) {
      no.esq = inserir(no.esq, nome);
    }
    return no;
  }

  public void imprimeLetra(char c) {
    imprimeLetra(raiz, c);
  }

  public void imprimeLetra(No no, char c) {
    System.out.println(no.letra);
    if (no.letra == c) {
      no.interno.imprime();
    } else if (no.letra > c) {
      imprimeLetra(no.esq, c);
    } else if (no.letra < c) {
      imprimeLetra(no.dir, c);
    }

  }

  public void caminha() {
    caminha(raiz);
  }

  public void caminha(No no) {
    if (no != null) {
      caminha(no.esq);
      System.out.println(no.letra);
      caminha(no.dir);
    }

  }

  public int contaPalavra(String palavra) {
    return contaPalavra(raiz, palavra.charAt(0), palavra.length(), 0);
  }

  public int contaPalavra(No no, char c, int tam, int resp) {
    if (no.letra == c) {
      resp = no.interno.contaPalavra(tam);
    } else if (no.letra > c) {
      resp = contaPalavra(no.esq, c, tam, resp);
    } else if (no.letra < c) {
      resp = contaPalavra(no.dir, c, tam, resp);
    }
    return resp;
  }

}
