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

    if (esq < tam) {
        comparacoes++;
        if (jogos[esq].getId() > jogos[raiz].getId()) {
            raiz = esq;
        }
    }

    if (dir < tam) {
        comparacoes++;
        if (jogos[dir].getId() > jogos[raiz].getId()) {
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
    escritor.write("889080\tTempo de execução = " + tempo + "ms\t" 
                            + "Número de Comparações = " + movimentacoes + "\t"
                            + "Número de Movimentações = " + comparacoes + "\n");    } catch (IOException e) {
        System.err.println("Erro ao escrever o log: " + e.getMessage());
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

    public static void leArquivo(Game[] jogos, String path) {
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
    return;
}
    public static  void main(String[] args) {
        Game[] list = new Game[10000000];
        String path = "/tmp/games.csv";
        leArquivo(list, path);
        try (Scanner scanf = new Scanner(System.in)) {
                String line = scanf.nextLine();
                int tamBusca = 0; // Tamanho do array de buscas
                Game[] buscas = new Game[100000];
                while (!line.equals("FIM")){
                    int id = Integer.parseInt(line);
                    Game g = buscarPorId(list, id);
                    if (g == null){
                       System.out.println("Not found");
                    }else{
                        buscas[tamBusca] = g;
                        tamBusca++;
                    }
                    line = scanf.nextLine();
                }
                //ordena por Owmers
                long inicio = System.nanoTime();
                heapsort(buscas, tamBusca);
                long fim = System.nanoTime();
                tempo = fim - inicio;
                escreveLog();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
            
       
    }
}
