package trabalhoPO;

import java.util.ArrayList;

public class NoABB {

	private ArrayList<Reserva> listaItens;
    private NoABB esq;
    private NoABB dir;

    public NoABB(Reserva item) {
        this.listaItens = new ArrayList<>();
        this.listaItens.add(item); 
        this.esq = null;
        this.dir = null;
    }

    public Reserva getChave() {
        return listaItens.get(0);
    }

    public ArrayList<Reserva> getListaItens() {
        return listaItens;
    }

    public void adicionarItem(Reserva item) {
        this.listaItens.add(item);
    }

    public NoABB getEsq() {
        return esq;
    }

    public void setEsq(NoABB esq) {
        this.esq = esq;
    }

    public NoABB getDir() {
        return dir;
    }

    public void setDir(NoABB dir) {
        this.dir = dir;
    }
}
