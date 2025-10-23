package tps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import exercicios.models.Game;

public class Preco {
    
    static int movimentacoes = 0;   
    static int comparacoes = 0;
    static long tempo;  


    public static void mergeSort(Game[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;

            mergeSort(vetor, inicio, meio);
            mergeSort(vetor, meio + 1, fim);

            merge(vetor, inicio, meio, fim);
        }
    }

    public static void merge(Game[] vetor, int inicio, int meio, int fim) {
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;

        Game[] esquerda = new Game[n1];
        Game[] direita = new Game[n2];

        for (int i = 0; i < n1; i++)
            esquerda[i] = vetor[inicio + i];
        for (int j = 0; j < n2; j++)
            direita[j] = vetor[meio + 1 + j];

        int i = 0, j = 0, k = inicio;

        while (i < n1 && j < n2) {
            if (esquerda[i].getPrice() <= direita[j].getPrice()) {
                vetor[k] = esquerda[i];
                i++;
            } else {
                vetor[k] = direita[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            vetor[k] = esquerda[i];
            i++;
            k++;
        }
        while (j < n2) {
            vetor[k] = direita[j];
            j++;
            k++;
        }
    }
    
    public static Game buscarPorId(Game[] jogos, int id, int tam) {
    int esq = 0;
    int dir = tam - 1;
    while (esq <= dir) {
        int meio = esq + (dir - esq) / 2;
        int meioID = jogos[meio].getId();
        if (meioID == id) {
            return jogos[meio];
        } else if (meioID < id) {
            esq = meio + 1;
        } else {
            dir = meio - 1;
        }
    }
    return null;
}

   public static void escreveLog() {
    String path = "AEDS/AEDS2/src/tps/src/tp05/log.txt";

    try (FileWriter escritor = new FileWriter(path,true)) { 
        escritor.write("889080\t" + "Tempo de execução = " + tempo + "ms\tNumero de Comparacoes = " + comparacoes);
    } catch (IOException e) {
        System.err.println("Erro ao escrever o log: " + e.getMessage());
    }
}    
    public static int leCsv(Game[] jogos, String path) {
        int tam =0;
         try(BufferedReader scanf = new BufferedReader(new FileReader(path))){
       
            String line = scanf.readLine();
            line = scanf.readLine();
            while (line != null){
                Game game;
                game = Game.fromCSV(line);
                jogos[tam] = game;
                line = scanf.readLine();
                tam++;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    return tam;
}
  
    public static void imprimePreco(Game[] list,int tam){

        System.out.println("| 5 mais caros | ");
        for(int i =0; i< tam; i++){
            list[i].imprimir();
        }
        System.out.println("| 5 mais baratos | ");
        for(int i = tam-1; i >=0 ; i--){
            list[i].imprimir();
        }

    }
     public static  void main(String[] args) {
        Game[] list = new Game[100000];
        String path = "/tmp/games.csv";
        int tamCsv = leCsv(list, path);

        long inicio = System.nanoTime();
        mergeSort(list,0, tamCsv); // trocar pelo merge
        long fim = System.nanoTime();
        tempo = fim - inicio;
        escreveLog();
        Game[] buscas = new Game[100000];
        try (Scanner scanf = new Scanner(System.in)) {
                String line = scanf.nextLine();
                int tamBuscas = 0;
                while (!line.equals("FIM")){
                    int id = Integer.parseInt(line);
                    Game g = buscarPorId(list, id, tamCsv);
                    if (g != null) {
                        buscas[tamBuscas] = g;
                        tamBuscas++;
                    }
                    line = scanf.nextLine();
                }
                imprimePreco(list, tamBuscas);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
