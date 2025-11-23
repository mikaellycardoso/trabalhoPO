package trabalhoPO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GerenciadorArquivos {

    public static ArrayList<Reserva> lerArquivo(String caminhoArquivo) {
        ArrayList<Reserva> listaReservas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                
                if (dados.length == 5) {
                    String codigo = dados[0].trim();
                    String nome = dados[1].trim();
                    String voo = dados[2].trim();
                    String data = dados[3].trim();
                    String assento = dados[4].trim();

                    Reserva reserva = new Reserva(codigo, nome, voo, data, assento);
                    listaReservas.add(reserva);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return listaReservas;
    }
    public static void gravarArquivoOrdenado(String nomeArquivoSaida, ArrayList<Reserva> listaReservas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivoSaida))) {
            for (Reserva r : listaReservas) {
                bw.write(r.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao gravar arquivo ordenado: " + e.getMessage());
        }
    }
    
    public static void gravarResultadoPesquisa(String nomeArquivoSaida, String resultadoPesquisa) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivoSaida, true))) {
            bw.write(resultadoPesquisa);
        } catch (IOException e) {
            System.out.println("Erro ao gravar resultado da pesquisa: " + e.getMessage());
        }
    }
}
