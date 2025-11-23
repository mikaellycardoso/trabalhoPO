package trabalhoPO;

public class Quicksort {

    public static void ordenar(Reserva[] vetor) {
        ordenarRecursivo(vetor, 0, vetor.length - 1);
    }

    private static void ordenarRecursivo(Reserva[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int p = particionar(vetor, inicio, fim);

            ordenarRecursivo(vetor, inicio, p - 1);
            ordenarRecursivo(vetor, p + 1, fim);
        }
    }
    private static int particionar(Reserva[] vetor, int inicio, int fim) {
        Reserva pivo = vetor[fim];
        
        int i = (inicio - 1); 

        for (int j = inicio; j < fim; j++) {
            if (vetor[j].compareTo(pivo) <= 0) {
                i++;
                trocar(vetor, i, j);
            }
        }
        trocar(vetor, i + 1, fim);

        return i + 1;
    }
    private static void trocar(Reserva[] vetor, int i, int j) {
        Reserva temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }
}