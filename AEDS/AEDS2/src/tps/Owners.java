package tps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


import exercicios.models.Game;

public class Owners {
   static int movimentacoes = 0;
    static long tempo;
    static int comparacoes = 0;

    public static void criaHeap(Game[] jogos, int tam, int i) {
        int raiz = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        if (esq < tam && jogos[esq] != null) {
            comparacoes++;
            if (jogos[raiz] == null ||
                jogos[esq].getEstimedOwners() > jogos[raiz].getEstimedOwners() ||
                (jogos[esq].getEstimedOwners() == jogos[raiz].getEstimedOwners() &&
                 jogos[esq].getId() < jogos[raiz].getId())) {
                raiz = esq;
            }
        }

        if (dir < tam && jogos[dir] != null) {
            comparacoes++;
            if (jogos[raiz] == null ||
                jogos[dir].getEstimedOwners() > jogos[raiz].getEstimedOwners() ||
                (jogos[dir].getEstimedOwners() == jogos[raiz].getEstimedOwners() &&
                 jogos[dir].getId() < jogos[raiz].getId())) {
                raiz = dir;
            }
        }
        if (raiz != i) {
            movimentacoes++;
            Game aux = jogos[i];
            jogos[i] = jogos[raiz];
            jogos[raiz] = aux;
            criaHeap(jogos, tam, raiz);
        }
    }

    public static void heapsort(Game[] jogos, int tam) {
        for (int i = tam / 2 - 1; i >= 0; i--) {
            criaHeap(jogos, tam, i);
        }

        for (int j = tam - 1; j > 0; j--) {
            movimentacoes += 3;
            Game aux = jogos[0];
            jogos[0] = jogos[j];
            jogos[j] = aux;
            criaHeap(jogos, j, 0);
        }
    }

    public static void escreveLog() {
        String path = System.getProperty("user.dir") + File.separator + "log.txt";
        try (FileWriter escritor = new FileWriter(path)) {
            escritor.write("889080\tTempo de execução = " + tempo + "ms\t" +
                    "Número de Comparações = " + comparacoes + "\t" +
                    "Número de Movimentações = " + movimentacoes + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao escrever o log: " + e.getMessage());
        }
    }

    public static Game buscarPorId(Game[] jogos, int id) {
        for (Game g : jogos) {
            if (g != null && g.getId() == id) {
                return g;
            }
        }
        return null;
    }

    public static int leArquivo(Game[] jogos, String path) {
        int tam = 0;
        try (BufferedReader scanf = new BufferedReader(new FileReader(path))) {
            scanf.readLine(); 
            String line;
            while ((line = scanf.readLine()) != null) {
                Game game = Game.fromCSV(line);
                jogos[tam++] = game;
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return tam; 
    }

    public static void main(String[] args) {
        Game[] list = new Game[10000000];
        String path = "/tmp/games.csv";
        int tam = leArquivo(list, path); // pega tamanho real

        try (Scanner scanf = new Scanner(System.in)) {
            String line = scanf.nextLine();
            int tamBusca = 0;
            Game[] buscas = new Game[100000];

            while (!line.equals("FIM")) {
                int id = Integer.parseInt(line);
                Game g = buscarPorId(list, id);
                if (g == null) {
                    System.out.println("Not found");
                } else {
                    buscas[tamBusca++] = g;
                }
                line = scanf.nextLine();
            }
            long inicio = System.nanoTime();
            heapsort(buscas, tamBusca);
            long fim = System.nanoTime();
            tempo = fim - inicio;
            for (int i = 0; i < tamBusca; i++) {
               buscas[i].imprimir();
            }

            escreveLog();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    
    }
}