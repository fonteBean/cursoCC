package tps.tp6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import exercicios.models.Game;

public class Inss {
  public static class Fila {
    Game inicio, fim;

    public Fila() {
    };

    public void inserir(Game g) {
      fim.prox = g;
      fim = g;
    }

    public String remover() {
      String nome = inicio.getNome();
      inicio = inicio.prox;
      return nome;
    }

    public Game buscaGame(int id) {
      for (Game g = inicio; g != null; g = g.prox) {
        if (g.getId() == id) {
          return g;
        }
      }
      return null;
    }

    public void mostrar() {
      Game i = inicio.prox;
      while (i != null) {
        System.out.println(i.getNome());
        i = i.prox;
      }
    }
  }

  public static void leCsv(Fila jogos, String path) {
    try (BufferedReader scanf = new BufferedReader(new FileReader(path))) {
      String line = scanf.readLine();
      line = scanf.readLine();
      while (line != null) {
        Game game;
        game = Game.fromCSV(line);
        jogos.inserir(game);
        line = scanf.readLine();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return;
  }

  public static Fila criaSubFila(Fila jogos) {

    Fila subFila = new Fila();
    try (Scanner scanf = new Scanner(System.in)) {
      String line = scanf.nextLine();
      while (!line.equals("FIM")) {
        int id = Integer.parseInt(line);
        Game g = jogos.buscaGame(id);
        if (g != null) {
          subFila.inserir(g);
        } else {
          System.out.println("Not Found");
        }
        line = scanf.nextLine();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return subFila;
  }

  public static void menu(Fila jogos, String entrada) throws Exception {
    String[] splitted = entrada.split(" ");
    String op = splitted[0];
    int id = Integer.parseInt(splitted[1]);
    Game g = new Game(id);
    System.out.println(id + "  " + op);
    switch (op) {
      case "I":
        jogos.inserir(g);
        break;
      case "R":
        jogos.remover();
        break;

      default:
        System.out.println("Escreva uma opção válida");
        break;
    }

  }

  public static void main(String[] args) {
    Fila jogos = new Fila();
    String path = "/tmp/games.csv";
    leCsv(jogos, path);
    jogos.mostrar();

    Fila subLista = criaSubFila(jogos);
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
