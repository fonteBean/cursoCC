#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>
#include <locale.h>
#include <time.h>

#define TAMROW 50000
#define TAMFIELD 2056

typedef struct Game
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

typedef struct Pilha
{
    Game *topo;
} Pilha;

Pilha *novaPilha()
{
    Pilha *pilha = (Pilha *)malloc(sizeof(Pilha));
    pilha->topo = NULL;
    return pilha;
}

void limpaListaRec(Game *g)
{
    if (g == NULL)
        return;
    limpaListaRec(g->prox);
    free(g);
}

void limpaPilha(Pilha *pilha)
{
    limpaListaRec(pilha->topo);
    free(pilha);
}

void inserir(Pilha *pilha, Game *g)
{
    g->prox = pilha->topo;
    pilha->topo = g;
}

char *remover(Pilha *pilha)
{
    if (pilha->topo == NULL)
        return NULL;

    Game *removido = pilha->topo;
    pilha->topo = removido->prox;

    static char nomeRemovido[100];
    strcpy(nomeRemovido, removido->name);

    free(removido);
    return nomeRemovido;
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

Pilha *leJogos()
{
    setlocale(LC_NUMERIC, "C");

    const char *path = "/tmp/games.csv";
    FILE *fr = fopen(path, "r");
    if (!fr)
    {
        perror("Erro ao abrir /tmp/games.csv");
        return NULL;
    }

    char row[TAMROW];
    Pilha *pilha = novaPilha();

    if (!fgets(row, sizeof(row), fr))
    {
        fclose(fr);
        return pilha;
    }

    while (fgets(row, sizeof(row), fr))
    {
        row[strcspn(row, "\r\n")] = 0;

        Game *novo = malloc(sizeof(Game));
        if (!novo)
        {
            perror("Erro ao alocar memória para jogo");
            fclose(fr);
            limpaPilha(pilha);
            return NULL;
        }

        gameFromCsv(novo, row);
        novo->prox = NULL;
        inserir(pilha, novo);
    }

    fclose(fr);
    return pilha;
}

int dataParaInt(const char *dataStr)
{
    int dia, mes, ano;

    if (sscanf(dataStr, "%d/%d/%d", &dia, &mes, &ano) == 3)
    {
        return ano * 10000 + mes * 100 + dia;
    }

    return 0;
}

void imprimePilhaRec(Game *g, int *i)
{
    if (g == NULL)
        return;

    imprimePilhaRec(g->prox, i);
    printf("[%d] ", (*i)++);
    printGame(g);
}

int imprimePilha(Pilha *p)
{
    int i = 0;
    imprimePilhaRec(p->topo, &i);
    return i;
}

Game *buscaGame(Pilha *pilha, int id)
{
    for (Game *tmp = pilha->topo; tmp != NULL; tmp = tmp->prox)
    {
        if (tmp->id == id)
        {
            Game *clone = malloc(sizeof(Game));
            if (!clone)
                return NULL;

            *clone = *tmp;

            clone->name = strdup(tmp->name);
            clone->releaseDate = strdup(tmp->releaseDate);

            if (tmp->qtdsupportedLanguages > 0)
            {
                clone->supportedLanguages = malloc(sizeof(char *) * tmp->qtdsupportedLanguages);
                for (int i = 0; i < tmp->qtdsupportedLanguages; i++)
                    clone->supportedLanguages[i] = strdup(tmp->supportedLanguages[i]);
            }
            else
            {
                clone->supportedLanguages = NULL;
            }

            if (tmp->qtdPublishers > 0)
            {
                clone->publishers = malloc(sizeof(char *) * tmp->qtdPublishers);
                for (int i = 0; i < tmp->qtdPublishers; i++)
                    clone->publishers[i] = strdup(tmp->publishers[i]);
            }
            else
            {
                clone->publishers = NULL;
            }

            if (tmp->qtdDevelopers > 0)
            {
                clone->developers = malloc(sizeof(char *) * tmp->qtdDevelopers);
                for (int i = 0; i < tmp->qtdDevelopers; i++)
                    clone->developers[i] = strdup(tmp->developers[i]);
            }
            else
            {
                clone->developers = NULL;
            }

            if (tmp->qtdCategories > 0)
            {
                clone->categories = malloc(sizeof(char *) * tmp->qtdCategories);
                for (int i = 0; i < tmp->qtdCategories; i++)
                    clone->categories[i] = strdup(tmp->categories[i]);
            }
            else
            {
                clone->categories = NULL;
            }

            if (tmp->qtdGenres > 0)
            {
                clone->genres = malloc(sizeof(char *) * tmp->qtdGenres);
                for (int i = 0; i < tmp->qtdGenres; i++)
                    clone->genres[i] = strdup(tmp->genres[i]);
            }
            else
            {
                clone->genres = NULL;
            }

            if (tmp->qtdTags > 0)
            {
                clone->tags = malloc(sizeof(char *) * tmp->qtdTags);
                for (int i = 0; i < tmp->qtdTags; i++)
                    clone->tags[i] = strdup(tmp->tags[i]);
            }
            else
            {
                clone->tags = NULL;
            }

            clone->prox = NULL;
            return clone;
        }
    }
    return NULL;
}

void menu(Pilha *baseCsv, Pilha *pilha, const char *entrada)
{
    char copia[TAMFIELD];
    strncpy(copia, entrada, sizeof(copia));
    copia[sizeof(copia) - 1] = '\0';

    char *op = strtok(copia, " ");
    char *arg1 = strtok(NULL, " ");

    if (!op)
    {
        printf("Escreva uma opção válida\n");
        return;
    }

    int id;

    if (strcmp(op, "I") == 0)
    {
        if (!arg1)
            return;
        id = atoi(arg1);
        Game *novo = buscaGame(baseCsv, id);
        if (novo)
            inserir(pilha, novo);
    }
    else if (strcmp(op, "R") == 0)
    {
        char *nome = remover(pilha);
        if (nome)
            printf("(R) %s\n", nome);
    }

    else
    {
        printf("Escreva uma opção válida\n");
    }
}

Pilha *criasubPilha(Pilha *jogos)
{
    Pilha *subPilha = novaPilha();
    char linha[TAMFIELD];

    while (fgets(linha, sizeof(linha), stdin))
    {
        linha[strcspn(linha, "\r\n")] = 0;
        char *p = linha;
        while (*p && isspace((unsigned char)*p))
            p++;

        if (strcmp(p, "FIM") == 0)
            break;

        if (*p == '\0')
            continue;

        int id = atoi(p);
        Game *g = buscaGame(jogos, id);

        if (g)
        {
            inserir(subPilha, g);
        }
        else
        {
            printf("Not Found\n");
        }
    }

    return subPilha;
}

int main()
{
    Pilha *jogos = leJogos();
    if (!jogos)
        return 1;

    Pilha *subPilha = criasubPilha(jogos);

    char linha[TAMFIELD];
    if (!fgets(linha, sizeof(linha), stdin))
    {
        printf("Erro ao ler o número de operações.\n");
        limpaPilha(subPilha);
        limpaPilha(jogos);
        return 1;
    }

    int n = atoi(linha);

    for (int i = 0; i < n; i++)
    {
        if (!fgets(linha, sizeof(linha), stdin))
            break;
        linha[strcspn(linha, "\r\n")] = 0;
        menu(jogos, subPilha, linha);
    }

    imprimePilha(subPilha);

    limpaPilha(subPilha);
    limpaPilha(jogos);

    return 0;
}