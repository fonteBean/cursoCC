package exercicios.estruturas.hibrida;

public class Main {

	public static void main(String[] args) {
		Contato ni = new Contato("Nicolas", "22920048384", "n9ckbean@gmail.com", 14020427798L);
		Celula cel0 = new Celula(ni);

		Contato na = new Contato("Naivete", "2299853423", "naivetePacheco@gmail.com", 227765412398L);
		Celula cel1 = new Celula(na);

		No no = new No('N');
		no.inserir(cel0);
		no.inserir(cel1);
		no.imprimirLista();
	}
}
