package trabalhoPO;

public class NoABB {

    private Reserva item;
    private NoABB esq;
    private NoABB dir;

    public NoABB(Reserva item) {
        this.item = item;
        this.esq = null;
        this.dir = null;
    }

    public Reserva getItem() {
        return item;
    }

    public void setItem(Reserva item) {
        this.item = item;
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