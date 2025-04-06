package org.example;

import java.io.BufferedReader;
import java.io.FileReader;

public class LeitorArquivo {
    ListaCircular lista = new ListaCircular();
    String path = "rodizio_de_veiculos.txt";

    public void carregarVeiculos(String tipoFiltro, String filtro) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 3) {
                    String placa = partes[0].trim();
                    String diaSemana = partes[1].trim();
                    String horario = partes[2].trim();

                    if (tipoFiltro.equals("Dia") && diaSemana.equalsIgnoreCase(filtro)) {
                        lista.inserir(placa, diaSemana, horario);
                    } else if (tipoFiltro.equals("Placa") && placa.endsWith(filtro)) {
                        lista.inserir(placa, diaSemana, horario);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}