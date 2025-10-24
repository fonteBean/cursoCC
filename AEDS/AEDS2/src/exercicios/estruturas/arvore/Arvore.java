package exercicios.estruturas.arvore;

public class Arvore {
    private No raiz;


    public Arvore(){
        this.raiz = null;
    }

    public void inserir(int x) throws Exception{
        raiz = inserir(x,raiz);
    }

    public No inserir(int x, No pai){
        if (pai == null) {
            No i = new No(x);
        }
    
        return raiz;
    }


    
}
