package tps.tp5;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class Name {

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

    public static int comparaNomes(String a, String b) {
        int len = Math.min(a.length(), b.length());
        a = a.toLowerCase();
        b = b.toLowerCase();
        for (int i = 0; i < len; i++) {
            char ca = a.charAt(i);
            char cb = b.charAt(i);

            if (ca != cb) {
                return ca - cb; 
            }
        }

        return a.length() - b.length();
    }   

     public static void criaHeapId(Game[] jogos, int tam, int i) {
    int raiz = i;
    int esq = 2 * i + 1;
    int dir = 2 * i + 2;

    if (esq < tam) {
        comparacoes++;
        if (jogos[esq].getId() > jogos[raiz].getId()){
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
        Game aux = jogos[i];
        jogos[i] = jogos[raiz];
        jogos[raiz] =  aux;
        criaHeapId(jogos, tam, raiz);
    }
}
    
    public static  void ordenaId(Game[] jogos, int tam)
    {
        for (int i= tam/2-1; i >=0; i--){
            comparacoes++; 
            criaHeapId(jogos,tam,i);
        }
        for (int j = tam-1;j>0;j--){
            comparacoes++; 
            Game aux = jogos[0];
            jogos[0] = jogos[j];
            jogos[j]= aux;
            criaHeapId(jogos,j,0);
        }   
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
        int esq = 0;
        int dir = tam - 1;
        while (esq <= dir) {
            int meio = esq + (dir - esq) / 2;
            int meioId = jogos[meio].getId();
            if (meioId == id) {
                return jogos[meio];
            } else if (meioId < id ) {
                esq = meio + 1;
            } else {
                dir = meio - 1;
            }
        }
        return null;
}

    public static void escreveLog() {
        String path = System.getProperty("user.dir") + File.separator + "889080_binaria.txt"; 

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
        int tam = leCsv(list, path);
        ordenaId(list, tam);


        int tamBusca = 0;
        Game[] buscas = new Game[tam];
        Scanner scanf = new Scanner(System.in);

        try{
                String line = scanf.nextLine();
                while (!line.equals("FIM")){
                    int id = Integer.parseInt(line);
                    Game g = buscarPorId(list,id,tam);
                    if(g != null){  
                            buscas[tamBusca] = g;
                            tamBusca++;
                    }
                        line = scanf.nextLine();
                }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        long inicio = System.nanoTime();
        heapsort(buscas,tamBusca);
        long fim = System.nanoTime();
        tempo = fim - inicio;
        escreveLog();

        try{
            String line = scanf.nextLine();   
            do{
                    Game g=  buscarPorNome(buscas,line,tamBusca);
                    if(g != null){
                            System.out.println(" SIM");;
                    }else{
                            System.out.println(" NAO");
                    }
                    line = scanf.nextLine();
                }while (!line.equals("FIM"));
               
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        scanf.close();
    }

}
