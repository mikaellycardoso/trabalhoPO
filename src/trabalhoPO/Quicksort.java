package trabalhoPO;

public class Quicksort {

    private Reserva[] lista;
    private int quant;

    public Quicksort(Reserva[] vetor) {
        this.lista = vetor;
        this.quant = vetor.length;
    }
    public void quicksort() {
        ordena(0, this.quant - 1);
    }
    
    private void ordena(int esq, int dir) {
        Reserva pivo, temp;
        int i = esq, j = dir;

        pivo = this.lista[(i + j) / 2];

        do {
            while (this.lista[i].compareTo(pivo) < 0) {
                i++;
            }
            while (this.lista[j].compareTo(pivo) > 0) {
                j--;
            }

            if (i <= j) {
                temp = this.lista[i];
                this.lista[i] = this.lista[j];
                this.lista[j] = temp;
                i++;
                j--;
            }

        } while (i <= j);

        if (esq < j) {
            ordena(esq, j);
        }
        
        if (dir > i) {
            ordena(i, dir);
        }
    }

    public Reserva[] getVetorOrdenado() {
        return this.lista;
    }
}