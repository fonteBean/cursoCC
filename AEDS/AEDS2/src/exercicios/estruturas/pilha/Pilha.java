package exercicios.estruturas.pilha;

import exercicios.estruturas.Celula;

public class Pilha {
    private Celula topo;

    public Pilha() {
        topo = null;
    }

    public void inserir(int elemento) {
        Celula nova = new Celula(elemento);
        nova.prox = topo;
        topo = nova;
        nova = null;
    }

    public int remover() throws Exception {
        if (topo == null) {
            throw new Exception();
        }
        int elemento = topo.elemento;
        Celula tmp = topo;
        topo = topo.prox;
        tmp.prox = null;
        tmp = null;
        return elemento;
    }

    public void mostrar() {
        mostrar(topo);
    }

    private void mostrar(Celula i) {
        if (i != null) {
            System.out.println(i.elemento);
            mostrar(i.prox);
        }
    }
}
