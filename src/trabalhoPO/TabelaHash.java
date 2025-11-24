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

    private int hashing(String chave) {
        int soma = 0;
        for (int i = 0; i < chave.length(); i++) {
            soma += (int) chave.charAt(i); 
        }
        return Math.abs(soma % M); 
    }

    public void inserir(Reserva r) {
        int indice = hashing(r.getNome());
        tabela[indice].add(r);
    }

    public ArrayList<Reserva> pesquisar(String nome) {
        int indice = hashing(nome);
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
