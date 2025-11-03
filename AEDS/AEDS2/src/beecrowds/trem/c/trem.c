#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int ordena(int *vec, int n)
{
	int trocas = 0;
	for (int i = 0; i < n - 1; i++)
	{
		for (int j = 0; j < n - i - 1; j++)
		{
			if (vec[j] > vec[j + 1])
			{
				int temp = vec[j];
				vec[j] = vec[j + 1];
				vec[j + 1] = temp;
				trocas++;
			}
		}
	}
	return trocas;
}

int main()
{
	int casos;
	scanf("%d", &casos);
	getchar();

	for (int i = 0; i < casos; i++)
	{
		int nTermos;
		scanf("%d", &nTermos);
		getchar();

		char linha[1000];
		fgets(linha, sizeof(linha), stdin);

		int vec[nTermos];
		int j = 0;

		char *token = strtok(linha, " ");
		while (token != NULL && j < nTermos)
		{
			vec[j++] = atoi(token);
			token = strtok(NULL, " ");
		}

		int trocas = ordena(vec, nTermos);
		printf("Optimal train swapping takes %d swaps.\n", trocas);
	}

	return 0;
}
