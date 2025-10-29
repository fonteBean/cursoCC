package exercicios.estruturas.hibrida.arvoreLista;

public class Main {

	public static void main(String[] args) throws Exception {
		Contato ni = new Contato("Nicolas", "22920048384", "n9ckbean@gmail.com", 14020427798L);
		Contato na = new Contato("Naivete", "2299853423", "naivetePacheco@gmail.com", 227765412398L);
		Contato am = new Contato("Amanda", "22988240817", "amandaSantos@gmail.com", 45234123498L);

		Agenda agenda = new Agenda();
		agenda.inserir(ni);
		agenda.inserir(na);
		agenda.inserir(am);
		if (agenda.pesquisar("Nicolas")) {
			System.out.println("Encontrado");
		}
		agenda.remover("Nicolas");
		if (!agenda.pesquisar("Nicolas")) {
			System.out.println("Nao encontrado");
		}
		;

	}
}
