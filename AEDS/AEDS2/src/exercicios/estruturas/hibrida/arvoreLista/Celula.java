package exercicios.estruturas.hibrida.arvoreLista;

public class Celula {
	public Contato contato;
	public Celula prox;

	public Celula() {
		this.contato = new Contato();
		this.prox = null;
	}

	public Celula(Contato c) {
		this.contato = c;
		this.prox = null;
	}
}
