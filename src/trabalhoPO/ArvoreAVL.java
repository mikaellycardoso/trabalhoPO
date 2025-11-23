package trabalhoPO;

import java.util.ArrayList;

public class ArvoreAVL {

    private NoAVL raiz;
    private int quant; 

    public ArvoreAVL() {
        this.raiz = null;
        this.quant = 0;
    }

    public int getQuant() {
        return quant;
    }
    public void inserir(Reserva item) {
        this.raiz = inserir(this.raiz, item);
        this.quant++; 
    }
    private NoAVL inserir(NoAVL no, Reserva item) {
        if (no == null) {
            return new NoAVL(item);
        }

        int comparacao = item.getNome().compareToIgnoreCase(no.getChave().getNome());

        if (comparacao < 0) {
            no.setEsq(inserir(no.getEsq(), item));
        } else if (comparacao > 0) {
            no.setDir(inserir(no.getDir(), item));
        } else {
            no.adicionarItem(item);
            return no;
        }

        no.setAltura(1 + Math.max(altura(no.getEsq()), altura(no.getDir())));

        int balanceamento = getFatorBalanceamento(no);

        if (balanceamento > 1 && item.getNome().compareToIgnoreCase(no.getEsq().getChave().getNome()) < 0) {
            return rotacaoDireita(no);
        }

        if (balanceamento < -1 && item.getNome().compareToIgnoreCase(no.getDir().getChave().getNome()) > 0) {
            return rotacaoEsquerda(no);
        }

        if (balanceamento > 1 && item.getNome().compareToIgnoreCase(no.getEsq().getChave().getNome()) > 0) {
            no.setEsq(rotacaoEsquerda(no.getEsq()));
            return rotacaoDireita(no);
        }

        if (balanceamento < -1 && item.getNome().compareToIgnoreCase(no.getDir().getChave().getNome()) < 0) {
            no.setDir(rotacaoDireita(no.getDir()));
            return rotacaoEsquerda(no);
        }

        return no;
    }
    
    public ArrayList<Reserva> pesquisar(String nome) {
        return pesquisar(nome, this.raiz);
    }

    private ArrayList<Reserva> pesquisar(String nome, NoAVL no) {
        if (no == null) {
            return null;
        }
        int comparacao = nome.compareToIgnoreCase(no.getChave().getNome());

        if (comparacao == 0) {
            return no.getListaItens();
        } else if (comparacao > 0) {
            return pesquisar(nome, no.getDir());
        } else {
            return pesquisar(nome, no.getEsq());
        }
    }
    private int altura(NoAVL N) {
        if (N == null)
            return 0;
        return N.getAltura();
    }

    private int getFatorBalanceamento(NoAVL N) {
        if (N == null)
            return 0;
        return altura(N.getEsq()) - altura(N.getDir());
    }

    private NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.getEsq();
        NoAVL T2 = x.getDir();

        x.setDir(y);
        y.setEsq(T2);

        y.setAltura(Math.max(altura(y.getEsq()), altura(y.getDir())) + 1);
        x.setAltura(Math.max(altura(x.getEsq()), altura(x.getDir())) + 1);

        return x;
    }

    private NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.getDir();
        NoAVL T2 = y.getEsq();

        y.setEsq(x);
        x.setDir(T2);

        x.setAltura(Math.max(altura(x.getEsq()), altura(x.getDir())) + 1);
        y.setAltura(Math.max(altura(y.getEsq()), altura(y.getDir())) + 1);

        return y;
    }
}
