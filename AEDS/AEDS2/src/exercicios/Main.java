package exercicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import exercicios.models.Game;


public class Main{



    public static void criaHeap(ArrayList<Game> games, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapsort(games, n, i);
        }
    }

    public static void heapsort(ArrayList<Game> games, int n, int i) {
        int raiz = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        if (esq < n && games.get(esq).getId() > games.get(raiz).getId()) {
            raiz = esq;
        }

        if (dir < n && games.get(dir).getId() > games.get(raiz).getId()) {
            raiz = dir;
        }

        if (raiz != i) {
            Game tmp = games.get(i);
            games.set(i, games.get(raiz));
            games.set(raiz, tmp);

            heapsort(games, n, raiz);
        }
    }
    
    public static Game buscarPorId(ArrayList<Game> jogos, int id) {
        for (Game g : jogos) {
            if (g.getId() == id) return g;
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
            try (Scanner scanf = new Scanner(System.in)) {
                String line = scanf.nextLine();
                while (!line.equals("FIM")){
                    int id = Integer.parseInt(line);
                    Game g = buscarPorId(list,id);
                    if (g !=null) g.imprimir();
                    line = scanf.nextLine();
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}


