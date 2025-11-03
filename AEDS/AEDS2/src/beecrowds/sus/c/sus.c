#include <stdio.h>
#include <stdlib.h>
#include <string.h>


typedef struct Paciente{
	int hora;
	int minutos;
	int horaMaxima;
	int critico;
}Paciente;


int formataHora(int hora){
	if(hora
}

void formataPaciente(Paciente p){
	p.horaMaxima =  hora*100 + p.minutos + p.critico;
}


int formata
int tratamento(Paciente **pacientes,int){
	int horaAtual = pacientes[0].horaMaxima;
	int temGente=0;


}

int main(){
	while(true){
		int entradas;
		scanf("%d",&entradas);
		Paciente **pacientes = malloc(sizeof(Paciente) * entradas);
		for(int i=0; i < entradas; i++){ 
			scanf("%d %d %d",pacientes[i].hora,pacientes.minutos,pacientes[i].critico);
		}

		
	}
}
