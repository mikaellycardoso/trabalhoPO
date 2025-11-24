package trabalhoPO;

public class Heapsort {

    private Reserva[] lista;
    private int quant;

    public Heapsort(Reserva[] vetor) {
        this.lista = vetor;
        this.quant = vetor.length;
    }

    public void heapSort() {
        int dir = quant - 1;
        int esq = (dir - 1) / 2;
        Reserva temp;

        while (esq >= 0) {
            refazHeap(esq, this.quant - 1);
            esq--;
        }

        while (dir > 0) {
            temp = this.lista[0];
            this.lista[0] = this.lista[dir];
            this.lista[dir] = temp;
            dir--;
            refazHeap(0, dir);
        }
    }
    
    private void refazHeap(int esq, int dir) {
        int i = esq;
        int mF = 2 * i + 1;
        Reserva raiz = this.lista[i];
        boolean heap = false;

        while ((mF <= dir) && (!heap)) {
            if (mF < dir) {
                if (this.lista[mF].compareTo(this.lista[mF + 1]) < 0) {
                    mF++;
                }
            }
            if (raiz.compareTo(this.lista[mF]) < 0) {
                this.lista[i] = this.lista[mF];
                i = mF;
                mF = 2 * i + 1;
            } else {
                heap = true;
            }
        }
        
        this.lista[i] = raiz;
    }
    
    public Reserva[] getVetorOrdenado() {
        return this.lista;
    }
 
}
