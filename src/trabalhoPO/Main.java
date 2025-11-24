package trabalhoPO;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    private static final String[] ARQUIVOS = {
        "Reserva1000alea.txt", "Reserva1000inv.txt", "Reserva1000ord.txt",
        "Reserva5000alea.txt", "Reserva5000inv.txt", "Reserva5000ord.txt",
        "Reserva10000alea.txt", "Reserva10000inv.txt", "Reserva10000ord.txt",
        "Reserva50000alea.txt", "Reserva50000inv.txt", "Reserva50000ord.txt"
    };

    private static final String ARQUIVO_BUSCA = "nome.txt"; 

    public static void main(String[] args) {
        System.out.println("---- INICIANDO TESTES ----");

        executarBateriaOrdenacao("---- Heapsort ----");
        executarBateriaOrdenacao("---- Quicksort ----");
        executarBateriaOrdenacao("---- Quicksort Hibrido ----");
        executarBateriaPesquisa("---- ABB ----");
        executarBateriaPesquisa("---- AVL ----");
        executarBateriaPesquisa("---- Hash ----");

        System.out.println("\n---- CONCLUÍDO COM SUCESSO ----");
    }

    private static void executarBateriaOrdenacao(String metodo) {
        System.out.println("\n---- Iniciando Método: " + metodo.toUpperCase());

        for (String arquivo : ARQUIVOS) {
            long somaTempo = 0;

            for (int i = 0; i < 5; i++) {
                long inicio = System.nanoTime();

                ArrayList<Reserva> lista = GerenciadorArquivos.lerArquivo(arquivo);
                
                Reserva[] vetor = lista.toArray(new Reserva[0]);

                switch (metodo) {
                    case "Heapsort":
                        Heapsort heap = new Heapsort(vetor);
                        heap.heapSort();
                        vetor = heap.getVetorOrdenado();
                        break;
                    case "Quicksort":
                        Quicksort quick = new Quicksort(vetor);
                        quick.quicksort();
                        vetor = quick.getVetorOrdenado();
                        break;
                    case "QuicksortHibrido":
                        QuickSortHibrido quickH = new QuickSortHibrido(vetor);
                        quickH.quicksort();
                        vetor = quickH.getVetorOrdenado();
                        break;
                }
                
                String arquivoSaida = metodo.toLowerCase() + arquivo;
                GerenciadorArquivos.gravarArquivoOrdenado(arquivoSaida, vetor);

                long fim = System.nanoTime();
                somaTempo += (fim - inicio);
                
                vetor = null;
                lista = null;
                System.gc();
            }
            System.out.println("Arquivo: " + arquivo + " | Tempo Médio: " + (somaTempo / 5) + " ns");
        }
    }
    
    private static void executarBateriaPesquisa(String metodo) {
        System.out.println("\n---- Iniciando Método de Pesquisa: " + metodo.toUpperCase());

        ArrayList<String> nomesPesquisa = GerenciadorArquivos.lerNomesPesquisa(ARQUIVO_BUSCA);

        for (String arquivo : ARQUIVOS) {
            long somaTempo = 0;

            for (int i = 0; i < 5; i++) {
                long inicio = System.nanoTime();

                ArrayList<Reserva> lista = GerenciadorArquivos.lerArquivo(arquivo);
                StringBuilder resultadoPesquisa = new StringBuilder();

                if (metodo.equals("Hash")) {
                    TabelaHash hash = new TabelaHash((int)(lista.size() * 1.3));
                    
                    for (Reserva r : lista) hash.inserir(r);
                    
                    for (String nome : nomesPesquisa) {
                        ArrayList<Reserva> achados = hash.pesquisar(nome);
                        resultadoPesquisa.append(formatarSaidaPesquisa(nome, achados));
                    }

                } else if (metodo.equals("ABB")) {
                    ArvoreABB abb = new ArvoreABB();
                    Collections.shuffle(lista); 
                    
                    for (Reserva r : lista) abb.inserir(r);
                    
                    for (String nome : nomesPesquisa) {
                        ArrayList<Reserva> achados = abb.pesquisar(nome);
                        resultadoPesquisa.append(formatarSaidaPesquisa(nome, achados));
                    }

                } else if (metodo.equals("AVL")) {
                    ArvoreAVL avl = new ArvoreAVL();
                    
                    for (Reserva r : lista) avl.inserir(r);
                    
                    for (String nome : nomesPesquisa) {
                        ArrayList<Reserva> achados = avl.pesquisar(nome); 
                        resultadoPesquisa.append(formatarSaidaPesquisa(nome, achados));
                    }
                }

                String arquivoSaida = metodo.toLowerCase() + arquivo;
                GerenciadorArquivos.gravarResultadoPesquisa(arquivoSaida, resultadoPesquisa.toString());

                long fim = System.nanoTime();
                somaTempo += (fim - inicio);
                
                lista = null;
                System.gc();
            }
            
            System.out.println("Arquivo: " + arquivo + " | Tempo Médio: " + (somaTempo / 5) + " ns");
        }
    }

    private static String formatarSaidaPesquisa(String nome, ArrayList<Reserva> encontrados) {
        StringBuilder sb = new StringBuilder();
        sb.append("NOME ").append(nome).append(":\n");
        
        if (encontrados == null || encontrados.isEmpty()) {
            sb.append("NÃO TEM RESERVA\n");
        } else {
            for (Reserva r : encontrados) {
                sb.append("Reserva: ").append(r.getReserva()).append("\n");
                sb.append("Voo: ").append(r.getVoo()).append("\n");
                sb.append("Data: ").append(r.getData()).append("\n");
                sb.append("Assento: ").append(r.getAssento()).append("\n\n");
            }
            sb.append("TOTAL: ").append(encontrados.size()).append(" reservas\n");
        }
        sb.append("--------------------------------\n");
        return sb.toString();
    }
}
