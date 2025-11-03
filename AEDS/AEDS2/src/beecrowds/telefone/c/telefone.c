#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int tam(char *str)
{
	int tam = 0;
	for (; str[tam] != '\0'; tam++)
		;
	return tam;
}
int mudanca(char *numero1, char *numero2)
{
	int tamanho = tam(numero1);
	int contador = 0;
	for (int i = 0; i < tamanho; i++)
	{
		if (numero1[i] == numero2[i])
		{
			// printf("[%c %c]",numero1[i],numero2[i]);

			contador++;
		}
	}
	return contador;
}

int contaMudancas(char numeros[][200], int n)
{
	int maior = 0;
	for (int i = 0; i < n - 1; i++)
	{
		int mudancas = mudanca(numeros[i], numeros[i + 1]);
		maior = maior < mudancas ? mudancas : maior;
	}
	return maior;
}

int main()
{
	int entradas;
	while (scanf("%d", &entradas) != EOF && entradas > 0)
	{
		char numeros[entradas][200];

		for (int i = 0; i < entradas; i++)
		{
			char tel[200];
			scanf("%s", tel);
			strcpy(numeros[i], tel);
		}
		int mudancas = contaMudancas(numeros, entradas);
		printf("%i\n", mudancas);
	}

	return 0;
}
