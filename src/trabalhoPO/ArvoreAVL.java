package trabalhoPO;

import java.util.ArrayList;

public class ArvoreAVL {

    private NoAVL raiz;
    private int quant;
	private boolean h; 

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
    
    private int altura(NoAVL N) {
        if (N == null)
            return 0;
        return N.getAltura();
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
    

    private int getFatorBalanceamento(NoAVL N) {
        if (N == null)
            return 0;
        return altura(N.getEsq()) - altura(N.getDir());
    }
    
    private NoAVL rotacaoDireita(NoAVL no){
        NoAVL temp1, temp2;
        temp1 = no.getEsq();

        if (temp1.getFatorBalanceamento() == -1){
            no.setEsq(temp1.getDir());
            temp1.setDir(no);
            no.setFatorBalanceamento(0);
            no = temp1;
        } else {
            temp2 = temp1.getDir();
            temp1.setDir(temp2.getEsq());
            temp2.setEsq(temp1);
            no.setEsq(temp2.getDir());
            temp2.setDir(no);


            if (temp2.getFatorBalanceamento() == -1)
                no.setFatorBalanceamento(1);
            else
                no.setFatorBalanceamento(0);
            if (temp2.getFatorBalanceamento() == 1)
                temp1.setFatorBalanceamento(-1);
            else
                temp1.setFatorBalanceamento(0);
            no = temp2;
        }
        no.setFatorBalanceamento(0);
        this.h = false;
        return no;
    }

    private NoAVL rotacaoEsquerda(NoAVL no){
        NoAVL temp1, temp2;
        temp1 = no.getDir();

        if (temp1.getFatorBalanceamento() == 1){
            no.setDir(temp1.getEsq());
            temp1.setEsq(no);
            no.setFatorBalanceamento(0);
            no = temp1;
        } else {

            temp2 = temp1.getEsq();
            temp1.setEsq(temp2.getDir());
            temp2.setDir(temp1);
            no.setDir(temp2.getEsq());
            temp2.setEsq(no);

            if (temp2.getFatorBalanceamento() == 1)
                no.setFatorBalanceamento(-1);
            else
                no.setFatorBalanceamento(0);

            if (temp2.getFatorBalanceamento() == -1)
                temp1.setFatorBalanceamento(1);
            else
                temp1.setFatorBalanceamento(0);

            no = temp2;
        }

        no.setFatorBalanceamento(0);
        this.h = false;
        return no;
    }

   
}
