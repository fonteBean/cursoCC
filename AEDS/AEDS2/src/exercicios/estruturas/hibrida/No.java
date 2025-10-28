package exercicios.estruturas.hibrida;

public class No {
	public char letra;
	public No esq, dir;
	public Celula primeiro, ultimo;

	public No(char letra) {
		this.letra = letra;
		this.esq = this.dir = null;
		primeiro = new Celula();
		ultimo = primeiro;
	}

	public void imprimirLista() {
		Celula tmp = primeiro.prox;
		for (; tmp != null; tmp = tmp.prox) {
			System.out.println(tmp.contato.nome);
		}
	}

	public void inserir(Celula c) {
		c.prox = primeiro.prox;
		primeiro.prox = c;
		c = null;
	}

	public int busca(String nome) {
		Celula tmp = primeiro.prox;
		int pos = 0;
		for (; tmp != null; tmp = tmp.prox, pos++) {
			if (tmp.contato.nome.equals(nome)) {
				return pos;
			}
		}
		return -1;
	}

	public String remover(String nome) {
		int pos = busca(nome);
		if (pos < 0)
			return null;
		Celula tmp = primeiro.prox;
		for (int i = 0; i < pos; i++, tmp = tmp.prox)
			;
		tmp.prox = tmp.prox.prox;
		tmp.prox = null;
		tmp = null;
		return nome;
	}

	public boolean buscaCpf(long cpf) {
		Celula tmp = primeiro.prox;
		for (; tmp != null; tmp = tmp.prox) {
			if (tmp.contato.cpf == cpf) {
				tmp.prox = tmp = null;
				return true;
			}
		}
		tmp = null;
		return false;
	}

}
