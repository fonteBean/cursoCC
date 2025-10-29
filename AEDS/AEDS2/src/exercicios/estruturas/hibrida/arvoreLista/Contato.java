package exercicios.estruturas.hibrida.arvoreLista;

public class Contato {
	public String nome, telefone, email;
	public long cpf;

	public Contato() {
	};


	public Contato(String nome, String tel, String email, long cpf) {
		this.nome = nome;
		this.telefone = tel;
		this.email = email;
		this.cpf = cpf;
	}

	public void imprimir() {
		System.out.println(this.nome);
		System.out.println(this.telefone);
		System.out.println(this.email);
		System.out.println(this.cpf);
	}

}
