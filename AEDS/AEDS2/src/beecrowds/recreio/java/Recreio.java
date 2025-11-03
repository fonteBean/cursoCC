import java.util.Scanner;

public class Recreio {

	public static int ordena(int[] vec) {
		int tam = vec.length;
		int naoTrocou = 0;
		for (int i = 0; i < tam; i++) {
			for (int j = 0; j < tam - i - 1; j++) {
				if (vec[j] < vec[j + 1]) {
					int tmp = vec[j];
					vec[j] = vec[j + 1];
					vec[j + 1] = tmp;
				} else {
					naoTrocou++;
				}
			}
		}
		return naoTrocou;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String numeroTestestr = scanner.nextLine();
		int nTestes = Integer.parseInt(numeroTestestr);

		for (int i = 0; i < nTestes; i++) {

			String nCriancasstr = scanner.nextLine();
			int nCriancas = Integer.parseInt(nCriancasstr);
			int[] notas = new int[nCriancas];
			String[] splitted = (scanner.nextLine()).split(" ");

			for (int j = 0; j < nCriancas; j++) {
				notas[j] = Integer.parseInt(splitted[j]);
			}
			System.out.println(ordena(notas));
		}

	}
}
