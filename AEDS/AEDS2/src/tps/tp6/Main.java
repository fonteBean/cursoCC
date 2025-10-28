package tps.tp6;

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
      System.out.println(e.getMessage() + "ee");
    }
    return;
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
    // leCsv(jogos, path);
    jogos.inserirNoInicio("The last");
    jogos.inserirNoInicio("xicrinha");
    jogos.inserirNoInicio("sapeca");
    jogos.mostrar();
    try (Scanner scanf = new Scanner(System.in)) {
      String ni = scanf.nextLine();
      int n = Integer.parseInt(ni);// numero de entradas
      for (int i = 0; i < n; i++) {
        menu(jogos, scanf.nextLine());
      }
      jogos.mostrar();

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
}
