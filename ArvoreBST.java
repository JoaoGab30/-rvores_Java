public class ArvoreBST {
    No raiz;

    public No rotacaoEsquerda(No x) {
        No y = x.direito;
        x.direito = y.esquerdo;

        if (y.esquerdo != null)
            y.esquerdo.pai = x;

        y.pai = x.pai;

        if (x.pai == null)
            raiz = y;
        else if (x == x.pai.esquerdo)
            x.pai.esquerdo = y;
        else
            x.pai.direito = y;

        y.esquerdo = x;
        x.pai = y;

        return y;
    }

    public No rotacaoDireita(No y) {
        No x = y.esquerdo;
        y.esquerdo = x.direito;

        if (x.direito != null)
            x.direito.pai = y;

        x.pai = y.pai;

        if (y.pai == null)
            raiz = x;
        else if (y == y.pai.direito)
            y.pai.direito = x;
        else
            y.pai.esquerdo = x;

        x.direito = y;
        y.pai = x;

        return x;
    }
    public void inserir(int chave) {
    No novo = new No(chave);
    No y = null;
    No x = raiz;

    while (x != null) {
        y = x;
        if (novo.chave < x.chave) {
            x = x.esquerdo;
        } else {
            x = x.direito;
        }
    }

    novo.pai = y;

    if (y == null) {
        raiz = novo;
    } else if (novo.chave < y.chave) {
        y.esquerdo = novo;
    } else {
        y.direito = novo;
    }

    novo.Cor = cor.VERMELHO;
    corrigirInsercao(novo);
}


    public void corrigirInsercao(No z) {
        while (z.pai != null && z.pai.Cor == cor.VERMELHO) {
            No avo = z.pai.pai;
            if (z.pai == avo.esquerdo) {
                No tio = avo.direito;
                if (tio != null && tio.Cor == cor.VERMELHO) {
                    z.pai.Cor = cor.PRETO;
                    tio.Cor = cor.PRETO;
                    avo.Cor = cor.VERMELHO;
                    z = avo;
                } else {
                    if (z == z.pai.direito) {
                        z = z.pai;
                        rotacaoEsquerda(z);
                    }
                    z.pai.Cor = cor.PRETO;
                    avo.Cor = cor.VERMELHO;
                    rotacaoDireita(avo);
                }
            } else {
                No tio = avo.esquerdo;
                if (tio != null && tio.Cor == cor.VERMELHO) {
                    z.pai.Cor = cor.PRETO;
                    tio.Cor = cor.PRETO;
                    avo.Cor = cor.VERMELHO;
                    z = avo;
                } else {
                    if (z == z.pai.esquerdo) {
                        z = z.pai;
                        rotacaoDireita(z);
                    }
                    z.pai.Cor = cor.PRETO;
                    avo.Cor = cor.VERMELHO;
                    rotacaoEsquerda(avo);
                }
            }
        }
        raiz.Cor = cor.PRETO;
    }

    public void transplante(No u, No v) {
        if (u.pai == null)
            raiz = v;
        else if (u == u.pai.esquerdo)
            u.pai.esquerdo = v;
        else
            u.pai.direito = v;

        if (v != null)
            v.pai = u.pai;
    }

    public No minimo(No no) {
        while (no.esquerdo != null)
            no = no.esquerdo;
        return no;
    }

    public void delecao(int chave) {
        No z = procurarArvore(raiz, chave);
        if (z == null)
            return;

        No y = z;
        cor yOriginalColor = y.Cor;
        No x;

        if (z.esquerdo == null) {
            x = z.direito;
            transplante(z, z.direito);
        } else if (z.direito == null) {
            x = z.esquerdo;
            transplante(z, z.esquerdo);
        } else {
            y = minimo(z.direito);
            yOriginalColor = y.Cor;
            x = y.direito;

            if (y.pai != z) {
                transplante(y, y.direito);
                y.direito = z.direito;
                if (y.direito != null)
                    y.direito.pai = y;
            }

            transplante(z, y);
            y.esquerdo = z.esquerdo;
            if (y.esquerdo != null)
                y.esquerdo.pai = y;
            y.Cor = z.Cor;
        }

        if (yOriginalColor == cor.PRETO && x != null) {
            deletarInsercao(x);
        }
    }

    public void deletarInsercao(No x) {
        while (x != raiz && x.Cor == cor.PRETO) {
            if (x == x.pai.esquerdo) {
                No w = x.pai.direito;
                if (w.Cor == cor.VERMELHO) {
                    w.Cor = cor.PRETO;
                    x.pai.Cor = cor.VERMELHO;
                    rotacaoEsquerda(x.pai);
                    w = x.pai.direito;
                }
                if ((w.esquerdo == null || w.esquerdo.Cor == cor.PRETO) &&
                    (w.direito == null || w.direito.Cor == cor.PRETO)) {
                    w.Cor = cor.VERMELHO;
                    x = x.pai;
                } else {
                    if (w.direito == null || w.direito.Cor == cor.PRETO) {
                        if (w.esquerdo != null)
                            w.esquerdo.Cor = cor.PRETO;
                        w.Cor = cor.VERMELHO;
                        rotacaoDireita(w);
                        w = x.pai.direito;
                    }

                    w.Cor = x.pai.Cor;
                    x.pai.Cor = cor.PRETO;
                    if (w.direito != null)
                        w.direito.Cor = cor.PRETO;
                    rotacaoEsquerda(x.pai);
                    x = raiz;
                }
            } else {
                No w = x.pai.esquerdo;
                if (w.Cor == cor.VERMELHO) {
                    w.Cor = cor.PRETO;
                    x.pai.Cor = cor.VERMELHO;
                    rotacaoDireita(x.pai);
                    w = x.pai.esquerdo;
                }
                if ((w.direito == null || w.direito.Cor == cor.PRETO) &&
                    (w.esquerdo == null || w.esquerdo.Cor == cor.PRETO)) {
                    w.Cor = cor.VERMELHO;
                    x = x.pai;
                } else {
                    if (w.esquerdo == null || w.esquerdo.Cor == cor.PRETO) {
                        if (w.direito != null)
                            w.direito.Cor = cor.PRETO;
                        w.Cor = cor.VERMELHO;
                        rotacaoEsquerda(w);
                        w = x.pai.esquerdo;
                    }

                    w.Cor = x.pai.Cor;
                    x.pai.Cor = cor.PRETO;
                    if (w.esquerdo != null)
                        w.esquerdo.Cor = cor.PRETO;
                    rotacaoDireita(x.pai);
                    x = raiz;
                }
            }
        }
        x.Cor = cor.PRETO;
    }

    public No procurarArvore(No no, int chave) {
        if (no == null || chave == no.chave)
            return no;
        if (chave < no.chave)
            return procurarArvore(no.esquerdo, chave);
        return procurarArvore(no.direito, chave);
    }

    public void emOrdem() {
        emOrdemAjuda(raiz);
    }

    public void emOrdemAjuda(No no) {
        if (no != null) {
            emOrdemAjuda(no.esquerdo);
            String corSuffix = (no.Cor == cor.VERMELHO) ? "R" : "B";
            System.out.println(no.chave + corSuffix);
            emOrdemAjuda(no.direito);
        }
    }
}
