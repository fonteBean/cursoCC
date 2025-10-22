package tps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import exercicios.models.Game;
public class Nome {



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

    public static void criaHeap(Game[] jogos, int tam, int i) {
    int raiz = i;
    int esq = 2 * i + 1;
    int dir = 2 * i + 2;

    if (esq < tam) {
        comparacoes++;
        if (comparaNomes(jogos[esq].getNome(), jogos[raiz].getNome()) > 0) {
            raiz = esq;
        }
    }

    if (dir < tam) {
        comparacoes++;
        if (comparaNomes(jogos[dir].getNome(), jogos[raiz].getNome()) > 0) {
            raiz = dir;
        }
    }

    if (raiz != i) {
        Game aux = jogos[i];
        jogos[i] = jogos[raiz];
        jogos[raiz] =  aux;
        criaHeap(jogos, tam, raiz);
    }
}
    public static  void heapsort(Game[] jogos, int tam)
    {
        for (int i= tam/2-1; i >=0; i--){
            comparacoes++; 
            criaHeap(jogos,tam,i);
        }
        for (int j = tam-1;j>0;j--){
            comparacoes++; 
            Game aux = jogos[0];
            jogos[0] = jogos[j];
            jogos[j]= aux;
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

    public static boolean buscarPorNome(Game[] jogos, String nome,int tam) {
    int esq = 0;
    int dir = tam - 1;
    while (esq <= dir) {
        int meio = esq + (dir - esq) / 2;
        String meioNome = jogos[meio].getNome();
        if (meioNome.equals(nome)) {
            return true;
        } else if (comparaNomes(meioNome, nome) < 0 ) {
            esq = meio + 1;
        } else {
            dir = meio - 1;
        }
    }
    return false;
    }
    
    public static int leArquivo(Game[] jogos, String path) {
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
   
     public static  void main(String[] args) {
        Game[] list = new Game[100000];
        String path = "/tmp/games.csv";
        int tam = leArquivo(list, path);
        
        long inicio = System.nanoTime();
        heapsort(list,tam);
        long fim = System.nanoTime();
        tempo = fim - inicio;
        escreveLog();
        try (Scanner scanf = new Scanner(System.in)) {
                String line = scanf.nextLine();
                while (!line.equals("FIM")){
                 if(buscarPorNome(list,line,tam)){
                        System.out.println("SIM");
                 }else{
                        System.out.println("NAO");
                 }
                    line = scanf.nextLine();

                }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
