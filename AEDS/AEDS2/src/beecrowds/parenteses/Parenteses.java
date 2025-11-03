import java.util.Scanner;



public class Parenteses{



	public static boolean checa(String expressao){
		int tam = expressao.length();
		boolean temAberto = false;
		int contaAbertos=0;
		for(int i=0; i < tam; i++){
			if(expressao.charAt(i) == ')' && !temAberto)
				return false;
			if(expressao.charAt(i) == '('){
				temAberto = true;
				contaAbertos++;
			}
			if(expressao.charAt(i) == ')'){
				contaAbertos--;
				if(contaAbertos == 0){
					temAberto =false;
				}
			}
		}
		if(contaAbertos > 0){
			return false;
		}else{
			return true;
		}
	}
	public static void main(String[] args){
		Scanner scanf = new Scanner(System.in);
		String expressao = scanf.nextLine();
		while(!expressao.equals("FIM")){
			
			if(checa(expressao)){
				System.out.println("correct");
			}else{
				System.out.println("incorrect");
			}
			expressao = scanf.nextLine();
		}
		scanf.close();
	}
}
