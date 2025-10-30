
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import exercicios.models.Game;

public class Main {

  static int movimentacoes = 0;
  static int comparacoes = 0;
  static long tempo;

  public static void leCsv(Lista jogos, String path) {
    try (BufferedReader scanf = new BufferedReader(new FileReader(path))) {
      String line = scanf.readLine();
      line = scanf.readLine();
      while (line != null) {
        Game game;
        game = Game.fromCSV(line);
        jogos.inserirNoInicio(game);
        line = scanf.readLine();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return;
  }

  public static Lista criaSubArray(Lista jogos) {

    Lista subLista = new Lista();
    try (Scanner scanf = new Scanner(System.in)) {
      String line = scanf.nextLine();
      while (!line.equals("FIM")) {
        int id = Integer.parseInt(line);
        Game g = jogos.buscaGame(id);
        if (g != null) {
          subLista.inserirNoInicio(g);
        } else {
          System.out.println("Not Found");
        }
        line = scanf.nextLine();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return subLista;
  }

  public static void menu(Lista jogos, String entrada) throws Exception {
    String[] splitted = entrada.split(" ");
    String op = splitted[0];
    String nome = splitted[1];
    System.out.println(nome + "  " + op);
    switch (op) {
      case "II":
        jogos.inserirNoInicio(nome);
        break;
      case "I*":
        jogos.inserirNoInicio(nome);
        break;
      case "IF":
        jogos.inserirNoFim(nome);
        break;
      case "RI":
        jogos.removerNoInicio();
        break;
      case "R*":
        jogos.remover(3);
        break;
      case "RF":
        jogos.removerNoFim();
        break;

      default:
        System.out.println("Escreva uma opção válida");
        break;
    }

  }

  public static void main(String[] args) {
    Lista jogos = new Lista();
    String path = "/tmp/games.csv";
    leCsv(jogos, path);
    jogos.mostrar();

    Lista subLista = criaSubArray(jogos);
    subLista.mostrar();

    try (Scanner scanf = new Scanner(System.in)) {

      String line = scanf.nextLine();
      int n = Integer.parseInt(line);

      for (int i = 0; i < n; i++) {
        menu(subLista, scanf.nextLine());
      }
      subLista.mostrar();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
}
