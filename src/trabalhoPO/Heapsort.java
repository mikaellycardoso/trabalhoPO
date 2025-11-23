package trabalhoPO;

public class Heapsort {

    public static void ordenar(Reserva[] vetor) {
        int n = vetor.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            aplicarHeap(vetor, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            trocar(vetor, 0, i);
            aplicarHeap(vetor, i, 0);
        }
    }

    private static void aplicarHeap(Reserva[] vetor, int n, int i) {
        int maior = i; 
        int esquerda = 2 * i + 1; 
        int direita = 2 * i + 2; 

        if (esquerda < n && vetor[esquerda].compareTo(vetor[maior]) > 0) {
            maior = esquerda;
        }

        if (direita < n && vetor[direita].compareTo(vetor[maior]) > 0) {
            maior = direita;
        }

        if (maior != i) {
            trocar(vetor, i, maior);

            aplicarHeap(vetor, n, maior);
        }
    }
    private static void trocar(Reserva[] vetor, int i, int j) {
        Reserva temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }
}