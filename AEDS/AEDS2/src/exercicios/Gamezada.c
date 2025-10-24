#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>
#include <locale.h>

#define TAMROW 50000
#define TAMFIELD 2056

typedef struct {
    int id,estimedOwners,metacriticScore,achiviments,
        qtdsupportedLanguages, qtdPublishers , qtdDevelopers,qtdGenres,
        qtdTags, qtdCategories; 

    char *name,  *releaseDae;

    float price, userScore;

    char **supportedLanguages, **publishers, **developers, **categories,
         **genres, **tags;    
} Game;



void freeList(char*** lista, int* qtd) {
    if (*lista) {
        for (int i = 0; i < *qtd; i++) free((*lista)[i]);
        free(*lista);
    }
    *lista = NULL;
    *qtd = 0;
}
void freeGame(Game* j) {
    free(j->name);
    free(j->releaseDae);
    freeList(&j->supportedLanguages, &j->qtdsupportedLanguages);
    freeList(&j->publishers, &j->qtdPublishers);
    freeList(&j->developers, &j->qtdDevelopers);
    freeList(&j->categories, &j->qtdCategories);
    freeList(&j->genres, &j->qtdGenres);
    freeList(&j->tags, &j->qtdTags);
}


void trim(char* s) {
    int i = strlen(s) - 1;
    while (i >= 0 && isspace((unsigned char)s[i])) {
        s[i] = '\0';
        i--;
    }
}

void pularEspacos(char** s) {
    while (**s && isspace((unsigned char)**s)) (*s)++;
}

char* copiarTexto(const char* s) {
    char* nova = malloc(strlen(s) + 1);
    if (nova) strcpy(nova, s);
    return nova;
}

int compare(const char* a, const char* b) {
    while (*a && *b) {
        if (tolower(*a) != tolower(*b)) return 0;
        a++; b++;
    }
    return *a == *b;
}

void formatField(char* s) {
    char* w = s;
    for (char* r = s; *r; r++) {
        if (*r == '[' || *r == ']' || *r == '"' || *r == '\'') continue;
        *w++ = *r;
    }
    *w = '\0';

    char temp[TAMFIELD * 2];
    int j = 0;
    for (int i = 0; s[i] && j + 2 < sizeof(temp); i++) {
        if (s[i] == ',') {
            temp[j++] = ',';
            if (s[i + 1] && s[i + 1] != ' ') temp[j++] = ' ';
        } else temp[j++] = s[i];
    }
    temp[j] = '\0';
    strcpy(s, temp);
}

char** separeTxt(const char* texto, const char* delim, int* qtd) {
    *qtd = 0;
    if (!texto || !*texto) return NULL;
    char* copia = copiarTexto(texto);
    char* token = strtok(copia, delim);
    char** lista = NULL;

    while (token) {
        char* p = token;
        pularEspacos(&p);
        trim(p);
        if (*p) {
            lista = realloc(lista, sizeof(char*) * (*qtd + 1));
            lista[*qtd] = copiarTexto(p);
            (*qtd)++;
        }
        token = strtok(NULL, delim);
    }
    free(copia);
    return lista;
}


void printList(char** lista, int qtd) {
    printf("[");
    for (int i = 0; i < qtd; i++) {
        printf("%s%s", lista[i], (i == qtd - 1) ? "" : ", ");
    }
    printf("]");
}

void printGame(const Game* j) {
    printf("=> %d ## %s ## %s ## %d ## %.2f ## ",j->id, 
        j->name, j->releaseDae, j->estimedOwners, j->price);

    printList(j->supportedLanguages, j->qtdsupportedLanguages);
    printf(" ## %d ## %.1f ## %d ## ", j->metacriticScore, j->userScore, j->achiviments);
    printList(j->publishers, j->qtdPublishers);
    printf(" ## ");
    printList(j->developers, j->qtdDevelopers);
    printf(" ## ");
    printList(j->categories, j->qtdCategories);
    printf(" ## ");
    printList(j->genres, j->qtdGenres);
    printf(" ## ");
    printList(j->tags, j->qtdTags);
    printf(" ##\n");
}



char* dataFudida(const char* dataCsv) {
    char tmp[TAMFIELD];
    int j = 0;
    for (int i = 0; dataCsv[i] && j + 1 < sizeof(tmp); i++) {
        if (dataCsv[i] != '"') tmp[j++] = dataCsv[i];
    }
    tmp[j] = '\0';

    char buf[TAMFIELD];
    strcpy(buf, tmp);
    char *mes = NULL, *dia = NULL, *ano = NULL;
    char* p = buf;
    pularEspacos(&p);
    if (!*p) return copiarTexto("sem data");

    char* espaco = strchr(p, ' ');
    if (espaco) {
        *espaco = '\0';
        mes = p;
        char* resto = espaco + 1;
        pularEspacos(&resto);

        char* virgula = strchr(resto, ',');
        if (virgula) {
            *virgula = '\0';
            dia = resto;
            ano = virgula + 1;
            pularEspacos(&ano);
        } else {
            dia = "01";
            ano = resto;
        }
    } else {
        mes = "Jan";
        dia = "01";
        ano = p;
    }

    int m = 1;
    if (!strcmp(mes, "Jan")) m = 1; else if (!strcmp(mes, "Feb")) m = 2;
    else if (!strcmp(mes, "Mar")) m = 3; else if (!strcmp(mes, "Apr")) m = 4;
    else if (!strcmp(mes, "May")) m = 5; else if (!strcmp(mes, "Jun")) m = 6;
    else if (!strcmp(mes, "Jul")) m = 7; else if (!strcmp(mes, "Aug")) m = 8;
    else if (!strcmp(mes, "Sep")) m = 9; else if (!strcmp(mes, "Oct")) m = 10;
    else if (!strcmp(mes, "Nov")) m = 11; else if (!strcmp(mes, "Dec")) m = 12;

    int d = atoi(dia);
    int y = atoi(ano);
    char out[16];
    sprintf(out, "%02d/%02d/%04d", d, m, y);
    return copiarTexto(out);
}

void gameFromCsv(Game* j, const char* row) {
    char field[TAMFIELD];
    int pos = 0, idx = 0;
    bool entreAspas = false;

    memset(j, 0, sizeof(*j));
    j->metacriticScore = -1; j->userScore = -1.0f; j->achiviments = 0;

    for (int i = 0;; i++) {
        char c = row[i];
        bool fim = (c == '\0');

        if (!fim && c == '"') {
            entreAspas = !entreAspas;
            continue;
        }

        if (fim || (c == ',' && !entreAspas)) {
            field[pos] = '\0';
            switch (idx) {
                case 0: j->id = atoi(field); break;
                case 1: j->name = copiarTexto(field); break;
                case 2: j->releaseDae = dataFudida(field); break;
                case 3: {
                    char temp[TAMFIELD]; strcpy(temp, field);
                    char* h = strchr(temp, '-'); if (h) *h = '\0';
                    j->estimedOwners = atoi(temp);
                } break;
                case 4: j->price = atof(field); break;
                case 5: {
                    char temp[TAMFIELD]; strncpy(temp, field, sizeof(temp)); temp[sizeof(temp)-1] = '\0';
                    formatField(temp);
                    j->supportedLanguages = separeTxt(temp, ",", &j->qtdsupportedLanguages);
                } break;
                case 6: j->metacriticScore = (field[0] ? atoi(field) : -1); break;
                case 7: j->userScore = (!field[0] || compare(field, "tbd")) ? -1.0f : atof(field); break;
                case 8: j->achiviments = (field[0] ? atoi(field) : 0); break;
                case 9: {
                    char temp[TAMFIELD]; strncpy(temp, field, sizeof(temp)); temp[sizeof(temp)-1] = '\0';
                    formatField(temp);
                    j->publishers = separeTxt(temp, ",", &j->qtdPublishers);
                } break;
                case 10: {
                    char temp[TAMFIELD]; strncpy(temp, field, sizeof(temp)); temp[sizeof(temp)-1] = '\0';
                    formatField(temp);
                    j->developers = separeTxt(temp, ",", &j->qtdDevelopers);
                } break;
                case 11: {
                    char temp[TAMFIELD]; strncpy(temp, field, sizeof(temp)); temp[sizeof(temp)-1] = '\0';
                    formatField(temp);
                    j->categories = separeTxt(temp, ",", &j->qtdCategories);
                } break;
                case 12: {
                    char temp[TAMFIELD]; strncpy(temp, field, sizeof(temp)); temp[sizeof(temp)-1] = '\0';
                    formatField(temp);
                    j->genres = separeTxt(temp, ",", &j->qtdGenres);
                } break;
                case 13: {
                    char temp[TAMFIELD]; strncpy(temp, field, sizeof(temp)); temp[sizeof(temp)-1] = '\0';
                    formatField(temp);
                    j->tags = separeTxt(temp, ",", &j->qtdTags);
                } break;
            }
            idx++; pos = 0;
            if (fim) break;
        } else if (pos + 1 < TAMFIELD) {
            field[pos++] = c;
        }
    }
}


int main() {
    setlocale(LC_NUMERIC, "C"); 
    const char* path =  "/tmp/games.csv";

    FILE* fr = fopen(path, "r");
    if (!fr) { perror("Error ao abri/tmp/games.csv"); return 1; }

    char row[TAMROW];
    int total = 0;

    if (fgets(row, sizeof(row), fr)) {
        while (fgets(row, sizeof(row), fr)) total++;
    }
    fclose(fr);

    Game* Games = (Game*) malloc(sizeof(Game) * (total > 0 ? total : 1));
    int i = 0;

    fr = fopen(path, "r");
    if (!fr) { perror("Error ao abri/tmp/games.csv"); free(Games); return 1; }

    fgets(row, sizeof(row), fr);
    while (fgets(row, sizeof(row), fr)) {
        row[strcspn(row, "\r\n")] = 0;
        if (i < total) {
            gameFromCsv(&Games[i], row);
            i++;
        }
    }
    fclose(fr);

    char entrada[TAMFIELD];
    while (fgets(entrada, sizeof(entrada), stdin)) {
        entrada[strcspn(entrada, "\r\n")] = 0;
        if (entrada[0] == '\0') continue;
        if (strcmp(entrada, "FIM") == 0) break;

        int id = atoi(entrada);
        for (int k = 0; k < i; k++) {
            if (Games[k].id == id) {
                printGame(&Games[k]);
                break;
            }
        }
    }

    for (int k = 0; k < i; k++) freeGame(&Games[k]);
    free(Games);
    return 0;
}
