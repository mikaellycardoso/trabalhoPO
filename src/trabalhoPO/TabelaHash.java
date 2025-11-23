package trabalhoPO;

import java.util.ArrayList;
import java.util.LinkedList;

public class TabelaHash {
    
    private LinkedList<Reserva>[] tabela;
    private int M; 

    @SuppressWarnings("unchecked")
    public TabelaHash(int tamanho) {
        this.M = tamanho;
        this.tabela = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    public int Hashing(String chave) {
        char carac;
        int i, soma = 0;
        
        for (i = 0; i < chave.length(); i++) {
            carac = chave.charAt(i);
            soma += Character.getNumericValue(carac);
        }
        return Math.abs(soma % M); 
    }

    public void inserir(Reserva r) {
        int indice = Hashing(r.getNome());
        tabela[indice].add(r);
    }

    public ArrayList<Reserva> pesquisar(String nome) {
        int indice = Hashing(nome);
        LinkedList<Reserva> lista = tabela[indice];
        
        ArrayList<Reserva> encontrados = new ArrayList<>();
        
        for (Reserva r : lista) {
            if (r.getNome().equalsIgnoreCase(nome)) {
                encontrados.add(r);
            }
        }
        
        return encontrados.isEmpty() ? null : encontrados;
    }
}