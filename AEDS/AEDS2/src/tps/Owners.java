package tps;

import java.io.BufferedReader;
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
        if (jogos[esq].getEstimedOwners() > jogos[raiz].getEstimedOwners() ||
            (jogos[esq].getEstimedOwners() == jogos[raiz].getEstimedOwners() &&
             jogos[esq].getId() > jogos[raiz].getId())) {
            raiz = esq;
        }
    }


    if (dir < tam) {
        comparacoes++;
        if (jogos[dir].getEstimedOwners() > jogos[raiz].getEstimedOwners() ||
            (jogos[dir].getEstimedOwners() == jogos[raiz].getEstimedOwners() &&
             jogos[dir].getId() > jogos[raiz].getId())) {
            raiz = dir;
        }
    }

    if (raiz != i) {
        movimentacoes += 3; 
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
    String path = "AEDS/AEDS2/src/exercicios/log.txt";

    try (FileWriter escritor = new FileWriter(path)) { 
        escritor.write("889080\t" + "Tempo de execução = " + tempo + "ms\tNumero de Comparacoes = " + comparacoes);
    } catch (IOException e) {
        System.err.println("Erro ao escrever o log: " + e.getMessage());
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
    public static  void main(String[] args) {
        Game[] list = new Game[10000000];
        String path = "/tmp/games.csv";
         int tam = 0;
        try(BufferedReader scanf = new BufferedReader(new FileReader(path))){
            String line = scanf.readLine();
            line = scanf.readLine();
            while (line != null){
                Game game;
                game = Game.fromCSV(line);
                list[tam] = game;
                line = scanf.readLine();
                tam++;
            }
           
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
                    long inicio = System.nanoTime();
                    heapsort(list,tam);
                    long fim = System.nanoTime();
                    tempo = fim - inicio;
                    escreveLog();
            try (Scanner scanf = new Scanner(System.in)) {
                String line = scanf.nextLine();
                while (!line.equals("FIM")){
                    int id = Integer.parseInt(line);
                    Game g = buscarPorId(list,id,tam);
                    if (g !=null){
                        System.out.println("SIM");
                    }else{
                        System.out.println("NAO");
                    }
                    line = scanf.nextLine();

                }
            }
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
