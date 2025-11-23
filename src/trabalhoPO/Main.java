package trabalhoPO;

public class Main {

    public static void main(String[] args) {

    	String[] arquivosParaOrdenar = {
                "Reserva1000alea.txt", 
                "Reserva1000inv.txt", 
                "Reserva1000ord.txt",
                "Reserva5000alea.txt", 
                "Reserva5000inv.txt", 
                "Reserva5000ord.txt",
                "Reserva10000alea.txt", 
                "Reserva10000inv.txt", 
                "Reserva10000ord.txt",
                "Reserva50000alea.txt", 
                "Reserva50000inv.txt", 
                "Reserva50000ord.txt"
        };
    	String arquivoPesquisa = "nome.txt";
    	
        // --- PARTE 1: ORDENAÇÃO ---
        System.out.println("=== INICIANDO BATERIA DE TESTES: HEAPSORT ===");
        for (String arquivo : arquivosParaOrdenar) {
            executarTesteOrdenacao("Heapsort", arquivo);
        }

        System.out.println("\n=== INICIANDO BATERIA DE TESTES: QUICKSORT ===");
        for (String arquivo : arquivosParaOrdenar) {
            executarTesteOrdenacao("Quicksort", arquivo);
        }
        
        // ... Repetir para Quicksort Híbrido ...


        // --- PARTE 2: PESQUISA ---
        // Você precisa carregar o arquivo de chaves de pesquisa também
        String arquivoChaves = "buscaP.txt"; // Nome hipotético do arquivo com 400 chaves

        System.out.println("\n=== INICIANDO BATERIA DE TESTES: ABB ===");
        for (String arquivo : arquivosParaOrdenar) {
            executarTestePesquisa("ABB", arquivo, arquivoChaves);
        }

        // ... Repetir para AVL e Hashing ...
        
        System.out.println("\nTODOS OS TESTES CONCLUÍDOS.");
    }


    // --- MÉTODO AUXILIAR PARA NÃO REPETIR CÓDIGO DE TEMPO ---
    public static void executarTesteOrdenacao(String metodo, String nomeArquivo) {
        long tempoTotal = 0;

        // O trabalho exige rodar 5 vezes e tirar a média
        for (int i = 0; i < 5; i++) {
            
            // 1. Carregar arquivo (Sempre recarregar para não ordenar vetor já ordenado!)
            Reserva[] vetor = GerenciadorArquivos.lerArquivo(nomeArquivo);
            
            long inicio = System.nanoTime();
            
            // 2. Chamar o método da classe específica
            switch (metodo) {
                case "Heapsort":
                    Heapsort.ordenar(vetor);
                    break;
                case "Quicksort":
                    Quicksort.ordenar(vetor);
                    break;
                case "QuicksortHibrido":
                    QuicksortHibrido.ordenar(vetor);
                    break;
            }
            
            long fim = System.nanoTime();
            tempoTotal += (fim - inicio);
            
            // 3. Gravar o arquivo ordenado (apenas na última vez para não gastar I/O à toa, ou em todas se preferir)
            if (i == 0) { // Grava só uma vez para provar que funcionou
                String nomeSaida = metodo.toLowerCase() + nomeArquivo; // Ex: heapsortreserva1000alea.txt
                GerenciadorArquivos.gravarArquivoOrdenado(nomeSaida, vetor);
            }
        }

        long media = tempoTotal / 5;
        System.out.println("Arquivo: " + nomeArquivo + " | Média: " + media + " ns");
    }
    
    // Você criaria um método similar 'executarTestePesquisa' para ABB, AVL e Hash
}
