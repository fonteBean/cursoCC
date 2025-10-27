package tps.tp5;




import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static class Game {
        private int id;
        private String nome;
        private String releaseDate;
        private int estimedOwners;
        private float price;
        private String[] supportedLanguages;
        private int metacriticScore;
        private float userScore;
        private int achiviments;
        private String[] publishers;
        private String[] developers;
        private String[]  categories;
        private String[] genres;
        private String[] tags;


        public Game(){}

        public static String consertaData(String data) {

            String[] meses = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            data = data.trim();
            data = data.replaceAll("^\"+|\"+$", "");

            String[] partes = data.split(" ");
            if (partes.length != 3) {
                return "Formato inválido";
            }

            String mesTexto = partes[0].trim();
            String dia = partes[1].replace(",", "").trim();
            String ano = partes[2].trim();
            String mesNumero = "";
            for (int i = 0; i < meses.length; i++) {
                if (meses[i].equalsIgnoreCase(mesTexto)) {
                    mesNumero = (i + 1 < 10) ? "0" + (i + 1) : String.valueOf(i + 1);
                    break;
                }
            }

            if (mesNumero.isEmpty()) {
                return "Mês inválido";
            }

            if (dia.length() == 1) {
                dia = "0" + dia;
            }
            return dia + "/" + mesNumero + "/" + ano;
        }
        public static Game fromCSV(String linha) {
            String[] partes = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            Game g = new Game();
            g.id = Integer.parseInt(partes[0]);
            g.nome = partes[1];
            g.releaseDate = consertaData(partes[2].replace("\"", "").trim());
            g.estimedOwners = Integer.parseInt(partes[3]);
            g.price = Float.parseFloat(partes[4]);

            g.supportedLanguages = partes[5]
                    .replace("\"", "")
                    .replace("[", "")
                    .replace("]", "")
                    .replace("'", "")
                    .split(",\\s*");

            g.metacriticScore = Integer.parseInt(partes[6]);
            g.userScore = Float.parseFloat(partes[7]);
            g.achiviments = Integer.parseInt(partes[8]);

            g.publishers = new String[]{partes.length > 9 ? partes[9].replaceAll("^\"|\"$", "") : ""};
            g.developers = new String[]{partes.length > 10 ? partes[10].replaceAll("^\"|\"$", "") : ""};

            g.categories = new String[]{partes.length > 11 ? partes[11].replaceAll("^\"|\"$", "") : ""};
            g.genres = new String[]{partes.length > 12 ? partes[12].replaceAll("^\"|\"$", "") : ""};
            g.tags = new String[]{partes.length > 13 ? partes[13].replaceAll("^\"|\"$", "") : ""};

            return g;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public int getEstimedOwners() {
            return estimedOwners;
        }

        public void setEstimedOwners(int estimedOwners) {
            this.estimedOwners = estimedOwners;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public String[] getSupportedLanguages() {
            return supportedLanguages;
        }

        public void setSupportedLanguages(String[] supportedLanguages) {
            this.supportedLanguages = supportedLanguages;
        }

        public int getMetacriticScore() {
            return metacriticScore;
        }

        public void setMetacriticScore(int metacriticScore) {
            this.metacriticScore = metacriticScore;
        }

        public float getUserScore() {
            return userScore;
        }

        public void setUserScore(float userScore) {
            this.userScore = userScore;
        }

        public int getAchiviments() {
            return achiviments;
        }

        public void setAchiviments(int achiviments) {
            this.achiviments = achiviments;
        }

        public String[] getPublishers() {
            return publishers;
        }

        public void setPublishers(String[] publishers) {
            this.publishers = publishers;
        }

        public String[] getDevelopers() {
            return developers;
        }

        public void setDevelopers(String[] developers) {
            this.developers = developers;
        }

        public String[] getCategories() {
            return categories;
        }

        public void setCategories(String[] categories) {
            this.categories = categories;
        }

        public String[] getGenres() {
            return genres;
        }

        public void setGenres(String[] genres) {
            this.genres = genres;
        }

        public String[] getTags() {
            return tags;
        }

        public void setTags(String[] tags) {
            this.tags = tags;
        }

        public void imprimir() {
            System.out.println(
                    "=>" + this.getId() + " ## " +
                            this.getNome() + " ## " +
                            this.getReleaseDate() + " ## " +
                            this.getEstimedOwners() + " ## " +
                            this.getPrice() + " ## " +
                            Arrays.toString(this.getSupportedLanguages()).replaceAll(",\\s*", ", ") + " ## " +
                            this.getMetacriticScore() + " ## " +
                            this.getUserScore() + " ## " +
                            this.getAchiviments() + " ## " +
                            Arrays.toString(this.getPublishers()).replaceAll(",\\s*", ", ") + " ## " +
                            Arrays.toString(this.getDevelopers()).replaceAll(",\\s*", ", ") + " ## " +
                            Arrays.toString(this.getCategories()).replaceAll(",\\s*", ", ") + " ## " +
                            Arrays.toString(this.getGenres()).replaceAll(",\\s*", ", ") + " ## " +
                            Arrays.toString(this.getTags()).replaceAll(",\\s*", ", ") + " ##"
            );
    }
}   
      
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

    Game[] esq = new Game[n1];
    Game[] dir = new Game[n2];

    for (int i = 0; i < n1; i++) {
        esq[i] = vetor[inicio + i];
        movimentacoes++;
    }
    for (int j = 0; j < n2; j++) {
        dir[j] = vetor[meio + 1 + j];
        movimentacoes++;
    }

    int i = 0, j = 0, k = inicio;

    while (i < n1 && j < n2) {
        comparacoes++;

        if (esq[i].getPrice() < dir[j].getPrice()) {
            vetor[k] = esq[i];
            i++;
        } 
        else if (esq[i].getPrice() == dir[j].getPrice()) {
            if (esq[i].getId() <= dir[j].getId()) {
                vetor[k] = esq[i];
                i++;
            } else {
                vetor[k] = dir[j];
                j++;
            }
        } 
        else {
            vetor[k] = dir[j];
            j++;
        }

        movimentacoes++;
        k++;
    }
        while (i < n1) {
        vetor[k] = esq[i];
        i++;
        k++;
        movimentacoes++;
    }

    while (j < n2) {
        vetor[k] = dir[j];
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
        String path = System.getProperty("user.dir") + File.separator + "889080_mergesort.txt"; // cria no diretório atual

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
        System.out.println("| 5 preços mais caros |");
        for(int i=tam;i>  tam-5;i--){
             list[i].imprimir();
        }
        System.out.println();
        System.out.println("| 5 preços mais baratos |");
        for(int i=0;i <5;i++){
            list[i].imprimir();
        }
      
      
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
