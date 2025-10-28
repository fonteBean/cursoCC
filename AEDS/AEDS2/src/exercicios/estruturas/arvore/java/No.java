package exercicios.estruturas.arvore.java;

public class No {
    int elemento;
    No esq,dir;

    public No(int e){
        elemento =e;
        esq = dir = null;
    }
    No(){
        this.elemento = 0;
        esq = dir = null;
    }
}
