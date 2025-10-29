package exercicios.estruturas.hibrida;

public class Agenda {
  No raiz;

  public Agenda() {
    this.raiz = new No('M');
    String alfabeto = "ABCDEFGHIJKLNOPQRSTUVWXYZ";
    char[] letras = alfabeto.toCharArray();
    for (int i = 0; i < letras.length; i++) {
      inserirNo(letras[i]);
    }
  }

  public void imprimeAgenda() {
    imprimeAgendaRec(raiz);
  }

  public void imprimeAgendaRec(No no) {
    if (no != null) {
      imprimeAgendaRec(no.esq);
      System.out.println(no.letra);
      imprimeAgendaRec(no.dir);
    }
  }

  public void inserirNo(char c) {
    raiz = inserirNoRec(c, raiz);
  }

  public No inserirNoRec(char c, No no) {
    if (no == null) {
      no = new No(c);
    } else if (c < no.letra) {
      no.esq = inserirNoRec(c, no.esq);
    } else if (c > no.letra) {
      no.dir = inserirNoRec(c, no.dir);
    }
    return no;
  }

  public void inserir(Contato c) throws Exception {
    raiz = inserir(c, raiz);
  }

  public No inserir(Contato c, No pai) throws Exception {
    if (c.nome.charAt(0) == pai.letra) {
      pai.inserir(new Celula(c));
      ;
    } else if (c.nome.charAt(0) > pai.letra) {
      pai.dir = inserir(c, pai.dir);
    } else if (c.nome.charAt(0) < pai.letra) {
      pai.esq = inserir(c, pai.esq);
    } else {
      throw new Exception();
    }
    return pai;
  }

  public boolean pesquisar(String nome) {
    No noDestino = caminhaAteNo(nome.charAt(0));
    int achou = noDestino.busca(nome);
    if (achou > -1) {
      return true;
    } else {
      return false;
    }
  }

  public boolean pesquisar(long cpf) {
    return pesquisarCpf(cpf, raiz);
  }

  public String remover(String nome) {
    No noDestino = caminhaAteNo(nome.charAt(0));
    return noDestino.remover(nome);

  }

  public boolean pesquisarCpf(long cpf, No no) {
    boolean resp = false;
    if (no != null) {
      resp = no.buscaCpf(cpf) || pesquisarCpf(cpf, no.dir) || pesquisarCpf(cpf, no.esq);
    }
    return resp;
  }

  public No caminhaAteNo(char c) {
    No tmp = raiz;
    while (c != tmp.letra) {
      if (c > tmp.letra) {
        tmp = tmp.dir;
      } else {
        tmp = tmp.esq;
      }
    }
    return tmp;
  }
}