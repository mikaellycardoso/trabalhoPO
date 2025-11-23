package trabalhoPO;

public class ArvoreABB {

    private NoABB raiz;
    private int quant;

    public ArvoreABB() {
        this.raiz = null;
        this.quant = 0;
    }

    public NoABB getRaiz() {
        return raiz;
    }
    public int getQuant() {
        return quant;
    }

    public void inserir(Reserva item) {
        NoABB aux = pesquisar(item.getNome(), this.raiz);
        if (aux != null) {
            return;
        }
        this.raiz = inserir(item, this.raiz);
        this.quant++;
    }

    private NoABB inserir(Reserva item, NoABB no) {
        if (no == null) {
            no = new NoABB(item);
        } else {
            int comparacao = item.getNome().compareToIgnoreCase(no.getItem().getNome());
            if (comparacao > 0) {
                no.setDir(inserir(item, no.getDir()));
            } else {
                no.setEsq(inserir(item, no.getEsq()));
            }
        }
        return no;
    }

    public NoABB pesquisar(String nome) {
        return pesquisar(nome, this.raiz);
    }

    private NoABB pesquisar(String nome, NoABB no) {
        if (no == null) {
            return null;
        }
        int comparacao = nome.compareToIgnoreCase(no.getItem().getNome());

        if (comparacao == 0) {
            return no;
        } else if (comparacao > 0) {
            return pesquisar(nome, no.getDir());
        } else {
            return pesquisar(nome, no.getEsq());
        }
    }



    public VetorItem CamCentral () {
        VetorItem vetor = new VetorItem();
        return (fazCamCentral(this.raiz, vetor));
    }


    private VetorItem fazCamCentral (NoABB no, VetorItem vetor) {
        if (no != null) {
            vetor = this.fazCamCentral(no.getEsq(), vetor);
            vetor.inserir(no.getItem());
            vetor = this.fazCamCentral(no.getDir(), vetor);
        }
        return vetor;
    }

    public ArvoreABB balancear() {
        ArvoreABB arv = new ArvoreABB();
        VetorItem vetor = CamCentral();
        balancear(vetor, arv, 0, this.quant - 1);
        return arv;
    }
    private void balancear(VetorItem vetor, ArvoreABB arv, int inicio, int fim) {
        int meio;
        if (inicio <= fim) {
            meio = (inicio + fim) / 2;
            arv.inserir(vetor.get(meio));
            balancear(vetor, arv, inicio, meio - 1);
            balancear(vetor, arv, meio + 1, fim);
        }
    }
}