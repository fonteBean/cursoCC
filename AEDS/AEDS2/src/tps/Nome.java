package tps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import exercicios.models.Game;
public class Nome {


    static int movimentacoes = 0;   
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
    

    public static Game buscarPorId(Game[] jogos, int id, int tam) {
        for(Game g : jogos){
            comparacoes++;
            if(g.getId() == id){
                return g;
            }
        }
        return null;
}

   public static void escreveLog() {
    String path = System.getProperty("user.dir") + File.separator + "log.txt"; // cria no diretório atual

    try (FileWriter escritor = new FileWriter(path,true)) { 
        escritor.write("889080\t" + "Tempo de execução = " + tempo + "ms\tNumero de Comparacoes = " + comparacoes);
    } catch (IOException e) {
        System.err.println("Erro ao escrever o log: " + e.getMessage());
    }
}

    public static Game buscarPorNome(Game[] buscas, String nome,int tam) {
    int esq = 0;
    int dir = tam - 1;
    while (esq <= dir) {
        int meio = esq + (dir - esq) / 2;
        String meioNome = buscas[meio].getNome();
        if (meioNome.equals(nome)) {
            return buscas[meio];
        } else if (comparaNomes(meioNome, nome) < 0 ) {
            esq = meio + 1;
        } else {
            dir = meio - 1;
        }
    }
    return null;
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
   
     public static  void main(String[] args) {
        Game[] list = new Game[100000];
        String path = "/tmp/games.csv";
        int tam = leCsv(list, path);// retorna tam csv


        int tamBusca = 0;//tamanho do array de buscas

        Game[] buscas = new Game[tam];
        //leitura dos ids
        Scanner scanf = new Scanner(System.in);
        try{
                String line = scanf.nextLine();
                while (!line.equals("FIM")){
                int id = Integer.parseInt(line);
                Game g = buscarPorId(list,id,tam);
                 if(g != null){
                        buscas[tamBusca] = g;
                        tamBusca++;
                        System.out.println("SIM");
                        System.out.println(g.getNome());
                 }else{
                        System.out.println("NAO");
                 }
                    line = scanf.nextLine();

                }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        //ordena por nome
        long inicio = System.nanoTime();
        heapsort(buscas,tamBusca);
        long fim = System.nanoTime();
        tempo = fim - inicio;
        escreveLog();

        //busca por nome
        try{
            String line;   
            do{
                    line = scanf.nextLine();
                    Game g=  buscarPorNome(buscas,line,tamBusca);
                    if(g != null){
                            g.imprimir();
                    }else{
                            System.out.println("NOT FOUND");
                    }
                }while (!line.equals("FIM"));
               
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        scanf.close();
    }
}
