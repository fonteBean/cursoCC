
public class Lista {
	public static class Celula {
		public int elemento;
		public Celula prox;

		public Celula(int x) {
			this.elemento = x;
			this.prox = null;
		};
	}

	public Lista() {
		this.primeiro = new Celula(0);
		ultimo = primeiro;
	}

	public Celula primeiro, ultimo;

	public void inserir(int x) {
		Celula tmp = new Celula(x);
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		tmp = null;
	}

	public int remover() {
		Celula tmp = primeiro;
		for (; tmp.prox != ultimo; tmp = tmp.prox)
			;
		int resp = ultimo.elemento;
		ultimo = tmp;
		tmp.prox = tmp = null;
		return resp;
	}

	public void imprimir() {
		for (Celula tmp = primeiro.prox; tmp != null; tmp = tmp.prox)
			System.out.println(tmp.elemento);
	}

	public void inverter() {
		Celula i = primeiro;
		Celula j = ultimo;
		System.out.println(j.elemento);

		while (i != j) {
			Celula k = i;
			for (; k.prox != j; k = k.prox)
				;
			System.out.println(j.elemento);

			int tmp = i.elemento;
			i.elemento = j.elemento;
			j.elemento = tmp;
			i = i.prox;
			System.out.println(j.elemento);
			j = k;
		}
	}

}
