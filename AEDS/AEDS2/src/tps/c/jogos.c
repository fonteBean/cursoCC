#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>

const int MAX_LINHAS = 4500;
const int TAM_LINHA = 8192;
const int NUM_CAMPOS = 14;

typedef struct {
    int id, 
    char *name, 
    char *releaseData,
    int estimatedOwners, 
    float price, 
    char **supportedLanguages, *metacritic, *userScore, *achiviements, *publishers, *developers, *categories, *genres, *tags;
} Game;

bool espVazio(char c) {
    return c == ' ' || c == '\t' || c == '\n' || c == '\r';
}

char* removeEspaco(const char* original) {
    if (original == NULL) return NULL;
    int inicio = 0;
    while (original[inicio] != '\0' && espVazio(original[inicio])) {
        inicio++;
    }
    int fim = strlen(original) - 1;
    while (fim >= inicio && espVazio(original[fim])) {
        fim--;
    }
    if (fim < inicio) {
        char* resultado = (char*)malloc(sizeof(char));
        resultado[0] = '\0';
        return resultado;
    }
    int tam = fim - inicio + 1;
    char* resultado = (char*)malloc((tam + 1) * sizeof(char));
    strncpy(resultado, original + inicio, tam);
    resultado[tam] = '\0';
    return resultado;
}

char* removeAspas(const char* original) {
    if (original == NULL) return NULL;
    int tam = strlen(original);
    char* resultado = (char*)malloc((tam + 1) * sizeof(char));
    int j = 0;
    for (int i = 0; i < tam; i++) {
        if (original[i] != '"') {
            resultado[j++] = original[i];
        }
    }
    resultado[j] = '\0';
    return resultado;
}

bool equals(const char* s1, const char* s2) {
    if (s1 == NULL || s2 == NULL) return false;
    if (strlen(s1) != strlen(s2)) return false;
    for (int i = 0; i < strlen(s1); i++) {
        if (s1[i] != s2[i]) return false;
    }
    return true;
}

char* formatarLista(const char* campo) {
    if (campo == NULL) {
        return strdup("[]");
    }
    int tam = strlen(campo);
    char* tmp = (char*)malloc((tam * 2 + 1) * sizeof(char)); 
    int j = 0;
    for (int i = 0; i < tam; i++) {
        char c = campo[i];
        if (c == '"' || c == '[' || c == ']' || c == '\'') continue;
        if (c == ',') {
            tmp[j++] = ',';
            tmp[j++] = ' ';
            while (i + 1 < tam && espVazio(campo[i + 1])) i++;
        } else {
            tmp[j++] = c;
        }
    }
    tmp[j] = '\0';
    char* resFinal = (char*)malloc((strlen(tmp) + 3) * sizeof(char));
    sprintf(resFinal, "[%s]", tmp);
    free(tmp);
    return resFinal;
}

char* formatarData(const char* data) {
    const char* meses[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    if (data == NULL || strlen(data) == 0) return strdup("");

    char* dataFim = removeEspaco(data);
    if (strlen(dataFim) == 0) {
        free(dataFim);
        return strdup("");
    }
    
    char* partes[3];
    int countPartes = 0;
    char* tmp = strdup(dataFim);
    char* saveptr;
    char* token = strtok_r(tmp, " ", &saveptr);
    while (token != NULL && countPartes < 3) {
        partes[countPartes++] = token;
        token = strtok_r(NULL, " ", &saveptr);
    }

    if (countPartes < 2) {
        free(tmp);
        return dataFim;
    }

    char* mes = partes[0];
    char* diaStr = partes[1];
    char dia[3] = {0};
    int j = 0;
    for (int i = 0; i < strlen(diaStr) && j < 2; i++) {
        if (diaStr[i] != ',') dia[j++] = diaStr[i];
    }

    char* ano;
    if (countPartes > 2) {
        ano = partes[2];
    } else {
        ano = "?";
    }

    int numMes = 0;
    for (int i = 0; i < 12; i++) {
        if (equals(mes, meses[i])) {
            numMes = i + 1;
            break;
        }
    }
    
    free(tmp);
    free(dataFim);
    
    if (numMes == 0) {
        return strdup(data);
    }
    
    char* dataFormatada = (char*)malloc(12 * sizeof(char)); 
    sprintf(dataFormatada, "%02d/%02d/%s", atoi(dia), numMes, ano);
    return dataFormatada;
}

void imprimir(Game* game) {
    char *id = removeAspas(game->id);
    char *name = removeAspas(game->name);
    char *data = formatarData(game->releaseData);
    char *supportedLanguages = formatarLista(game->supportedLanguages);
    char *publishers = formatarLista(game->publishers);
    char *dev = formatarLista(game->developers);
    char *categories = formatarLista(game->categories);
    char *genres = formatarLista(game->genres);
    char *tags = formatarLista(game->tags);
    
    char *idFormat, *nameFormat, *dataFormat, *propFormat, *priceFormat, *supportedLanguagesFormat, *metaFormat, *userScoreFormat, *conqFormat, *pubsFormat, *devsFormat, *catsFormat, *gensFormat, *tagsFormat;

    if (id != NULL) { idFormat = id; } else { idFormat = ""; }
    if (name != NULL) { nameFormat = name; } else { nameFormat = ""; }
    if (data != NULL) { dataFormat = data; } else { dataFormat = ""; }
    if (game->estimatedOwners != NULL) { propFormat = game->estimatedOwners; } else { propFormat = ""; }
    if (game->price != NULL) { priceFormat = game->price; } else { priceFormat = ""; }
    if (supportedLanguages != NULL) { supportedLanguagesFormat = supportedLanguages; } else { supportedLanguagesFormat = "[]"; }
    if (game->metacritic != NULL) { metaFormat = game->metacritic; } else { metaFormat = ""; }
    if (game->userScore != NULL) { userScoreFormat = game->userScore; } else { userScoreFormat = ""; }
    if (game->achiviements != NULL) { conqFormat = game->achiviements; } else { conqFormat = ""; }
    if (publishers != NULL) { pubsFormat = publishers; } else { pubsFormat = "[]"; }
    if (dev != NULL) { devsFormat = dev; } else { devsFormat = "[]"; }
    if (categories != NULL) { catsFormat = categories; } else { catsFormat = "[]"; }
    if (genres != NULL) { gensFormat = genres; } else { gensFormat = "[]"; }
    if (tags != NULL) { tagsFormat = tags; } else { tagsFormat = "[]"; }

    printf("=> %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s.0 ## %s ## %s ## %s ## %s ## %s ## %s ##\n",
        idFormat, nameFormat, dataFormat, propFormat, priceFormat, supportedLanguagesFormat, metaFormat, userScoreFormat, conqFormat, pubsFormat,
        devsFormat, catsFormat, gensFormat, tagsFormat);

    free(id); free(name); free(data); free(supportedLanguages);
    free(publishers); free(dev); free(categories); free(genres);
    free(tags);
}

void lerCsv(char* linha, char** dados) {
    int indice = 0;
    char* inicio = linha;
    bool aspas = false, colchetes = false;

    for (int i = 0; linha[i] != '\0'; i++) {
        if (linha[i] == '"') aspas = !aspas;
        else if (linha[i] == '[') colchetes = true;
        else if (linha[i] == ']') colchetes = false;
        
        if (linha[i] == ',' && !aspas && !colchetes && indice < NUM_CAMPOS - 1) {
            linha[i] = '\0'; 
            dados[indice++] = strdup(inicio); 
            inicio = &linha[i + 1];
        }
    }
    if (indice < NUM_CAMPOS) {
        dados[indice] = strdup(inicio); 
    }
}

int lerArquivo(const char* nameArquivo, char** linhas) {
    FILE* file = fopen(nameArquivo, "r");
    if (file == NULL) {
        perror("Erro ao abrir o arquivo");
        return 0;
    }

    char buffer[TAM_LINHA];
    int count = 0;
    
    fgets(buffer, TAM_LINHA, file); 

    while (fgets(buffer, TAM_LINHA, file) != NULL && count < MAX_LINHAS) {
        buffer[strcspn(buffer, "\n")] = 0; 
        linhas[count++] = strdup(buffer); 
    }

    fclose(file);
    return count;
}

int main() {
    char* arquivoCsv = "/tmp/games.csv";
    char** linhas = (char**)malloc(MAX_LINHAS * sizeof(char*));
    int totalDeGames = lerArquivo(arquivoCsv, linhas);

    int tam_alloc;
    if (totalDeGames > 0) {
        tam_alloc = totalDeGames;
    } else {
        tam_alloc = 1;
    }
    Game* games = (Game*)malloc(tam_alloc * sizeof(Game));
    char** ids = (char**)malloc(tam_alloc * sizeof(char*));
    
    int contadorGames = 0;

    for (int i = 0; i < totalDeGames; i++) {
        char* dados[NUM_CAMPOS];
        for (int j = 0; j < NUM_CAMPOS; j++) {
            dados[j] = NULL;
        }

        lerCsv(linhas[i], dados);

        if (dados[0] != NULL && strlen(dados[0]) > 0) {
            for (int j = 0; j < NUM_CAMPOS; j++) {
                char* tmp = removeEspaco(dados[j]);
                free(dados[j]); 
                dados[j] = tmp;
            }
            
            games[contadorGames].id = dados[0];
            games[contadorGames].name = dados[1];
            games[contadorGames].releaseData = dados[2];
            games[contadorGames].estimatedOwners = dados[3];
            games[contadorGames].price = dados[4];
            games[contadorGames].supportedLanguages = dados[5];
            games[contadorGames].metacritic = dados[6];
            games[contadorGames].userScore = dados[7];
            games[contadorGames].achiviements = dados[8];
            games[contadorGames].publishers = dados[9];
            games[contadorGames].developers = dados[10];
            games[contadorGames].categories = dados[11];
            games[contadorGames].genres = dados[12];
            games[contadorGames].tags = dados[13];

            ids[contadorGames] = removeAspas(dados[0]);
            
            contadorGames++;
        } else {
            for (int j = 0; j < NUM_CAMPOS; j++) {
                if (dados[j] != NULL) free(dados[j]);
            }
        }
    }

    char entrada[TAM_LINHA];
    while (fgets(entrada, TAM_LINHA, stdin) != NULL) {
        entrada[strcspn(entrada, "\n")] = 0; 
        if (equals(entrada, "FIM")) {
            break;
        }

        bool encontrado = false;
        for (int i = 0; i < contadorGames; i++) {
            if (equals(ids[i], entrada)) {
                imprimir(&games[i]);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            printf("Jogo não encontrado.\n");
        }
    }

    for (int i = 0; i < totalDeGames; i++) {
        free(linhas[i]);
    }
    free(linhas);

    for (int i = 0; i < contadorGames; i++) {
        free(games[i].id);
        free(games[i].name);
        free(games[i].releaseData);
        free(games[i].estimatedOwners);
        free(games[i].price);
        free(games[i].supportedLanguages);
        free(games[i].metacritic);
        free(games[i].userScore);
        free(games[i].achiviements);
        free(games[i].publishers);
        free(games[i].developers);
        free(games[i].categories);
        free(games[i].genres);
        free(games[i].tags);
        
        free(ids[i]);
    }
    free(games);
    free(ids);
    
    return 0;
}