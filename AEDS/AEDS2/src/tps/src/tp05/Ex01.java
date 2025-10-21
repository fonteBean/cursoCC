package tps.src.tp05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exercicios.models.Game;

public class Ex01 {
    static int comparacoes = 0;
    static long tempo;

    public static int comparaNomes(String a, String b) {
        int len = Math.min(a.length(), b.length());

        for (int i = 0; i < len; i++) {
            char ca = a.charAt(i);
            char cb = b.charAt(i);

            if (ca != cb) {
                return ca - cb; 
            }
        }

        return a.length() - b.length();
    }   

    public static void criaHeap(ArrayList<Game> jogos, int tam, int i) {
    int raiz = i;
    int esq = 2 * i + 1;
    int dir = 2 * i + 2;

    if (esq < tam) {
        comparacoes++;
        if (comparaNomes(jogos.get(esq).getNome(), jogos.get(raiz).getNome()) > 0) {
            raiz = esq;
        }
    }

    if (dir < tam) {
        comparacoes++;
        if (comparaNomes(jogos.get(dir).getNome(), jogos.get(raiz).getNome()) > 0) {
            raiz = dir;
        }
    }

    if (raiz != i) {
        Game aux = jogos.get(i);
        jogos.set(i, jogos.get(raiz));
        jogos.set(raiz, aux);
        criaHeap(jogos, tam, raiz);
    }
}
    public static  void heapsort(ArrayList<Game> jogos)
    {
        int tam = jogos.size();
        for (int i= tam/2-1; i >=0; i--){
            comparacoes++; 
            criaHeap(jogos,tam,i);
        }
        for (int j = tam-1;j>0;j--){
            comparacoes++; 
            Game aux = jogos.get(0);
            jogos.set(0, jogos.get(j));
            jogos.set(j, aux);
            criaHeap(jogos,j,0);
        }   
    }
    

   public static void escreveLog() {
    String path = "AEDS/AEDS2/src/tps/src/tp05/log.txt";

    try (FileWriter escritor = new FileWriter(path,true)) { 
        escritor.write("889080\t" + "Tempo de execução = " + tempo + "ms\tNumero de Comparacoes = " + comparacoes);
    } catch (IOException e) {
        System.err.println("Erro ao escrever o log: " + e.getMessage());
    }
}

    public static Game buscarPorNome(ArrayList<Game> jogos, String nome) {
    int esq = 0;
    int dir = jogos.size() - 1;
    while (esq <= dir) {
        int meio = esq + (dir - esq) / 2;
        String meioNome = jogos.get(meio).getNome();
        if (meioNome.equals(nome)) {
            return jogos.get(meio);
        } else if (comparaNomes(meioNome, nome) < 0 ) {
            esq = meio + 1;
        } else {
            dir = meio - 1;
        }
    }
    return null;
    }
   
     public static  void main(String[] args) {
        ArrayList<Game> list = new ArrayList<Game>();
        String path = "AEDS/AEDS2/src/exercicios/games.csv";
        try(BufferedReader scanf = new BufferedReader(new FileReader(path))){
            String line = scanf.readLine();
            line = scanf.readLine();

            while (line != null){
                Game game;
                game = Game.fromCSV(line);
                list.add(game);
                line = scanf.readLine();
            }
            
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
                    long inicio = System.nanoTime();
                    heapsort(list);
                    long fim = System.nanoTime();
                    tempo = fim - inicio;
                    escreveLog();
            try (Scanner scanf = new Scanner(System.in)) {
                String line = scanf.nextLine();
                while (!line.equals("FIM")){
                    Game g = buscarPorNome(list,line);
                    if (g !=null){
                        g.imprimir();
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
