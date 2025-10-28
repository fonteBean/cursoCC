package exercicios.estruturas.hibrida;

public class Agenda {
  No raiz;

  public Agenda() {
    this.raiz = new No('M');
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

  public String pesquisar(String nome) {
    No noDestino = caminhaAteNo(nome.charAt(0));
    int achou = noDestino.busca(nome);
    if (achou > -1) {
      return nome;
    } else {
      return null;
    }
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