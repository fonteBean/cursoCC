package exercicios.estruturas;

public class Heap{

    public static void criaHeap(int vec[],int tam, int i){
        int raiz = i;
        int esq = 2*i+1;
        int dir = 2*i+2;
        if (esq < tam && vec[esq] > vec[raiz]){
            raiz = esq;
        }
        if (dir < tam && vec[dir] > vec[raiz]){
            raiz = dir;
        }
        if (raiz != i){
            int aux = vec[i];
            vec[i] = vec[raiz];
            vec[raiz] = aux;
            criaHeap(vec, tam, raiz);
        }
        return;
    }
    public static  void heapsort(int vec[])
    {
        int tam = vec.length;
        for (int i= tam/2-1; i >=0; i--){
            criaHeap(vec,tam,i);
        }
        for (int j = tam-1;j>0;j--){
            int aux = vec[0];
            vec[0]= vec[j];
            vec[j] = aux;
            criaHeap(vec,j,0);
        }

    }

    public static void imprimeVec(int vec[]){
        for (int i = 0; i < vec.length; i++) {
            System.out.print(vec[i] + " ");
        }
        System.out.println();
    }

   public static void main(String[] args) {
        int[] vetor = new int[10];
        for(int i = 0; i < vetor.length; i++){
            vetor[i] = (int) Math.floor(Math.random() * vetor.length);
        }
        imprimeVec(vetor);
        heapsort(vetor);
        imprimeVec(vetor);
    }
}