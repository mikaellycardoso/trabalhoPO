package trabalhoPO;

public class VetorItem {

    private Reserva[] vetor;
    private int quant;
    private static final int TAMANHO_MAX = 50000;

    public VetorItem() {
        this.vetor = new Reserva[TAMANHO_MAX];
        this.quant = 0;
    }

    public VetorItem(int tamanho) {
        this.vetor = new Reserva[tamanho];
        this.quant = 0;
    }

    public int getQuant() {
        return quant;
    }

    public void inserir(Reserva item) {
        if (quant < vetor.length) {
            this.vetor[quant] = item;
            this.quant++;
        }
    }

    public Reserva get(int indice) {
        return this.vetor[indice];
    }

    public Reserva[] getVetor() {
        Reserva[] vetorReal = new Reserva[quant];
        for (int i = 0; i < quant; i++) {
            vetorReal[i] = vetor[i];
        }
        return vetorReal;
    }
}
