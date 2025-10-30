#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>
#include <locale.h>
#include <time.h>

#define TAMROW 50000
#define TAMFIELD 2056

typedef struct
{
    int id, estimedOwners, metacriticScore, achiviments,
        qtdsupportedLanguages, qtdPublishers, qtdDevelopers, qtdGenres,
        qtdTags, qtdCategories;

    char *name, *releaseDate;

    float price, userScore;

    char **supportedLanguages, **publishers, **developers, **categories,
        **genres, **tags;

    struct Game *prox;

} Game;

typedef struct Lista
{
    Game *primeiro, *ultimo;
} Lista;

void inserirNoInicio(Lista jogos, Game g)
{
    g->prox = jogos->primeiro->prox;
    jogos->primeiro->prox = g;
}

void inserir(Lista jogos, Game g, int pos)
{
    if (pos > tam(jogos))
    {
        printf("erro de posicao") return;
    }
    Game *tmp = primeiro;
    for (int i = 0; i < pos; i++, tmp = tmp->prox)
        ;
    g->prox = tmp->prox;
    tmp->prox = g;
    free(tmp);
    return
}

void inserirNoFim(Lista jogos, Game g)
{
    ultimo->prox = g;
    ultimo = g;
}

char *removerNoInicio(Lista jogos)
{
    char *nome;
    strcpy(jogos->primeiro->nome, nome);
    Game *tmp = primeiro;
    primeiro = primeiro->prox;
    free(tmp);
    return nome;
}

char *remover(Lista jogos, int pos)
{
    if (pos > tam(jogos))
    {
        printf("erro de posicao") return;
    }
    Game *tmp = primeiro;
    for (int i = 0; i < pos; i++, tmp = tmp->prox)
        ;
    tmp->prox = tmp->prox->prox;
    free(tmp);
    return
}
// Corigir esta merda dps

char *removerNoFim(Lista jogos)
{
    Game *g = primeiro;
    char *nome;
    strcpy(jogos->ultimo->nome, nome) for (; g->prox != jogos->ultimo; g = g->prox);
    jogos->ultimo = g;
    g = g->prox;
    free(g);
}

int tam(Lista jogos)
{
    int i = 0;
    Game *g = primeiro;
    for (; g != NULL; i++)
        ;
    free(g);
    return i;
}

void freeVec(char ***vector, int *qtd)
{
    if (*vector)
    {
        for (int i = 0; i < *qtd; i++)
            free((*vector)[i]);
        free(*vector);
    }
    *vector = NULL;
    *qtd = 0;
}
void freeGame(Game *j)
{
    free(j->name);
    free(j->releaseDate);
    freeVec(&j->supportedLanguages, &j->qtdsupportedLanguages);
    freeVec(&j->publishers, &j->qtdPublishers);
    freeVec(&j->developers, &j->qtdDevelopers);
    freeVec(&j->categories, &j->qtdCategories);
    freeVec(&j->genres, &j->qtdGenres);
    freeVec(&j->tags, &j->qtdTags);
}

void trim(char *s)
{
    int i = strlen(s) - 1;
    while (i >= 0 && isspace((unsigned char)s[i]))
    {
        s[i] = '\0';
        i--;
    }
}

void pularEspacos(char **s)
{
    while (**s && isspace((unsigned char)**s))
        (*s)++;
}

char *copiarTexto(const char *s)
{
    char *nova = malloc(strlen(s) + 1);
    if (nova)
        strcpy(nova, s);
    return nova;
}

int compare(const char *a, const char *b)
{
    while (*a && *b)
    {
        if (tolower(*a) != tolower(*b))
            return 0;
        a++;
        b++;
    }
    return *a == *b;
}

void formatField(char *s)
{
    char *w = s;
    for (char *r = s; *r; r++)
    {
        if (*r == '[' || *r == ']' || *r == '"' || *r == '\'')
            continue;
        *w++ = *r;
    }
    *w = '\0';

    char temp[TAMFIELD * 2];
    int j = 0;
    for (int i = 0; s[i] && j + 2 < sizeof(temp); i++)
    {
        if (s[i] == ',')
        {
            temp[j++] = ',';
            if (s[i + 1] && s[i + 1] != ' ')
                temp[j++] = ' ';
        }
        else
            temp[j++] = s[i];
    }
    temp[j] = '\0';
    strcpy(s, temp);
}

char **separeTxt(const char *texto, const char *delim, int *qtd)
{
    *qtd = 0;
    if (!texto || !*texto)
        return NULL;
    char *copia = copiarTexto(texto);
    char *token = strtok(copia, delim);
    char **vector = NULL;

    while (token)
    {
        char *p = token;
        pularEspacos(&p);
        trim(p);
        if (*p)
        {
            vector = realloc(vector, sizeof(char *) * (*qtd + 1));
            vector[*qtd] = copiarTexto(p);
            (*qtd)++;
        }
        token = strtok(NULL, delim);
    }
    free(copia);
    return vector;
}

void printvector(char **vector, int qtd)
{
    printf("[");
    for (int i = 0; i < qtd; i++)
    {
        printf("%s%s", vector[i], (i == qtd - 1) ? "" : ", ");
    }
    printf("]");
}

void printGame(const Game *j)
{
    printf("=> %d ## %s ## %s ## %d ## %.2f ## ", j->id,
           j->name, j->releaseDate, j->estimedOwners, j->price);

    printvector(j->supportedLanguages, j->qtdsupportedLanguages);
    printf(" ## %d ## %.1f ## %d ## ", j->metacriticScore, j->userScore, j->achiviments);
    printvector(j->publishers, j->qtdPublishers);
    printf(" ## ");
    printvector(j->developers, j->qtdDevelopers);
    printf(" ## ");
    printvector(j->categories, j->qtdCategories);
    printf(" ## ");
    printvector(j->genres, j->qtdGenres);
    printf(" ## ");
    printvector(j->tags, j->qtdTags);
    printf(" ##\n");
}

char *dataFudida(const char *dataCsv)
{
    char tmp[TAMFIELD];
    int j = 0;
    for (int i = 0; dataCsv[i] && j + 1 < sizeof(tmp); i++)
    {
        if (dataCsv[i] != '"')
            tmp[j++] = dataCsv[i];
    }
    tmp[j] = '\0';

    char buf[TAMFIELD];
    strcpy(buf, tmp);
    char *mes = NULL, *dia = NULL, *ano = NULL;
    char *p = buf;
    pularEspacos(&p);
    if (!*p)
        return copiarTexto("sem data");

    char *espaco = strchr(p, ' ');
    if (espaco)
    {
        *espaco = '\0';
        mes = p;
        char *resto = espaco + 1;
        pularEspacos(&resto);

        char *virgula = strchr(resto, ',');
        if (virgula)
        {
            *virgula = '\0';
            dia = resto;
            ano = virgula + 1;
            pularEspacos(&ano);
        }
        else
        {
            dia = "01";
            ano = resto;
        }
    }
    else
    {
        mes = "Jan";
        dia = "01";
        ano = p;
    }

    int m = 1;
    if (!strcmp(mes, "Jan"))
        m = 1;
    else if (!strcmp(mes, "Feb"))
        m = 2;
    else if (!strcmp(mes, "Mar"))
        m = 3;
    else if (!strcmp(mes, "Apr"))
        m = 4;
    else if (!strcmp(mes, "May"))
        m = 5;
    else if (!strcmp(mes, "Jun"))
        m = 6;
    else if (!strcmp(mes, "Jul"))
        m = 7;
    else if (!strcmp(mes, "Aug"))
        m = 8;
    else if (!strcmp(mes, "Sep"))
        m = 9;
    else if (!strcmp(mes, "Oct"))
        m = 10;
    else if (!strcmp(mes, "Nov"))
        m = 11;
    else if (!strcmp(mes, "Dec"))
        m = 12;

    int d = atoi(dia);
    int y = atoi(ano);
    char out[16];
    sprintf(out, "%02d/%02d/%04d", d, m, y);
    return copiarTexto(out);
}

void gameFromCsv(Game *j, const char *row)
{
    char field[TAMFIELD];
    int pos = 0, idx = 0;
    bool entreAspas = false;

    memset(j, 0, sizeof(*j));
    j->metacriticScore = -1;
    j->userScore = -1.0f;
    j->achiviments = 0;

    for (int i = 0;; i++)
    {
        char c = row[i];
        bool fim = (c == '\0');

        if (!fim && c == '"')
        {
            entreAspas = !entreAspas;
            continue;
        }

        if (fim || (c == ',' && !entreAspas))
        {
            field[pos] = '\0';
            switch (idx)
            {
            case 0:
                j->id = atoi(field);
                break;
            case 1:
                j->name = copiarTexto(field);
                break;
            case 2:
                j->releaseDate = dataFudida(field);
                break;
            case 3:
            {
                char temp[TAMFIELD];
                strcpy(temp, field);
                char *h = strchr(temp, '-');
                if (h)
                    *h = '\0';
                j->estimedOwners = atoi(temp);
            }
            break;
            case 4:
                j->price = atof(field);
                break;
            case 5:
            {
                char temp[TAMFIELD];
                strncpy(temp, field, sizeof(temp));
                temp[sizeof(temp) - 1] = '\0';
                formatField(temp);
                j->supportedLanguages = separeTxt(temp, ",", &j->qtdsupportedLanguages);
            }
            break;
            case 6:
                j->metacriticScore = (field[0] ? atoi(field) : -1);
                break;
            case 7:
                j->userScore = (!field[0] || compare(field, "tbd")) ? -1.0f : atof(field);
                break;
            case 8:
                j->achiviments = (field[0] ? atoi(field) : 0);
                break;
            case 9:
            {
                char temp[TAMFIELD];
                strncpy(temp, field, sizeof(temp));
                temp[sizeof(temp) - 1] = '\0';
                formatField(temp);
                j->publishers = separeTxt(temp, ",", &j->qtdPublishers);
            }
            break;
            case 10:
            {
                char temp[TAMFIELD];
                strncpy(temp, field, sizeof(temp));
                temp[sizeof(temp) - 1] = '\0';
                formatField(temp);
                j->developers = separeTxt(temp, ",", &j->qtdDevelopers);
            }
            break;
            case 11:
            {
                char temp[TAMFIELD];
                strncpy(temp, field, sizeof(temp));
                temp[sizeof(temp) - 1] = '\0';
                formatField(temp);
                j->categories = separeTxt(temp, ",", &j->qtdCategories);
            }
            break;
            case 12:
            {
                char temp[TAMFIELD];
                strncpy(temp, field, sizeof(temp));
                temp[sizeof(temp) - 1] = '\0';
                formatField(temp);
                j->genres = separeTxt(temp, ",", &j->qtdGenres);
            }
            break;
            case 13:
            {
                char temp[TAMFIELD];
                strncpy(temp, field, sizeof(temp));
                temp[sizeof(temp) - 1] = '\0';
                formatField(temp);
                j->tags = separeTxt(temp, ",", &j->qtdTags);
            }
            break;
            }
            idx++;
            pos = 0;
            if (fim)
                break;
        }
        else if (pos + 1 < TAMFIELD)
        {
            field[pos++] = c;
        }
    }
}

Game *leJogos(int *qtd)
{
    setlocale(LC_NUMERIC, "C");

    const char *path = "/tmp/games.csv";
    FILE *fr = fopen(path, "r");
    if (!fr)
    {
        perror("Erro ao abrir /tmp/games.csv");
        *qtd = 0;
        return NULL;
    }

    char row[TAMROW];
    int total = 0;

    if (fgets(row, sizeof(row), fr))
    {
        while (fgets(row, sizeof(row), fr))
            total++;
    }
    fclose(fr);

    Game games = (Game *)malloc(sizeof(Game) * (total > 0 ? total : 1));
    if (!games)
    {
        perror("Erro ao alocar memória para jogos");
        *qtd = 0;
        return NULL;
    }

    fr = fopen(path, "r");
    if (!fr)
    {
        perror("Erro ao reabrir /tmp/games.csv");
        free(games);
        *qtd = 0;
        return NULL;
    }

    int i = 0;
    fgets(row, sizeof(row), fr);

    while (fgets(row, sizeof(row), fr))
    {
        row[strcspn(row, "\r\n")] = 0;
        if (i < total)
        {
            gameFromCsv(&games[i], row);
            i++;
        }
    }

    fclose(fr);

    *qtd = i;
    return games;
}

void escreveLog(double tempo, int comparacoes, int movimentacoes)
{
    FILE *arquivo = fopen("889080_quick.txt", "w");
    if (arquivo == NULL)
    {
        perror("Erro ao abrir o arquivo de log");
        return;
    }

    fprintf(arquivo, "889080\tTempo de execução = %.3f ms\tNúmero de comparações = %d\tNúmero de movimentações = %d\n", tempo, comparacoes, movimentacoes);
    fclose(arquivo);
}

static int comparacoes = 0;
static int movimentacoes = 0;

int dataParaInt(const char *dataStr)
{
    int dia, mes, ano;

    if (sscanf(dataStr, "%d/%d/%d", &dia, &mes, &ano) == 3)
    {
        return ano * 10000 + mes * 100 + dia;
    }

    return 0;
}

void swap(Game *a, Game *b)
{
    Game temp = *a;
    *a = *b;
    *b = temp;
    movimentacoes += 3;
    return;
}

int particiona(Game jogos[], int esq, int dir)
{
    long pivoData = dataParaInt(jogos[(esq + dir) / 2].releaseDate);
    int i = esq - 1;
    int j = dir + 1;
    while (true)
    {
        do
        {
            i++;
            comparacoes++;
        } while (dataParaInt(jogos[i].releaseDate) < pivoData);

        do
        {
            j--;
            comparacoes++;
        } while (dataParaInt(jogos[j].releaseDate) > pivoData);

        if (i >= j)
        {
            return j;
        }
        swap(&jogos[i], &jogos[j]);
    }
}

void quicksort(Game jogos[], int esq, int dir)
{
    if (esq < dir)
    {
        int p = particiona(jogos, esq, dir);
        quicksort(jogos, esq, p);
        quicksort(jogos, p + 1, dir);
    }
}

Lista subLista(Lista jogos)
{
}

int main()
{
    int total = 0;
    Game *listaDoCsv = leJogos(&total);
    if (!listaDoCsv)
        return 1;
    Game *lista = malloc(sizeof(Game) * total);
    char entrada[TAMFIELD];
    int numBuscas = 0;
    while (fgets(entrada, sizeof(entrada), stdin))
    {
        entrada[strcspn(entrada, "\r\n")] = 0;
        if (entrada[0] == '\0')
            continue;
        if (strcmp(entrada, "FIM") == 0)
            break;
        int id = atoi(entrada);
        for (int k = 0; k < total; k++)
        {
            if (listaDoCsv[k].id == id)
            {
                lista[numBuscas] = listaDoCsv[k];
                numBuscas++;
            }
        }
    }
    clock_t inicio, fim;
    double tempo_cpu;
    inicio = clock();
    quicksort(lista, 0, numBuscas - 1);
    fim = clock();
    tempo_cpu = ((fim - inicio) / (double)CLOCKS_PER_SEC) * 1000.0;
    escreveLog(tempo_cpu, comparacoes, movimentacoes);

    for (int i = 0; i < numBuscas; i++)
    {
        printGame(&lista[i]);
    }

    for (int k = 0; k < total; k++)
    {
        freeGame(&listaDoCsv[k]);
    }

    free(listaDoCsv);
    free(lista);
    return 0;
}
