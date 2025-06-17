import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ArvoreBinaria {

    int contarNos(No no) {
        if (no == null) {
            return 0;
        }
        return 1 + contarNos(no.esquerdo) + contarNos(no.direito);
    }

    void preOrdem(No no) {
        if (no != null) {
            System.out.print(no.valor + " ");
            preOrdem(no.esquerdo);
            preOrdem(no.direito);
        }
    }

    void emOrdem(No no) {
        if (no != null) {
            emOrdem(no.esquerdo);
            System.out.print(no.valor + " ");
            emOrdem(no.direito);
        }
    }
    
    void posOrdem(No no) {
        if (no != null) {
            posOrdem(no.esquerdo);
            preOrdem(no.direito);
            System.out.println(no.valor + " ");
        }
    }

    int altura(No no) {
        if (no == null) return 0;
            int altEsq = altura(no.esquerdo);
            int altDir = altura(no.direito);
            return 1 + Math.max(altEsq, altDir);
}

    void imprimirNivel(No no, int nivel) {
        if (no == null) return;
        if (nivel == 1) {
            System.out.print(no.valor + " ");
        } else {
            imprimirNivel(no.esquerdo, nivel - 1);
            imprimirNivel(no.direito, nivel - 1);
        }
}

    void porNivelRecursivo(No raiz) {
        int h = altura(raiz);
        for (int i = 1; i <= h; i++) {
        imprimirNivel(raiz, i);
    }
}

    void preOrdemIterativo(No raiz) {
        if (raiz == null) return;

        Stack<No> pilha = new Stack<>();
        pilha.push(raiz);

        while (!pilha.isEmpty()) {
            No atual = pilha.pop();
            System.out.print(atual.valor + " ");

            if (atual.direito != null) {
                pilha.push(atual.direito);
            }
            if (atual.esquerdo != null) {
                pilha.push(atual.esquerdo);
            }
        }
    }

    void emOrdemIterativo(No raiz) {
        Stack<No> pilha = new Stack<>();
        No atual = raiz;

        while (atual != null || !pilha.isEmpty()) {
            while (atual != null) {
                pilha.push(atual);
                atual = atual.esquerdo;
            }

            atual = pilha.pop();
            System.out.print(atual.valor + " ");
            atual = atual.direito;
        }
    }
    void posOrdemIterativo(No raiz) {
    if (raiz == null) return;

    Stack<No> pilha1 = new Stack<>();
    Stack<No> pilha2 = new Stack<>();

    pilha1.push(raiz);

    while (!pilha1.isEmpty()) {
        No atual = pilha1.pop();
        pilha2.push(atual);

        if (atual.esquerdo != null) {
            pilha1.push(atual.esquerdo);
        }
        if (atual.direito != null) {
            pilha1.push(atual.direito);
        }
    }

    while (!pilha2.isEmpty()) {
        System.out.print(pilha2.pop().valor + " ");
    }
}
    void porNivel(No raiz) {
    if (raiz == null) return;

    Queue<No> fila = new LinkedList<>();
    fila.add(raiz);

    while (!fila.isEmpty()) {
        No atual = fila.poll();
        System.out.print(atual.valor + " ");

        if (atual.esquerdo != null) {
            fila.add(atual.esquerdo);
        }
        if (atual.direito != null) {
            fila.add(atual.direito);
        }
    }   
}

public int contarNosIterativo(No raiz) {
    if (raiz == null) {
        return 0;
    }

    Queue<No> fila = new LinkedList<>();
    fila.add(raiz);

    int contador = 0;

    while (!fila.isEmpty()) {
        No atual = fila.poll();
        contador++;

        if (atual.esquerdo != null) {
            fila.add(atual.esquerdo);
        }
        if (atual.direito != null) {
            fila.add(atual.direito);
        }
    }

    return contador;
}
    

    public int contarNosIterativoPilha(No raiz) {
        if (raiz == null) {
            return 0;
        }

        Stack<No> pilha = new Stack<>();
        pilha.push(raiz);

        int contador = 0;

        while (!pilha.isEmpty()) {
            No atual = pilha.pop();
            contador++;

            if (atual.direito != null) {
                pilha.push(atual.direito);
            }
            if (atual.esquerdo != null) {
                pilha.push(atual.esquerdo);
            }
        }

    }

    public int contarNosFolhaRecursivo(No no) {
    if (no == null) {
        return 0;
    }

    if (no.esquerdo == null && no.direito == null) {
        return 1;
    }

    return contarNosFolhaRecursivo(no.esquerdo) + contarNosFolhaRecursivo(no.direito);
}
    public int contarNosFolhaIterativo(No raiz) {
    if (raiz == null) {
        return 0;
    }

    Queue<No> fila = new LinkedList<>();
    fila.add(raiz);

    int contador = 0;

    while (!fila.isEmpty()) {
        No atual = fila.poll();

        if (atual.esquerdo == null && atual.direito == null) {
            contador++;
        }

        if (atual.esquerdo != null) {
            fila.add(atual.esquerdo);
        }

        if (atual.direito != null) {
            fila.add(atual.direito);
        }
    }

    return contador;
}


}
