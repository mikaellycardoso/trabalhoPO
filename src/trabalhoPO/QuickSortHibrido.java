package trabalhoPO;

public class QuickSortHibrido {

    private Reserva[] lista;

    public QuickSortHibrido(Reserva[] lista) {
        this.lista = lista;
    }

    public void quicksort() {
        if (this.lista != null && this.lista.length > 1) {
            ordena(0, this.lista.length - 1);
        }
    }

    private void ordena(int esq, int dir) {
        int tamanho = dir - esq + 1;

        if (tamanho <= 20) {
            insercaoDireta(esq, dir);
        } else {
            particionarERecorrer(esq, dir);
        }
    }

    private void particionarERecorrer(int esq, int dir) {
        int i = esq;
        int j = dir;
        Reserva temp;

        Reserva pivo = this.lista[(i + j) / 2];

        do {
            while (comparar(this.lista[i], pivo) < 0) {
                i++;
            }
            while (comparar(this.lista[j], pivo) > 0) {
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

    private void insercaoDireta(int esq, int dir) {
        int i, j;
        Reserva temp;

        for (i = esq + 1; i <= dir; i++) {
            temp = this.lista[i];
            j = i - 1;

            while ((j >= esq) && (comparar(this.lista[j], temp) > 0)) {
                this.lista[j + 1] = this.lista[j];
                j--;
            }

            this.lista[j + 1] = temp;
        }
    }

    private int comparar(Reserva r1, Reserva r2) {
        return r1.compareTo(r2);
    }

    public Reserva[] getVetorOrdenado() {
        return this.lista;
    }
}
