package trabalhoPO;

import java.util.ArrayList;

public class QuickSortHibrido {

    private ArrayList<Reserva> lista;

    public QuickSortHibrido(ArrayList<Reserva> lista) {
        this.lista = lista;
    }

    public void quicksort() {
        if (this.lista != null && this.lista.size() > 1) {
            ordena(0, this.lista.size() - 1);
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

        Reserva pivo = this.lista.get((i + j) / 2);

        do {
            while (comparar(this.lista.get(i), pivo) < 0) {
                i++;
            }
            while (comparar(this.lista.get(j), pivo) > 0) {
                j--;
            }

            if (i <= j) {
                temp = this.lista.get(i);
                this.lista.set(i, this.lista.get(j));
                this.lista.set(j, temp);
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
            temp = this.lista.get(i);
            j = i - 1;

            while ((j >= esq) && (comparar(this.lista.get(j), temp) > 0)) {
                this.lista.set(j + 1, this.lista.get(j));
                j--;
            }

            this.lista.set(j + 1, temp);
        }
    }

    private int comparar(Reserva r1, Reserva r2) {
        int resultadoNome = r1.getNome().compareToIgnoreCase(r2.getNome());

        if (resultadoNome != 0) {
            return resultadoNome;
        }
        return r1.getReserva().compareToIgnoreCase(r2.getReserva());
    }
}