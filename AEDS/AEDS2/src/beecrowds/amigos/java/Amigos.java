package beecrowds.amigos.java;

import java.util.Scanner;

public class Amigos {

  public static void swap(String[] amigos, int i, int j) {
    String tmp = amigos[i];
    amigos[i] = amigos[j];
    amigos[j] = tmp;

  }

  public static int particiona(String[] amigos, int inicio, int fim) {
    String pivo = amigos[fim];
    int i = inicio;
    for (int j = inicio; j < fim; j++) {
      if (amigos[j].compareTo(pivo) < 0) {
        swap(amigos, i, j);
        i++;
      }
    }
    swap(amigos, i, fim);
    return i;
  }

  public static void quicksort(String[] amigos, int inicio, int fim) {
    if (fim > inicio) {
      int pivo = particiona(amigos, inicio, fim);
      quicksort(amigos, inicio, pivo - 1);
      quicksort(amigos, pivo + 1, fim);
    }
  }

  public static boolean checaNaLista(String[] amigos, String nome, int tam) {
    for (int i = 0; i < tam; i++) {
      if (amigos[i].equals(nome)) {
        return false;
      }
    }
    return true;
  }

  public static void imprimeVetor(String[] vec, int tam) {
    for (int i = 0; i < tam; i++) {
      System.out.println(vec[i]);
    }
  }

  public static String amigo(String[] amigos, int tam) {
    String nome = amigos[0];
    int menor = 0;
    for (int i = 1; i < tam; i++) {
      if (amigos[i].length() > nome.length()) {
        nome = amigos[i];

        if (amigos[i].length() == nome.length() && menor > i) {
          nome = amigos[i];
          ;
          menor = i;
        }

      }

    }
    return nome;
  }

  public static void main(String[] args) {

    String[] amigos = new String[1000];
    int tamAmigos = 0;
    String[] colegas = new String[1000];
    int tamColegas = 0;
    Scanner scanf = new Scanner(System.in);
    String line = scanf.nextLine().trim();
    try {
      while (!line.equals("FIM")) {
        String[] splitted = line.split(" ");
        if (splitted.length < 2) {
          line = scanf.nextLine().trim();
          continue;
        }

        String nome = splitted[0];
        String resposta = splitted[1];

        if (resposta.equals("YES") && checaNaLista(amigos, nome, tamAmigos)) {
          amigos[tamAmigos] = nome;
          tamAmigos++;
        } else if (resposta.equals("NO")) {
          colegas[tamColegas] = nome;
          tamColegas++;
        }

        line = scanf.nextLine().trim();
      }
      String amigo = amigo(amigos, tamColegas);
      quicksort(amigos, 0, tamAmigos - 1);
      quicksort(colegas, 0, tamColegas - 1);
      imprimeVetor(amigos, tamAmigos);
      imprimeVetor(colegas, tamColegas);
      System.out.printf("\nAmigo do Habay:\n%s", amigo);
      scanf.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
