package exercicios.estruturas;

public class Celula {
    public int elemento;
    public Celula prox;

    public Celula(int elemento) {
        this.elemento = elemento;
        this.prox = null;
    }

    public Celula() {
        this(0);
    }
}
