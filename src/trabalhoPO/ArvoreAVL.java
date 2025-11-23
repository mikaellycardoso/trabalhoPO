public class ArvoreAVL {

    private NoAVL raiz;
    private int quant;
    private boolean h;

    public ArvoreAVL() {
        this.raiz = null;
        this.quant = 0;
        this.h = false;
    }

    private int getFatorBalanceamento(NoAVL no) {
        if (no == null) {
            return 0;
        } else {
            return no.getFatorBalanceamento();
        }
    }

    public void inserir(Reserva item) {
        this.h = false;
        this.raiz = this.inserir(item, this.raiz);
    }

    private NoAVL inserir(Reserva item, NoAVL no) {
        if (no == null) {
            this.h = true;
            this.quant++;
            return new NoAVL(item);
        }

        int comparacao = item.getNome().compareToIgnoreCase(no.getItem().getNome());

        if (comparacao > 0) {
            no.setDir(this.inserir(item, no.getDir()));
            no = this.balancearEsq(no);
            return no;

        } else if (comparacao < 0) {
            no.setEsq(this.inserir(item, no.getEsq()));
            no = this.balancearDir(no);
            return no;

        } else {
            this.h = false;
            return no;
        }
    }

    public NoAVL pesquisar(String nome) {
        return pesquisar(nome, this.raiz);
    }

    private NoAVL pesquisar(String nome, NoAVL no) {
        if (no == null) { return null; }
        int comparacao = nome.compareToIgnoreCase(no.getItem().getNome());

        if (comparacao == 0) {
            return no;
        } else if (comparacao > 0) {
            return pesquisar(nome, no.getDir());
        } else {
            return pesquisar(nome, no.getEsq());
        }
    }

    private NoAVL balancearDir(NoAVL no) {
        if (this.h) {
            no.setFatorBalanceamento(no.getFatorBalanceamento() + 1);

            if (no.getFatorBalanceamento() == 0) {
                this.h = false;
            } else if (no.getFatorBalanceamento() == 2) {
                no = rotacaoDireita(no);
            }
        }
        return no;
    }

    private NoAVL balancearEsq(NoAVL no) {
        if (this.h) {
            no.setFatorBalanceamento(no.getFatorBalanceamento() - 1);
            if (no.getFatorBalanceamento() == 0) {
                this.h = false;
            } else if (no.getFatorBalanceamento() == -2) {
                no = rotacaoEsquerda(no);
            }
        }
        return no;
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
