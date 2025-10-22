package exercicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import exercicios.models.*;

public class Main{

    static long tempo;
    static int comparacoes = 0;
    
    public static void criaHeap(ArrayList<Game> jogos, int tam, int i){
        int raiz = i;
        int esq = 2*i+1;
        int dir = 2*i+2;
      
        if (esq < tam) {
            comparacoes++; 
            if (jogos.get(esq).getId() > jogos.get(raiz).getId()){
                raiz = esq;
                comparacoes++; 
            }
        }

     
        if (dir < tam) {
            comparacoes++; 
            if (jogos.get(dir).getId() > jogos.get(raiz).getId()){
                raiz = dir;
                comparacoes++; 

            }
        }
        if (raiz != i){
            comparacoes++; 
            Game aux = jogos.get(i);
            jogos.set(i, jogos.get(raiz));
            jogos.set(raiz, aux);
            criaHeap(jogos, tam, raiz);
        }
        return;
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
    String path = "AEDS/AEDS2/src/exercicios/log.txt";

    try (FileWriter escritor = new FileWriter(path)) { 
        escritor.write("889080\t" + "Tempo de execução = " + tempo + "ms\tNumero de Comparacoes = " + comparacoes);
    } catch (IOException e) {
        System.err.println("Erro ao escrever o log: " + e.getMessage());
    }
}

    public static Game buscarPorId(ArrayList<Game> jogos, int id) {
    int esq = 0;
    int dir = jogos.size() - 1;
    while (esq <= dir) {
        int meio = esq + (dir - esq) / 2;
        int meioID = jogos.get(meio).getId();
        if (meioID == id) {
            return jogos.get(meio);
        } else if (meioID < id) {
            esq = meio + 1;
        } else {
            dir = meio - 1;
        }
    }
    return null;
}
    public static  void main(String[] args) {
        ArrayList<Game> list = new ArrayList<Game>();
        String path = "/tmp/games.csv";
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
                    int id = Integer.parseInt(line);
                    Game g = buscarPorId(list,id);
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


