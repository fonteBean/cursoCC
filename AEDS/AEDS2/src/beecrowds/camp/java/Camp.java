package beecrowds.camp.java;

import java.util.Scanner;

public class Camp {
	public static class Crianca {
		String nome;
		int ficha;
		Crianca ant, prox;

		public Crianca(String nome, int ficha) {
			this.nome = nome;
			this.ficha = ficha;
			this.ant = this.prox = null;
		}

	}

	public static class Lista {
		Crianca inicio, fim;

		public Lista() {
			this.fim = null;
			this.inicio = fim;
		}

		public void inserir(Crianca c) {
			if (inicio == null) {
				inicio = fim = c;
				c.prox = c;
				c.ant = c;
				return;
			}
			fim.prox = c;
			c.ant = fim;
			fim = c;
			c.prox = inicio;
			inicio.ant = c;
		}

		public Crianca remover(Crianca comeco, int passos) {
			Crianca tmp = caminha(comeco, passos);
			if (inicio == tmp.prox) {
				int e = inicio.ficha;
				inicio = inicio.prox;
				fim.prox = inicio;
				inicio.ant = fim;
				return e;
			} else if (fim == tmp.prox) {
				Crianca e = fim.ficha;
				fim = fim.ant;
				inicio.ant = fim;
				fim.prox = inicio;
				return e;
			}
			Crianca e = tmp.prox.ficha;
			tmp.prox = tmp.prox.prox;
			tmp.prox.ant = tmp;
			return e;
		}

		public Crianca caminha(Crianca comeco, int passos) {
			Crianca tmp = comeco;
			if (passos % 2 == 0) {
				for (int i = 0; i < passos; i++, tmp = tmp.prox)
					;
			} else {
				for (int i = 0; i < passos; i++, tmp = tmp.ant)
					;
			}
			return tmp;
		}

		public String vencedor() {
			if (inicio == fim && inicio != null) {
				return inicio.nome;
			}
			return "deu merda";
		}

		public void imprime() {
			Crianca tmp = inicio;
			System.out.printf("[%s ", tmp.nome);
			tmp = tmp.prox;
			for (; tmp != inicio; tmp = tmp.prox)
				System.out.printf("%s ", tmp.nome);

			System.out.printf("]\n");
		}

	}

	public static void jogo(Lista lista, int n) {
		int passos = lista.inicio.ficha;
		while (n > 1) {
			passos = lista.remover(passos);
			n--;
			lista.imprime();
		}
		System.out.println("Vencedor(a): " + lista.vencedor());
	}

	public static void main(String[] args) {
		Scanner scanf = new Scanner(System.in);
		String nCriancas = scanf.nextLine();
		int n = Integer.parseInt(nCriancas);
		while (n > 0) {
			Lista lista = new Lista();
			for (int i = 0; i < n; i++) {
				String entrada = scanf.nextLine();

				String[] splitted = entrada.split(" ");
				lista.inserir(new Crianca(splitted[0], Integer.parseInt(splitted[1])));
			}

			jogo(lista, n);

			n = Integer.parseInt(scanf.nextLine());
		}
	}
}
