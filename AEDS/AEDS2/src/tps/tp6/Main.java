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

  public static void menu(String entrada) {
    String[] splitted = entrada.split(" ");
    String nome = splitted[1];
    String op = splitted[1];
    switch (op) {

      case "II":
        // inserirNoInicio();
        break;

      default:
        break;
    }

  }

  public static void main(String[] args) {
    Lista jogos = new Lista();
    String path = "/tmp/games.csv";
    leCsv(jogos, path);
    jogos.mostrar();
    try (Scanner scanf = new Scanner(System.in)) {
      int n = scanf.nextInt(); // numero de entradas

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
}
