public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    

    ArvoreAVL arvore = new ArvoreAVL();

    int [] chaves = {10, 20, 30, 40, 50, 25};

    for (int chave : chaves) {
        arvore.raiz = arvore.inserir(arvore.raiz, chave);

        }
    
    System.out.println(("Percuso em ordem da árvore AVL:"));
    arvore.emOrdem(arvore.raiz);
    }
  
}
