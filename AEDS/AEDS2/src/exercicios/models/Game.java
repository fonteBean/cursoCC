package exercicios.models;

import java.util.Arrays;

public class Game {
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
                        Arrays.toString(this.getTags()).replaceAll(",\\s*", ", ") + " ##");
    }
}
