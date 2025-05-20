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

}
