public class ArvoreAVL {
    No raiz;

    int altura(No no) {
        return no == null ? 0 : no.altura;
    }

    int fatorBalanceamento(No no) {
        return no == null ? 0 : altura(no.esquerdo) - altura(no.direito);
    }

    No rotacaoDireita(No y) {
        No x = y.esquerdo;
        No T2 = x.direito;

        x.direito = y;
        y.esquerdo = T2;

        y.altura = Math.max(altura(y.esquerdo), altura(y.direito)) + 1;
        x.altura = Math.max(altura(x.esquerdo), altura(x.direito)) + 1;

        return x;
    }

    No rotacaoEsquerda(No x) {
        No y = x.direito;
        No T2 = y.esquerdo;

        y.esquerdo = x;
        x.direito = T2;

        x.altura = Math.max(altura(x.esquerdo), altura(x.direito)) + 1;
        y.altura = Math.max(altura(y.esquerdo), altura(y.direito)) + 1;

        return y;
    }

    No inserir(No no, int valor) {
        if (no == null) {
            return new No(valor);
        }

        if (valor < no.valor) {
            no.esquerdo = inserir(no.esquerdo, valor);
        } else if (valor > no.valor) {
            no.direito = inserir(no.direito, valor);
        } else {
            return no; 
        }

        no.altura = 1 + Math.max(altura(no.esquerdo), altura(no.direito));

        int fb = fatorBalanceamento(no);


        if (fb > 1 && valor < no.esquerdo.valor) {
            return rotacaoDireita(no);
        }

        if (fb < -1 && valor > no.direito.valor) {
            return rotacaoEsquerda(no);
        }

        if (fb > 1 && valor > no.esquerdo.valor) {
            no.esquerdo = rotacaoEsquerda(no.esquerdo);
            return rotacaoDireita(no);
        }

        if (fb < -1 && valor < no.direito.valor) {
            no.direito = rotacaoDireita(no.direito);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    void inserir(int valor) {
        raiz = inserir(raiz, valor);
    }


    void emOrdem(No no) {
        if (no != null) {
            emOrdem(no.esquerdo);
            System.out.print(no.valor + " ");
            emOrdem(no.direito);
        }
}
}