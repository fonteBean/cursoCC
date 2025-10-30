package tps.tp6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import exercicios.models.Game;

public class Pilhagem {

  public static class Pilha {
    private Game topo;

    public Pilha() {
      topo = null;
    }

    public void inserir(Game g) {
      if (topo == null) {
        topo = g;
      }
      g.prox = topo.prox;
      topo = g;
    }

    public String remover() throws Exception {
      if (topo == null) {
        throw new Exception();
      }
      String nome = topo.getNome();
      topo = topo.prox;
      return nome;
    }

    public Game buscaGame(int id) {
      for (Game g = topo; g != null; g = g.prox) {
        if (g.getId() == id) {
          return g;
        }
      }
      return null;
    }

    public void mostrar() {
      Game i = topo.prox;
      while (i != null) {
        System.out.println(i.getNome());
        i = i.prox;
      }
    }
  }

  public static void leCsv(Pilha jogos, String path) {
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

  public static Pilha criaSubPilha(Pilha jogos) {

    Pilha subPilha = new Pilha();
    try (Scanner scanf = new Scanner(System.in)) {
      String line = scanf.nextLine();
      while (!line.equals("FIM")) {
        int id = Integer.parseInt(line);
        Game g = jogos.buscaGame(id);
        if (g != null) {
          subPilha.inserir(g);
        } else {
          System.out.println("Not Found");
        }
        line = scanf.nextLine();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return subPilha;
  }

  public static void menu(Pilha jogos, String entrada) throws Exception {
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
    Pilha jogos = new Pilha();
    String path = "/tmp/games.csv";
    leCsv(jogos, path);
    jogos.mostrar();

    Pilha subLista = criaSubPilha(jogos);
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
