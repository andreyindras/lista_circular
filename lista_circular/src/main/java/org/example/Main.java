package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Selecione o critério para filtrar os veículos:");
        System.out.println("(1) - Por dia da semana");
        System.out.println("(2) - Por final de placa");
        System.out.print("Opção: ");
        int opcaoFiltro = sc.nextInt();
        sc.nextLine();

        String filtro = "";
        String tipoFiltro = "";

        if (opcaoFiltro == 1) {
            tipoFiltro = "Dia";
            System.out.println("\nSelecione o dia da semana para buscar:");
            System.out.println("(1) - Segunda-feira");
            System.out.println("(2) - Terça-feira");
            System.out.println("(3) - Quarta-feira");
            System.out.println("(4) - Quinta-feira");
            System.out.println("(5) - Sexta-feira");
            System.out.print("Opção: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    filtro = "Segunda-feira";
                    break;
                case 2:
                    filtro = "Terça-feira";
                    break;
                case 3:
                    filtro = "Quarta-feira";
                    break;
                case 4:
                    filtro = "Quinta-feira";
                    break;
                case 5:
                    filtro = "Sexta-feira";
                    break;
                default:
                    System.out.println("Opção inválida!");
                    return;
            }
        } else if (opcaoFiltro == 2) {
            tipoFiltro = "Placa";
            System.out.print("\nDigite o final da placa para buscar (1 dígito): ");
            filtro = sc.nextLine();
            if (filtro.length() != 1 || !Character.isDigit(filtro.charAt(0))) {
                System.out.println("Final de placa inválido!");
                return;
            }
        } else {
            System.out.println("Opção inválida!");
            return;
        }

        LeitorArquivo leitor = new LeitorArquivo();
        leitor.carregarVeiculos(tipoFiltro, filtro);

        System.out.println("\nVeículos no rodízio (" + tipoFiltro + ": " + filtro + "):\n");
        leitor.lista.exibirLista();

        leitor.lista.navegarLista();

        leitor.lista.liberarLista();
        sc.close();
    }
}