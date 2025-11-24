package trabalhoPO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GerenciadorArquivos {

    public static ArrayList<Reserva> lerArquivo(String nomeArquivo) {
        ArrayList<Reserva> lista = new ArrayList<>();
        File arquivo = new File(nomeArquivo);

        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado: " + nomeArquivo);
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 5) {
                    String reserva = partes[0];
                    String nome = partes[1];
                    String voo = partes[2]; 
                    String data = partes[3];
                    String assento = partes[4];
                    lista.add(new Reserva(reserva, nome, voo, data, assento));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return lista;
    }

    public static void gravarArquivoOrdenado(String nomeArquivo, Reserva[] vetor) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Reserva r : vetor) {
                bw.write(r.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao gravar arquivo ordenado: " + e.getMessage());
        }
    }

    public static ArrayList<String> lerNomesPesquisa(String nomeArquivo) {
        ArrayList<String> nomes = new ArrayList<>();
        File arquivo = new File(nomeArquivo);

        if (!arquivo.exists()) {
            System.out.println("Arquivo de pesquisa não encontrado: " + nomeArquivo);
            return nomes;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    nomes.add(linha.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de nomes: " + e.getMessage());
        }
        return nomes;
    }

    public static void gravarResultadoPesquisa(String nomeArquivo, String conteudo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            bw.write(conteudo);
        } catch (IOException e) {
            System.out.println("Erro ao gravar resultado da pesquisa: " + e.getMessage());
        }
    }
}
