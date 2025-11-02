package tps.tp6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static class Game {
    private int id;
    public Game prox;
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
    private String[] categories;
    private String[] genres;
    private String[] tags;

    public Game() {
      prox = null;
    }

    public Game(String nome) {
      this.nome = nome;
      prox = null;
    }

    public Game(int id) {
      this.id = id;
      prox = null;
    }

    public static String consertaData(String data) {

      String[] meses = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
          "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
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

      g.publishers = new String[] { partes.length > 9 ? partes[9].replaceAll("^\"|\"$", "") : "" };
      g.developers = new String[] { partes.length > 10 ? partes[10].replaceAll("^\"|\"$", "") : "" };

      g.categories = new String[] { partes.length > 11 ? partes[11].replaceAll("^\"|\"$", "") : "" };
      g.genres = new String[] { partes.length > 12 ? partes[12].replaceAll("^\"|\"$", "") : "" };
      g.tags = new String[] { partes.length > 13 ? partes[13].replaceAll("^\"|\"$", "") : "" };

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
          "=> " + this.getId() + " ## " +
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
              Arrays.toString(this.getTags()).replaceAll(",\\s*", ", ") + " ##");
    }

    @Override
    public Game clone() {
      Game copia = new Game();
      copia.id = this.id;
      copia.nome = this.nome;
      copia.releaseDate = this.releaseDate;
      copia.estimedOwners = this.estimedOwners;
      copia.price = this.price;
      copia.metacriticScore = this.metacriticScore;
      copia.userScore = this.userScore;
      copia.achiviments = this.achiviments;

      copia.supportedLanguages = this.supportedLanguages != null ? this.supportedLanguages.clone() : null;
      copia.publishers = this.publishers != null ? this.publishers.clone() : null;
      copia.developers = this.developers != null ? this.developers.clone() : null;
      copia.categories = this.categories != null ? this.categories.clone() : null;
      copia.genres = this.genres != null ? this.genres.clone() : null;
      copia.tags = this.tags != null ? this.tags.clone() : null;

      copia.prox = null;

      return copia;
    }

  }

  public static class Lista {
    public Game primeiro, ultimo;

    public Lista() {
      primeiro = new Game();
      ultimo = primeiro;
    }

    public void mostrar() {
      int indice = 0;
      Game i = primeiro.prox;
      while (i != null) {
        System.out.printf("[%d] ", indice);
        i.imprimir();
        indice++;
        i = i.prox;
      }
    }

    public int tam() {
      int tam = 0;
      for (Game i = primeiro; i.prox != null; i = i.prox, tam++)
        ;
      return tam;
    }

    public void inserirNoInicio(Game g) {
      g.prox = primeiro.prox;
      primeiro.prox = g;
      if (ultimo == primeiro)
        ultimo = g;
    }

    public void inserir(Game g, int pos) throws Exception {
      if (pos > this.tam()) {
        throw new Exception();
      }
      Game tmp = primeiro.prox;
      for (int i = 0; i < pos; i++, tmp = tmp.prox)
        ;
      g.prox = tmp.prox;
      tmp.prox = g;
    }

    public void inserirNoFim(Game g) {
      ultimo.prox = g;
      ultimo = g;
    }

    public String removerNoInicio() {
      String nome = primeiro.prox.getNome();
      Game tmp = primeiro;
      primeiro = primeiro.prox;
      tmp.prox = null;
      return nome;
    }

    public String remover(int pos) throws Exception {
      if (pos < 0 || pos >= this.tam()) {
        throw new Exception("Posição inválida");
      }

      Game tmp = primeiro;
      for (int i = 0; i < pos; i++, tmp = tmp.prox)
        ;

      String nome = tmp.prox.getNome();
      tmp.prox = tmp.prox.prox;
      return nome;
    }

    public String removerNoFim() {
      Game i = primeiro;
      for (; i.prox != ultimo; i = i.prox)
        ;
      String e = ultimo.getNome();
      ultimo = i;
      ultimo.prox = null;
      return e;
    }

    public Game buscaGame(int id) {
      for (Game g = primeiro.prox; g != null; g = g.prox) {
        if (g.getId() == id) {
          return g;
        }
      }
      return null;
    }

  }

  public static void menu(Lista baseCsv, Lista lista, String entrada) throws Exception {
    String[] splitted = entrada.split(" ");
    String op = splitted[0];
    Game g = null;
    int id, pos;

    switch (op) {
      case "II":
        id = Integer.parseInt(splitted[1]);
        g = buscaGame(baseCsv, id);
        if (g != null)
          lista.inserirNoInicio(g);
        break;

      case "I*":
        pos = Integer.parseInt(splitted[1]);
        id = Integer.parseInt(splitted[2]);
        g = buscaGame(baseCsv, id);
        if (g != null)
          lista.inserir(g, pos);
        break;

      case "IF":
        id = Integer.parseInt(splitted[1]);
        g = buscaGame(baseCsv, id);
        if (g != null)
          lista.inserirNoFim(g);
        break;

      case "RI":
        System.out.println("(R) " + lista.removerNoInicio());
        break;

      case "R*":
        pos = Integer.parseInt(splitted[1]);
        System.out.println("(R) " + lista.remover(pos));
        break;

      case "RF":
        System.out.println("(R) " + lista.removerNoFim());
        break;

      default:
        System.out.println("Escreva uma opção válida");
        break;
    }
  }

  public static void leCsv(Lista jogos, String path) {
    try (BufferedReader scanf = new BufferedReader(new FileReader(path))) {
      String line = scanf.readLine();
      line = scanf.readLine();
      while (line != null) {
        Game game;
        game = Game.fromCSV(line);
        jogos.inserirNoFim(game);
        line = scanf.readLine();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return;
  }

  public static Game buscaGame(Lista jogos, int id) {
    Game g = jogos.buscaGame(id);
    return g != null ? g.clone() : null;
  }

  public static Lista criaSubArray(Lista jogos, Scanner scanf) {
    Lista subLista = new Lista();
    try {
      String line = scanf.nextLine().trim();
      while (!line.equals("FIM")) {
        int id = Integer.parseInt(line);
        Game g = buscaGame(jogos, id);

        if (g != null) {
          subLista.inserirNoFim(g);
        } else {
          System.out.println("Not Found");
        }

        line = scanf.nextLine().trim();
      }
    } catch (Exception e) {
      System.out.println("Erro: " + e.getMessage());
    }
    return subLista;
  }

  public static void main(String[] args) {
    Lista jogos = new Lista();
    String path = "/tmp/games.csv";
    leCsv(jogos, path);

    Scanner scanf = new Scanner(System.in);
    Lista subLista = criaSubArray(jogos, scanf);

    try {
      int n = Integer.parseInt(scanf.nextLine().trim());
      for (int i = 0; i < n; i++) {
        menu(jogos, subLista, scanf.nextLine());
      }
      subLista.mostrar();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    scanf.close();
  }
}
