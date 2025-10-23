package tps;

import java.io.BufferedReader;
import java.io.File;
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

    
        for (int i = 0; i < n1; i++) {
            esquerda[i] = vetor[inicio + i];
            movimentacoes++; 
        }
        for (int j = 0; j < n2; j++) {
            direita[j] = vetor[meio + 1 + j];
            movimentacoes++;
        }

        int i = 0, j = 0, k = inicio;

        
        while (i < n1 && j < n2) {
            comparacoes++;
            if (esquerda[i].getPrice() <= direita[j].getPrice()) {
                vetor[k] = esquerda[i];
                i++;
            } else {
                vetor[k] = direita[j];
                j++;
            }
            movimentacoes++; 
            k++;
        }

        while (i < n1) {
            vetor[k] = esquerda[i];
            i++;
            k++;
            movimentacoes++;
        }

        while (j < n2) {
            vetor[k] = direita[j];
            j++;
            k++;
            movimentacoes++;
        }
    }



    public static Game buscarPorId(Game[] jogos, int id) {
    for(Game g: jogos){
        if (g.getId() == id) {
            return g;
        }
    }
    return null;
}

    public static void escreveLog() {
        String path = System.getProperty("user.dir") + File.separator + "log.txt"; // cria no diretório atual

        try (FileWriter escritor = new FileWriter(path)) {
            escritor.write("889080\tTempo de execução = " + tempo + "ms\t" 
                            + "Número de Comparações = " + movimentacoes + "\t"
                            + "Número de Movimentações = " + comparacoes + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao escrever o log: " + e.getMessage());
        }
    }
   
    public static void leCsv(Game[] jogos, String path) {
       
         try(BufferedReader scanf = new BufferedReader(new FileReader(path))){
            int tam =0;
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
    return;
}
  
    public static void imprimePreco(Game[] list,int tam){
        System.out.println("| 5 mais caros |");
        for(int i=tam;i>= tam-2;i--){
            list[i].imprimir();
        }
        for(int i=tam;i<=2;i++){
            list[i].imprimir();
        }
        System.out.println("| 5 mais baratos |");
      
    }
     public static  void main(String[] args) {
        Game[] list = new Game[100000];
        String path = "/tmp/games.csv";
        leCsv(list, path);
        try (Scanner scanf = new Scanner(System.in)) {
                Game[] buscas = new Game[100000];
                String line = scanf.nextLine();
                int tamBuscas = 0;
                while (!line.equals("FIM")){
                    int id = Integer.parseInt(line);
                    Game g = buscarPorId(list, id);
                    if (g == null) {
                     System.out.println("Not Found");
                    }else{
                        buscas[tamBuscas] = g;
                        tamBuscas++;
                    }
                    line = scanf.nextLine();
                }
                long inicio = System.nanoTime();
                mergeSort(buscas,0, tamBuscas-1); // organiza para buscar
                long fim = System.nanoTime();
                tempo = fim - inicio;
                escreveLog();
                imprimePreco(buscas, tamBuscas-1);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
