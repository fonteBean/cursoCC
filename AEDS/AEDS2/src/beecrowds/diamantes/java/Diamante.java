import java.util.Scanner;


public class Diamante{


	public static int garimpa(String expressao){
		int tam = expressao.length();
		boolean temAberto =false;
		int contaAbertos = 0;
		int resp =0;
		for(int i=0; i < tam;i++){
			if(expressao.charAt(i) == '>' && !temAberto){
				continue;
			}
			if(expressao.charAt(i) == '<'){
				contaAbertos++;
				temAberto =true;
			}
			if(expressao.charAt(i) == '>'){
				contaAbertos--;
				resp++;
				if(contaAbertos == 0){
					temAberto = false;
				}
			}
		}
		return resp;
	}
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		String nEntradastr = scanner.nextLine();
		int nEntrada = Integer.parseInt(nEntradastr);
		for(int i =0; i < nEntrada; i++){
			String expressao = scanner.nextLine();
			int conta = garimpa(expressao);
			System.out.println(conta);
		}
		scanner.close();
	}
}
